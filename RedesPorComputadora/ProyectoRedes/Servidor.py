import socket
import threading

HEADER = 64 #Para definir el tamaño max de los mensajes que esperamos recibir
PORT = 5050
#SERVER = "192.168.100.43" #IP local obtenida con ifconfig
SERVER = socket.gethostbyname(socket.gethostname()) #Obtiene la ip de forma automática
ADDR = (SERVER, PORT)
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = '\\bye'

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #creacion del socket
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1) #le dice al kernel que reutilice el socket, siempre que no esté activamente escuchando
server.bind(ADDR) #binding del socket con el server

clientes = {} #Lista que tendra a todos los clientes

#Se maneja la conexion cliente - servidor de forma individual e independiente a las demas
def handle_client(conn, addr):
    print(f"[NUEVA CONEXION] IP y puerto: {addr} conectados.")
    #alias = conn.recv(HEADER).decode(FORMAT)
    alias_length = int(conn.recv(HEADER).decode(FORMAT))
    alias = conn.recv(alias_length).decode(FORMAT)
    clientes[alias] = (conn, addr)
    print(f"Usuario {alias} conectado desde {addr}")

    connected = True
    cantMsg = 0
    # Bucle principal para recibir mensajes
    while connected:
        if cantMsg == 0:
            conn.send("Con que alias quieres hablar?".encode(FORMAT))
        else:
            conn.send(" ".encode(FORMAT)) # Para que pueda mandar varios mensajes sin que pregunte cada vez por el alias
        # Primero recibe la longitud del mensaje
        msg_length = conn.recv(HEADER).decode(FORMAT)
        if msg_length:
            msg_length = int(msg_length)
            # Luego recibe el mensaje de esa longitud
            msg = conn.recv(msg_length).decode(FORMAT)
            cantMsg += 1
            # Comprueba si es el mensaje de desconexión
            if msg == DISCONNECT_MESSAGE:
                connected = False
            else:
                print(f"[{alias}]: {msg}")


    conn.close() #Cierra la conexión

def start():
    server.listen()
    print(f"[LISTENING] el servidor está escuchando en el puerto {SERVER}")
    while True:
        conn, addr = server.accept() #se guarda la informacion de una nueva conexion
        thread = threading.Thread(target=handle_client, args=(conn,addr))#Comienza un nuevo thread,
        thread.start()
        print(f"[CONEXIONES ACTIVAS] {threading.activeCount() - 1 }")

print("[STARTING] el server está empezando...")
start()
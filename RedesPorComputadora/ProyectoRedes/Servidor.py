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
conversacionesActivas = {} # Estructura {{idConv, [alias1,alias2]}}
idConversacion = 0

#Se maneja la conexion cliente - servidor de forma individual e independiente a las demas
def handle_client(conn, addr):
    print(f"[NUEVA CONEXION] IP y puerto: {addr} conectados.")
    alias_length = int(conn.recv(HEADER).decode(FORMAT))
    alias = conn.recv(alias_length).decode(FORMAT)
    clientes[alias] = (conn, addr)
    print(f"Usuario {alias} conectado desde {addr}")

    connected = True
    # Bucle principal para recibir mensajes
    
    while connected:
        conn.send("Con que alias quieres hablar?\n".encode(FORMAT))
        alias_destino_length = int(conn.recv(HEADER).decode(FORMAT))
        alias_destino = conn.recv(alias_destino_length).decode(FORMAT)

        if alias_destino in clientes:
            conn_destino, _ = clientes[alias_destino] # Obtiene la conexión del destinatario
            print(f"{alias} quiere hablar con {alias_destino}")
            conn_destino.send(f"{alias} quiere hablar contigo. Aceptas? (s/n)\n".encode(FORMAT))

            respuesta = conn_destino.recv(HEADER).decode(FORMAT).strip().lower()   # Recibir respuesta directamente sin leer la longitud primero

            if respuesta.lower() == 's':
                conn.send(f"{alias_destino} aceptó tu solicitud de chat.\n".encode(FORMAT))
                conn.send(f"[CONECTANDO] con {alias_destino}.\n".encode(FORMAT))
                conn_destino.send(f"[CONECTANDO] con {alias}.\n".encode(FORMAT))
                idConversacion += 1
                conversacionesActivas[idConversacion] = [alias, alias_destino]
            else:
                conn.send(f"{alias_destino} rechazó tu solicitud de chat.\n".encode(FORMAT))

        else: #No esta el alias destinatario conectado
            conn.send("[ERROR] Usuario no encontrado.\n".encode(FORMAT))

        # Primero recibe la longitud del mensaje
        msg_length = conn.recv(HEADER).decode(FORMAT)
        if msg_length:
            msg_length = int(msg_length)
            msg = conn.recv(msg_length).decode(FORMAT) # Luego recibe el mensaje de esa longitud
            if msg == DISCONNECT_MESSAGE: # Comprueba si es el mensaje de desconexión
                connected = False

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
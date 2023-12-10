import socket
import threading 

HEADER = 64 #Para definir el tamaño max de los mensajes que esperamos recibir
PORT = 5050
#SERVER = "192.168.100.43" #IP local obtenida con ifconfig
SERVER = socket.gethostbyname(socket.gethostname()) #Obtiene la ip de forma automática 

ADDR = (SERVER, PORT)
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = "\bye"

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #creacion del socket

server.bind(ADDR) #binding del socket con el server

#Se maneja la conexion cliente - servidor de forma individual e independiente a las demas
def handle_client(conn, addr):
    print(f"[NEW CONNECTION] {addr} connected.")

    connected = True
    while connected: 
        msg_length = conn.recv(HEADER).decode(FORMAT) #Dice que tan largo es el mensaje que está entrando
        if msg_length:
            msg_length = int(msg_length)
            msg = conn.recv(msg_length).decode(FORMAT)

            if msg == DISCONNECT_MESSAGE:
                connected = False 

            print(f"[{addr}] {msg}")

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
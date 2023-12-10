import socket
import threading 

HEADER = 1024 #Para definir el tamaño max de los mensajes que esperamos recibir
PORT = 5050
#SERVER = "192.168.1.144" #IP local obtenida con ifconfig
SERVER = socket.gethostbyname(socket.gethostname()) #Obtiene la ip de forma automática 
FORMAT = 'utf-8'      #Formato utilizado para encriptar
DISCONNECT_MESSAGE = "\bye" #mensaje de salida

ADDR = (SERVER, PORT) 

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM) #creacion del socket

server.bind(ADDR) #binding del socket con el server

clientes = {} #Lista que tendra a todos los clientes



#Se maneja la conexion cliente - servidor de forma individual e independiente a las demas
def handle_client(conn, addr):
    
    alias = conn.recv(HEADER).decode(FORMAT)
    clientes[alias] = (conn, addr)
    print(f"Usuario {alias} conectado desde {addr}")
    
    while True:
        try:
            alias_destino = conn.recv(HEADER).decode('utf-8')
            if alias_destino in clientes:
               # Obtiene la conexión del destinatario
               socket_destino, _ = clientes[alias_destino]
               conn.send(str(clientes[alias_destino][1]).encode('utf-8'))
               socket_destino.send(str(addr).encode('utf-8'))

               # Comienza la conversación
               while True:
                   message = conn.recv(1024).decode('utf-8')
                   if message == DISCONNECT_MESSAGE:
                       break
                   print(f"{alias} dice: {message}")

                   # Envía el mensaje al destinatario
                   socket_destino.send(f"{alias} dice: {message}".encode('utf-8'))

               print(f"Conversación entre {alias} y {alias_destino} finalizada.")
            else:
               conn.send("**Usuario no encontrado.**".encode('utf-8'))
        except Exception as e:
            print(f"Error: {e}")
            break
    
    print(f"Usuario {alias} desconectado.")
    del clientes[alias]
        
    conn.close() #Cierra la conexión
    
    
    
    
    

def start():
    server.listen()
    print(f"[LISTENING] el servidor está escuchando en el puerto {SERVER}")
    while True:
        conn, addr = server.accept() #se guarda la informacion de una nueva conexion
        thread = threading.Thread(target=handle_client, args=(conn,addr))#Comienza un nuevo thread, 
        thread.start()

print("[STARTING] el server está empezando...")
start()
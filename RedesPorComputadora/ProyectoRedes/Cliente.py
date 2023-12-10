import socket

HEADER = 64 #Para definir el tamaño max de los mensajes que esperamos recibir
PORT = 5050
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = '\\bye'
SERVER = socket.gethostbyname(socket.gethostname())
ADDR = (SERVER, PORT) #Guarda la info del cliente

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(ADDR) #Conexion al servidor

def send(msg):
    message = msg.encode(FORMAT) #Codifica la string a una secuencia de bits
    msg_length = len(message)
    send_length = str(msg_length).encode(FORMAT)
    send_length += b' ' * (HEADER - len(send_length))  #Se le agrega padding al mensaje para que sea del tamaño requerido
    client.send(send_length)
    client.send(message)
    print(client.recv(2048).decode(FORMAT))

aliasPropio = str(input("Ingrese su alias: "))
send(aliasPropio)

message = input()
send(message)
while message != DISCONNECT_MESSAGE:
    send(message)
    message = input()

if message == DISCONNECT_MESSAGE:
    send(DISCONNECT_MESSAGE)
    print("[DESCONECTANDOSE]")

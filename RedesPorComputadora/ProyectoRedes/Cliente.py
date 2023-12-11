import socket
import threading

HEADER = 64  # Para definir el tamaño máximo de los mensajes que esperamos recibir
PORT = 5050
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = '\\bye'
SERVER = socket.gethostbyname(socket.gethostname())
ADDR = (SERVER, PORT)  # Guarda la info del cliente

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(ADDR)  # Conexión al servidor

def send(msg):
    message = msg.encode(FORMAT)  # Codifica la string a una secuencia de bits
    client.send(message)  # Envía el mensaje directamente

def listen():
    while True:
        try:
            msg = client.recv(HEADER).decode(FORMAT)
            if msg:
                print(msg)
        except Exception as e:
            print(f"Error al recibir datos: {e}")
            break

# Hilo para escuchar mensajes del servidor
listen_thread = threading.Thread(target=listen)
listen_thread.start()

aliasPropio = input("Ingrese su alias: ")
send(aliasPropio)

while True:
    message = input()
    if message == DISCONNECT_MESSAGE:
        send(DISCONNECT_MESSAGE)
        break
    send(message)

print("[DESCONECTANDOSE]")
listen_thread.join()  # Espera a que el hilo de escucha termine

import socket

HEADER = 1024
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = "\\bye"

alias = str(input("Ingrese su alias: "))
SERVER = input("Ingrese la dirección IP del servidor: ")
PORT = int(input("Ingrese el puerto del servidor: "))

ADDR = (SERVER, PORT)

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(ADDR)

client.send(alias.encode(FORMAT))

# Recibe un mensaje del servidor
mensaje_servidor = client.recv(HEADER).decode(FORMAT)
print(f"Mensaje del servidor: {mensaje_servidor}")

while True:
    alias_destino = str(input("Ingrese el alias del destinatario: "))
    client.send(alias_destino.encode(FORMAT))

    direccion_destino = eval(client.recv(HEADER).decode(FORMAT))
    print(f"Conectando con {alias_destino} en {direccion_destino}")

    socket_destino = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    socket_destino.connect(direccion_destino)

    # Comienza la conversación
    while True:
        message = input("Usted dice: ")
        socket_destino.send(message.encode(FORMAT))
        if message == DISCONNECT_MESSAGE:
            break

        nuevo_mensaje = socket_destino.recv(HEADER).decode(FORMAT)
        print(f"{alias_destino} dice: {nuevo_mensaje}")

    socket_destino.close()
    print("Conversación finalizada.")
    break

client.close()

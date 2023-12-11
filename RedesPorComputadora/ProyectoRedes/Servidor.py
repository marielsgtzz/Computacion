import socket
import threading
import select


HEADER = 64 # Para definir el tamaño max de los mensajes que esperamos recibir
PORT = 5050
# SERVER = "192.168.100.43" # IP local obtenida con ifconfig
SERVER = socket.gethostbyname(socket.gethostname()) # Obtiene la ip de forma automática
ADDR = (SERVER, PORT)
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = '\\bye'

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # Creación del socket
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1) # Le dice al kernel que reutilice el socket, siempre que no esté activamente escuchando
server.bind(ADDR) # Binding del socket con el server

clientes = {} # Lista que tendrá a todos los clientes
conversacionesActivas = {} # Estructura {{idConv, [alias1,alias2]}}
idConversacion = 0


# Función para imprimir el diccionario en formato de tabla
def imprimir_conversaciones():
    print("----------------------------------------------")
    print("| IdConve | AliasCliente1 | AliasCliente2 |")
    print("----------------------------------------------")
    for idConv, (alias1, alias2) in conversacionesActivas.items():
        print(f"| {idConv:<7} | {alias1:<12} | {alias2:<12} |")
    print("----------------------------------------------")

def reenviar_mensaje(alias_remitente,alias_destinatario,msj):
    print(f"Intentando reenviar mensaje de {alias_remitente} a {alias_destinatario}")
    if alias_destinatario in clientes:
        try:
            cliente_destino, _ = clientes[alias_destinatario]
            cliente_destino.send(f"{alias_remitente}: {msj}".encode(FORMAT))
            print(f"Mensaje enviado de {alias_remitente} a {alias_destinatario}: {msj}")
        except Exception as e:
            print(f"Error al reenviar mensaje: {e}")
    else:
        print(f"ERROR: {alias_destinatario} no encontrado al reenviar mensaje.")

def manejar_mensaje(conn, alias):
    try:
        mensaje = conn.recv(HEADER).decode(FORMAT)
        if not mensaje:
            print(f"Error: Mensaje vacío recibido de {alias}")
            return None
        return mensaje
    except Exception as e:
        print(f"Error al recibir mensaje de {alias}: {e}")
        return None

def manejar_conversacion(cliente1, cliente2, alias1, alias2):
    inputs = [cliente1, cliente2]
    outputs = []

    while True:
        readable, writable, exceptional = select.select(inputs, outputs, inputs)

        for s in readable:
            if s is cliente1:
                mensaje = manejar_mensaje(cliente1, alias1)
                if mensaje == DISCONNECT_MESSAGE or mensaje is None:
                    return alias1
                reenviar_mensaje(alias1, alias2, mensaje)
            elif s is cliente2:
                mensaje = manejar_mensaje(cliente2, alias2)
                if mensaje == DISCONNECT_MESSAGE or mensaje is None:
                    return alias2
                reenviar_mensaje(alias2, alias1, mensaje)

        for s in exceptional:
            if s is cliente1:
                return alias1
            elif s is cliente2:
                return alias2


# Se maneja la conexión cliente - servidor de forma individual e independiente a las demás
def handle_client(conn, addr):
    global idConversacion

    print(f"[NUEVA CONEXION] IP y puerto: {addr} conectados.")
    alias = conn.recv(HEADER).decode(FORMAT).strip()
    if not alias:
        print(f"Error: Alias vacío recibido de {addr}")
        conn.close()
        return

    clientes[alias] = (conn, addr)
    print(f"Usuario {alias} conectado desde {addr}")

    while True:
        conn.send("Con que alias quieres hablar?".encode(FORMAT))
        alias_destino = conn.recv(HEADER).decode(FORMAT).strip()

        if alias_destino == DISCONNECT_MESSAGE:
            break
        elif alias_destino in clientes:
            cliente_destino, _ = clientes[alias_destino]
            cliente_destino.send(f"{alias} quiere hablar contigo. Aceptas? (s/n)".encode(FORMAT))
            respuesta = conn.recv(HEADER).decode(FORMAT).strip().lower()

            if respuesta == 's':
                idConversacion += 1
                conversacionesActivas[idConversacion] = (alias, alias_destino)
                imprimir_conversaciones()
                alias_desconectado = manejar_conversacion(conn, cliente_destino, alias, alias_destino)

                print(f"[DESCONEXION] {alias_desconectado} se ha desconectado.")
                del clientes[alias_desconectado]
                del conversacionesActivas[idConversacion]
                imprimir_conversaciones()
                break
            else:
                print(f"{alias} rechazó la conversación con {alias_destino}.")
        else:
            conn.send("[ERROR] Usuario no encontrado.".encode(FORMAT))

    conn.close()
    print(f"[DESCONEXION] {alias} se ha desconectado.")


def start():
    server.listen()
    print(f"[LISTENING] el servidor está escuchando en el puerto {SERVER}")
    while True:
        conn, addr = server.accept()
        thread = threading.Thread(target=handle_client, args=(conn, addr))
        thread.start()
        print(f"[CONEXIONES ACTIVAS] {threading.activeCount() - 1}")

print("[STARTING] el server está empezando...")
start()

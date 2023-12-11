import socket
import threading

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


# Se maneja la conexión cliente - servidor de forma individual e independiente a las demás
def handle_client(conn, addr):
    global idConversacion

    print(f"[NUEVA CONEXION] IP y puerto: {addr} conectados.")
    try:
        alias = conn.recv(HEADER).decode(FORMAT).strip()
    except ValueError:
        print(f"Error al recibir el alias del cliente {addr}")
        conn.close()
        return

    clientes[alias] = conn
    print(f"Usuario {alias} conectado desde {addr}")

    esperando_respuesta = False  # Indica si estamos esperando una respuesta de chat
    alias_solicitante = None  # Almacena el alias del cliente que hace la solicitud

    connected = True
        
    while connected:
        try:
            # Solo envía la pregunta si no estamos esperando una respuesta
            if not esperando_respuesta:
                conn.send("Con que alias quieres hablar?<END>".encode(FORMAT))
                alias_destino = conn.recv(HEADER).decode(FORMAT).strip()

            # Si estamos esperando una respuesta, no preguntamos por un alias nuevamente
            if esperando_respuesta:
                respuesta = conn.recv(HEADER).decode(FORMAT).strip().lower()
                if respuesta == 's':
                    conn.send(f"[CONECTANDO] con {alias_solicitante}.\n".encode(FORMAT)) # Mensaje para destinatario
                    clientes[alias_solicitante].send(f"{alias} aceptó tu solicitud de chat.\n".encode(FORMAT)) # Mensaje para solicitante
                    clientes[alias_solicitante].send(f"[CONECTANDO] con {alias}.\n".encode(FORMAT))

                    idConversacion += 1
                    conversacionesActivas[idConversacion] = [alias_solicitante, alias_destino]
                    imprimir_conversaciones()
                else:
                    clientes[alias_solicitante].send(f"{alias} rechazó tu solicitud de chat.".encode(FORMAT))

                esperando_respuesta = False
            elif alias_destino in clientes:
                conn_destino = clientes[alias_destino]
                conn_destino.send(f"{alias} quiere hablar contigo. Aceptas? (s/n)".encode(FORMAT))

                # Configuramos el estado para esperar una respuesta
                alias_solicitante = alias
                esperando_respuesta = True
            else:
                conn.send("[ERROR] Usuario no encontrado.".encode(FORMAT))
        except Exception as e:
            print(f"Error: {e}")
            connected = False

    conn.close()

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

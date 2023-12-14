import socket
import threading
import select
import time

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
clientes_ocupados = {} #Lista que tendra todos los clientes ocupados
idConversacion = 0



# Función para imprimir el diccionario en formato de tabla
def imprimir_conversaciones():
    print("----------------------------------------------")
    print("| IdConve | AliasCliente1 | AliasCliente2 |")
    print("----------------------------------------------")
    for idConv, (alias1, alias2) in conversacionesActivas.items():
        print(f"| {idConv:<7} | {alias1:<12} | {alias2:<12} |")
    print("----------------------------------------------")


# Función para reenviar un mensaje de un cliente a otro
def reenviar_mensaje(alias_remitente, alias_destinatario, msj):
    print(f"Intentando reenviar mensaje de {alias_remitente} a {alias_destinatario}")
    if alias_destinatario in clientes:
        cliente_destino, _ = clientes[alias_destinatario]
        try: #Intenta mandar el mensaje al cliente destino
            cliente_destino.send(f"{alias_remitente}: {msj}".encode(FORMAT))
            print(f"Mensaje enviado de {alias_remitente} a {alias_destinatario}: {msj}")
        except Exception as e: #De no ser posible, marca error
            print(f"Error al reenviar mensaje: {e}")
    else:
        print(f"ERROR: {alias_destinatario} no encontrado al reenviar mensaje.")

def manejar_mensaje(conn, alias): # intenta recibir el mensaje
    try: 
        mensaje = conn.recv(HEADER).decode(FORMAT)
        if not mensaje: #Verifica que el mensaje no este vacio
            print(f"Error: Mensaje vacío recibido de {alias}")
            return None
        return mensaje
    except ConnectionResetError:
        # Si se produce un error de conexion por reseteo maneja la excepcion. 
        # Esto pasa si se cierra abruptamente por el host remoto
        print(f"Error al recibir mensaje de {alias}: La conexión fue reseteada por el host remoto.")
        return None


# Funcion para manejar la conversacoón entre dos clientes
def manejar_conversacion(cliente1, cliente2, alias1, alias2):
    inputs = [cliente1, cliente2] # toma como inputs a dos clientes
    outputs = []
    last_active = {cliente1: time.time(), cliente2: time.time()} # Registro de la última actividad

    try:
        while True:
            readable, writable, exceptional = select.select(inputs, outputs, inputs, 30) # Timeout ajustado a 30 segundos

            if not readable:  # Si no hay actividad en 30 segundos
                for s in inputs:
                    if time.time() - last_active[s] > 30:  # Verifica si se ha superado el tiempo de inactividad
                        try:
                            s.send("Desconexión por inactividad.\n".encode(FORMAT))
                        except Exception as e:
                            print(f"Error al enviar mensaje de desconexión por inactividad: {e}")
                        finally:
                            s.close()
                            return alias1 if s is cliente1 else alias2

            for s in readable:
                if s is cliente1:
                    mensaje = manejar_mensaje(cliente1, alias1)
                    if mensaje == DISCONNECT_MESSAGE or mensaje is None:
                        cliente2.send(f"{alias1} se ha desconectado. Con qué alias quieres hablar?".encode(FORMAT))
                        return alias1
                    reenviar_mensaje(alias1, alias2, mensaje)
                    last_active[cliente1] = time.time()  # Actualizar el tiempo de la última actividad
                elif s is cliente2:
                    mensaje = manejar_mensaje(cliente2, alias2)
                    if mensaje == DISCONNECT_MESSAGE or mensaje is None:
                        cliente1.send(f"{alias2} se ha desconectado. Con qué alias quieres hablar?\n".encode(FORMAT))
                        return alias2
                    reenviar_mensaje(alias2, alias1, mensaje)
                    last_active[cliente2] = time.time()  # Actualizar el tiempo de la última actividad

            for s in exceptional:
                if s is cliente1:
                    try:
                         # Notifica al otro cliente que el cliente1 ha tenido un problema
                        cliente2.send(f"{alias1} ha tenido un problema. Con qué alias quieres hablar?\n".encode(FORMAT))
                    except OSError as e:
                        print(f"Error al enviar mensaje a {alias2}: {e}")
                    return alias1
                elif s is cliente2:
                    try:
                        # Notifica al otro cliente que el cliente2 ha tenido un problema
                        cliente1.send(f"{alias2} ha tenido un problema. Con qué alias quieres hablar?\n".encode(FORMAT))
                    except OSError as e:
                        print(f"Error al enviar mensaje a {alias1}: {e}")
                    return alias2

    except OSError as e: # Captura excepciones relacionadas con operaciones del sistema
        print(f"Error en la conversación entre {alias1} y {alias2}: {e}")
        return None


def handle_client(conn, addr):
    global idConversacion
    # Imprime un mensaje indicando que hay una nueva conexion
    print(f"[NUEVA CONEXION] IP y puerto: {addr} conectados.")
    alias = conn.recv(HEADER).decode(FORMAT).strip()

     # Verifica si el alias esta vacio
    if not alias:
        print(f"Error: Alias vacío recibido de {addr}")
        conn.close()
        return

    clientes[alias] = (conn, addr)
    print(f"Usuario {alias} conectado desde {addr}")

    try:
        while True:
            try:
                # Intenta enviar un mensaje solicitando el alias de destino al cliente
                conn.send("Con que alias quieres hablar?\n".encode(FORMAT))
            except (ConnectionResetError, BrokenPipeError):
                # Imprime un mensaje indicando que el cliente se desconecto durante la solicitud de alias
                print(f"[DESCONEXION] {alias} se ha desconectado durante la solicitud de alias.")
                break

            try:
                alias_destino = conn.recv(HEADER).decode(FORMAT).strip()

                # Verifica si el alias de destino esta vacio
                if not alias_destino:
                    # Imprime un mensaje de error y termina la funcion
                    print(f"Error: Alias de destino vacío recibido from {alias}")
                    break

                if alias_destino == DISCONNECT_MESSAGE:
                    break
                
                # Verifica si el alias de destino esta en el diccionario cliente
                elif alias_destino in clientes:
                    cliente_destino, _ = clientes[alias_destino]
                    
                    # Verifica si el alias destino ya está ocupado en una conversación
                    if alias_destino in clientes_ocupados.values():
                        conn.send(f"Error: {alias_destino} esta en otro chat\n".encode(FORMAT))
                        continue
                        

                    # Envia un mensaje al cliente de destino solicitando su aceptacion para la conversacion
                    cliente_destino.send(f"{alias} quiere hablar contigo. Aceptas? (s/n)".encode(FORMAT))
                    respuesta = conn.recv(HEADER).decode(FORMAT).strip().lower()

                     # Verifica si la respuesta es afirmativa
                    if respuesta == 's':
                        # Incrementa idConversacion para asignar un identificador único a la conversación
                        idConversacion += 1
                        conversacionesActivas[idConversacion] = (alias, alias_destino)
                        imprimir_conversaciones()
                        
                        #Mete los cliente en la lista clientes ocupados
                        clientes_ocupados[alias] = alias
                        clientes_ocupados[alias_destino] = alias_destino

                        # Llama a la funcion manejar_conversacion para gestionar la conversacion
                        alias_desconectado = manejar_conversacion(conn, cliente_destino, alias, alias_destino)
                        
                        #Saca a los clientes una vez que la conversacion esta terminada
                        clientes_ocupados.pop(alias, None)
                        clientes_ocupados.pop(alias_destino, None)

                        # Maneja la desconexion del usuario
                        if alias_desconectado:
                            try:
                                # Elimina la referencia al cliente desconectado del diccionario clientes
                                del clientes[alias_desconectado]
                            except KeyError:
                                # Captura un KeyError si el cliente no existe en el diccionario
                                print(f"KeyError: El cliente {alias_desconectado} no existe en el diccionario.")

                            for idConv, aliases in list(conversacionesActivas.items()):
                                if alias_desconectado in aliases:
                                    del conversacionesActivas[idConv]
                            imprimir_conversaciones()

                            # Notificar al otro cliente si todavía está conectado
                            otro_cliente = alias if alias_desconectado == alias_destino else alias_destino
                            if otro_cliente in clientes:
                                try:
                                    clientes[otro_cliente][0].send(f"Tu compañero de chat {alias_desconectado} se ha desconectado.\n".encode(FORMAT))
                                except (ConnectionResetError, BrokenPipeError):
                                    print(f"Error al enviar mensaje a {otro_cliente}: La conexión fue reseteada por el host remoto.")
                            continue  # Hace que el bucle vuelva al inicio para preguntar de nuevo

                    else:
                        print(f"{alias} rechazó la conversación con {alias_destino}.")
                else:
                    conn.send("[ERROR] Usuario no encontrado.\n".encode(FORMAT))

            except (ConnectionResetError, BrokenPipeError): # Captura excepciones de conexion reseteada o tuberia rota durante la conversacion
                # Imprime un mensaje indicando que el cliente se ha desconectado abruptamente durante la conversacion
                print(f"[DESCONEXION] {alias} se ha desconectado abruptamente durante la conversación.")
                break

    finally:
        conn.close()
        
        # Elimina al cliente desconectado del diccionario clientes
        if alias in clientes:
            del clientes[alias]
        
        # Elimina la conversacion activa del diccionario conversacionesActivas
        for idConv, aliases in list(conversacionesActivas.items()):
            if alias in aliases:
                del conversacionesActivas[idConv]
        imprimir_conversaciones()
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

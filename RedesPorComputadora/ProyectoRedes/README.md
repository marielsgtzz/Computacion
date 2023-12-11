1. Poner el servidor a correr dandole click a `run` en un IDE o en la terminal yendo a la dirección (mediante cd) donde está el archivo de python del Servidor (`Servidor.py`).
2. Conectarse al servidor desde un cliente, correr el archivo de `Cliente.py` con `python Cliente.py` estando en la misma dirección donde está dicho archivo.
3. Al cliente recien conectado se le va a pedir su alias
4. Posteriormente se le va a preguntar al cliente el alias de la persona con la que se quiere entablar la conversación.
5. Conectarse con un cliente nuevo al servidor (desde una nueva ventana de la terminal) (se va a repetir el inicio de la conexion del cliente)
6. Si el alias con el que se está intentando comunicar sí está activamente conectado al servidor se le va a preguntar a dicho cliente (el alias del destinatario) si quiere entablar una conversación con el cliente solicitante
7. Si el destinatario responde la afirmativa ("s") hay un problema en donde aparece el mensaje de error de usuario no encontrado, entonces lo que tiene que hacer el cliente destinatario es escribir el alias del cliente que está intentando entablar comunicación.
8. El mensaje de solicitud de conversación le aparecerá al cliente solicitante y de aceptar podrá comenzar a mandar mensajes al cliente destinatario
9. De igual forma el cliente destinatario podrá comenzar a mandarle mensajes al cliente solicitante.
10. A veces hay un pequeño error en el cliente destinatario donde durante la conversación sale un mensaje de error de ([ERROR] Usuario no encontrado.Con que alias quieres hablar?) lo único que se tiene que hacer es darle enter y se puede mandar nuevamente mensajes al otro cliente.

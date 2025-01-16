import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class MainServer {
    // Lista para mantener todas las conexiones de clientes activas.
    public static List<Connection> clientConnections = Collections.synchronizedList(new ArrayList<>());
    public static Map<String, String[]> diccClientes = new HashMap<>();
    public static int contador;
    public static int[] posMonstruoActual = new int[2];
    public static boolean puntosSumados = false;
    public static String winner = "";
    public static String dirrMulticast = "228.5.6.7";
    public static int puertoMulticast = 49155;

    public static void setPosMonstruoActual(int x, int y){
        posMonstruoActual[0] = x;
        posMonstruoActual[1] = y;
    }

    public static int[] getPosMonstruoActual(){
        int[] res = {posMonstruoActual[0],posMonstruoActual[1]};
        return res;
    }

    public static void setPuertoMulticast(int res){
        puertoMulticast = res;
    }

    public static int getPuertoMulticast(){
        return puertoMulticast;
    }

    public static void setDirrMulticast(String res){
        dirrMulticast = res;
    }

    public static String getDirrMulticast(){
        return dirrMulticast;
    }

    public static void setPuntosSumados(boolean res){
        puntosSumados = res;
    }

    public static boolean getPuntosSumados(){
        return puntosSumados;
    }

    public static void setWinner(String ganador){
        winner = ganador;
    }

    public static String getWinner(){
        return winner;
    }

    public static void resetearPuntajes(){
        imprimirDiccionarioComoTabla(diccClientes);
        for (Map.Entry<String, String[]> entry : diccClientes.entrySet()) {
            entry.getValue()[0] = "0"; // Restablece los puntos a 0
        }
        imprimirDiccionarioComoTabla(diccClientes);
    }

    public static String buscarGanador() {
        for (Map.Entry<String, String[]> entrada : diccClientes.entrySet()) {
            String nombreUsuario = entrada.getKey();
            String[] detalles = entrada.getValue();
            int puntos = Integer.parseInt(detalles[0]);
            
            if (puntos == 5) {
                return nombreUsuario; // Devuelve el primer nombre de usuario encontrado con 5 puntos
            }
        }
        return null; // Si no se encuentra ninguno con 5 puntos, devuelve null
    }

    public static void imprimirDiccionarioComoTabla(Map<String, String[]> dicc) {
        System.out.println();
        // Encabezados de la tabla
        System.out.printf("%-15s %-15s%n", "Usuario", "Puntos y IP");
    
        // Separador de encabezado
        System.out.println("-----------------------------------------------");
    
        // Imprimir cada par de clave/valor en una línea separada
        for (Map.Entry<String, String[]> entry : dicc.entrySet()) {
            // Uniendo la IP y los puntos con una coma
            String details = String.join(", ", entry.getValue());
            System.out.printf("%-15s %-15s%n", entry.getKey(), details);
        }
        System.out.println();
    }
    
    public static String obtenerDireccionIPServidor() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface interfaz : Collections.list(interfaces)) {
                // Filtrar por interfaz no loopback (127.0.0.1) y que esté activa
                if (!interfaz.isLoopback() && interfaz.isUp()) {
                    Enumeration<InetAddress> direcciones = interfaz.getInetAddresses();
                    for (InetAddress addr : Collections.list(direcciones)) {
                        // Retorna la primera dirección IPv4 que no sea de loopback encontrada
                        if (addr instanceof java.net.Inet4Address) {
                            return addr.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la dirección IP: " + e.getMessage());
        }
        return null; // Retornar null o "localhost" si prefieres un valor por defecto
    }

    public static void main(String args[]) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        setPosMonstruoActual(0,0);
        posMonstruoActual = new int[2];

        contador = (int) (Math.random()*1000)+1;

        try {
            //Para conexion TCP (el servidor recibe mensajes via TCP)
            int serverPort = 49152;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Direccion IP del servidor: "+obtenerDireccionIPServidor());
            imprimirDiccionarioComoTabla(diccClientes);
            // Tarea para enviar mensajes periódicamente a todos los clientes.
            Runnable sendCoordinates = () -> {
                Random rand = new Random();

                int x = rand.nextInt(3);
                int y = rand.nextInt(4);
                posMonstruoActual[0] = x;
                posMonstruoActual[1] = y;
                String message = String.format("(%d,%d)",x,y);

                MulticastSocket socket = null;

                try{
                    //Para conexion Multicast (el servidor manda mensajes via Multicast)
                    InetAddress group = InetAddress.getByName(dirrMulticast);
                    socket = new MulticastSocket(puertoMulticast);
                    socket.joinGroup(group);

                    //Convierte las coordenadas a bytes
                    byte[] m = message.getBytes();
                    DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 49155);
                    MainServer.setPuntosSumados(false);
                    socket.send(messageOut);

                    if(!getWinner().equals("")){
                        String mensajeFinPartida = "!!! TENEMOS UN WINNER: "+getWinner();
                        m = mensajeFinPartida.getBytes();
                        messageOut = new DatagramPacket(m, m.length, group, 49155);
                        socket.send(messageOut);
                        System.out.println(mensajeFinPartida);
                        setWinner("");
                        resetearPuntajes();
                    }

                }catch(SocketException e){
                    System.out.println("IO: " + e.getMessage());
                }catch (IOException e) {
                    System.out.println("IO: " + e.getMessage());
                } finally {
                    if (socket != null) socket.close();

                }
            };

            // Programa la tarea para que se ejecute cada dos segundos.
            scheduler.scheduleAtFixedRate(sendCoordinates, 0, 1, TimeUnit.SECONDS);

            while (true) {
                System.out.println("Waiting for messages...");
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
                clientConnections.add(c); // Añadir la nueva conexión a la lista
                c.start();
            }
        } catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        } finally {
            scheduler.shutdown();
        }
    }
}

class Connection extends Thread {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
    private String nombreCliente;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            
            nombreCliente = in.readUTF();
            String ipCliente  = ""+clientSocket.getRemoteSocketAddress();

            synchronized (MainServer.diccClientes){
                // Si el cliente ya existía, actualizas su IP manteniendo su puntaje.
                // Si no, lo añades con puntaje inicial de 0.
                String[] clienteInfo = MainServer.diccClientes.getOrDefault(nombreCliente, new String[] {"0", ipCliente});
                clienteInfo[1] = ipCliente; // Actualiza la IP
                MainServer.diccClientes.put(nombreCliente,clienteInfo);
                System.out.println("Client connected: "+nombreCliente);
                out.writeUTF(nombreCliente);
                MainServer.contador++;
            }
            MainServer.imprimirDiccionarioComoTabla(MainServer.diccClientes);
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public boolean hayGanador(){
        boolean res = false;
        String winner = MainServer.buscarGanador();
        if(winner!=null){
            System.out.println(winner);
            MainServer.setWinner(winner);
        }
        return res;
    }

    public void sumarPuntos(String data){
        int[] coordenadasRes = datosCoordenadas(data);
        int[] coordenadasMonstruoActual = MainServer.getPosMonstruoActual();
        boolean seSumaronPuntos = MainServer.getPuntosSumados();
        if (!seSumaronPuntos && coordenadasMonstruoActual[0]==coordenadasRes[0] && coordenadasMonstruoActual[1]==coordenadasRes[1]){
            System.out.println("RESPUESTA CORRECTA "+nombreCliente);
            String[] valoresActuales = MainServer.diccClientes.get(nombreCliente);
            int puntosNuevos = Integer.valueOf(MainServer.diccClientes.get(nombreCliente)[0]) + 1;
            String[] valoresNuevos = {(""+puntosNuevos), MainServer.diccClientes.get(nombreCliente)[1]};
            
            MainServer.diccClientes.put(nombreCliente, valoresNuevos);

            MainServer.setPuntosSumados(true);
        }
        MainServer.imprimirDiccionarioComoTabla(MainServer.diccClientes);
    }

    public void enviarInformacionMulticast() {
        // Asume que tienes una manera de acceder a la dirección y puerto multicast
        String direccionMulticast = MainServer.getDirrMulticast(); // Ejemplo, deberías tener esto definido en algún lugar

        try {
            String mensaje = "Multicast en: " + direccionMulticast + ":" + MainServer.getPuertoMulticast();
            out.writeUTF(mensaje);
        } catch (IOException e) {
            System.out.println("Error enviando información multicast al cliente: " + e.getMessage());
        }
    }

    public int[] datosCoordenadas(String msj){
        int [] coordenadas = new int[2];
        int x, y;
        x = Character.getNumericValue(msj.charAt(1));
        y = Character.getNumericValue(msj.charAt(3));
        coordenadas[0] = x;
        coordenadas[1] = y;
        return coordenadas;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String data = in.readUTF();
                System.out.println("Message received from: " + nombreCliente + ": " + data);
                sumarPuntos(data);
                hayGanador();
                if (data.equals("Client Disconnected")) {
                    System.out.println("Client " + nombreCliente + " disconnected.");
                    MainServer.clientConnections.remove(this); // Eliminar la conexión de la lista
                    break;
                }
            }
        } catch (EOFException e) {
            System.out.println("Client " + clientSocket.getRemoteSocketAddress() + " disconnected abruptly.");
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            System.out.println("Error sending message to client: " + e.getMessage());
            MainServer.clientConnections.remove(this); // Eliminar la conexión de la lista si no se puede enviar el mensaje
        }
    }
}
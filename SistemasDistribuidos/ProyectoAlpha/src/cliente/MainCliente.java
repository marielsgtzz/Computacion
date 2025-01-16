//Punto de entrada para la aplicación cliente. Inicializa la UI y maneja la conexión al servidor.
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;

public class MainCliente extends Application {

    private Socket socket; // Socket para comunicarse con el servidor
    private DataOutputStream out; // Flujo de salida para enviar mensajes al servidor
    private DataInputStream in; // Flujo de entrada para recibir mensajes del servidor
    private UIcliente uiCliente; // UI cliente que contiene la interfaz de usuario
    private ControladorCliente controlador; // Controlador para manejar las acciones del cliente
    private int[] monstruoActual;
    // Método init que se llama antes de que la aplicación comience
    @Override
    public void init() throws Exception {
        connectToServer();
        monstruoActual = new int[2];
    }

    // Método start que se llama al inicio de la aplicación JavaFX
    @Override
    public void start(Stage primaryStage) {
        controlador = new ControladorCliente(out);
        if (controlador != null) {
            uiCliente = new UIcliente(controlador);
            uiCliente.start(primaryStage);
            controlador.setUiCliente(uiCliente);
        } else {
            System.out.println("El controlador no ha sido inicializado.");
            Platform.exit();
        }
    }

    // Método stop que se llama cuando la aplicación está cerrando
    @Override
    public void stop() throws Exception {
        if (out != null) {
            out.writeUTF("Client Disconnected");
            System.out.println("Sent disconnection message to server.");
        }
        closeConnection();
    }
    

    // Cierra la conexión y los flujos de datos
    private void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean msjWinner(String msj){
        boolean res = false;
        if(msj.charAt(0)=='!'){
            System.out.println("Alguien gano");
            res = true;
        }
        return res;
    }

    public void connectToServer() {
        
        try {
            // Se conecta al servidor en el puerto especificado para comunicacion TCP
            int serverPort = 49152;
            socket = new Socket("10.10.21.254", serverPort);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            // Escucha los mensajes del servidor en un hilo separado
            new Thread(() -> {
                MulticastSocket socketM = null;
                try {
                    //Para comunicacion Multicast
                    InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group
                    socketM = new MulticastSocket(49155);
                    socketM.joinGroup(group);
                    byte[] buffer = new byte[1000];
                    while(true){
                        DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                        socketM.receive(messageIn);
                        String data = new String(messageIn.getData(),0,messageIn.getLength());
                        System.out.println("Received: "+data+ "from: "+ messageIn.getAddress());
                        if(msjWinner(data)){
                            uiCliente.mostrarAlertaWinner(data);
                        } else if (data.charAt(0)=='(') {
                            int[] coordenadas = datosCoordenadas(data); //Descopone el mensaje en enteros para representar adecuadamente las coordenadas
                            colocarMonstruo(coordenadas); //envia la coordenada del monstruo a la GUI
                            quitarMonstruo(coordenadas);
                        } else {
                            uiCliente.mostrarAlertaWinner(data);
                        }
                    }
                } catch (IOException e) {
                    // Maneja el caso en que el servidor cierre la conexión
                    System.out.println("Server has closed the connection: " + e.getMessage());
                    Platform.runLater(() -> {
                        // Muestra una alerta y cierra el cliente
                        uiCliente.mostrarAlertaDesconexion();
                        stopClient();
                    });
                }
            }).start();
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }

    //Toma el mensaje recibido del servidor y cambia el boton en esa posicion
    public void colocarMonstruo(int[] coordenadas){
        uiCliente.mostrarMonstruo(coordenadas[0],coordenadas[1]);
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

    public void quitarMonstruo(int[] posMonstruoNuevo){
        uiCliente.quitarMonstruo(this.monstruoActual[0],this.monstruoActual[1]);
        this.monstruoActual = posMonstruoNuevo;
        uiCliente.setPosMonstruoActual(posMonstruoNuevo);
    }

    // Cierra el cliente
    private void stopClient() {
        try {
            stop();
            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Punto de entrada principal para la aplicación
    public static void main(String[] args) {
        launch(args); // Lanza la aplicación JavaFX
    }
}
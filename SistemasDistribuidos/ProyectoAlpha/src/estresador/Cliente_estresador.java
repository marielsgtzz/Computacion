package estresador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

import javafx.application.Platform;

public class Cliente_estresador {
    private Socket socket; // Socket para comunicarse con el servidor
    private DataOutputStream out; // Flujo de salida para enviar mensajes al servidor
    private DataInputStream in; // Flujo de entrada para recibir mensajes del servidor
    private String nombre;
    private ClienteCallback callback;

    public Cliente_estresador(String nombre, ClienteCallback callback){
        this.nombre = nombre;
        this.callback = callback;
        connectToServer();
    }


    public void connectToServer() {
        
        try {
            // Se conecta al servidor en el puerto especificado para comunicacion TCP
            int serverPort = 49152;
            socket = new Socket("localhost", serverPort);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //Se cuenta el tiempo de respuesta del registro
            long startTime = System.currentTimeMillis();
            out.writeUTF(nombre);
            nombre = in.readUTF();
            long spentTime = System.currentTimeMillis() -startTime;
            System.out.println("TR: "+spentTime);

            // Escucha los mensajes del servidor en un hilo separado
            new Thread(() -> {
                long sTime = System.currentTimeMillis();
                MulticastSocket socketM = null;
                Random r = new Random();
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
                        //System.out.println("Received: "+data+ " from: "+ messageIn.getAddress());

                        if(!msjWinner(data)){
                            int tiempoDelay = r.nextInt(501);
                            Thread.sleep(tiempoDelay);
                            out.writeUTF(data);
                        }
                        else{
                            long spentT = System.currentTimeMillis() -startTime;
                            System.out.println("TJ: "+spentT);
                            stopClient();
                        }

                        

                    }
                } catch (SocketException e) {
                    //System.out.println("Socket: " + e.getMessage());
                } catch (IOException e) {
                    // Maneja el caso en que el servidor cierre la conexión
                    System.out.println("Server has closed the connection: " + e.getMessage());
                    Platform.runLater(() -> {
                        // Muestra una alerta y cierra el cliente
                        stopClient();
                    });
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    if (socketM != null) {
                        //socketM.leaveGroup(group);
                        socketM.close();
                    }
                }
            }).start();
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }

    public boolean msjWinner(String msj){
        boolean res = false;
        if(msj.charAt(0)=='!'){
            res = true;
            if (callback != null) {
                callback.onJuegoTerminado(nombre);
            }
            stopClient();
        }
        return res;
    }

    // Cierra el cliente
    public void stopClient() {
        try {
            // Solo intenta enviar el mensaje si el socket aún está abierto
            if (socket != null && !socket.isClosed() && out != null) {
                out.writeUTF("Client Disconnected");
            }
        } catch (IOException e) {
            System.out.println("Error sending disconnection message: " + e.getMessage());
        } finally {
            closeConnection();
            try {
                Platform.exit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Método stop que se llama cuando la aplicación está cerrando
    public void stop() throws Exception {
        if (out != null) {
            out.writeUTF("Client Disconnected");
            //System.out.println("Sent disconnection message to server.");
        }
        closeConnection();
    }

    // Cierra la conexión y los flujos de datos
    private void closeConnection() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
}

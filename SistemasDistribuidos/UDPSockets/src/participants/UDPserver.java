package participants;

import java.net.*;
import java.io.*;

public class UDPserver {
    public static void main(String args[]) {
        DatagramSocket socket = null;
        try {
            int serverPort = 49152;
            socket = new DatagramSocket(serverPort);
            byte[] buffer = new byte[1000]; // buffer encapsulará mensajes
            while (true) {
                System.out.println("Waiting for messages...");
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);  // bloqueante (si no lo fuera, al correr el servidor el while correria una y otra vez)

                DatagramPacket reply = new  //se envia un mensaje de regreso
                        DatagramPacket(request.getData(), 
                        request.getLength(),
                        request.getAddress(),
                        request.getPort());

                System.out.println("Server received a request from " + request.getAddress());
                socket.send(reply); //No es bloqueante
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (socket != null)
                socket.close(); //Importante cerrar los sockets despues de usarlos
        }
    }
}

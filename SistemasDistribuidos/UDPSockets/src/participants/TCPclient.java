package participants;

import java.net.*;
import java.io.*;

public class TCPclient {

    public static void main(String args[]) {

        Socket s = null;

        try {
            int serverPort = 49152;

            s = new Socket("localhost", serverPort);
            //s = new Socket("127.0.0.1", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream()); //Camino de entrada 
            DataOutputStream out = new DataOutputStream(s.getOutputStream()); //Camino de salida

            out.writeUTF("Hello");  // UTF is a string encoding

            String data = in.readUTF(); //para recibir, no se tiene explicitamente el receive, ya que se está leyendo el canal (camino) de in
            System.out.println("Received: " + data);

        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null) try {
                s.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }
    }
}
package participants;

import java.net.*;
import java.io.*;

public class TCPclient {

    private int numMensajes;

    public TCPclient(int numMensajes){
        this.numMensajes = numMensajes;
        main();
    }

    public void main() {
        int m = numMensajes;
        Socket s = null;

        try {
            int serverPort = 49152;

            s = new Socket("localhost", serverPort);
            //s = new Socket("127.0.0.1", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream()); //Camino de entrada 
            DataOutputStream out = new DataOutputStream(s.getOutputStream()); //Camino de salida

            int[] id = {0,1,2,3,4,5,6,7,8};

            int i=0;
            while(i<m){
                int randomNum = (int)(Math.random() * ((8 - 1) + 1)) + 1;
                String myMessage = ""+id[randomNum];
                out.writeUTF(myMessage);  // UTF is a string encoding
    
                String data = in.readUTF(); //para recibir, no se tiene explicitamente el receive, ya que se está leyendo el canal (camino) de in
                System.out.println("Received: " + data);
            } 

            if(i==m){
                out.writeUTF("Fin");
            }
            
            

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
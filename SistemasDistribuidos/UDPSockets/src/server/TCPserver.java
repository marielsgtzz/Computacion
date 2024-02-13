package server;

import java.net.*;
import java.io.*;

/* En TCP se pueden enviar double,string,int directamente, ya no como en UDP que lo que se intercambia
 * son bytes. 
 */

 public class TCPserver {

    public static void main(String args[]) {
        try {
            int serverPort = 49152;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Waiting for messages...");
                Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
                Connection c = new Connection(clientSocket);
                c.start();
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }

}

class Connection extends Thread {
    private DataInputStream in;
    private DataOutputStream out; // el que es out para el servidor, es la entrada del cliente
    private Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket; 
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            // an echo server
            String data = in.readUTF();         // recibo solicitud
            while(data!="Fin"){
                int key = Integer.parseInt(data); 
                AddressBook addressBook = new AddressBook();
                Person p = new Person();
                String msj;
                if(addressBook.isKeyValid(key)){
                    p = addressBook.getRecord(key);
                    msj = "Message received from: " + p.getName();
                    System.out.println(msj);
                } else { 
                    msj = "Invalid key";
                    System.out.println(msj);
                }
                
                out.writeUTF(msj);                // envio respuesta
                data = in.readUTF(); 
            }

            if(data=="Fin"){
                clientSocket.close();
                System.out.println("Bye");
            }
           
            

        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

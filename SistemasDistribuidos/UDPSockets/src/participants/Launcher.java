package participants;

public class Launcher {

    public static void main (String args[]){
        //Hacerlo dentro de un ciclo 
        ClientThread clientThread = new ClientThread();
        clientThread.start(); //No funciona si ClientThread no extiende a Thread
    }
    
}

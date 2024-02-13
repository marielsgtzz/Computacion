package participants;

public class ClientThread extends Thread{
    public ClientThread(){

    }

    public void run(){ //Instancia a TCPclient

        //clientThread.start(); //No funciona si ClientThread no extiende a Thread

        TCPclient client = new TCPclient(50);
    }
}

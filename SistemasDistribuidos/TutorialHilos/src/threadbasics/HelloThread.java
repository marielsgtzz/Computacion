package threadbasics;

public class HelloThread extends Thread {
    @Override
    public void run(){
        for(int i=0; i<1000; i++){
            System.out.println(i + "Hola soy hilo extends "+Thread.currentThread().getName());
        }
    }
}

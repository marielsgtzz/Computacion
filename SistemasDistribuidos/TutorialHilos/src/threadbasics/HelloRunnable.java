package threadbasics;

public class HelloRunnable implements Runnable {
    @Override
    public void run(){
        //Gracias al for podemos ver como se interlazan
        for(int i=0; i<1000; i++){
            System.out.println(i + "Hola soy hilo runnable "+Thread.currentThread().getName());
        }
        
    }
    
}

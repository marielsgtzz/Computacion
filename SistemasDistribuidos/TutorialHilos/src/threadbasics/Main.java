package threadbasics;

public class Main {
    public static void main(String[] args) {
        try {
            HelloThread hilo1 = new HelloThread();
        Thread hilo2 = new Thread(new HelloRunnable()); //Crear un hilo así es sinónimo de como se creó el hilo1
        //hilo1.run(); Las instrucciones de hilo1 se meten en el mismo hilo que el del main, por eso aparecía en ambos hilomain
        //hilo1.start(); //Con start se crea otro thread y ese hilo empieza después de que termina el hilo del main
        //Thread.sleep( 5000); //Arranca el hilo 1 (Thread-0), después de 5sec arranca el hilo 2(Thread-1) Esto no sincroniza los hilos adecuadamente
        //hilo2.start();

        //Forma correcta de sincronizar Primero 1 y luego el hilo 2
        // hilo1.start();
        // hilo1.join();
        // hilo2.start();

        //Interlaza los hilos 
        // hilo1.start();
        // hilo2.start();
        // hilo1.join();
        // hilo2.join();
        Counter counter = new Counter(0);
        SyncronizedThread hilo3 = new SyncronizedThread(counter);
        SyncronizedThread hilo4 = new SyncronizedThread(counter);
        hilo3.start();
        hilo4.start();
        //Aunque digamos que primero el 1 y luego el 2, como los hilos no son deterministicos, no siempre empiezan en ese orden
        System.out.println("Hola soy hilo" + Thread.currentThread().getName());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

package threadbasics;

public class SyncronizedThread extends Thread {
    private Counter counter;
    public SyncronizedThread(Counter counter){
        this.counter = counter;
    }

    public void run(){
        counter.increase(Thread.currentThread().getName());
    }
    
}

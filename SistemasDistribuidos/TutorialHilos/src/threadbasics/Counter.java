package threadbasics;

public class Counter {
    private int count = 0;

    public Counter(int count){
        this.count = 0;
    }

    public void increase(String threadId){
        for(int i=0; i<1000; i++){
            count++;
            System.out.println(threadId + "incremento " + count);
        }
    }
}

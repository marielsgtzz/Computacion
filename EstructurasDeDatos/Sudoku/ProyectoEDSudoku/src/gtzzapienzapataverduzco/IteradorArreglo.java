package gtzzapienzapataverduzco;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mariel S. Gtz. Zapien, Mariana Zapata Covarrubias & Mauricio Verduzco Chavira
 */

public class IteradorArreglo<T> implements Iterator<T> {
    private T[] coleccion;
    private int total;
    private int actual;
   
    public IteradorArreglo(T[] coleccion, int total){
        this.coleccion=coleccion;
        this.total=total;
        this.actual=0;
    }
    
    public boolean hasNext(){//regresa verdadero es que tiene un elemento que analizar
        return actual<total;
    }
    
    public T next(){
        if(hasNext()){
            T result;
            result=coleccion[actual];
            actual++;
            return result;
        }
        else
            throw new NoSuchElementException();
    }
    
    public void remove(){
        throw new UnsupportedOperationException();
    }
    
}
package gtzzapienzapataverduzco;

import java.util.Iterator;

/**
 * @author Mariel S. Gtz. Zapien, Mariana Zapata Covarrubias & Mauricio Verduzco Chavira
 */

public interface SetADT<T> extends Iterable<T> {
    public boolean contains(T dato);
    public boolean isEmpty();
    public void add(T dato);
    public T remove(T dato);
    public SetADT<T> union(SetADT<T> otro);
    public SetADT<T> inter(SetADT<T> otro);
    public Iterator<T> iterator();
    public int getCardinalidad();
    public String toString();
    public boolean equals(SetADT <T> otro);
    public SetADT<T> diferencia(SetADT<T> otro);
    public SetADT<T> unionR(SetADT<T> otro);
    public SetADT<T> interR(SetADT<T> otro);
    public SetADT<T> diferR(SetADT<T> otro);
  
    
}
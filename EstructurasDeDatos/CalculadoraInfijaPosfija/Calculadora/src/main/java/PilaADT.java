/**
 * @author Emiliano
 * Mariel Guiterrez Zapien
 * Camilo Palma
 * Diego Arellano
 */
public interface PilaADT <T>  {
    public void push(T dato);
    public T pop();
    public boolean isEmpty();
    public T peek();
     
}

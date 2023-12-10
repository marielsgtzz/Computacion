/**
 * @author Emilio
 * Mariel Guiterrez Zapien
 * Camilo Palma
 * Diego Arellano
 */
public class PilaA<T>  implements PilaADT<T> {
    private T datos[];
    private int tope;
    private final int MAX = 20;
    
    public PilaA() {
        datos = (T[]) new Object[MAX];
        tope = -1; // Indica pila vacía
    }
    
    public PilaA(int max) {
        datos = (T[]) new Object[max];
        tope = -1; // Indica pila vacía
    }
    
    public boolean isEmpty() {
        return tope == -1;
    }
    
    public T peek()  {
        if(isEmpty())
            throw new ExcepcionColeccionVacia("Pila vacía. No se puede consultar. ");
        return datos[tope];
    }
    
      public T pop() {
        if(isEmpty())
            throw new ExcepcionColeccionVacia("Pila vacía. No se puede quitar. ");
        T dato = datos[tope];
        datos[tope] = null;
        tope--;
        return dato;
    }
    
    public void push(T dato) {
        if(tope + 1 == datos.length)
            aumentaCapacidad();
        tope++;
        datos[tope] = dato;
    }
    
    private void aumentaCapacidad() {
        T [] masGrande = (T[]) new Object[datos.length*2];
        for(int i = 0; i <= tope; i++)
            masGrande[i] = datos[i];
        datos = masGrande;
    }
    
    public String toString() {
        StringBuilder cad = new StringBuilder();
        for(int i = 0; i <= tope; i++)
            cad.append("Elemento " + i + " = " + datos[i] + "\n");
        return cad.toString();
    }
    
}


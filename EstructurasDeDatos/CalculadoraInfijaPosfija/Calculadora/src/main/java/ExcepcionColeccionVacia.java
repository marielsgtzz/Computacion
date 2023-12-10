/**
 * @author Emiliano
 * Mariel Guiterrez Zapien
 * Camilo Palma
 * Diego Arellano
 */
public class ExcepcionColeccionVacia extends RuntimeException  {
    
    public ExcepcionColeccionVacia() {
        super("colección vacía");
    }
    
      public ExcepcionColeccionVacia(String mensaje) {
        super(mensaje);
    }

}

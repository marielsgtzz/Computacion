package gtzzapienzapataverduzco;

/**
 * @authors Mariel S. Gtz. Zapien, Mariana Zapata Covarrubias & Mauricio Verduzco Chavira
 */

public class ErroneousDataException extends RuntimeException {
    
    public ErroneousDataException (String s) {
      super ("Los datos son erroneos: " + s);
   }
    
}

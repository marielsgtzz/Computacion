//Conecta la funcionalidad del cliente con su interfaz gráfica
import java.io.DataOutputStream;
import java.io.IOException;

public class ControladorCliente {
    private DataOutputStream out;
    private UIcliente uiCliente;

    public ControladorCliente(DataOutputStream out) {
        this.out = out;
    }

    public void setUiCliente(UIcliente uIcliente){
        this.uiCliente = uIcliente;
    }
    public void enviarMensaje(String mensaje) {
        try {
            out.writeUTF(mensaje);
            System.out.println("Nombre enviado al servidor: " + mensaje);
            uiCliente.showGrid();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

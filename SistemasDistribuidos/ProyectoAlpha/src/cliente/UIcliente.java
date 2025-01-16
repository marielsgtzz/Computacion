//Define la interfaz de usuario del cliente, la cuadrícula de botones.
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UIcliente {

    private ControladorCliente controlador;
    private GridPane grid;
    private Button[][] buttonsGrid;
    private Button btnEnviar;
    private TextField nombre;
    private int[] posMonstruoActual;

    // Constructor que acepta un controlador y lo asocia con la interfaz de usuario.
    public UIcliente(ControladorCliente controlador) {
        this.controlador = controlador;
        this.grid = new GridPane();// Usar un GridPane para la cuadrícula de botones.
        this.buttonsGrid = new Button[3][4];
    }

    public void setPosMonstruoActual(int[] posMonstruo){
        this.posMonstruoActual = posMonstruo;
    }

    // Método start que configura y muestra la ventana principal de la aplicación.
    public void start(Stage primaryStage) {
        
        grid.setAlignment(Pos.CENTER); // Centrar la cuadrícula en el GridPane.
        grid.setHgap(10); // Espacio horizontal entre botones
        grid.setVgap(10); // Espacio vertical entre botones

        // Crear una cuadrícula de botones de 4x3.
        int f= 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                // Crear un nuevo botón y establecer su acción.
                Button btn = new Button("(" + i + "," + j + ")");
                int finalI = i;
                int finalJ = j;
                btn.setOnAction(event -> btnOnClick(btn,finalI,finalJ));
                btn.setDisable(true);
                buttonsGrid[i][j] = btn;
                grid.add(buttonsGrid[i][j], j, i); // Añadir botón al GridPane
            }
        }

        // Crear un botón para enviar mensaje de login al servidor
        btnEnviar = new Button("Login");

        // Crear un textbox para escribir el nombre que se va a mandar al servidor
        nombre = new TextField();

        btnEnviar.setOnAction(event -> controlador.enviarMensaje(nombre.getText()));

        // VBox para organizar los elementos verticalmente
        VBox vbox = new VBox(10); // Espacio de 10px entre elementos
        vbox.setAlignment(Pos.CENTER); // Centrar elementos en el VBox

        // Añadir el botón de enviar, el nombre del login, y la cuadrícula al VBox
        //vbox.getChildren().addAll(nombre,grid);
        vbox.getChildren().addAll(nombre, btnEnviar, grid);

        // Configurar la escena con el VBox
        Scene scene = new Scene(vbox, 400, 500); // Ajusta el tamaño según necesites
        primaryStage.setTitle("Cliente - Interfaz Gráfica");
        
        // Mostrar la ventana principal
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para mostrar un cuadro de diálogo de alerta cuando se desconecta el servidor.
    public void mostrarAlertaDesconexion() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Desconexión del servidor");
        alert.setHeaderText(null);
        alert.setContentText("El servidor se ha desconectado.");
        
        // Esperar a que el usuario cierre el cuadro de diálogo de alerta.
        alert.showAndWait();
    }

    // Método para mostrar un cuadro de diálogo de alerta cuando algún cliente gana.
    public void mostrarAlertaWinner(String winner) {
         Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Juego Terminado");
            alert.setHeaderText(null);
            alert.setContentText(winner+" gano la partida. Empieza la siguiente partida.");
            alert.show();
            // Crea una pausa de transición de 5 segundos
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> alert.close());
            delay.play();
        });
    }

    public void btnOnClick(Button btn, int finalI,int finalJ){
        System.out.println("Botón presionado en: " + finalI + "," + finalJ);
        boolean res = checarRespuesta(finalI,finalJ);
        if (res){
            controlador.enviarMensaje("(" + finalI + "," + finalJ+")");
        }
        btn.setStyle("-fx-background-color: #light-grey;");
    }

    public void mostrarMonstruo(int x, int y){
        buttonsGrid[x][y].setStyle("-fx-background-color: #01a049;");
    }

    public void quitarMonstruo(int x, int y){
        buttonsGrid[x][y].setStyle("-fx-background-color: #light-grey;");
    }

    public boolean checarRespuesta(int x, int y){
        boolean res = false;
        if (x==posMonstruoActual[0] && y==posMonstruoActual[1]){
            res = true;
        }
        return res;
    }
    
    public void showGrid(){
        btnEnviar.setDisable(true);
        nombre.setDisable(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                buttonsGrid[i][j].setDisable(false);
            }
        }
    }

    public void hideGrid(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                buttonsGrid[i][j].setDisable(true);
            }
        }
    }
}

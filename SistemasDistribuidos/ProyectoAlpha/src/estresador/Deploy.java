package estresador;

public class Deploy {
    private static int clientesTerminados = 0;
    private static final Object lock = new Object();


    public static void main(String[] args) {
        ClienteCallback callback = nombreCliente -> {
            synchronized (lock) {
                clientesTerminados++;
                if (clientesTerminados == 30) {
                    lock.notify(); // Notifica que todos los clientes han terminado
                }
            }
        };

        for (int j = 1; j <= 30; j++) {
            System.out.println("Iniciando juego número: " + j);
            clientesTerminados = 0; // Restablecer el contador para el nuevo juego

            for (int i = 0; i < 30; i++) {
                new Cliente_estresador("Santi", callback);
            }

            synchronized (lock) {
                while (clientesTerminados < 30) {
                    try {
                        lock.wait(); // Espera a que todos los clientes terminen
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        
    }
}

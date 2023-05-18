import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EcoServidor {
    public static final int PORT = 4444;
    private static final AtomicInteger clientCounter = new AtomicInteger(0);
    private static final List<ThreadSocket> activeThreads = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        //objeto ServerSocket que escucha las conexiones en el puerto especificado.
        ServerSocket socketServidor = null;

        try {
            //Se muestra un mensaje indicando que el servidor está activo en el puerto.
            socketServidor = new ServerSocket(PORT);
            System.out.println("Servidor activo en el puerto: " + PORT);
            //Se inicia un bucle infinito (while (true)) para aceptar conexiones de clientes de forma continua.
            while (true) {
                Socket socketCliente = socketServidor.accept();
                System.out.println("Conexión aceptada: " + socketCliente);

                //se crea una instancia de ThreadSocket pasando el Socket del cliente y el número de cliente
                //único obtenido mediante clientCounter.incrementAndGet().
                ThreadSocket threadSocket = new ThreadSocket(socketCliente, clientCounter.incrementAndGet());

                //agrega el hila activo a la lista
                activeThreads.add(threadSocket);
                //inicia el thread
                threadSocket.start();
            }
        } catch (IOException e) {
            System.out.println("Error al escuchar en el puerto: " + PORT);
            e.printStackTrace();
        }
    }

    static class ThreadSocket extends Thread {
        private Socket socketCliente;
        private int clientNumber;

        ThreadSocket(Socket socketCliente, int clientNumber) {
            this.socketCliente = socketCliente;
            this.clientNumber = clientNumber;
        }

        @Override
        public void run() {
            try {
                //Se crean los flujos de entrada y salida para comunicarse con el cliente
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);

                while (true) {
                    String str = entrada.readLine();
                    System.out.println("Cliente " + clientNumber + ": " + str);
                    salida.println(str);
                    if (str.equals("Adios")) {
                        break;
                    }
                }
                //Se cierran los flujos
                entrada.close();
                salida.close();

                //se cierra el socket y se elimina de la lista, si la lista esta vacia entonces
                //se cierra el programa
                socketCliente.close();
                synchronized (activeThreads) {
                    activeThreads.remove(this);
                    if (activeThreads.isEmpty()) {
                        System.out.println("No hay conexiones activas. Server cerrado");
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error en la comunicación con el cliente " + clientNumber);
                e.printStackTrace();
            }
        }
    }
}

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHTTP {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Servidor activo en el puerto: " + PORT);
            while (true) {
                new ThreadSocket(server.accept());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ThreadSocket extends Thread {
        private Socket insocket;

        ThreadSocket(Socket insocket) {
            this.insocket = insocket;
            this.start();
        }

        @Override
        public void run() {
            try {
                InputStream is = insocket.getInputStream();
                PrintWriter out = new PrintWriter(insocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(is));

                String line;
                line = in.readLine();
                System.out.println("HTTP-HEADER: " + line);
                line = "";

                // busca post data
                int postDataI = -1;
                while ((line = in.readLine()) != null && (line.length() != 0)) {
                    System.out.println("HTTP-HEADER: " + line);
                    if (line.indexOf("Content-Length:") > -1) {
                        postDataI = Integer.parseInt(line.substring(line.indexOf("Content-Length:") + 16, line.length()));
                    }
                }

                String postData = "";

                // lee el post data
                if (postDataI > 0) {
                    char[] charArray = new char[postDataI];
                    in.read(charArray, 0, postDataI);
                    postData = new String(charArray);
                }

                out.println("HTTP/1.0 200 OK");
                out.println("Content-Type: text/html; charset=utf-8");
                out.println("Server: MINISERVER");
                out.println("");



                //verificacion
                if(postData.contains("admin")){
                    Bienvenida(out);
                    out.close();
                    insocket.close();
                }else{

                    EnviarFormulario(out);

                    out.close();
                    insocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void EnviarFormulario(PrintWriter out) throws IOException {
            // Envía el HTML
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\DIEGO\\Documents\\Proyects\\Semillero\\ServerHTTP\\serverJava.html"));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if(line == null){
                    break;
                }
                out.println(line);
            }
            reader.close();
        }
        public void Bienvenida(PrintWriter out) throws IOException{
            out.println("<title>JavaServerHTTP</title>" +
                    "<style>" +
                    "body {\n" +
                    "        font-family: Arial, sans-serif;\n" +
                    "        background-color: #f2f2f2;\n" +
                    "        padding: 20px;\n" +
                    "    }\n" +
                    "    h1 {\n" +
                    "        text-align: center;\n" +
                    "        color: #333;\n" +
                    "    }" +
                    "</style>" +
                    "<h1>Bienvenido administrador</h1>");
        }
    }
}
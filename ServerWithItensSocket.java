import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerWithItensSocket {

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server is running");

            try (Socket clientSocket = serverSocket.accept()) {
                // what the client had write
                InputStream clientIS = clientSocket.getInputStream();

                StringBuilder requestBuilder = new StringBuilder();
                // reading request data - Headers
                int data;
                do {
                    data = clientIS.read();
                    requestBuilder.append((char) data);

                } while (clientIS.available() > 0);

                String request = requestBuilder.toString();
                System.out.println(request);

                Path path = Path.of("itensCardapio.json");
                String json = Files.readString(path);

                OutputStream clientOS = clientSocket.getOutputStream();
                PrintStream clientOut = new PrintStream(clientOS);


                clientOut.println("HTTP/1.1 200 OK");
                clientOut.println("Content-type: application/json; chatset=UTF-8");
                clientOut.println();
                clientOut.println(json);

            }


        }
    }

}

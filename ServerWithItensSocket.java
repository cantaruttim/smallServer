import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerWithItensSocket {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server is running on port 8000");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(() -> {
                    try {
                        treatClientRequest(clientSocket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error starting server", e);
        }
    }

    private static void treatClientRequest(Socket clientSocket)
            throws IOException {

        try (clientSocket) {

            InputStream clientIS = clientSocket.getInputStream();

            // Read HTTP request
            StringBuilder requestBuilder = new StringBuilder();
            int data;
            while ((data = clientIS.read()) != -1) {
                requestBuilder.append((char) data);

                if (requestBuilder.toString().endsWith("\r\n\r\n")) {
                    break; // finished headers
                }
            }

            String request = requestBuilder.toString();
            System.out.println("Request received:");
            System.out.println(request);

            // Load JSON file
            Path path = Path.of("itensCardapio.json");
            String json = Files.readString(path, StandardCharsets.UTF_8);

            // Send response
            OutputStream clientOS = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(clientOS, true, StandardCharsets.UTF_8);

            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: application/json; charset=UTF-8");
            out.println("Content-Length: " + json.getBytes(StandardCharsets.UTF_8).length);
            out.println();
            out.println(json);

        } catch (Exception e) {
            System.err.println("Error treating client request: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

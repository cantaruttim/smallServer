import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

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


            }


        }
    }

}

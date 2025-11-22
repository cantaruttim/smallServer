import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServidorItensCardapio {

    public static void main(String[] args) throws Exception {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

        httpServer.createContext(
                "/itensCardapio.json",
                exchange -> {

                    Path path = Path.of("itensCardapio.json");
                    String json = Files.readString(path);
                    byte[] bytes = json.getBytes();

                    Headers responseHeaders = exchange.getResponseHeaders();
                    responseHeaders.add("Content-type", "application/json; chatset=UTF-8");


                    exchange.sendResponseHeaders(200, bytes.length);

                    OutputStream responseBody = exchange.getResponseBody();
                    responseBody.write(bytes);

                }
        );

        System.out.println("Server is running on: " + httpServer.getAddress());
        httpServer.start();

    }
}

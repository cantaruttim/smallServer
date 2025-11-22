import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCEp  {

    public static void main(String[] args) throws Exception {

        // URI uri = URI.create("https://viacep.com.br/ws/01001000/json/");
        URI uri = URI.create("http://localhost:8000/itensCardapio.json");
        // Client Side
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = HttpRequest.newBuilder(uri).build();

            HttpResponse<String> httpResponse = httpClient.send(
                    httpRequest,
                    HttpResponse.BodyHandlers.ofString()
            );
            int statusCode = httpResponse.statusCode();
            String body = httpResponse.body();
            System.out.println(statusCode);
            System.out.println(body);

        }

        /*
            // Padrão até o Java 1.7
            // URL url = new URL("https://viacep.com.br/ws/01001000/json/");
            // URLConnection urlConnection = url.openConnection();
         */

        /*
        // Scanner scanner = null;
        try (Scanner scanner = new Scanner(url.openStream())) {
            // bytes workflow
            // InputStream inputStream = urlConnection.getInputStream();
            // scanner = new Scanner(url.openStream()); // já faz o inputStream
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }

        /*finally {
            if (scanner != null) {
                scanner.close();
            }
        } */




    }
}

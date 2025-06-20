import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Base64;

public class FaceSearchApiExample {

    private static final String ACCESS_TOKEN = "your_access_token";
    private static final String IMAGE_PATH = "path_to_image";

    public static void main(String[] args) throws IOException, InterruptedException {
        byte[] imageBytes = Files.readAllBytes(new File(IMAGE_PATH).toPath());
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        String jsonBody = "{"
                + "\"image\":\"" + base64Image + "\","
                + "\"category\":\"duplicates\","
                + "\"sortType\":\"QUALITY_DESCENDING\","
                // + "\"domain\":\"\"," - optional doamin filter
                + "\"page\":1"
                + "}";

        String response = sendPostRequest("https://api.lenso.ai/search", jsonBody, ACCESS_TOKEN);

        System.out.println("Response from API:");
        System.out.println(response);
    }

    private static String sendPostRequest(String url, String jsonBody, String accessToken) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("HTTP Status Code: " + response.statusCode());

        return response.body();
    }
}

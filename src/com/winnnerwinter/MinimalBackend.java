import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class MinimalBackend {
    public static void main(String[] args) throws IOException {
        // Create an HTTP server on port 8301
        HttpServer server = HttpServer.create(new InetSocketAddress(8301), 0);
        
        // Define a context and handler for the root path
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "Hello, World!";
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        });

        server.createContext("/hello", exchange -> {
            String name = getQueryParam(exchange, "name", "World");
            String response = "Hello, " + name + "!";
            sendResponse(exchange, 200, response);
        });

        server.createContext("/info", exchange -> {
            String response = "{ \"message\": \"This is a JSON response\", \"status\": \"success\" }";
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            sendResponse(exchange, 200, response);
        });

        server.createContext("/greet", exchange -> {
            String path = exchange.getRequestURI().getPath();
            String[] segments = path.split("/");
            String name = (segments.length > 2) ? segments[2] : "Stranger";
            String response = "Greetings, " + name + "!";
            sendResponse(exchange, 200, response);
        });
        
        // Start the server
        server.setExecutor(null); // Use the default executor
        server.start();
        System.out.println("Server started on port 8301");
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    private static String getQueryParam(HttpExchange exchange, String key, String defaultValue) {
        String query = exchange.getRequestURI().getQuery();
        if (query == null) return defaultValue;

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2 && keyValue[0].equalsIgnoreCase(key)) {
                return keyValue[1];
            }
        }
        return defaultValue;
    }
}

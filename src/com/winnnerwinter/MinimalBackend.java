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
        
        // Start the server
        server.setExecutor(null); // Use the default executor
        server.start();
        System.out.println("Server started on port 8301");
    }
}

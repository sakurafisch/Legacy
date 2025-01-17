import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MinimalBackendTest {

    private static Thread serverThread;

    // ANSI escape codes for text colors
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        try {
            startServer();
            Thread.sleep(1000);

            runTest("Root Endpoint", "/", "Hello, World!");
            runTest("Hello Endpoint (without name)", "/hello", "Hello, World!");
            runTest("Hello Endpoint (with name)", "/hello?name=Alice", "Hello, Alice!");
            runTest("Info Endpoint", "/info", "{ \"message\": \"This is a JSON response\", \"status\": \"success\" }");
            runTest("Greet Endpoint (without name)", "/greet", "Greetings, Stranger!");
            runTest("Greet Endpoint (with name)", "/greet/Alice", "Greetings, Alice!");

            stopServer();
            System.exit(0);
        } catch (Exception e) {
            System.out.println(RED + "[ERROR] An unexpected error occurred: " + e.getMessage() + RESET);
            e.printStackTrace();
            System.exit(1);
        }
    }

    static void startServer() {
        serverThread = new Thread(() -> {
            try {
                MinimalBackend.main(new String[]{});
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }

    static void stopServer() {
        serverThread.interrupt();
    }

    static String sendRequest(String endpoint) throws IOException {
        URL url = new URL("http://localhost:8301" + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Get the response code and body
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            System.out.println(RED + "[ERROR] Received non-OK response code " + responseCode + RESET);
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    static void printTestHeader(String testName) {
        System.out.println(CYAN + "\n========================================");
        System.out.println("Running Test: " + BLUE + testName + RESET);
        System.out.println("========================================" + RESET);
    }

    static void printTestResult(String testName, boolean success) {
        if (success) {
            System.out.println(GREEN + "[PASS] " + testName + " - Test Passed." + RESET);
        } else {
            System.out.println(RED + "[FAIL] " + testName + " - Test Failed." + RESET);
        }
    }

    static void runTest(String testName, String endpoint, String expectedResponse) {
        try {
            printTestHeader(testName);
            String response = sendRequest(endpoint);
            boolean success = response != null && response.equals(expectedResponse);
            printTestResult(testName, success);
        } catch (IOException e) {
            printTestResult(testName, false);
            System.out.println(RED + "[ERROR] " + testName + " Test failed due to an exception: " + e.getMessage() + RESET);
        }
    }
}
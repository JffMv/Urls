package org.example;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The EchoServerSendImageDocument class implements a simple HTTP server that serves files,
 * including images, to clients based on HTTP GET requests.
 */
public class EchoServerSendImageDocument {

    private static final int PORT = 35000;

    /**
     * The main method that starts the EchoServerSendImageDocument.
     *
     * @param args the command line arguments (not used)
     * @throws IOException if an I/O error occurs when creating the server socket or handling client requests
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            // Create a server socket listening on port 35000
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + PORT);
            System.exit(1);
        }

        System.out.println("Server is running and listening on port " + PORT + "...");

        // Accept and handle client connections indefinitely
        while (true) {
            Socket clientSocket = null;
            try {
                // Wait for a client connection
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            System.out.println("Accepted connection from client: " + clientSocket);
            handleClientRequest(clientSocket);
        }
    }

    /**
     * Handles the client request by processing the HTTP request and serving the requested file.
     *
     * @param clientSocket the socket representing the client connection
     */
    private static void handleClientRequest(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream()) {

            // Read the request line
            String requestLine = in.readLine();
            System.out.println("Request: " + requestLine);

            // Parse the request
            String[] requestParts = requestLine.split(" ");
            if (requestParts.length != 3) {
                sendErrorResponse(out, 400, "Bad Request");
                return;
            }

            String method = requestParts[0];
            String path = requestParts[1];
            String httpVersion = requestParts[2];

            if (!method.equals("GET")) {
                sendErrorResponse(out, 405, "Method Not Allowed");
                return;
            }

            // Serve the requested file
            String filePath = "src/main/resource/" + path;
            File file = new File(filePath);
            if (!file.exists() || file.isDirectory()) {
                sendErrorResponse(out, 404, "Not Found");
                return;
            }

            // Send the response
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            String contentType = Files.probeContentType(Paths.get(filePath));

            sendSuccessResponse(out, fileContent, contentType);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sends an HTTP error response to the client.
     *
     * @param out the output stream to write the response
     * @param statusCode the HTTP status code
     * @param statusMessage the HTTP status message
     * @throws IOException if an I/O error occurs
     */
    private static void sendErrorResponse(OutputStream out, int statusCode, String statusMessage) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        writer.println("HTTP/1.1 " + statusCode + " " + statusMessage);
        writer.println("Content-Type: text/html");
        writer.println();
        writer.println("<html><body><h1>" + statusCode + " " + statusMessage + "</h1></body></html>");
        writer.flush();
    }

    /**
     * Sends an HTTP success response with the file content to the client.
     *
     * @param out the output stream to write the response
     * @param content the byte array containing the file content
     * @param contentType the MIME type of the content
     * @throws IOException if an I/O error occurs
     */
    private static void sendSuccessResponse(OutputStream out, byte[] content, String contentType) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + content.length);
        writer.println();
        writer.flush();
        out.write(content);
        out.flush();
    }
}

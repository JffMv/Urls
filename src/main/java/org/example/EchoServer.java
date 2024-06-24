package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The EchoServer class implements a simple server that echoes back the square of an integer received from a client.
 */
public class EchoServer {

    /**
     * The main method that starts the EchoServer.
     *
     * @param args the command line arguments (not used)
     * @throws IOException if an I/O error occurs when creating the server socket or reading/writing data
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            // Create a server socket listening on port 35000
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            // Wait for a client connection
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        // Setup input and output streams
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensaje: " + inputLine);
            try {
                // Parse input as an integer
                int answer = Integer.parseInt(inputLine);
                // Calculate square of the integer
                outputLine = "Respuesta " + answer * answer;
                // Send the squared value back to the client
                out.println(outputLine);
                // Check if client wants to end communication
                if (outputLine.equals("Respuesta: Bye."))
                    break;
            } catch (NumberFormatException e) {
                // Handle case where input is not a valid integer
                out.println("Invalid input. Please enter an integer.");
            }
        }

        // Close resources
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

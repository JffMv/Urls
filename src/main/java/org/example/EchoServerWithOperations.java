package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple echo server that can perform mathematical operations based on client requests.
 * The server listens on port 35000 and can handle two types of requests:
 * 1. Evaluate trigonometric functions (sin, cos, tan) on a given number.
 * 2. Change the current operation to be performed (sin, cos, tan).
 *
 * The client sends either a number or a command to change the operation. The server responds
 * with the result of the operation or an appropriate error message if the input is invalid.
 */
public class EchoServerWithOperations {
    private static String currentOperation = "cos";

    /**
     * Main method to start the echo server.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an I/O error occurs when creating the server socket.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        System.out.println("Server is running and listening on port 35000...");

        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            System.out.println("Accepted connection from client: " + clientSocket);

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received message: " + inputLine);

                String response;
                if (inputLine.startsWith("fun:")) {
                    currentOperation = inputLine.substring(4);
                    response = "Operation changed to: " + currentOperation;
                } else {
                    try {
                        double number = Double.parseDouble(inputLine);
                        double result = evaluateOperation(number);
                        response = "Result: " + result;
                    } catch (NumberFormatException e) {
                        response = "Invalid input. Please send a valid number or 'fun:operation'.";
                    }
                }

                out.println(response);

                if (inputLine.equals("Bye."))
                    break;
            }

            out.close();
            in.close();
            clientSocket.close();
        }
    }

    /**
     * Evaluates the current mathematical operation on the given number.
     *
     * @param number The number on which the operation should be performed.
     * @return The result of the operation.
     * @throws IllegalArgumentException If the current operation is not supported.
     */
    private static double evaluateOperation(double number) {
        switch (currentOperation) {
            case "sin":
                return Math.sin(number);
            case "cos":
                return Math.cos(number);
            case "tan":
                return Math.tan(number);
            default:
                throw new IllegalArgumentException("Unsupported operation: " + currentOperation);
        }
    }
}

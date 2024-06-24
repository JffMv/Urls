package org.example;

import java.io.*;
import java.net.*;

/**
 * The EchoClient class implements a simple client that connects to an echo server.
 * It reads input from the user, sends it to the server, and prints the response from the server.
 */
public class EchoClient {

    /**
     * The main method that starts the EchoClient.
     *
     * @param args the command line arguments (not used)
     * @throws IOException if an I/O error occurs when creating the socket or reading/writing data
     */
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            // Connect to the echo server at localhost on port 35000
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don’t know about host!.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn’t get I/O for the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        // Read user input and send it to the server, then print the server's response
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
        }

        // Close all resources
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}

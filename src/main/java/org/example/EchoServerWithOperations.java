package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerWithOperations {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensaje: " + inputLine);


            outputLine = "Respuesta " + evaluarFuncion(inputLine);
            out.println(outputLine);
            if (outputLine.equals("Respuestas: Bye."))
                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static double evaluarFuncion(String funcion) {
        if (funcion.startsWith("fun:")) {
            String operacion = funcion.substring(4, 7); // Obtener la operación (sin, cos, tan)
            if (funcion.contains("/") || funcion.contains("*") || funcion.contains("pi")){
                return 0;
            }
            double numero = Double.parseDouble(funcion.substring(7)); // Obtener el número
            switch (operacion) {
                case "sin":
                    return Math.sin(numero);
                case "cos":
                    return Math.cos(numero);
                case "tan":
                    return Math.tan(numero);
                default:
                    throw new IllegalArgumentException("Operación matemática no reconocida: " + operacion);
            }
        } else {
            throw new IllegalArgumentException("La cadena no comienza con 'fun'.");
        }
    }



}

package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * The EchoClientImplementationRemote class implements a remote client that interacts with an echo server using RMI.
 */
public class EchoClientImplementationRemote extends UnicastRemoteObject implements EchoClientRemoteInterface {
    private EchoServerRemoteInterface echoServer;

    /**
     * Constructor for EchoClientImplementationRemote.
     *
     * @throws RemoteException if a remote communication error occurs
     */
    public EchoClientImplementationRemote() throws RemoteException {
        super();
    }

    /**
     * Connects to the RMI registry and registers the client with the echo server.
     *
     * @param ipRmiregistry the IP address of the RMI registry
     * @param puertoRmiRegistry the port number of the RMI registry
     * @param nombreServicio the name of the echo server service
     * @param clientName the name of the client
     */
    public void ejecutaServicio(String ipRmiregistry, int puertoRmiRegistry, String nombreServicio, String clientName) {
        try {
            Registry registry = LocateRegistry.getRegistry(ipRmiregistry, puertoRmiRegistry);
            echoServer = (EchoServerRemoteInterface) registry.lookup(nombreServicio);
            echoServer.registerClient(clientName, this);
            System.out.println("Cliente registrado en el servidor.");
        } catch (Exception e) {
            System.err.println("Hay un problema:");
            e.printStackTrace();
        }
    }

    /**
     * Receives a message from another client.
     *
     * @param fromClient the name of the client sending the message
     * @param message the message content
     */
    @Override
    public void receiveMessage(String fromClient, String message) {
        System.out.println("Mensaje de " + fromClient + ": " + message);
    }

    /**
     * Sends a message to another client through the echo server.
     *
     * @param fromClient the name of the client sending the message
     * @param toClient the name of the recipient client
     * @param mensaje the message content
     */
    public void enviarMensaje(String fromClient, String toClient, String mensaje) {
        try {
            echoServer.sendMessage(fromClient, toClient, mensaje);
        } catch (Exception e) {
            System.err.println("Error enviando mensaje:");
            e.printStackTrace();
        }
    }

    /**
     * The main method to start the EchoClientImplementationRemote.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre de cliente: ");
        String clientName = scanner.nextLine();
        System.out.print("Ingrese la dirección IP del servidor: ");
        String ip = scanner.nextLine();
        System.out.print("Ingrese el puerto del servidor: ");
        int puerto = Integer.parseInt(scanner.nextLine());

        try {
            EchoClientImplementationRemote cliente = new EchoClientImplementationRemote();
            cliente.ejecutaServicio(ip, puerto, "echoServer", clientName);

            while (true) {
                System.out.print("Ingrese el nombre del cliente destinatario: ");
                String destinatario = scanner.nextLine();
                System.out.print("Ingrese un mensaje para enviar: ");
                String mensaje = scanner.nextLine();
                cliente.enviarMensaje(clientName, destinatario, mensaje);
            }
        } catch (Exception e) {
            System.err.println("Error iniciando el cliente:");
            e.printStackTrace();
        }
    }
}

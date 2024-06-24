package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class EchoClientImplementationRemote extends UnicastRemoteObject implements EchoClientRemoteInterface {
    private EchoServerRemoteInterface echoServer;

    public EchoClientImplementationRemote() throws RemoteException {
        super();
    }

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

    @Override
    public void receiveMessage(String fromClient, String message) {
        System.out.println("Mensaje de " + fromClient + ": " + message);
    }

    public void enviarMensaje(String fromClient, String toClient, String mensaje) {
        try {
            echoServer.sendMessage(fromClient, toClient, mensaje);
        } catch (Exception e) {
            System.err.println("Error enviando mensaje:");
            e.printStackTrace();
        }
    }

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



package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class EchoServerImplementationRemote extends UnicastRemoteObject implements EchoServerRemoteInterface {
    private Map<String, EchoClientRemoteInterface> clients;

    public EchoServerImplementationRemote() throws RemoteException {
        super();
        clients = new HashMap<>();
    }

    @Override
    public void registerClient(String clientName, EchoClientRemoteInterface client) throws RemoteException {
        clients.put(clientName, client);
        System.out.println("Cliente registrado: " + clientName);
    }

    @Override
    public void sendMessage(String fromClient, String toClient, String message) throws RemoteException {
        EchoClientRemoteInterface client = clients.get(toClient);
        if (client != null) {
            client.receiveMessage(fromClient, message);
        } else {
            System.out.println("Cliente no encontrado: " + toClient);
        }
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(23000);
            EchoServerImplementationRemote server = new EchoServerImplementationRemote();
            registry.rebind("echoServer", server);
            System.out.println("Echo server ready...");
        } catch (Exception e) {
            System.err.println("Echo server exception:");
            e.printStackTrace();
        }
    }
}

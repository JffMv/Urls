package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * The EchoServerImplementationRemote class implements the remote interface for an echo server.
 * It manages registered clients and facilitates message passing between clients.
 */
public class EchoServerImplementationRemote extends UnicastRemoteObject implements EchoServerRemoteInterface {
    private Map<String, EchoClientRemoteInterface> clients;

    /**
     * Constructor for EchoServerImplementationRemote.
     *
     * @throws RemoteException if a remote communication error occurs
     */
    public EchoServerImplementationRemote() throws RemoteException {
        super();
        clients = new HashMap<>();
    }

    /**
     * Registers a client with the echo server.
     *
     * @param clientName the name of the client
     * @param client the remote interface of the client
     * @throws RemoteException if a remote communication error occurs
     */
    @Override
    public void registerClient(String clientName, EchoClientRemoteInterface client) throws RemoteException {
        clients.put(clientName, client);
        System.out.println("Cliente registrado: " + clientName);
    }

    /**
     * Sends a message from one client to another.
     *
     * @param fromClient the name of the client sending the message
     * @param toClient the name of the client receiving the message
     * @param message the message content
     * @throws RemoteException if a remote communication error occurs
     */
    @Override
    public void sendMessage(String fromClient, String toClient, String message) throws RemoteException {
        EchoClientRemoteInterface client = clients.get(toClient);
        if (client != null) {
            client.receiveMessage(fromClient, message);
        } else {
            System.out.println("Cliente no encontrado: " + toClient);
        }
    }

    /**
     * The main method to start the EchoServerImplementationRemote.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Create the RMI registry on port 23000
            Registry registry = LocateRegistry.createRegistry(23000);
            EchoServerImplementationRemote server = new EchoServerImplementationRemote();
            // Bind the server instance to the registry with the name "echoServer"
            registry.rebind("echoServer", server);
            System.out.println("Echo server ready...");
        } catch (Exception e) {
            System.err.println("Echo server exception:");
            e.printStackTrace();
        }
    }
}

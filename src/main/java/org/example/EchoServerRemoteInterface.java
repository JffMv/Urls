package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The EchoServerRemoteInterface defines the remote interface for an echo server.
 * It includes methods for registering clients and sending messages between clients.
 */
public interface EchoServerRemoteInterface extends Remote {

    /**
     * Registers a client with the echo server.
     *
     * @param clientName the name of the client
     * @param client the remote interface of the client
     * @throws RemoteException if a remote communication error occurs
     */
    void registerClient(String clientName, EchoClientRemoteInterface client) throws RemoteException;

    /**
     * Sends a message from one client to another.
     *
     * @param fromClient the name of the client sending the message
     * @param toClient the name of the client receiving the message
     * @param message the message content
     * @throws RemoteException if a remote communication error occurs
     */
    void sendMessage(String fromClient, String toClient, String message) throws RemoteException;
}

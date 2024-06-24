package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The EchoClientRemoteInterface defines the remote interface for an echo client.
 * It provides a method for receiving messages from other clients.
 */
public interface EchoClientRemoteInterface extends Remote {

    /**
     * Receives a message from another client.
     *
     * @param fromClient the name of the client sending the message
     * @param message the message content
     * @throws RemoteException if a remote communication error occurs
     */
    void receiveMessage(String fromClient, String message) throws RemoteException;
}

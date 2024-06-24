package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchoServerRemoteInterface extends Remote {
    void registerClient(String clientName, EchoClientRemoteInterface client) throws RemoteException;
    void sendMessage(String fromClient, String toClient, String message) throws RemoteException;

}
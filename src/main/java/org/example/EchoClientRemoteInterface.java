package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchoClientRemoteInterface extends Remote {
    void receiveMessage(String fromClient, String message) throws RemoteException;
}
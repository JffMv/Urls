package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchoServerRemoteInterface extends Remote {
    public String echo(String chane) throws RemoteException;
}
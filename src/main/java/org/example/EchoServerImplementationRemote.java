package org.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class EchoServerImplementationRemote implements EchoServerRemoteInterface{

    public EchoServerImplementationRemote(String ipRMIregistry,
            int puertoRMIregistry, String nombreDePublicacion){

        try {
            EchoServerRemoteInterface echoServerRemote =
                    (EchoServerRemoteInterface) UnicastRemoteObject.exportObject(this,0);
            Registry registry = LocateRegistry.getRegistry(ipRMIregistry, puertoRMIregistry);
            registry.rebind(nombreDePublicacion, echoServerRemote);
            System.out.println("Echo server ready...");
            } catch (Exception e) {
            System.err.println("Echo server exception:");
            e.printStackTrace();
        }
        }
        public String echo(String cadena) throws RemoteException {
                return "desde el servidor: " + cadena;
                }
        public static void main(String[] args){
            EchoServerImplementationRemote ec = new EchoServerImplementationRemote("127.0.0.1", 23000, "echoServer");
                }
}
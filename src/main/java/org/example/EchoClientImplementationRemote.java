package org.example;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author dnielben
 */
        public class EchoClientImplementationRemote {
public void ejecutaServicio(String ipRmiregistry, int puertoRmiRegistry,
                               String nombreServicio) {
        try {
            Registry registry = LocateRegistry.getRegistry(ipRmiregistry, puertoRmiRegistry);
            EchoServerRemoteInterface echoServer = (EchoServerRemoteInterface) registry.lookup(nombreServicio);
            System.out.println(echoServer.echo("Hola como estas?"));
        } catch (Exception e) {
            System.err.println("Hay un problema:");
            e.printStackTrace();
            }
    }

    public static void main(String[] args){
        EchoClientImplementationRemote ec = new EchoClientImplementationRemote();
        ec.ejecutaServicio("127.0.0.1", 23000, "echoServer");
        }
}


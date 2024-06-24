package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The DatagramTimeServer class is responsible for receiving datagram packets from clients
 * and responding with the current date and time.
 */
public class DatagramTimeServer {
    DatagramSocket socket;

    /**
     * Constructor for DatagramTimeServer.
     * Initializes the DatagramSocket on port 4445.
     */
    public DatagramTimeServer() {
        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Starts the server to listen for incoming datagram packets and responds with the current date and time.
     */
    public void startServer() {
        byte[] buf = new byte[256];
        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String dString = new Date().toString();
                buf = dString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            }
        } catch (IOException ex) {
            Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            socket.close();
        }
    }

    /**
     * The main method to start the DatagramTimeServer.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        DatagramTimeServer ds = new DatagramTimeServer();
        ds.startServer();
    }
}

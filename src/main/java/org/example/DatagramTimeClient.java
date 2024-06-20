package org.example;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeClient {
    private static final int TIMEOUT = 4000;
    public static void main(String[] args) {
        byte[] sendBuf = new byte[256];
        String received = "";
        try {
            while (true) {
                DatagramSocket socket = new DatagramSocket();
                byte[] buf = new byte[256];
                InetAddress address = InetAddress.getByName("127.0.0.1");


                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);
                packet = new DatagramPacket(buf, buf.length);

                // Create Reception Thread
                DatagramPacket finalPacket = packet;
                Thread receiveThread = new Thread(() -> {
                    try {
                        socket.receive(finalPacket);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                });

                // Start the reception thread
                receiveThread.start();

                try {
                    // Wait the time, until the thread finish
                    receiveThread.join(TIMEOUT);
                } catch (InterruptedException e) {
                    Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, e);
                }

                if (receiveThread.isAlive()) {
                    // If the thread continue live means that time over.
                    receiveThread.interrupt(); // Interrupt the receiving thread
                    System.out.println("Date: " + received);
                } else {
                    // If the thread terminated, process the received packet.
                    received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Date: " + received);
                }
                Thread.sleep(5000);
            }

        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
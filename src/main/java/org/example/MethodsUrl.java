package org.example;
import java.net.*;
public class MethodsUrl {

        public static void getProtocol(URL url) {
            String protocol = url.getProtocol();
            System.out.println("Protocol: " + protocol);

        }

        public static void getAuthority(URL url) {
            String authority = url.getAuthority();
            System.out.println("Authority: " + authority);
        }

        public static void getHost(URL url) {
            String host = url.getHost();
            System.out.println("Host: " + host);
        }

        public static void getPort(URL url) {
            int port = url.getPort();
            System.out.println("Port: " + port);
        }

        public static void getPath(URL url) {
            String path = url.getPath();
            System.out.println("Path: " + path);
        }

        public static void getQuery(URL url) {
            String query = url.getQuery();
            System.out.println("Query: " + query);
        }

        public static void getFile(URL url) {
            String file = url.getFile();
            System.out.println("File: " + file);
        }

        public static void getRef(URL url) {
            String ref = url.getRef();
            System.out.println("Reference: " + ref);
        }

        public static void main(String[] args) {
            try {
                URL url = new URL("http://www.google.com/");

                getProtocol(url);
                getAuthority(url);
                getHost(url);
                getPort(url);
                getPath(url);
                getQuery(url);
                getFile(url);
                getRef(url);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }




}

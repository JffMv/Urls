package org.example;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class WriteHTML {


    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Write the url: ");

        String urlWrite = myObj.nextLine();

        URL url = new URL(urlWrite);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder htmlContent = new StringBuilder();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                htmlContent.append(inputLine);
                htmlContent.append("\n");
            }
            writeToFile("page.html", htmlContent.toString());
            System.out.println("Content save in page.html");
            reader.close();
        } catch (IOException e) {
            System.err.println("Error to read page: " + e.getMessage());
        }
    }

    private static void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error to write page: " + e.getMessage());
        }
    }

}


package com.masteryodo.laba2client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class LifeCycle {
    private static final int SERVER_PORT = 8181;
    
    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName("localhost");

        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, SERVER_PORT);
        try {
            System.out.println("socket = " + socket);
            String s1;
            Scanner sc = new Scanner(System.in);
            
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            do
            {
                System.out.println("enter command: ");
                s1 = sc.nextLine();
                out.println(s1);
                System.out.println(s1);
            }
            while (!s1.equals("exit"));
            out.println("disconnect"); // отправляем команду на закрытие сокета
        } finally {
            System.out.print("closing...");
            socket.close();
            System.out.println("closed!");
        }
    }
}

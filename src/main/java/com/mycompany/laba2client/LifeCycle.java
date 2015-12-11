
package com.mycompany.laba2client;

import com.mycompany.laba2client.view.ClientList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class LifeCycle {
    private static final int SERVER_PORT = 8181;
    
    public static void main(String[] args) throws IOException {
        System.out.println("Starting");
        InetAddress addr = InetAddress.getByName("localhost");
        System.out.println("addr = " + addr);
        Socket socket = null;
        
        // Пока что сюда поставлю вызов таблички клиентов
        ClientList frame = new ClientList();        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        try 
        {   socket = new Socket(addr, SERVER_PORT);
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
            }
            while (!s1.equals("end"));
        }
        catch (Exception e) 
        {
            System.out.println(e);        
        } 
        finally 
        {
            System.out.print("closing...");
            socket.close();
            System.out.println("closed!");
        }
    }
}

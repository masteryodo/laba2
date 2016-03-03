
package com.mycompany.laba2client;

import com.mycompany.laba2client.controller.ClientController;
import com.mycompany.laba2client.view.TestFrame;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import static com.mycompany.laba2client.Constants.*;
public class LifeCycle {
    
 
    
    public static void main(String[] args) throws IOException {
        System.out.println("Starting");
        InetAddress addr = InetAddress.getByName("localhost");
        System.out.println("addr = " + addr);
        final Socket socket = new Socket(addr, SERVER_PORT);
        final ClientController controller =  new ClientController(socket);
        controller.getUpdates();

        // Создаем GUI
         TestFrame frame1 = new TestFrame(controller);
         frame1.displayView();
         
        try 
        {
            System.out.println("socket = " + socket);
        }
        catch (Exception e) 
        {
            System.out.println(e);        
        }

    }
}

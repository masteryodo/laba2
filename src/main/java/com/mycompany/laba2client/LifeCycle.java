
package com.mycompany.laba2client;

import com.mycompany.laba2client.controller.ClientController;
import com.mycompany.laba2client.view.ClientList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LifeCycle {
    private static final int SERVER_PORT = 8181; //TODO Убрать в константы
 
    public static void main(String[] args) throws IOException {
        System.out.println("Starting");
        InetAddress addr = InetAddress.getByName("localhost");
        System.out.println("addr = " + addr);
        final Socket socket = new Socket(addr, SERVER_PORT);
        final ClientController controller =  new ClientController(socket);
        
        // Пока что сюда поставлю вызов таблички клиентов
        ClientList frame = new ClientList();        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closing..." + socket);
                controller.closeBOS();
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(LifeCycle.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });
        
        try 
        {
            System.out.println("socket = " + socket);
            controller.getCommands();
        }
        catch (Exception e) 
        {
            System.out.println(e);        
        }

    }
}

package com.mycompany.laba2client.controller;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientController {

    private Socket socket;
    private BufferedReader in ;
    private PrintWriter out ;
    private BufferedOutputStream bos;

    public ClientController(Socket socket) throws IOException
    {
        
        this.socket = socket;
        this.bos =  new BufferedOutputStream(socket.getOutputStream());
    }

    public void getCommands() throws IOException {
        sendFile();
        System.out.println("Файл отправлен");
    }

    public void sendFile() throws IOException {
        File file = new File("lastCommand.xml");
        FileInputStream fis = new FileInputStream(file);
        int in;
        byte[] buffer = new byte[1024];
        
        while ((in = fis.read()) != -1 ){
            bos.write(in);
        }
        
        bos.flush();
        fis.close();
    }
    public void closeBOS()
    {
        try {
            bos.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}

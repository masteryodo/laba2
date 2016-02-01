package com.mycompany.laba2client.controller;

import com.mycompany.laba2client.dto.Order;
import com.mycompany.laba2client.utils.XmlReaderWriter;
import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientController {

    private Socket socket;
    private BufferedReader in ;
    private PrintWriter out ;
    private BufferedOutputStream bos;
    private final String transactionFile;
    private final XmlReaderWriter xml = new XmlReaderWriter();
    private File file ;

    //---------------------------------------------------------------------------
    public ClientController(Socket socket) throws IOException
    {
        this.socket = socket;
        this.bos =  new BufferedOutputStream(socket.getOutputStream());
        this.transactionFile = "trans"+socket.getLocalPort()+".xml";
        this.file = new File(transactionFile);
    }
    //--------------------------------------------------------------------------

    public void sendCommands() throws IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        xml.orderEventToXml("remove", new Order(new Long("222"), new Long("333"), format.parse("25.10.2015") , 25.50), file);
        sendFile();
        System.out.println("Файл отправлен");
    }

    //--------------------------------------------------------------------------
    public void sendFile() throws IOException {
        
        FileInputStream fis = new FileInputStream(file);
        int in;
        byte[] buffer = new byte[1024];
        while ((in = fis.read()) != -1 ){
            bos.write(in);
        }
        bos.write(0);
        bos.flush();
        fis.close();
    }
    //--------------------------------------------------------------------------
    
    private void requestUpdate(){
        
    }
    
    //--------------------------------------------------------------------------
    public void closeBOS()
    {
        try {
            bos.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //--------------------------------------------------------------------------

    /**
     *  Удалет временный xml файл транзакций
     */
    public void purge() {
        file.delete();
    }

}

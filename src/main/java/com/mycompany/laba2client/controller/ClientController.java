package com.mycompany.laba2client.controller;

import com.mycompany.laba2client.dto.Client;
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
    private BufferedOutputStream bos;
    private BufferedInputStream bis;
    private final String transactionFile;
    private final XmlReaderWriter xml = new XmlReaderWriter();
    private File file ;

    //---------------------------------------------------------------------------
    public ClientController(Socket socket) throws IOException
    {
        this.socket = socket;
        this.bos =  new BufferedOutputStream(socket.getOutputStream());
        this.bis = new BufferedInputStream(socket.getInputStream());
        this.transactionFile = "trans"+socket.getLocalPort()+".xml";
        this.file = new File(transactionFile);
    }
    //--------------------------------------------------------------------------
    // TODO Удалить этот метод
    public void sendCommands() throws IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        xml.orderEventToXml("remove", new Order(new Long("222"), new Long("333"), format.parse("25.10.2015") , 25.50), file);
        sendFile();
        System.out.println("Файл отправлен");
        getUpdates();
    }
    //--------------------------------------------------------------------------

    public void sendClientUpdates(String event, Client client) throws IOException, ParseException {
        
        xml.clientEventToXml(event, client, file);
        sendFile();
        System.out.println("Обновление клиента отправлено");
        getUpdates();
    }
    //--------------------------------------------------------------------------

    public void sendOrderUpdates( String event, Order order) throws IOException, ParseException {

        xml.orderEventToXml(event, order, file);
        sendFile();
        System.out.println("Обновление ордера отправлено");
        getUpdates();
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
    
    public void getUpdates() throws FileNotFoundException, IOException{
        File file1 = new File("clients_db.xml");
        File file2 = new File("orders_db.xml");
        FileOutputStream fos = new FileOutputStream(file1);
        FileOutputStream fos2 = new FileOutputStream(file2);
        int in = 0;

        while ((in = bis.read()) > 0){
            fos.write(in);
        }
        fos.close();
        System.out.println("Клиенты получены");
        
        in = 0;
        while ((in = bis.read()) > 0){
            fos2.write(in);
        }
        fos2.close();
        System.out.println("Ордера получены");
        
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

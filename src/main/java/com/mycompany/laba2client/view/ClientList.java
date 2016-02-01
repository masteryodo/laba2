package com.mycompany.laba2client.view;

import com.mycompany.laba2client.dto.Client;
import com.mycompany.laba2client.utils.XmlReaderWriter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static com.mycompany.laba2client.Constants.*;
import java.awt.BorderLayout;
import java.util.HashSet;

public class ClientList extends JFrame {
    
    private JTable table;
    private XmlReaderWriter xml;
    //-------------------------------------------------
  
    private javax.swing.JButton jButton1 = new javax.swing.JButton();
    private javax.swing.JButton jButton2 = new javax.swing.JButton();
    private javax.swing.JButton jButton3 = new javax.swing.JButton();
    private javax.swing.JButton jButton4 = new javax.swing.JButton();
    private javax.swing.JButton jButton5 = new javax.swing.JButton();
    
 
    private java.awt.TextField textField1 = new java.awt.TextField();
        
        
        
    //---------------------------------------------------
    
    public ClientList() {
        xml = new XmlReaderWriter();
        setTitle("Client list");
        
            
        setSize(1000, 500);
        table = new JTable();
        table.setRowHeight(26);
        init();
        JScrollPane tablePane = new JScrollPane(table);
        add(tablePane);
        add(jButton1, BorderLayout.NORTH);
        add(jButton2, BorderLayout.EAST);
        add(jButton3, BorderLayout.EAST);
        add(jButton4, BorderLayout.EAST);
        add(jButton5, BorderLayout.EAST);
        add(textField1, BorderLayout.NORTH);
        setVisible(true);
    }
    
    
    private void init() {
        try {//TODO при старте получение актуальных данныхс сервера
            HashSet<Client> clients = xml.readClientsFromXml(CLIENTS_FILE);
            ClientsTableModel model = new ClientsTableModel(clients, xml);
            table.setModel(model);
            //System.out.println(clients);
            jButton1.setText("Найти!!!");
            jButton1.setSize(100,100);
            textField1.setText("textField1");
            jButton2.setText("Добавить");
            jButton3.setText("Удалить");
            jButton4.setText("Добавить ордер");
            jButton5.setText("Удалить ордер");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}

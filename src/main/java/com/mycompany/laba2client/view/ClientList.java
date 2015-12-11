package com.mycompany.laba2client.view;

import com.mycompany.laba2client.dto.Client;
import com.mycompany.laba2client.utils.XmlReaderWriter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static com.mycompany.laba2client.Constants.*;
import java.util.HashSet;

public class ClientList extends JFrame {
    
    private JTable table;
    private XmlReaderWriter xml;
    
    public ClientList() {
        xml = new XmlReaderWriter();
        setTitle("Client list");
        setSize(800, 300);
        table = new JTable();
        table.setRowHeight(26);
        init();
        JScrollPane tablePane = new JScrollPane(table);
        add(tablePane);
        setVisible(true);
    }
    
    private void init() {
        try {
            HashSet<Client> clients = xml.readClientsFromXml(CLIENTS_FILE);
            ClientsTableModel model = new ClientsTableModel(clients, xml);
            table.setModel(model);
            System.out.println(clients);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}

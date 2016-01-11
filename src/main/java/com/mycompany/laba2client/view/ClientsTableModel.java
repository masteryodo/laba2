package com.mycompany.laba2client.view;

import com.mycompany.laba2client.dto.Client;
import com.mycompany.laba2client.utils.XmlReaderWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ClientsTableModel implements TableModel {
    
    private final int NAME_COL = 1;
    private final int ADDR_COL = 2;
    private final int PHONE_COL = 3;
    private String[] columnNames = {"id", "name", "address", "phone"};
    private List<Client> clients;
    private XmlReaderWriter xml;
    
    public ClientsTableModel(HashSet<Client> clients, XmlReaderWriter xml) {
        this.clients = new ArrayList<>(clients);
        this.xml = xml;
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return getValueAt(0, i).getClass();
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Client client = clients.get(row);
        
        switch(col) {
//            case ID_COL:
//                return customer.getId();
            case NAME_COL:
                return client.getName();
            case PHONE_COL:
                return client.getPhone();
            case ADDR_COL:
                return client.getAddress();
            default:
                return client.getId();
        }
    }

    @Override
    public void setValueAt(Object o, int row, int col) {

        switch(col) {
            case NAME_COL:
                clients.get(row).setName((String) o); break;
            case ADDR_COL:
                clients.get(row).setAddress((String) o); break;
            case PHONE_COL:
                clients.get(row).setPhone((String) o); break;
            default: ;}
          //TODO sendToServer(clients.get(row));  
          xml.writeClientsToXml(new HashSet<Client>(clients));
        
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
        
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
        
    }
    
}

package com.mycompany.laba2client.view;

import com.mycompany.laba2client.controller.ClientController;
import com.mycompany.laba2client.dto.Client;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ClientsTableModel implements TableModel {
    
    
    private final int NAME_COL = 1;
    private final int ADDR_COL = 2;
    private final int PHONE_COL = 3;
    private String[] columnNames = {"id", "name", "address", "phone"};
    private List<Client> clients;
    private ClientController controller;
    private TestFrame frame;
    
    
    public ClientsTableModel(HashSet<Client> clients, ClientController controller, TestFrame frame) {
        this.clients = new ArrayList<>(clients);
        this.controller = controller;
        this.frame = frame;
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
          try {
            controller.sendClientUpdates("modify", clients.get(row));
            //frame.refreshClientsTableModel();
        } catch (Exception e) {
              System.out.println("setValueAt: " + e);
        }
          
        
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
        
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
        
    }
    
}

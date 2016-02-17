package com.mycompany.laba2client.view;

import com.mycompany.laba2client.controller.ClientController;
import com.mycompany.laba2client.dto.Order;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class OrdersTableModel implements TableModel {
    
    private final int ORDERID_COL = 0;
    private final int CLIENTID_COL = 1;
    private final int DATE_COL = 2;
    private final int SUM_COL = 3;
    private String[] columnNames = {"order id", "client id", "date", "sum"};
    private ClientController controller;
    private List<Order> orders;
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    
    public OrdersTableModel(HashSet<Order> orders, ClientController controller) {
        this.orders = new ArrayList<>(orders);
        this.controller = controller;
    }

    @Override
    public int getRowCount() {
        return orders.size();
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
        Order order = orders.get(row);
        
        switch(col) {
            case ORDERID_COL:
                return  order.getOrderId();
            case CLIENTID_COL:
                return order.getClientId();
            case DATE_COL:
                return format.format(order.getOrderDate());
            case SUM_COL:
                return order.getOrderSum();
            default: return "none";
        }
    }

    @Override
    public void setValueAt(Object o, int row, int col) {

        switch(col) {
            case CLIENTID_COL:
                orders.get(row).setClientId((Long) o); break;
                
            case DATE_COL:
                try { 
                    orders.get(row).setOrderDate((String) o);  
                } 
                catch (ParseException ex) {
                    Logger.getLogger(OrdersTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case SUM_COL:
                orders.get(row).setOrderSum((Double) o); break;
            default: ;}
        try {
            controller.sendOrderUpdates("modify", orders.get(row));
        } catch (Exception e) {
        }
        
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
        
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
        
    }
    
}

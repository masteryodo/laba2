
package com.mycompany.laba2client.utils;

import com.mycompany.laba2client.dto.Client;


public class MyComparator implements java.util.Comparator<Client>{

    @Override
    public int compare(Client c1, Client c2) {
        return c1.compareTo(c2);
    }

}

   

   

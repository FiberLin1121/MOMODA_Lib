/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.mmd.entity.Customer;
import uuu.mmd.entity.MMDException;
import uuu.mmd.entity.VIP;

/**
 *
 * @author Admin
 */
public class TestCustomerEquals {
    public static void main(String[] args) {
        try {
            Customer c1 = new Customer("orange01@gmail.com", "123456", "林橘比");
            Customer c2 = new Customer("orange01@gmail.com", "123456", "林橘比");
            
            System.out.println(c1==c2);
            System.out.println(c1.equals(c2));
            
            VIP vip = new VIP();
            vip.setEmail("orange01@gmail.com");
            vip.setName("林橘比");
            vip.setPassword("123456");
            System.out.println(vip==c1);
            System.out.println(c1.equals(vip));
        } catch (MMDException ex) {
            Logger.getLogger(TestCustomerEquals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

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
import uuu.mmd.model.CustomerService;

/**
 *
 * @author Admin
 */
public class TestCustomerServiceUpdate {
    public static void main(String[] args) {
        try {
            CustomerService service = new CustomerService();
            Customer c = service.login("orange06@gmail.com", "12345678");
            System.out.println("c = " + c);
            c.setPassword("123456");
            
            service.update(c);
            
            Customer c2 = service.login("orange06@gmail.com", "123456");
            System.out.println("c2:" + c2);            
            
        } catch (MMDException ex) {
            Logger.getLogger(TestCustomerServiceUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

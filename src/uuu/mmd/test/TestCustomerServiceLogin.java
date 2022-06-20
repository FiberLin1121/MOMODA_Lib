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
public class TestCustomerServiceLogin {

    public static void main(String[] args) {
        
        try {
            CustomerService service = new CustomerService();

            Customer c = service.login("orange01@gmail.com", "123456");
            System.out.println("c = " + c);
        } catch (MMDException ex) {
            Logger.getLogger(TestCustomerServiceLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

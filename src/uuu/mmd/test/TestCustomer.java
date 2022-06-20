/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.mmd.entity.Customer;
import uuu.mmd.entity.MMDException;

/**
 *
 * @author Admin
 */
public class TestCustomer {

    public static void main(String[] args) {
        try {
            Customer c1 = new Customer("林橘比","orange01@gmail.com","123456");
            c1.setBirthday(2000, 1,1);
            System.out.println(c1);
            
            Customer c2 = new Customer("林橘橘","orange02@gmail.com","123456");
            c2.setBirthday(2000,2,2);
            System.out.println(c2);
            
            Customer c3 = new Customer("林小葵","orange03@gmail.com","123456");
            c3.setBirthday(2000,3,3);
            System.out.println(c3);
            
            
        } catch (MMDException ex) {
            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, "客戶資料有誤", ex);
        } catch (Exception ex) {
            Logger.getLogger(TestCustomer.class.getName()).log(Level.SEVERE, "發生非預期錯誤", ex);
        }

    }

}

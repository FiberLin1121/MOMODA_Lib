/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.test;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.mmd.entity.Customer;
import uuu.mmd.entity.MMDException;
import uuu.mmd.entity.VIP;

/**
 *
 * @author Admin
 */
public class TestPolymorphism {

    public static void main(String[] args) {
        try {
            Object o = "Hello"; //polymorphism
//          System.out.println(o.length());  //因為o已經轉型成Object型別,就不能用原本實體物件的方法
            System.out.println(o.toString());

//          getClass():是抓實體物件真正的型別
            System.out.println(o.getClass().getName()); //java.lang.String

            o = LocalDate.now();  //polymorphism
            System.out.println(o.toString());
            System.out.println(o.getClass().getName());  //java.lang.LocalDate
            System.out.println(((LocalDate) o).getYear());  //getYear()不是Object定義的,要轉型回LocalDate型別,才能恢復其方法

            o = true;  //auto-boxing, polymorphism
            System.out.println(o.toString());
            System.out.println(o.getClass().getName());  //java.lang.Boolean
//          System.out.println((o.booleanValue());  //booleanValue()不是Object定義的,要轉型回boolean型別,才能恢復其方法

            o = new Integer(1);  //auto-boxing, polymorphism
            System.out.println(o.toString());
            System.out.println(o.getClass().getName());  //java.lang.Integer
            System.out.println(((Integer) o).intValue());

            Object o1 = new Customer("林橘比", "orange01@gmail.com", "132456");  //polymorphism
            System.out.println(o1.toString());
            System.out.println(o1.getClass().getName());  //uuu.vgb.entity.Customer
            System.out.println(((Customer) o1).getName());

//        Customer c = new VIP("林橘比","orange01@gmail.com", "132456");  //父類別的建構不不會被子類別繼承
            Customer c = new VIP();
            c.setName("林橘比");
            c.setEmail("orange01@gmail.com");
            c.setPassword("123456");
//         c.setDiscount(15);  //setDiscount()不是Customer定義的
            System.out.println(c);
//        System.out.println(c.toString());  //要預想c有可能是null的情況
        } catch (MMDException ex) {
            Logger.getLogger(TestPolymorphism.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

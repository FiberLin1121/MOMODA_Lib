/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.test;

import uuu.mmd.entity.Customer;
import uuu.mmd.entity.VIP;

/**
 *
 * @author Admin
 */
public class TestGetClass {
    public static void main(String[] args) {
        Object o = new Object();
        Customer c = new Customer();
        VIP v = new VIP();
        
        System.out.println(o.getClass() == Object.class);
        System.out.println(o.getClass() == Customer.class);
        System.out.println(o.getClass() == VIP.class);
        
//       System.out.println(c.getClass() == Object.class); //無法編譯
        System.out.println(c.getClass() == Customer.class);
        System.out.println(c.getClass() == VIP.class);
        
//        System.out.println(v.getClass() == Object.class);  //無法編譯
//        System.out.println(v.getClass() == Customer.class);  //無法編譯
        System.out.println(v.getClass() == VIP.class);
        
        Object o1 = null;
        Customer c1 =  null;
        VIP v1 =  null;
        System.out.println(o1.getClass() == Object.class);  //執行時期發生錯誤
        System.out.println(c1.getClass() == Customer.class);  //執行時期發生錯誤
        System.out.println(v1.getClass() == VIP.class);  //執行時期發生錯誤
        
    }
}

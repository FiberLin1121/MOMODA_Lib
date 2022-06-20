/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.test;

import uuu.mmd.entity.Customer;
import uuu.mmd.entity.Product;
import uuu.mmd.entity.VIP;

/**
 *
 * @author Admin
 */
public class TestInstanceof {
    public static void main(String[] args) {
        Object o = new Object();
        Customer c = new Customer();
        VIP v = new VIP();
        
        System.out.println(o instanceof Object);
        System.out.println(c instanceof Object);
        System.out.println(v instanceof Object);
        
        System.out.println(o instanceof Customer);
        System.out.println(c instanceof Customer);
        System.out.println(v instanceof Customer);
        
        System.out.println(o instanceof VIP);
        System.out.println(c instanceof VIP);
        System.out.println(v instanceof VIP);
        
        Object o1 = null;
        Customer c1 = null;
        VIP v1 = null;
        System.out.println(o1 instanceof Object);
        System.out.println(c1 instanceof Customer);
        System.out.println(v1 instanceof VIP);
//        System.out.println(v1 instanceof Product);  //無法編譯
        
    }
}

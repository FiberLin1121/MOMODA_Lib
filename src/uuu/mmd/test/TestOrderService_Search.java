/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.mmd.entity.MMDException;
import uuu.mmd.entity.Order;
import uuu.mmd.model.OrderService;

/**
 *
 * @author fiber
 */
public class TestOrderService_Search {
    public static void main(String[] args) {
        try {
            //用客戶email查詢歷史訂單
            OrderService service = new OrderService();
            List<Order> list = service.searchOrdersByCustomerEmail("orange01@gmail.com");
            System.out.println("list = " + list);
            
             //用訂單編號查詢歷史訂單
            Order order = service.searchOrderById("20");
            System.out.println("order = " + order);
        } catch (MMDException ex) {
            Logger.getLogger(TestOrderService_Search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.model;

import java.util.List;
import uuu.mmd.entity.MMDException;
import uuu.mmd.entity.Order;

/**
 *
 * @author fiber
 */
public class OrderService {
     //1.宣告private屬性
    private OrdersDAO dao = new OrdersDAO();

    //2.Insert Code -> Override Delegate Method
    //3.改方法名稱(sql命名方式 -> 前端商業邏輯命名方式)
    //4.改存取範圍(private -> public 因為要給外部前端存取)
    public void insert(Order order) throws MMDException {
        dao.insert(order);
    }

    public List<Order> searchOrdersByCustomerEmail(String customerEmail) throws MMDException {
        return dao.selectOrdersByCustomerEmail(customerEmail);
    }

    public Order searchOrderById(String id) throws MMDException {
        return dao.selectOrderById(id);
    }
    
    
}

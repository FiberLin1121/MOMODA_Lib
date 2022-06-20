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
import uuu.mmd.entity.Order;
import uuu.mmd.entity.PaymentType;
import uuu.mmd.entity.Product;
import uuu.mmd.entity.ShippingType;
import uuu.mmd.entity.ShoppingCart;
import uuu.mmd.model.CustomerService;
import uuu.mmd.model.ProductService;

/**
 *
 * @author fiber
 */
public class TestShoppingCart_TO_Order {
     public static void main(String[] args) {
        try {
            //1.登入會員
            CustomerService cService = new CustomerService();
            Customer c1 = cService.login("orange01@gmail.com", "123456");
            System.out.println("c1 = " + c1);
            
            //2.查詢產品
            ProductService pService = new ProductService();
            Product p1 = pService.searchProductById("AB7745");
            Product p2 = pService.searchProductById("AB9020");
            Product p3 = pService.searchProductById("AB9064");
            
            //3.購買產品
            ShoppingCart cart = new ShoppingCart();
            cart.setMember(c1);//登入會員
            //p1加入購物車
            cart.addToCart(p1, "深灰藍", "S", 1);
            System.out.println("cart = " + cart);
            //p2加入購物車
            cart.addToCart(p2, "粉", "M", 1);
            System.out.println("cart = " + cart);
            
            cart.addToCart(p3, "灰紫", "L", 1);
            System.out.println("cart = " + cart);
            
            //結帳: 把畫面上的資料抓下來建成 -> 訂單
            Order order = new Order();
            order.setMember(cart.getMember()); //會員從購物車來,不是從session(因為訂單看不到session,這裡是java類別,session在web專案)
            order.setPaymentType(PaymentType.ATM);
            order.setPaymentFee(PaymentType.ATM.getFee());
            order.setShippingType(ShippingType.SHOP);
            order.setShippingFee(ShippingType.SHOP.getFee());
            
            order.setRecipientName("林橘比");
            order.setRecipientEmail("orange01@gmail.com");
            order.setRecipientPhone("0987654321");
            order.setShippingAddress("台北市新生南路100號");
            
            order.add(cart);//給controller從畫面將購物車(CartItem, quantity)加入Order物件(的orderItem集合中)
            
            System.out.println("order = " + order);
            
            //insert to 資料庫
        } catch (MMDException ex) {
            Logger.getLogger(TestShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

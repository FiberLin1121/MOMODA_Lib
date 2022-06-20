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
import uuu.mmd.entity.Product;
import uuu.mmd.entity.ShoppingCart;
import uuu.mmd.model.CustomerService;
import uuu.mmd.model.ProductService;

/**
 *
 * @author fiber
 */
public class TestShoppingCart {

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
        } catch (MMDException ex) {
            Logger.getLogger(TestShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

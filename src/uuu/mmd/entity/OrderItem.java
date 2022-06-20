/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.entity;

import java.util.Objects;

/**
 *
 * @author fiber
 */
public class OrderItem { //訂單明細有(has)...
    
    //1.宣告為private屬性
    
    private int orderId; //訂單編號 :required, PKey
    private Product product; //訂購產品: required, PKey
    private String color; //顏色: required, PKey
    private String size; //尺寸: required, PKey
    private int quantity; //數量: required
    private double price; //交易價格: required
    
    
    //2.Insert Code -> getter/setter

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    //3.Insert Code -> Override toString
    //給程式人員用來測試entity的輸出文字模式

    @Override
    public String toString() {
        return  "訂單編號=" + orderId
                + ", 訂購產品=" + product 
                + ", 顏色=" + color
                + ", 尺寸=" + size 
                + ", 數量=" + quantity
                + ", 交易價格=" + price;
    }
    
    //4.Insert Code -> equals() and hashCode()

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.orderId;
        hash = 61 * hash + Objects.hashCode(this.product);
        hash = 61 * hash + Objects.hashCode(this.color);
        hash = 61 * hash + Objects.hashCode(this.size);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItem other = (OrderItem) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }
    
    
}

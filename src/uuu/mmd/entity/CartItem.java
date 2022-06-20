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
public class CartItem { //產品明細
    //1.宣告屬性為private(封裝)
    private Product product; //Key1 -> 產品明細有產品
    private String color; //Key2 -> 產品明細有顏色
    private String size; //Key3 -> 產品明細有尺寸
    
    //2.Insert Code -> getter/setter
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
    
    //3.Insert Code -> Override toString
    //給程式人員用來測試entity的輸出文字模式
    @Override
    public String toString() {
        return this.getClass().getName()
                + "產品=" + product
                + ", 顏色=" + color
                + ", 尺寸=" + size;
    }
    
    //4.Insert Code -> equals() and hashCode()
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.product);
        hash = 83 * hash + Objects.hashCode(this.color);
        hash = 83 * hash + Objects.hashCode(this.size);
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
        final CartItem other = (CartItem) obj;
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

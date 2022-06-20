/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.entity;

import java.nio.channels.Pipe;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author fiber
 */
public class ShoppingCart {
    //宣告NumberFormat物件
    public static final NumberFormat priceNumberFormat;   //= NumberFormat.getCurrencyInstance();//貨幣的標準格式化
    public static final NumberFormat amountNumberFormat; //= NumberFormat.getIntegerInstance();//整數的標準格式化
     static{
        priceNumberFormat = new DecimalFormat("#.##");
        priceNumberFormat.setMinimumFractionDigits(2);
        priceNumberFormat.setMaximumFractionDigits(2);
        
        amountNumberFormat = new DecimalFormat("#.##");
        amountNumberFormat.setMinimumFractionDigits(0);
        amountNumberFormat.setMaximumFractionDigits(0);        
    }
    //1.宣告為private屬性(封裝)
    private Customer member; //購物車有客戶
    private Map<CartItem, Integer> cart = new HashMap<>(); //購物車有產品明細集合(key=產品明細,value=購買數量)

    //2.Insert Code -> getter/setter
    public Customer getMember() {
        return member;
    }

    public void setMember(Customer member) {
        this.member = member;
    }

    //集合不能直接getter/setter
    //集合用mutator(s):取代setter  
    public void addToCart(Product p, String color, String size, int quantity) {
        if (p == null || quantity <= 0) {
            throw new IllegalArgumentException("加入購物車的產品不得為null,數量必須大於0");
        }
        if (color == null) {
            throw new IllegalArgumentException("加入購物車的產品顏色不得為null");
        }
        if (size == null) {
            throw new IllegalArgumentException("加入購物車的產品尺寸不得為null");
        }

        CartItem item = new CartItem();
        item.setProduct(p);
        item.setColor(color);
        item.setSize(size);
        Integer oldQuantity = cart.get(item);
        if (oldQuantity == null) {
            oldQuantity = 0;
        }
        cart.put(item, quantity + oldQuantity); //加進cart(購買明細,購買數量+舊購買數量)
    }

    public void update(CartItem item, int quantity) {
        if (cart.get(item) != null) { //如果cart中的有購買明細(購買數量)的話
            cart.put(item, quantity); //就修改
        }
    }

    public void remove(CartItem item) {
        if (cart.get(item) != null) { //如果cart中有產品明細(購買數量)的話
            cart.remove(item); //就刪除
        }
    }

    //集合用accessor(s): 取代getter
    //以下4支是cart代理 Map<K,V> 集合的方法 (Delegate Methods)
    public int size() { //取得cart中有幾項購買明細
        return cart.size();
    }

    public boolean isEmpty() { //檢查cart是否為空的
        return cart.isEmpty();
    }

    public Integer getQuantity(CartItem item) { //取得cart中該明細的購買數量
        return cart.get(item);
    }

    public Set<CartItem> getCartItemSet() { //取得 CartItem(key) 的Set集合
        return cart.keySet();
    }

    /*-------------------------------------------------------------------------*/
    public Double getProductAmount(CartItem item){ //取得單項產品的總金額
        double sum = item.getProduct().getUnitPrice() * this.getQuantity(item);
        return sum;
    }
    
    public int getTotalQuantity() { //總共購買數量(value的總和)
        int sum = 0;

        Collection<Integer> quantityValues = cart.values();//取得map集合所有value (返回此Map集合中所有value的collection集合)
        for (Integer quantity : quantityValues) {
            sum += quantity;
        }
        return sum;
    }

    public String getTotalAmount() { //計算總共購買金額
        double sum = 0;
        for (CartItem item : cart.keySet()) {
            double amount = item.getProduct().getUnitPrice() * this.getQuantity(item);
            sum += amount;
        }
        return amountNumberFormat.format(sum);
    }

    @Override
    public String toString() {
        return "會員=" + member + ", "
                + "購物車內容=" + cart
                + "共" + this.size() + "項" + this.getTotalQuantity() + "件, "
                + " 總金額:" + this.getTotalAmount();
    }

}

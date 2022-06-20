/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author fiber
 */
public class Order { //訂單有(has)...屬性

    //1.宣告為private屬性
    private int id; //訂單編號: PKey, auto-increment

    private Customer member; //會員: 非畫面傳來,是從session

    private LocalDate orderDate = LocalDate.now(); //訂單日期: required 非畫面傳來,是從database

    private LocalTime orderTime = LocalTime.now(); //訂單時間: required 非畫面傳來,是從database

    private PaymentType paymentType; //付款方式: required

    private ShippingType shippingType; //貨運方式: required

    private double paymentFee; //付款手續費: required

    private double shippingFee; //運費: required

    private String paymentNote; //付款備註: optional

    private String shippingNote; //貨運備註: optional

    private int status = 0; //0-新訂單,1-已付款,2-已入帳,3-已出貨,4-已送達,5-已簽收,6-已完成

    //集合不能直接getter/setter 要用mutators/accessors取代
    //宣告時就先建立成空集合(Early : 一開始就建起來 / Lazy Binding : 要用之前才建立)
    //宣告 final 避免集合被重複建立
    private Set<OrderItem> orderItemSet = new HashSet<OrderItem>(); //訂單明細(set集合)

    private String recipientName;  //required

    private String recipientEmail; //required

    private String recipientPhone; //required

    private String shippingAddress; //required

    //有明細從 Set<OrderItem> orderItem 加回來,沒明細才從資料庫讀回來
    //derived column 衍生欄位(是由其他欄位計算出來的,沒有setter方法,不會存在資料庫的欄位)
    private double totalAmount; //derived attribute

    //2.Insert Code -> getter/setter
    public int getId() {
        return id;
    }

    public String getFormatedId() {
        //整數資料格式化(可以加字串 %加一組補0 總長度 格式是d(整數))S
        //整數轉字串前面補0,補足到設定的長度
        //String.format可以做到格式化整形,字符串,日期等
        return String.format("MMD%08d", this.id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getMember() {
        return member;
    }

    public void setMember(Customer member) {
        this.member = member;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    //overload 用這支方法先將String型別的日期轉成LocalDate型別
    //就不用每次從資料庫撈出來日期存到物件前還要轉型
    public void setOrderDate(String date) {
        if (date != null) {
            this.setOrderDate(LocalDate.parse(date));
        }

    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public void setOrderTime(String time) {
        if (time != null) {
            this.setOrderTime(LocalTime.parse(time));
        }
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public ShippingType getShippingType() {
        return shippingType;
    }

    public void setShippingType(ShippingType shippingType) {
        this.shippingType = shippingType;
    }

    public double getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(double paymentFee) {
        this.paymentFee = paymentFee;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getPaymentNote() {
        return paymentNote;
    }

    public void setPaymentNote(String paymentNote) {
        this.paymentNote = paymentNote;
    }

    public String getShippingNote() {
        return shippingNote;
    }

    public void setShippingNote(String shippingNote) {
        this.shippingNote = shippingNote;
    }

    public int getStatus() { 
        return status;
    }

      public String getStatusString() {
        if(status>=0 && status<OrderStatus.values().length){
            return OrderStatus.values()[status].getDescription();
        }else{
            return String.valueOf(id);
        }
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getTotalAmount() {//derived attribute
        if (orderItemSet != null && orderItemSet.size() > 0) {
            double sum = 0;
            for (OrderItem item : orderItemSet) {
                sum += item.getPrice() * item.getQuantity();
            }
            return Math.round(sum);
        } else {
            return Math.round(totalAmount); //給歷史訂單用的
        }
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    //orderItemSet mutators(setter) : 2 add methods
    public void add(ShoppingCart cart) {//給controller從畫面將購物車(CartItem, quantity)加入Order物件的orderItem集合中
        for (CartItem cartItem : cart.getCartItemSet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setColor(cartItem.getColor());
            orderItem.setSize(cartItem.getSize());
            orderItem.setQuantity(cart.getQuantity(cartItem)); //數量在購物車
            orderItem.setPrice(cartItem.getProduct().getUnitPrice()); //價錢在產品明細的產品
            this.orderItemSet.add(orderItem); //最後加進訂單明細集合!
        }
    }

    public void add(OrderItem item) {//給dao從資料庫將訂單明細讀出並存入orderItem物件的屬性, 然後加入Order物件中
        this.orderItemSet.add(item);
    }

    //TODO: orderItemSet accessor(getter)
    public Set<OrderItem> getOrderItemSet() {
        //return orderItemSet;//回傳集合的參考，不宜
        return new HashSet<>(orderItemSet); //回傳集合的複本
    }

    public int getTotalQuantity() {
        int sum = 0;
        for (OrderItem item : orderItemSet) {
            sum += item.getQuantity();
        }
        return sum;
    }

    //3.Insert code -> ToString
    @Override
    public String toString() {
        return "訂單編號=" + id + ",\n"
                + ", 訂購人=" + member + ",\n"
                + ", 訂購日期=" + orderDate + ",\n"
                + ", 訂購時間=" + orderTime + ",\n"
                + ", 付款方式=" + paymentType + ",\n"
                + ", 貨運方式=" + shippingType + ",\n"
                + ", 付款手續費=" + paymentFee + ",\n"
                + ", 物流費=" + shippingFee + ",\n"
                + ", 付款備註=" + paymentNote + ",\n"
                + ", 貨運備註=" + shippingNote + ",\n"
                + ", 訂單狀態=" + status + ",\n"
                + ", 訂單明細=" + orderItemSet + ",\n"
                + ", 收件人=" + recipientName
                + ", " + recipientEmail
                + ", " + recipientPhone
                + ", " + shippingAddress
                + ", 總金額=" + this.getTotalAmount();
    }

    public enum OrderStatus {
        NEW("新訂單"), PAID("已通知付款"), POSTED("已入帳"), SHIPPED("已出貨"), ARRIVED("已送達"), CHECKED("已簽收"), COMPLETED("已完成");

        private final String description;

        private OrderStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

    }
}

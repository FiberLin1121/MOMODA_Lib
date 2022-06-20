/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.entity;

/**
 *
 * @author fiber
 */
public enum PaymentType {

    //1.宣告列舉值
    SHOP("門市付款", ShippingType.SHOP),
    ATM("ATM轉帳", new ShippingType[]{ShippingType.HOME, ShippingType.STORE}),//陣列:宣告時給值的寫法 new 陣列型別[]{}
    HOME("貨到付款", 50, ShippingType.HOME),
    STORE("超商付款", ShippingType.STORE);
//    CARD("信用卡付款", ShippingType.HOME, ShippingType.STORE);//因寫成參數列表,所以參數會自動被包裝成陣列後在傳遞

    //final 屬性: 該變數為常數 -> 宣告同時就要指派初值 (允許先宣告,在建構子給值)
    //變數在給值後,值不能再更改
    //所以沒有setter方法
    private final String description;
    private final double fee;
    private final ShippingType[] shippingTypeArray;//因為有兩個以上不確定有幾個,所以宣告成陣列

    //2.Insert Code -> Constructor 
    //建構子，列舉型別只能為 private
    private PaymentType(String description, ShippingType... shippingTypeArray) {//...是參數列表(可變動參數)
        this(description, 0, shippingTypeArray);//在建構子呼叫別支建構子來設定初值
//        this.fee = fee;
//        this.shippingTypeArray = shippingTypeArray;
    }

    private PaymentType(String description, double fee, ShippingType... shippingTypeArray) {
        this.description = description;
        this.fee = fee;
        this.shippingTypeArray = shippingTypeArray;
    }

    //3.Insert Code -> getter
    public String getDescription() {
        return description;
    }

    public double getFee() {
        return fee;
    }

    public ShippingType[] getShippingTypeArray() {
        return shippingTypeArray;
    }

    //4.Insert Code -> toString
    @Override
    public String toString() {
        return description + (fee > 0 ? "-" + fee + '元' : "");//錯誤寫法'-',因要寫成"-"才不會變成字元相加
    }

}

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
public enum ShippingType {
    //1.宣告列舉值
    SHOP("門市取貨", 0), HOME("送貨到府", 100), STORE("超商取貨", 65);

    //final 屬性: 該變數為常數了 -> 宣告同時就要指派初值 (允許先宣告,在建構子給值)
    //變數在給值後,值不能再更改
    //所以沒有setter方法
    private final String description;
    private final double fee;

    //2.Insert Code -> Constructor 
    //建構子，列舉型別只能為 private
    private ShippingType(String description, double fee) {
        this.description = description;
        this.fee = fee;
    }

    //3.Insert Code -> getter
    public String getDescription() {
        return description;
    }

    public double getFee() {
        return fee;
    }

    //4.Insert Code -> toString
    @Override
    public String toString() {
        return description + (fee > 0 ? "-" + fee + '元' : "");//-加上""而不是'':避免字元變成整數運算
    }

}

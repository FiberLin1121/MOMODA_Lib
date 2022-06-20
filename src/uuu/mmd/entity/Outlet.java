package uuu.mmd.entity;

import java.util.Set;

public class Outlet extends Product {

    private int discount;

    public Outlet() {
//        super(0,"", 0);
    }

    public Outlet(String id, String name, String type, double unitPrice, int discount) {
        super(id, name, type, unitPrice);
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDiscountString() {
        String discountStr = "";

        int data = 100 - this.discount;

        if (data % 10 == 0) {
            discountStr = data / 10 + "折";
        } else {
            discountStr = data + "折";
        }

        return discountStr;
    }

    /**
     *
     * @return
     */
    @Override
    public double getUnitPrice() { //查詢售價
        double price = super.getUnitPrice() * (100 - discount) / 100;

        return price;
    }

    public double getListPrice() { //查詢定價
        return super.getUnitPrice();
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\n折扣=" + discount + "% off"
                + ", 售價: " + this.getUnitPrice();
    }
}

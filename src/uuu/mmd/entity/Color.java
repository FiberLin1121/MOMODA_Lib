/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.entity;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Color {

    private String color_name;
    private String main_photoUrl;
    private String detail_photoUrl_1;
    private String detail_photoUrl_2;
    private String png_photoUrl;
    private String collocation_photoUrl;
    private String collocation_id;
    private String color_block;
    private String product_id;
    
    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color) {
        this.color_name = color;
    }

    public String getMain_photoUrl() {
        return main_photoUrl;
    }

    public void setMain_photoUrl(String main_photoUrl) {
        this.main_photoUrl = main_photoUrl;
    }

    public String getDetail_photoUrl_1() {
        return detail_photoUrl_1;
    }

    public void setDetail_photoUrl_1(String detail_photoUrl_1) {
        this.detail_photoUrl_1 = detail_photoUrl_1;
    }

    public String getDetail_photoUrl_2() {
        return detail_photoUrl_2;
    }

    public void setDetail_photoUrl_2(String detail_photoUrl_2) {
        this.detail_photoUrl_2 = detail_photoUrl_2;
    }

    public String getPng_photoUrl() {
        return png_photoUrl;
    }

    public void setPng_photoUrl(String png_photoUrl) {
        this.png_photoUrl = png_photoUrl;
    }

    public String getCollocation_photoUrl() {
        return collocation_photoUrl;
    }

    public void setCollocation_photoUrl(String collocation_photoUrl) {
        this.collocation_photoUrl = collocation_photoUrl;
    }

    public String getCollocation_id() {
        return collocation_id;
    }

    public void setCollocation_id(String collocation_id) {
        this.collocation_id = collocation_id;
    }

    public String getColor_block() {
        return color_block;
    }

    public void setColor_block(String color_block) {
        this.color_block = color_block;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    
    

    @Override
    public String toString() {
        return  "Color{"+",\n"
                + "color_name=" + color_name + ",\n"
                + "main_photoUrl=" + main_photoUrl + ",\n"
                + "detail_photoUrl_1=" + detail_photoUrl_1 + ",\n"
                + "detail_photoUrl_2=" + detail_photoUrl_2 + ",\n"
                + "png_photoUrl=" + png_photoUrl + ",\n"
                + "collocation_photoUrl=" + collocation_photoUrl
                + "collocation_id=" + collocation_id
                + "color_block=" + color_block
                + "product_id=" + product_id
                + '}'+"\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.color_name);
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
        final Color other = (Color) obj;
        if (!Objects.equals(this.color_name, other.color_name)) {
            return false;
        }
        return true;
    }

}

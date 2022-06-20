/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.test;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import uuu.mmd.entity.Color;
import uuu.mmd.entity.Product;
import uuu.mmd.entity.Size;

/**
 *
 * @author Admin
 */
public class TestProduct {
    public static void main(String[] args) {
        Product p = new Product("AB9161","荷葉領排釦雪紡上衣","上衣",459);
        Color color1 = new Color();
        color1.setColor_name("粉");
        p.addColor(color1);
        Color color2 = new Color();
        color2.setColor_name("藍灰");
        p.addColor(color2);
        
        p.addSizeList(Size.S);
        p.addSizeList(Size.M);
        p.addSizeList(Size.L);
        p.getSizeList();
        System.out.println(p.getSizeList());
        System.out.println(p);
//        List<Size> setSize = Size.values();
//        System.out.println("setSize = " + setSize);
//        Iterator<Size> it = setSize.iterator();
//        while(it.hasNext()){
//            System.out.println(it.next());
//        }
    }
}

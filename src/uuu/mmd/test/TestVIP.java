package uuu.mmd.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.mmd.entity.MMDException;
import uuu.mmd.entity.VIP;

public class TestVIP {

	public static void main(String[] args) {
            try {
                VIP v = new VIP();
                v.setEmail("orange01@gmail.com");
                System.out.println(v.getEmail());
                
                v.setName("林橘比");
                System.out.println(v.getName());
                
                v.setBirthday(2000,1,1);
                System.out.println(v.getBirthday());
                
                v.setDiscount(15);
                System.out.println(v.getDiscount());
                System.out.println(v.getDiscountString());
                System.out.println(v);
            } catch (MMDException ex) {
                Logger.getLogger(TestVIP.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}

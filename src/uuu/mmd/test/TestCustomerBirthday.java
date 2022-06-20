package uuu.mmd.test;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import uuu.mmd.entity.Customer;
import uuu.mmd.entity.MMDException;

public class TestCustomerBirthday {

    public static void main(String args[]) {

        try {
            Customer c = new Customer();
            c.setBirthday(1990, 10, 10);
            System.out.println(c.getBirthday());

            c.setBirthday("1990-11-31");
            System.out.println(c.getBirthday());

            c.setBirthday(-1, 12, 10);
            System.out.println(c.getBirthday());

//		c.setBirthday("");  //執行時期錯誤: java.time.format.DateTimeParseException
//		c.setBirthday("abcd-12-12");  //執行時期錯誤:  java.time.format.DateTimeParseException
            c.setBirthday("2020-12-12");
            System.out.println(c.getBirthday());

            c.setBirthday((String) null);
            System.out.println(c.getBirthday());

            c.setBirthday((LocalDate) null);
            System.out.println(c.getBirthday());

        } catch (MMDException ex) {
            Logger.getLogger(TestCustomerBirthday.class.getName()).log(Level.SEVERE, "客戶物件生日資料有誤", ex);
        }

    }
}

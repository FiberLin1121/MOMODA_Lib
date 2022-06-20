/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.model;

import uuu.mmd.entity.Customer;
import uuu.mmd.entity.MMDException;

/**
 *
 * @author Admin
 */
public class CustomerService {
    //1.宣告private屬性
    private CustomersDAO dao = new CustomersDAO();
    
    //2.Insert Code -> Override Delegate Method
    //3.改方法名稱(sql命名方式 -> 前端商業邏輯命名方式)
    //4.改存取範圍(private -> public 因為要給前端存取)
    public Customer login(String email, String password) throws MMDException {
        Customer c = dao.selectByEmail(email);
        System.out.println("c = " + c);
        if (c != null && password != null && password.equals(c.getPassword())) {//這裡不能用==,因為來源的記憶體位置不同(後端的資料庫和前端的瀏覽器記憶體)
            return c;
        } else {
            throw new MMDException("登入失敗,帳號或密碼不正確");
        }
    }

    public void register(Customer c) throws MMDException {
        dao.insert(c);
    }

    public void update(Customer c) throws MMDException {
        dao.update(c);
    }
}

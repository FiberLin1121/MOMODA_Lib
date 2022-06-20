/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.mmd.entity.Customer;
import uuu.mmd.entity.MMDException;
import uuu.mmd.entity.VIP;

/**
 *
 * @author Admin
 */
class CustomersDAO {//不宣告public(package-friendly),目的是只讓同套件的類別看得到

    // 這段宣告 + 載入Driver 已搬去RDBConnetion    
//    private static final String driver = "com.mysql.jdbc.Driver";
//    private static final String url = "jdbc:mysql://localhost:3306/mmd?zeroDateTimeBehavior=convertToNull";
//    private static final String userid = "root";
//    private static final String pwd = "10478978";
    private static final String SELECT_BY_EMAIL
            = "SELECT name,email,password,birthday" //整理時貼過來的指令要留意空白不要讓字串連再一起
            + " FROM customers"
            + " WHERE email=?";
    
    Customer selectByEmail(String email) throws MMDException {//因類別已不宣告public,所以這裡和以下方法前面加或不加public皆可
        Customer c = null;//查不到的預設值就是null(沒有)  查不到不能不給值(因為這裡是方法中的區域變數)

        //查詢:在資料庫的一筆資料讀出來之前,先建立客戶物件,再將查到的資料塞到客戶物件裡
        try (
                //1,2. 建立連線
                Connection connection = RDBConnection.getConnection();
                //3.準備指令物件(先傳入sql字串)
                PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_EMAIL);) {

            //3.1傳入?的值
            pstmt.setString(1, email);

            //4.執行指令
            try (ResultSet rs = pstmt.executeQuery();) {
                //5.處理rs
                while (rs.next()) {
                    c = new Customer();
                    c.setEmail(rs.getString("email"));
                    c.setName(rs.getString("name"));
                    c.setPassword(rs.getString("password"));
                    c.setBirthday(rs.getString("birthday"));
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomersDAO.class.getName()).log(Level.SEVERE, "無法查詢客戶-建立連線或執行指令失敗", ex);
            throw new MMDException("無法查詢客戶", ex);//使用者可以回報給管理人員的訊息
        }
        
        return c;
    }
    
    private static final String INSERT_CUSTOMER = "INSERT INTO customers"
            + "(name,email,password,birthday)"
            + "VALUES(?,?,?,?)";
    
    void insert(Customer c) throws MMDException {//因為沒有查詢,所以沒有回傳結果5.處理rs
        //這裡不用new建立客戶物件,因為資料是從畫面來

        if (c == null) {
            throw new IllegalArgumentException("新增客戶時Customer物件不得為null");
        }
        try (
                //1, 2. 取得連線
                Connection connection = RDBConnection.getConnection();
                //3. 準備INSERT指令(先傳入sql字串) 
                PreparedStatement pstmt = connection.prepareStatement(INSERT_CUSTOMER);) {
            //3.1 傳入?的值
            pstmt.setString(1, c.getName());
            pstmt.setString(2, c.getEmail());
            pstmt.setString(3, c.getPassword());
            pstmt.setString(4, c.getBirthday() != null ? String.valueOf(c.getBirthday()) : "");//Birthday不是null的話回傳轉字串的Birthday
            System.out.println("123");
            //4. 執行指令
            int rowCount = pstmt.executeUpdate();
            System.out.println("新增客戶成功rowCount = " + rowCount);
        } catch (SQLException ex) {
            throw new MMDException("新增客戶失敗", ex);
        }
    }
    
    private static final String UPDATE_CUSTOMER = "UPDATE customers "
            + "SET password=?,name=?,birthday=? "
            + "WHERE email=?";

    void update(Customer c) throws MMDException {//因為沒有查詢,所以沒有回傳結果5.處理rs
        //這裡不用new建立客戶物件,因為資料是從畫面來
        if (c == null) {
            throw new IllegalArgumentException("修改客戶時Customer物件不得為null");
        }
        try (
                //1, 2. 取得連線
                Connection connection = RDBConnection.getConnection();
                //3. 準備INSERT指令
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_CUSTOMER);) {
            pstmt.setString(1, c.getPassword());
            pstmt.setString(2, c.getName());
            pstmt.setString(3, c.getBirthday() != null ? String.valueOf(c.getBirthday()) : "");
            pstmt.setString(4, c.getEmail());
            
            //4.執行指令
            int rowCount = pstmt.executeUpdate();
            System.out.println("修改客戶成功rowCount = " + rowCount);
     
         } catch (SQLException ex) {
            throw new MMDException("修改客戶失敗", ex);
        }
    }
}

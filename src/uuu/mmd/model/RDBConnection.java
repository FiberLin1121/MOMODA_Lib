/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.mmd.entity.MMDException;

/**
 *
 * @author Admin
 */
public class RDBConnection {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/mmd?zeroDateTimeBehavior=convertToNull&characterEncoding=utf8"; //&characterEncoding=utf8
    private static final String userid = "root";
    private static final String pwd = "10478978";

    public static Connection getConnection() throws MMDException {
        try {
            //1.載入Driver
            Class.forName(driver);

            //2.建立連線
            try {//還要傳回去給呼叫RDBConnection.getConnection()方法的物件用,所以 2.建立連線 還不能關閉(不要放try()裡),由呼叫者完成處理後自行關閉
                Connection connection = DriverManager.getConnection(url, userid, pwd);
                return connection;
            } catch (SQLException ex) {
                Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, "建立連線失敗", ex);
                throw new MMDException("建立連線失敗", ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, "載入Driver失敗", ex);
            throw new MMDException("載入Driver失敗", ex);
        }
    }
}

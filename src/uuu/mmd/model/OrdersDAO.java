/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.mmd.entity.Customer;
import uuu.mmd.entity.MMDException;
import uuu.mmd.entity.Order;
import uuu.mmd.entity.OrderItem;
import uuu.mmd.entity.PaymentType;
import uuu.mmd.entity.Product;
import uuu.mmd.entity.ShippingType;
import uuu.mmd.entity.StockShortageException;

/**
 *
 * @author fiber
 */
public class OrdersDAO {

    private static final String UPDATE_PRDUCT_STOCK = "UPDATE products SET stock = stock-? "
            + "WHERE id=? AND stock>=?";
    private static final String UPDATE_PRDUCT_COLOR_SIZE_STOCK = "UPDATE product_color_size_stock SET stock = stock-? "
            + "WHERE product_id=? AND stock>=? AND color_name=? AND size=?;";
    private static final String INSERT_ORDER = "INSERT INTO orders "
            + "(id,customer_email,order_date,order_time,payment_type,payment_fee, "
            + "shipping_type,shipping_fee,recipient_name,recipient_email,recipient_phone,shipping_address) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String INSERT_ORDER_ITEM = "INSERT INTO order_items "
            + "(order_id,product_id,color,size,price,quantity)"
            + "VALUES(?,?,?,?,?,?);";

    void insert(Order order) throws MMDException {
        if (order == null) {
            throw new IllegalArgumentException("建立訂單時訂單資料不得為null"); //通知程式人員傳的參數有錯
        }
        try (
                Connection connetion = RDBConnection.getConnection(); //1.2 建立連線
                //當在MySQL使用AUTO_INCREMENT產生索引的值(自動給號)
                //需要加入Statement.RETURN_GENERATED_KEYS參數,執行getGeneratedKeys()
                //來取得新增的索引值
                PreparedStatement stockPstmt = connetion.prepareStatement(UPDATE_PRDUCT_STOCK);
                PreparedStatement colorSizeStockPstmt = connetion.prepareStatement(UPDATE_PRDUCT_COLOR_SIZE_STOCK);
                PreparedStatement pstmt1 = connetion.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS); //3.準備指令pstmt1
                PreparedStatement pstmt2 = connetion.prepareStatement(INSERT_ORDER_ITEM); //3.準備指令pstmt2
            ) {
            connetion.setAutoCommit(false);//關掉AutoCommit(自動確認交易) 等於SQL的Begin Transaction 開始交易
            try {
                //0.處理庫存
                for(OrderItem item:order.getOrderItemSet()){
                    PreparedStatement pstmt0 = colorSizeStockPstmt;
                    
                    pstmt0.setInt(1, item.getQuantity());
                    pstmt0.setString(2, item.getProduct().getId());
                    pstmt0.setInt(3, item.getQuantity());
                    
                    String color =item.getColor();
                    if(color!=null && color.length()>0){
                        pstmt0 = colorSizeStockPstmt;
                        pstmt0.setString(4, color);
                    }
                    String size = item.getSize();
                    if(size!=null && size.length()>0){
                        pstmt0 = colorSizeStockPstmt;
                        pstmt0.setString(5, size);
                    }
                    int rows = pstmt0.executeUpdate();
                    if(rows==0){
                        throw new StockShortageException(item.getProduct());
                    }
                }
                
                //3.1傳入?的值給pstmt1
                pstmt1.setInt(1, order.getId()); //id的值給0
                pstmt1.setString(2, order.getMember().getEmail());
                pstmt1.setString(3, order.getOrderDate().toString()); //toString():物件轉字串
                pstmt1.setString(4, order.getOrderTime().toString());
                pstmt1.setString(5, order.getPaymentType().name()); //name():返回列舉常數的名稱
                pstmt1.setDouble(6, order.getPaymentType().getFee());
                pstmt1.setString(7, order.getShippingType().name());
                pstmt1.setDouble(8, order.getShippingFee());
                pstmt1.setString(9, order.getRecipientName());
                pstmt1.setString(10, order.getRecipientEmail());
                pstmt1.setString(11, order.getRecipientPhone());
                pstmt1.setString(12, order.getShippingAddress());

                //4.執行指令pstmt1
                pstmt1.executeUpdate();

                //5.處理rs
                try (ResultSet rs = pstmt1.getGeneratedKeys()) { //先抓到自動給號的值
                    while (rs.next()) {

                        order.setId(rs.getInt(1)); //把讀出來的第一個(自動給號的訂單編號)塞進order物件
                    }
                }

                //新增OrderItem
                for (OrderItem item : order.getOrderItemSet()) {
                    //3.1 傳入?的值給pstmt2
                    pstmt2.setInt(1, order.getId()); //抓order自動給號的編號 //不能直接用 int id = rs.getInt(1);的 id ,因為那是區域變數
                    pstmt2.setString(2, item.getProduct().getId());
                    pstmt2.setString(3, item.getColor());
                    pstmt2.setString(4, item.getSize());
                    pstmt2.setDouble(5, item.getPrice());
                    pstmt2.setInt(6, item.getQuantity());

                    //4.執行指令pstmt2
                    pstmt2.executeUpdate();
                }
                connetion.commit(); //確認交易
            } catch (SQLException ex) {
                connetion.rollback();
                throw ex; //把錯拋回到 -> catch (SQLException ex)
            } finally {
                //如果是在Connection Pool (共用連線)就要做finally{}這段
                //如果connection在程式使用完後就Close的話，就不需要做finally{}這段
                connetion.setAutoCommit(true); ////再把AutoCommit(自動確認交易)打開
            }

        } catch (SQLException ex) {
            throw new MMDException("建立訂單失敗", ex);
        }
    }

    private static final String SELECT_ORDERS_BY_CUSTOMERS_EMAIL = "SELECT id, order_id, customer_email, "
            + "order_date, order_time, payment_type, payment_fee, shipping_type, shipping_fee, "
            + "SUM(price*quantity) as total_amount "
            + "FROM orders LEFT JOIN order_items ON orders.id = order_items.order_id "
            + "WHERE customer_email=? "
            + "GROUP BY orders.id "
            + "ORDER BY order_date desc, order_time desc";

    List<Order> selectOrdersByCustomerEmail(String customerEmail) throws MMDException {

        List<Order> list = new ArrayList<>();

        try (
                Connection connetion = RDBConnection.getConnection();
                PreparedStatement pstmt = connetion.prepareStatement(SELECT_ORDERS_BY_CUSTOMERS_EMAIL);) {

            pstmt.setString(1, customerEmail);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));

                    Customer c = new Customer();
                    c.setEmail(rs.getString("customer_email"));
                    order.setMember(c);

                    order.setOrderDate(rs.getString("order_date"));
                    order.setOrderTime(rs.getString("order_time"));

                    //order.setPaymentType(PaymentType.valueOf(rs.getString("payment_type")));
                    String pType = rs.getString("payment_type");
                    order.setPaymentType(PaymentType.valueOf(pType));
                    String shType = rs.getString("shipping_type");
                    order.setShippingType(ShippingType.valueOf(shType));

                    order.setPaymentFee(rs.getDouble("payment_fee"));
                    order.setShippingFee(rs.getDouble("shipping_fee"));
                    order.setTotalAmount(rs.getDouble("total_amount"));

                    list.add(order); //最後加進立歷史訂單list
                }
            }

        } catch (SQLException ex) {
            throw new MMDException("查詢客戶歷史訂單發生錯誤", ex);
        }
        return list;
    }

    private static final String SELECT_ORDER_BY_ID = "SELECT orders.id as oid, "
            + "customers.email as cemail, customers.name as cname, order_date, order_time,status, "
            + "payment_type,payment_fee,payment_note, "
            + "shipping_type,shipping_fee,shipping_note,"
            + "recipient_name,recipient_email,recipient_phone,shipping_address,"
            + "product_id,products.name as pname, color,order_items.size as osize,price,quantity "
            + "FROM orders LEFT JOIN order_items ON orders.id = order_items.order_id "
            + "LEFT JOIN customers ON orders.customer_email = customers.email "
            + "LEFT JOIN products ON order_items.product_id = products.id "
            + "WHERE orders.id=?";

    Order selectOrderById(String id) throws MMDException {
        Order order = null;
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ORDER_BY_ID); //3.準備指令
                ) {
            //3.1 傳入?的值
            pstmt.setString(1, id);
            //4.執行指令
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    //5.處理rs
                    if (order == null) {
                        order = new Order();
                        order.setId(rs.getInt("oid"));

                        Customer c = new Customer();
                        c.setEmail(rs.getString("cemail"));
                        c.setName(rs.getString("cname"));
                        order.setMember(c);
                        order.setOrderDate(rs.getString("order_date"));
                        order.setOrderTime(rs.getString("order_time"));
                        order.setStatus(rs.getInt("status"));

                        String pType = rs.getString("payment_type");
                        order.setPaymentType(PaymentType.valueOf(pType));
                        String shType = rs.getString("shipping_type");
                        order.setShippingType(ShippingType.valueOf(shType));

                        order.setPaymentFee(rs.getDouble("payment_fee"));
                        order.setShippingFee(rs.getDouble("shipping_fee"));

                        order.setPaymentNote(rs.getString("payment_note"));
                        order.setShippingNote(rs.getString("shipping_note"));

                        order.setRecipientName(rs.getString("recipient_name"));
                        order.setRecipientEmail(rs.getString("recipient_email"));
                        order.setRecipientPhone(rs.getString("recipient_phone"));
                        order.setShippingAddress(rs.getString("shipping_address"));
                    }

                    OrderItem item = new OrderItem();
                    item.setOrderId(order.getId());

                    Product p = new Product();
                    p.setId(rs.getString("product_id"));
                    p.setName(rs.getString("pname"));
                    item.setProduct(p);
                    item.setColor(rs.getString("color"));
                    item.setSize(rs.getString("osize"));
                    item.setPrice(rs.getDouble("price"));
                    item.setQuantity(rs.getInt("quantity"));
                    order.add(item);
                }
            }
        } catch (SQLException ex) {
            throw new MMDException("查詢客戶歷史訂單發生錯誤", ex);
        }

        return order;

    }
}

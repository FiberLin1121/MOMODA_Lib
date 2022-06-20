package uuu.mmd.model;

import java.util.ArrayList;
import java.util.List;

import uuu.mmd.entity.Outlet;
import uuu.mmd.entity.Product;
import uuu.mmd.entity.MMDException;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.mmd.entity.Color;
import uuu.mmd.entity.Size;

class ProductsDAO {

    //GROUP BY 分組後只取第一筆
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products GROUP BY products.id";

    List<Product> selectAllProducts() throws MMDException {
        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_PRODUCTS); //3.準備指令
                ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                ) {
            //5.處理rs
            while (rs.next()) {
                Product p;

                String className = rs.getString("class_name");
                if (className.equals("Outlet")) {
                    p = new Outlet();
                } else {
                    p = new Product();
                }

                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setType(rs.getString("type"));

                String sizes = rs.getString("size");
                if (sizes != null && sizes.length() > 0) {
                    String[] sizeArray = sizes.split(",");
                    for (String s : sizeArray) {
                        Size size = Size.valueOf(s);
                        p.addSizeList(size);
                    }
                }
                p.setLaunch_date(rs.getString("launch_date"));
                p.setUnitPrice(rs.getDouble("unitPrice"));
                p.setStock(rs.getInt("stock"));
                p.setDescription(rs.getString("description"));
                p.setOutfit(rs.getString("outfit"));
                p.setActivity(rs.getString("activity"));

                if (p instanceof Outlet) {
                    ((Outlet) p).setDiscount(rs.getInt("discount"));
                }

                Color color = new Color();
                color.setColor_name(rs.getString("color_name"));
                color.setMain_photoUrl(rs.getString("main_photoUrl"));
                color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                color.setPng_photoUrl(rs.getString("png_photoUrl"));
                color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                color.setCollocation_id(rs.getString("collocation_id"));
                color.setColor_block(rs.getString("color_block"));

                p.addColor(color);

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("查詢全部產品失敗", e);
        }

        return list;
    }

    private static final String SELECT_PRODUCTS_BY_NAME = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products"
            + " WHERE name LIKE ? GROUP BY products.id";

    List<Product> selectProductsByName(String search) throws MMDException {
        List<Product> list = new ArrayList<>(); //集合沒找到是0個 list.size = 0 ,不是null ,所以一開始宣告可先建立集合(慣用寫法)
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCTS_BY_NAME); //3.準備指令
                ) {

            pstmt.setString(1, '%' + search + '%'); //3.1 傳入?的值
            try (
                    ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                    ) {
                //5.處理rs
                while (rs.next()) {
                    Product p;

                    String className = rs.getString("class_name");
                    if (className.equals("Outlet")) {
                        p = new Outlet();
                    } else {
                        p = new Product();
                    }

                    p.setId(rs.getString("id"));
                    p.setName(rs.getString("name"));
                    p.setType(rs.getString("type"));

                    String sizes = rs.getString("size");
                    if (sizes != null && sizes.length() > 0) {
                        String[] sizeArray = sizes.split(",");
                        for (String s : sizeArray) {
                            Size size = Size.valueOf(s);
                            p.addSizeList(size);
                        }
                    }
                    p.setLaunch_date(rs.getString("launch_date"));
                    p.setUnitPrice(rs.getDouble("unitPrice"));
                    p.setStock(rs.getInt("stock"));
                    p.setDescription(rs.getString("description"));
                    p.setOutfit(rs.getString("outfit"));
                    p.setActivity(rs.getString("activity"));
                    if (p instanceof Outlet) {
                        ((Outlet) p).setDiscount(rs.getInt("discount"));
                    }

                    Color color = new Color();
                    color.setColor_name(rs.getString("color_name"));
                    color.setMain_photoUrl(rs.getString("main_photoUrl"));
                    color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                    color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                    color.setPng_photoUrl(rs.getString("png_photoUrl"));
                    color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                    color.setCollocation_id(rs.getString("collocation_id"));
                    color.setColor_block(rs.getString("color_block"));

                    p.addColor(color);

                    list.add(p);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("用部分名稱查詢產品失敗", e);
        }
        return list;
    }

    private static final String SELECT_PEODUCT_BY_ID = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products"
            + " WHERE id =?";

    Product selectProductById(String id) throws MMDException {
        //用主鍵匙 相等 最多只會查到一個(所以product不加s)
        Product p = null;
        try (
                Connection connection = RDBConnection.getConnection();// 1.2.取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PEODUCT_BY_ID); //2.準備指令
                ) {
            //3.1 傳入?的值
            pstmt.setString(1, id);
            try (
                    ResultSet rs = pstmt.executeQuery(); //4.執行指令
                    ) {
                // 5.處理rs
                while (rs.next()) {
                    String className = rs.getString("class_name");

                    //product只會建1筆,color會建成n筆,然後加進Map集合
                    //特殊寫法:if (p == null) 當p還沒建立時才會建立,所以只會建1筆
                    if (p == null) {
                        if ("Outlet".equals(className)) { //特殊寫法:字串放前面可以少寫null檢查,因為"Outlet"不會是null
                            p = new Outlet();
                        } else {
                            p = new Product();
                        }
                        p.setId(rs.getString("id"));
                        p.setName(rs.getString("name"));
                        p.setType(rs.getString("type"));

                        String sizes = rs.getString("size");
                        if (sizes != null && sizes.length() > 0) {
                            String[] sizeArray = sizes.split(",");
                            for (String s : sizeArray) {
                                Size size = Size.valueOf(s);
                                p.addSizeList(size);
                            }
                        }
                        p.setLaunch_date(rs.getString("launch_date"));
                        p.setUnitPrice(rs.getDouble("unitPrice"));
                        p.setStock(rs.getInt("stock"));
                        p.setDescription(rs.getString("description"));
                        p.setOutfit(rs.getString("outfit"));
                        p.setActivity(rs.getString("activity"));
                        if (p instanceof Outlet) {
                            ((Outlet) p).setDiscount(rs.getInt("discount"));
                        }
                    }
                    Color color = new Color();
                    color.setColor_name(rs.getString("color_name"));
                    color.setMain_photoUrl(rs.getString("main_photoUrl"));
                    color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                    color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                    color.setPng_photoUrl(rs.getString("png_photoUrl"));
                    color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                    color.setCollocation_id(rs.getString("collocation_id"));
                    color.setColor_block(rs.getString("color_block"));

                    p.addColor(color); //把color物件加進產品p
                }
            }
        } catch (SQLException ex) {
            throw new MMDException("用產品編號查詢產品失敗", ex); //錯誤訊息要清楚到一看就知道是哪支段程式出錯
        }
        return p;
    }

    private static final String SELECT_PEODUCT_PHOTO_BY_ID = "SELECT main_photoUrl,detail_photoUrl_1,detail_photoUrl_2,png_photoUrl,collocation_photoUrl FROM products INNER JOIN colors ON products.id=colors.fkey_2_products"
            + " WHERE id=? and color_name=?";

    String selectPhotoByIdandColor(String id, String color, int photoId) throws MMDException {
        String photo_url = "";
        try (
                Connection connection = RDBConnection.getConnection();// 1.2.取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PEODUCT_PHOTO_BY_ID); //2.準備指令
                ) {
            //3.1 傳入?的值
            //pstmt.setString(1, "detail_photoUrl_" + photoId);
            pstmt.setString(1, id);
            pstmt.setString(2, color);
            try (
                    ResultSet rs = pstmt.executeQuery(); //4.執行指令
                    ) {
                // 5.處理rs
                while (rs.next()) {
                    switch (photoId) {
                        case 1:
                            photo_url = rs.getString("main_photoUrl");
                            break;
                        case 2:
                        case 3:
                            photo_url = rs.getString("detail_photoUrl_" + (photoId - 1));
                            break;
                        case 4:
                            photo_url = rs.getString("png_photoUrl");
                            break;
                        case 5:
                            photo_url = rs.getString("collocation_photoUrl");
                    }
                }
            }
        } catch (SQLException ex) {
            throw new MMDException("查詢產品照片失敗", ex); //錯誤訊息要清楚到一看就知道是哪支段程式出錯
        }
        return photo_url;

    }

    private static final String SELECT_PEODUCT_BY_TYPE = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products "
            + "WHERE type=? GROUP BY products.id";

    List<Product> selectProductByType(String type) throws MMDException {

        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PEODUCT_BY_TYPE); //3.準備指令
                ) {

            pstmt.setString(1, type); //3.1 傳入?的值
            try (
                    ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                    ) {
                //5.處理rs
                while (rs.next()) {
                    Product p;

                    String className = rs.getString("class_name");
                    if (className.equals("Outlet")) {
                        p = new Outlet();
                    } else {
                        p = new Product();
                    }

                    p.setId(rs.getString("id"));
                    p.setName(rs.getString("name"));
                    p.setType(rs.getString("type"));

                    String sizes = rs.getString("size");
                    if (sizes != null && sizes.length() > 0) {
                        String[] sizeArray = sizes.split(",");
                        for (String s : sizeArray) {
                            Size size = Size.valueOf(s);
                            p.addSizeList(size);
                        }
                    }
                    p.setLaunch_date(rs.getString("launch_date"));
                    p.setUnitPrice(rs.getDouble("unitPrice"));
                    p.setStock(rs.getInt("stock"));
                    p.setDescription(rs.getString("description"));
                    p.setOutfit(rs.getString("outfit"));
                    p.setActivity(rs.getString("activity"));
                    if (p instanceof Outlet) {
                        ((Outlet) p).setDiscount(rs.getInt("discount"));
                    }

                    Color color = new Color();
                    color.setColor_name(rs.getString("color_name"));
                    color.setMain_photoUrl(rs.getString("main_photoUrl"));
                    color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                    color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                    color.setPng_photoUrl(rs.getString("png_photoUrl"));
                    color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                    color.setCollocation_id(rs.getString("collocation_id"));
                    color.setColor_block(rs.getString("color_block"));

                    p.addColor(color);

                    list.add(p);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("用分類查詢產品失敗", e);
        }
        return list;
    }

    private static final String SELECT_PEODUCT_BY_OUTFIT = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products "
            + "WHERE outfit=? GROUP BY products.id";

    List<Product> selectProductByOutfit(String outfit) throws MMDException {

        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PEODUCT_BY_OUTFIT); //3.準備指令
                ) {

            pstmt.setString(1, outfit); //3.1 傳入?的值
            try (
                    ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                    ) {
                //5.處理rs
                while (rs.next()) {
                    Product p;

                    String className = rs.getString("class_name");
                    if (className.equals("Outlet")) {
                        p = new Outlet();
                    } else {
                        p = new Product();
                    }

                    p.setId(rs.getString("id"));
                    p.setName(rs.getString("name"));
                    p.setType(rs.getString("type"));

                    String sizes = rs.getString("size");
                    if (sizes != null && sizes.length() > 0) {
                        String[] sizeArray = sizes.split(",");
                        for (String s : sizeArray) {
                            Size size = Size.valueOf(s);
                            p.addSizeList(size);
                        }
                    }
                    p.setLaunch_date(rs.getString("launch_date"));
                    p.setUnitPrice(rs.getDouble("unitPrice"));
                    p.setStock(rs.getInt("stock"));
                    p.setDescription(rs.getString("description"));
                    p.setOutfit(rs.getString("outfit"));
                    p.setActivity(rs.getString("activity"));
                    if (p instanceof Outlet) {
                        ((Outlet) p).setDiscount(rs.getInt("discount"));
                    }

                    Color color = new Color();
                    color.setColor_name(rs.getString("color_name"));
                    color.setMain_photoUrl(rs.getString("main_photoUrl"));
                    color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                    color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                    color.setPng_photoUrl(rs.getString("png_photoUrl"));
                    color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                    color.setCollocation_id(rs.getString("collocation_id"));
                    color.setColor_block(rs.getString("color_block"));

                    p.addColor(color);

                    list.add(p);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("用穿搭主題查詢產品失敗", e);
        }
        return list;
    }

    private static final String SELECT_ALL_PEODUCTS_ORDER_BY_UNITPRICE = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products GROUP BY products.id,unitPrice ORDER BY unitPrice*(100-discount)/100;";

    List<Product> selectAllProductsOrderByUnitPrice() throws MMDException {

        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_PEODUCTS_ORDER_BY_UNITPRICE); //3.準備指令
                ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                ) {
            //5.處理rs
            while (rs.next()) {
                Product p;

                String className = rs.getString("class_name");
                if (className.equals("Outlet")) {
                    p = new Outlet();
                } else {
                    p = new Product();
                }

                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setType(rs.getString("type"));

                String sizes = rs.getString("size");
                if (sizes != null && sizes.length() > 0) {
                    String[] sizeArray = sizes.split(",");
                    for (String s : sizeArray) {
                        Size size = Size.valueOf(s);
                        p.addSizeList(size);
                    }
                }
                p.setLaunch_date(rs.getString("launch_date"));
                p.setUnitPrice(rs.getDouble("unitPrice"));
                p.setStock(rs.getInt("stock"));
                p.setDescription(rs.getString("description"));
                p.setOutfit(rs.getString("outfit"));
                p.setActivity(rs.getString("activity"));

                if (p instanceof Outlet) {
                    ((Outlet) p).setDiscount(rs.getInt("discount"));
                }

                Color color = new Color();
                color.setColor_name(rs.getString("color_name"));
                color.setMain_photoUrl(rs.getString("main_photoUrl"));
                color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                color.setPng_photoUrl(rs.getString("png_photoUrl"));
                color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                color.setCollocation_id(rs.getString("collocation_id"));
                color.setColor_block(rs.getString("color_block"));

                p.addColor(color);

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("用價錢低到高查詢全部產品失敗", e);
        }

        return list;
    }

    private static final String SELECT_ALL_PEODUCTS_ORDER_BY_UNITPRICE_DESC = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products GROUP BY products.id,unitPrice ORDER BY unitPrice*(100-discount)/100 DESC;";

    List<Product> selectAllProductsOrderByUnitPriceDesc() throws MMDException {

        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_PEODUCTS_ORDER_BY_UNITPRICE_DESC); //3.準備指令
                ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                ) {
            //5.處理rs
            while (rs.next()) {
                Product p;

                String className = rs.getString("class_name");
                if (className.equals("Outlet")) {
                    p = new Outlet();
                } else {
                    p = new Product();
                }

                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setType(rs.getString("type"));

                String sizes = rs.getString("size");
                if (sizes != null && sizes.length() > 0) {
                    String[] sizeArray = sizes.split(",");
                    for (String s : sizeArray) {
                        Size size = Size.valueOf(s);
                        p.addSizeList(size);
                    }
                }
                p.setLaunch_date(rs.getString("launch_date"));
                p.setUnitPrice(rs.getDouble("unitPrice"));
                p.setStock(rs.getInt("stock"));
                p.setDescription(rs.getString("description"));
                p.setOutfit(rs.getString("outfit"));
                p.setActivity(rs.getString("activity"));

                if (p instanceof Outlet) {
                    ((Outlet) p).setDiscount(rs.getInt("discount"));
                }

                Color color = new Color();
                color.setColor_name(rs.getString("color_name"));
                color.setMain_photoUrl(rs.getString("main_photoUrl"));
                color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                color.setPng_photoUrl(rs.getString("png_photoUrl"));
                color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                color.setCollocation_id(rs.getString("collocation_id"));
                color.setColor_block(rs.getString("color_block"));

                p.addColor(color);

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("用價錢高到低查詢全部產品失敗", e);
        }

        return list;
    }

    private static final String SELECT_ALL_PEODUCTS_ORDER_BY_LAUNCH_DATE = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products GROUP BY products.id,launch_date ORDER BY launch_date DESC;";

    List<Product> selectAllProductsOrderByLaunchDate() throws MMDException {

        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_PEODUCTS_ORDER_BY_LAUNCH_DATE); //3.準備指令
                ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                ) {
            //5.處理rs
            while (rs.next()) {
                Product p;

                String className = rs.getString("class_name");
                if (className.equals("Outlet")) {
                    p = new Outlet();
                } else {
                    p = new Product();
                }

                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setType(rs.getString("type"));

                String sizes = rs.getString("size");
                if (sizes != null && sizes.length() > 0) {
                    String[] sizeArray = sizes.split(",");
                    for (String s : sizeArray) {
                        Size size = Size.valueOf(s);
                        p.addSizeList(size);
                    }
                }
                p.setLaunch_date(rs.getString("launch_date"));
                p.setUnitPrice(rs.getDouble("unitPrice"));
                p.setStock(rs.getInt("stock"));
                p.setDescription(rs.getString("description"));
                p.setOutfit(rs.getString("outfit"));
                p.setActivity(rs.getString("activity"));

                if (p instanceof Outlet) {
                    ((Outlet) p).setDiscount(rs.getInt("discount"));
                }

                Color color = new Color();
                color.setColor_name(rs.getString("color_name"));
                color.setMain_photoUrl(rs.getString("main_photoUrl"));
                color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                color.setPng_photoUrl(rs.getString("png_photoUrl"));
                color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                color.setCollocation_id(rs.getString("collocation_id"));
                color.setColor_block(rs.getString("color_block"));

                p.addColor(color);

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("用上架時間近到遠查詢產品分類失敗", e);
        }

        return list;
    }

    private static final String SELECT_NEW_PEODUCTS_BY_TODAY_MINUS1WEEK = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE launch_date >= ? GROUP BY products.id,launch_date ORDER BY launch_date DESC;";

    List<Product> selectNewProductsByTodayMinusWeek() throws MMDException {

        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_NEW_PEODUCTS_BY_TODAY_MINUS1WEEK); //3.準備指令
                ) {
            String date = String.valueOf(LocalDate.now().minusWeeks(1));
            pstmt.setString(1, date); //3.1 傳入?的值
            try (
                    ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                    ) {
                //5.處理rs
                while (rs.next()) {
                    Product p;

                    String className = rs.getString("class_name");
                    if (className.equals("Outlet")) {
                        p = new Outlet();
                    } else {
                        p = new Product();
                    }

                    p.setId(rs.getString("id"));
                    p.setName(rs.getString("name"));
                    p.setType(rs.getString("type"));

                    String sizes = rs.getString("size");
                    if (sizes != null && sizes.length() > 0) {
                        String[] sizeArray = sizes.split(",");
                        for (String s : sizeArray) {
                            Size size = Size.valueOf(s);
                            p.addSizeList(size);
                        }
                    }
                    p.setLaunch_date(rs.getString("launch_date"));
                    p.setUnitPrice(rs.getDouble("unitPrice"));
                    p.setStock(rs.getInt("stock"));
                    p.setDescription(rs.getString("description"));
                    p.setOutfit(rs.getString("outfit"));
                    p.setActivity(rs.getString("activity"));
                    if (p instanceof Outlet) {
                        ((Outlet) p).setDiscount(rs.getInt("discount"));
                    }

                    Color color = new Color();
                    color.setColor_name(rs.getString("color_name"));
                    color.setMain_photoUrl(rs.getString("main_photoUrl"));
                    color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                    color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                    color.setPng_photoUrl(rs.getString("png_photoUrl"));
                    color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                    color.setCollocation_id(rs.getString("collocation_id"));
                    color.setColor_block(rs.getString("color_block"));

                    p.addColor(color);

                    list.add(p);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("用近7天上架查詢產品失敗", e);
        }
        return list;
    }

    private static final String SELECT_PEODUCT_BY_TYPE_ORDER_BY_UNITPRICE = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE type = ? GROUP BY products.id,unitPrice ORDER BY unitPrice*(100-discount)/100;";

    List<Product> selectProductByTypeOrderByUnitprice(String type) throws MMDException {

        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PEODUCT_BY_TYPE_ORDER_BY_UNITPRICE); //3.準備指令
                ) {

            pstmt.setString(1, type); //3.1 傳入?的值
            try (
                    ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                    ) {
                //5.處理rs
                while (rs.next()) {
                    Product p;

                    String className = rs.getString("class_name");
                    if (className.equals("Outlet")) {
                        p = new Outlet();
                    } else {
                        p = new Product();
                    }

                    p.setId(rs.getString("id"));
                    p.setName(rs.getString("name"));
                    p.setType(rs.getString("type"));

                    String sizes = rs.getString("size");
                    if (sizes != null && sizes.length() > 0) {
                        String[] sizeArray = sizes.split(",");
                        for (String s : sizeArray) {
                            Size size = Size.valueOf(s);
                            p.addSizeList(size);
                        }
                    }
                    p.setLaunch_date(rs.getString("launch_date"));
                    p.setUnitPrice(rs.getDouble("unitPrice"));
                    p.setStock(rs.getInt("stock"));
                    p.setDescription(rs.getString("description"));
                    p.setOutfit(rs.getString("outfit"));
                    p.setActivity(rs.getString("activity"));
                    if (p instanceof Outlet) {
                        ((Outlet) p).setDiscount(rs.getInt("discount"));
                    }

                    Color color = new Color();
                    color.setColor_name(rs.getString("color_name"));
                    color.setMain_photoUrl(rs.getString("main_photoUrl"));
                    color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                    color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                    color.setPng_photoUrl(rs.getString("png_photoUrl"));
                    color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                    color.setCollocation_id(rs.getString("collocation_id"));
                    color.setColor_block(rs.getString("color_block"));

                    p.addColor(color);

                    list.add(p);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("用價錢低到高查詢產品分類失敗", e);
        }
        return list;
    }

    private static final String SELECT_PEODUCT_BY_TYPE_ORDER_BY_UNITPRICE_DESC = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE type =? GROUP BY products.id,unitPrice ORDER BY unitPrice*(100-discount)/100 DESC;";

    List<Product> selectProductByTypeOrderByUnitpriceDESC(String type) throws MMDException {

        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PEODUCT_BY_TYPE_ORDER_BY_UNITPRICE_DESC); //3.準備指令
                ) {

            pstmt.setString(1, type); //3.1 傳入?的值
            try (
                    ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                    ) {
                //5.處理rs
                while (rs.next()) {
                    Product p;

                    String className = rs.getString("class_name");
                    if (className.equals("Outlet")) {
                        p = new Outlet();
                    } else {
                        p = new Product();
                    }

                    p.setId(rs.getString("id"));
                    p.setName(rs.getString("name"));
                    p.setType(rs.getString("type"));

                    String sizes = rs.getString("size");
                    if (sizes != null && sizes.length() > 0) {
                        String[] sizeArray = sizes.split(",");
                        for (String s : sizeArray) {
                            Size size = Size.valueOf(s);
                            p.addSizeList(size);
                        }
                    }
                    p.setLaunch_date(rs.getString("launch_date"));
                    p.setUnitPrice(rs.getDouble("unitPrice"));
                    p.setStock(rs.getInt("stock"));
                    p.setDescription(rs.getString("description"));
                    p.setOutfit(rs.getString("outfit"));
                    p.setActivity(rs.getString("activity"));
                    if (p instanceof Outlet) {
                        ((Outlet) p).setDiscount(rs.getInt("discount"));
                    }

                    Color color = new Color();
                    color.setColor_name(rs.getString("color_name"));
                    color.setMain_photoUrl(rs.getString("main_photoUrl"));
                    color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                    color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                    color.setPng_photoUrl(rs.getString("png_photoUrl"));
                    color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                    color.setCollocation_id(rs.getString("collocation_id"));
                    color.setColor_block(rs.getString("color_block"));

                    p.addColor(color);

                    list.add(p);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("用價錢高到低查詢產品分類失敗", e);
        }
        return list;
    }

    private static final String SELECT_PEODUCT_BY_TYPE_ORDER_BY_LAUNCH_DATE = "SELECT * FROM products INNER JOIN colors ON products.id=colors.fkey_2_products WHERE type =? GROUP BY products.id,unitPrice ORDER BY launch_date DESC;";

    List<Product> selectProductByTypeOrderByLaunchDate(String type) throws MMDException {

        List<Product> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PEODUCT_BY_TYPE_ORDER_BY_LAUNCH_DATE); //3.準備指令
                ) {

            pstmt.setString(1, type); //3.1 傳入?的值
            try (
                    ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                    ) {
                //5.處理rs
                while (rs.next()) {
                    Product p;

                    String className = rs.getString("class_name");
                    if (className.equals("Outlet")) {
                        p = new Outlet();
                    } else {
                        p = new Product();
                    }

                    p.setId(rs.getString("id"));
                    p.setName(rs.getString("name"));
                    p.setType(rs.getString("type"));

                    String sizes = rs.getString("size");
                    if (sizes != null && sizes.length() > 0) {
                        String[] sizeArray = sizes.split(",");
                        for (String s : sizeArray) {
                            Size size = Size.valueOf(s);
                            p.addSizeList(size);
                        }
                    }
                    p.setLaunch_date(rs.getString("launch_date"));
                    p.setUnitPrice(rs.getDouble("unitPrice"));
                    p.setStock(rs.getInt("stock"));
                    p.setDescription(rs.getString("description"));
                    p.setOutfit(rs.getString("outfit"));
                    p.setActivity(rs.getString("activity"));
                    if (p instanceof Outlet) {
                        ((Outlet) p).setDiscount(rs.getInt("discount"));
                    }

                    Color color = new Color();
                    color.setColor_name(rs.getString("color_name"));
                    color.setMain_photoUrl(rs.getString("main_photoUrl"));
                    color.setDetail_photoUrl_1(rs.getString("detail_photoUrl_1"));
                    color.setDetail_photoUrl_2(rs.getString("detail_photoUrl_2"));
                    color.setPng_photoUrl(rs.getString("png_photoUrl"));
                    color.setCollocation_photoUrl(rs.getString("collocation_photoUrl"));
                    color.setCollocation_id(rs.getString("collocation_id"));
                    color.setColor_block(rs.getString("color_block"));

                    p.addColor(color);

                    list.add(p);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MMDException("用上假日期查詢產品失敗", e);
        }
        return list;
    }

    private static String SELECT_PRODUCT_STOCK_BY_ID_AND_COLOR_AND_SIZE = "SELECT id,color_name,product_color_size_stock.size,product_color_size_stock.stock as the_stock "
            + "FROM products LEFT JOIN product_color_size_stock ON products.id=product_color_size_stock.product_id "
            + "WHERE products.id=? AND color_name=? AND product_color_size_stock.size=?;";

    int selectProductStock(String productId, String color, String size) throws MMDException {
        int stock=0;
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCT_STOCK_BY_ID_AND_COLOR_AND_SIZE); //3.準備指令
                ) {
            //3.1 傳入?的值
            pstmt.setString(1, productId);
            pstmt.setString(2, color);
            pstmt.setString(3, size);
            
            try(
                    ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                    ){
                while(rs.next()){
                    String colorName = rs.getString("color_name");
                    if(color.equals(colorName)){
                        stock = rs.getInt("the_stock");
                    }
                }
            }
        }catch(SQLException ex){
            throw new MMDException("查詢庫存失敗",ex);
        }           
        return stock;
    }

    private static String SELECT_CLOTHPHOTO_AND_PRODUCTID_BY_RAND = "SELECT fkey_2_products,png_photoUrl FROM colors WHERE png_photoUrl IS NOT NULL AND fkey_2_products LIKE 'AB%' "
            + "ORDER BY  RAND() LIMIT 15;";
    List<Color> selectClothPhotoAndProductIdByRand() throws MMDException{
        List<Color> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_CLOTHPHOTO_AND_PRODUCTID_BY_RAND); //3.準備指令
                ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                ) {
            //5.處理rs
            while (rs.next()) {
                 Color color = new Color();
                 color.setPng_photoUrl(rs.getString("png_photoUrl"));
                 color.setProduct_id(rs.getString("fkey_2_products"));
                 list.add(color);
            }
            } catch (SQLException ex) {
           throw new MMDException("查詢上衣去背圖和產品編號失敗",ex);
        }
           return list;
    }
    
    private static String SELECT_PANTS_OR_SKIRT_PHOTO_AND_PRODUCTID_BY_RAND ="SELECT fkey_2_products,png_photoUrl FROM colors WHERE png_photoUrl IS NOT NULL AND (fkey_2_products LIKE 'CA%' OR fkey_2_products LIKE 'BA%') " 
            +"ORDER BY  RAND() LIMIT 15;";
               List<Color> selectPantsOrSkirtPhotoAndProductIdByRand() throws MMDException{
        List<Color> list = new ArrayList<>();
        try (
                Connection connection = RDBConnection.getConnection(); //1.2 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_PANTS_OR_SKIRT_PHOTO_AND_PRODUCTID_BY_RAND); //3.準備指令
                ResultSet rs = pstmt.executeQuery(); //4. 執行指令
                ) {
            //5.處理rs
            while (rs.next()) {
                 Color color = new Color();
                 color.setPng_photoUrl(rs.getString("png_photoUrl"));
                 color.setProduct_id(rs.getString("fkey_2_products"));
                 list.add(color);
            }
            } catch (SQLException ex) {
           throw new MMDException("查詢下著去背圖和產品編號失敗",ex);
        }
           return list;
    }
               
    //測試程式
    public static void main(String[] args) {
        ProductsDAO dao = new ProductsDAO();
        try {
            //查全部產品 (GROUP BY products.id : 只取第一個顏色 )
//            List<Product> list = dao.selectAllProducts();
//            System.out.println("list = " + list);
//            System.out.println("list.size() = " + list.size());

            //用部分名稱查詢 (GROUP BY products.id : 只取第一個顏色 )
//            List<Product> list2 = dao.selectProductsByName("上衣");
//            System.out.println("list2 = " + list2);
//            System.out.println("list2.size() = " + list2.size());
            //用產品編號查詢
//            Product p = dao.selectProductById("AB9020");
//            System.out.println("p =" + p);
//            //查照片路徑(用產品編號 顏色 照片編號) 
//            String photo_url1 = dao.selectPhotoByIdandColor("AB9020", "粉", 1);
//            String photo_url2 = dao.selectPhotoByIdandColor("AB9020", "粉", 2);
//            String photo_url3 = dao.selectPhotoByIdandColor("AB9020", "粉", 3);
//            String photo_url4 = dao.selectPhotoByIdandColor("AB9020", "藍灰", 4);
//            System.out.println("main_photoUrl = " + photo_url1);
//            System.out.println("detail_photoUrl_1 = " + photo_url2);
//            System.out.println("detail_photoUrl_2 = " + photo_url3);
//            System.out.println("detail_photoUrl_4 = " + photo_url4);
            //用分類編號查詢
//             List<Product> list3 = dao.searchProductByType("上衣");
//             System.out.println("list3 = " + list3);
//             System.out.println(list3.size());
            //用穿搭主題查詢
//             List<Product> list4 = dao.selectProductByOutfit("約會");
//             System.out.println("list4 = " + list4);
//             System.out.println(list4.size());
            //查全部產品(價錢排序 低到高)
//                List<Product> list5 = dao.selectAllProductsOrderByUnitPrice();
//                System.out.println("list5 = " + list5);
//                System.out.println(list5.size());
            //查全部產品(價錢排序 高到低)
//                List<Product> list6 = dao.selectAllProductsOrderByUnitPriceDesc();
//                System.out.println("list6 = " + list6);
//                System.out.println(list6.size());
//            //查全部產品(上架時間排序 新到舊)
//                List<Product> list7 = dao.selectAllProductsOrderByLaunchDate();
//                System.out.println("list7 = " + list7);
//                System.out.println(list7.size());
            //查新品(上架時間離今天一個一週內)
//              List<Product> list8 = dao.selectNewProductsByTodayMinusWeek();
//              System.out.println("list8 = " + list8);
//              System.out.println(list8.size());
            //查分類(價錢排序 低到高)
//               List<Product> list9 =  dao.selectProductByTypeOrderByUnitprice("上衣");
//               System.out.println("list9 = " + list9);
//               System.out.println(list9.size());
//               //查分類(價錢排序 高到低)
//               List<Product> list10 =  dao.selectProductByTypeOrderByUnitpriceDESC("褲子");
//               System.out.println("list10 = " + list10);
//               System.out.println(list10.size());
//            //查分類(上架時間 低到高)
//            List<Product> list11 = dao.selectProductByTypeOrderByLaunchDate("洋裝");
//            System.out.println("list11 = " + list11);
//            System.out.println(list11.size());
//            //查庫存
//            int stock = dao.selectProductStock("AB7745","深灰藍","L");
//            System.out.println("stock = " + stock);
              //亂數產生上衣去背圖和產品編號
//              List<Color> list12 = dao.selectClothPhotoAndProductIdByRand();
//              System.out.println("list12 = " + list12);
//              System.out.println(list12.size());
              //亂數產生上衣去背圖和產品編號
              List<Color> list13 = dao.selectPantsOrSkirtPhotoAndProductIdByRand();
              System.out.println("list13 = " + list13);
              System.out.println(list13.size());
        } catch (MMDException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

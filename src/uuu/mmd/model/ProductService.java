package uuu.mmd.model;

import java.util.List;
import uuu.mmd.entity.Color;
import uuu.mmd.entity.MMDException;
import uuu.mmd.entity.Product;

public class ProductService {

    //1.宣告private屬性
    private ProductsDAO dao = new ProductsDAO();

    //2.Insert Code -> Override Delegate Method
    //3.改方法名稱(sql命名方式 -> 前端商業邏輯命名方式)
    //4.改存取範圍(private -> public 因為要給前端存取)
    public List<Product> getAllProducts() throws MMDException {
        List<Product> list = dao.selectAllProducts();
        return list;
    }

    public List<Product> searchProductsByName(String search) throws MMDException {
        return dao.selectProductsByName(search);
    }

    public Product searchProductById(String id) throws MMDException {
        return dao.selectProductById(id);
    }

    public String selectPhotoByIdandColor(String id, String color, String photoId) throws MMDException {
        if (photoId == null || !photoId.matches("[1-5]")) {
            throw new MMDException("photoId invalid");
        }
        return dao.selectPhotoByIdandColor(id, color, Integer.parseInt(photoId));
    }

    public List<Product> searchProductByType(String type) throws MMDException {
        return dao.selectProductByType(type);
    }

    public List<Product> searchProductByOutfit(String outfit) throws MMDException {
        return dao.selectProductByOutfit(outfit);
    }

    public List<Product> searchProductByTypeOrderBy(String type, String order) throws MMDException {
        if (order.equals("launch_dateDESC")) {
            return dao.selectProductByTypeOrderByLaunchDate(type);
        } else if (order.equals("unitPriceASC")) {
            return dao.selectProductByTypeOrderByUnitprice(type);
        } else {
            return dao.selectProductByTypeOrderByUnitpriceDESC(type);
        }
    }

    public List<Product> searchAllProductsOrderBy(String order) throws MMDException {
        if (order.equals("launch_dateDESC")) {
            return dao.selectAllProductsOrderByLaunchDate();
        } else if (order.equals("unitPriceASC")) {
            return dao.selectAllProductsOrderByUnitPrice();
        } else {
            return dao.selectAllProductsOrderByUnitPriceDesc();
        }
    }
    //把以下三支DAO整理在一支service處理
//    public List<Product> searchAllProductsOrderByUnitPrice() throws MMDException {
//        return dao.selectAllProductsOrderByUnitPrice();
//    }
//
//    public List<Product> searchAllProductsOrderByUnitPriceDesc() throws MMDException {
//        return dao.selectAllProductsOrderByUnitPriceDesc();
//    }
//
//    public List<Product> searchAllProductsOrderByLaunchDate() throws MMDException {
//        return dao.selectAllProductsOrderByLaunchDate();
//    }

    public List<Product> searchNewProductsByTodayMinusWeek() throws MMDException {
        return dao.selectNewProductsByTodayMinusWeek();
    }

    public int getProductStock(String productId, String color, String size) throws MMDException {
        if(productId==null){
            throw new MMDException("查詢產品庫存必須輸入產品編號");
        }
        if(color==null){
            throw new MMDException("查詢產品庫存必須輸入產品顏色");
        }
         if(size==null){
            throw new MMDException("查詢產品庫存必須輸入產品尺寸");
        }
        return dao.selectProductStock(productId, color, size);
    }

    public List<Color> searchClothPhotoAndProductIdByRand() throws MMDException {
        return dao.selectClothPhotoAndProductIdByRand();
    }

    public List<Color> searchPantsOrSkirtPhotoAndProductIdByRand() throws MMDException {
        return dao.selectPantsOrSkirtPhotoAndProductIdByRand();
    }
    
    
    
    

    //以下兩個方法是示範PASS-BY-VALUE用的，不是商業邏輯    
    public void addPrice(double price) {	//參數price隨方法結束也跟著結束，並未傳值出去給物件 
        price = price + 50;
    }

//	public double addPrice(double price) {	//傳值: 雖然傳的值是對的價格,但不建議這寫法,因為參數是傳輸入值用的,用來當回傳值會混淆
//		return price+50;
//	}
    public void addPrice(Product p) {	//傳址: 雖然傳的參考是對的價格,但不建議這寫法,因為把參考型別的參數整個傳進去，其他屬性可能也會被改
        p.setUnitPrice(p.getUnitPrice() + 50);
    }

//	public double addPrice(Product p) {	//建議寫法  
//		return(p.getUnitPrice()+50);		
//	}
}

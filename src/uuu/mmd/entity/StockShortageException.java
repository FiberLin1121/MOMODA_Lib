/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.entity;

/**
 *
 * @author Admin
 */
public class StockShortageException extends MMDException {
    private Product product;
    
     public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    /**
     * Creates a new instance of <code>StockShortageException</code> without
     * detail message.
     */
    public StockShortageException() {
        super("產品庫存不足");
    }

    public StockShortageException(Product product){
        super("產品庫存不足");
    }
    /**
     * Constructs an instance of <code>StockShortageException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public StockShortageException(String msg) {
        super(msg);
    }

    public StockShortageException(String message, Throwable cause) {
        super(message, cause);
    }

   



}


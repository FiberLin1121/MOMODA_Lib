package uuu.mmd.entity;

public class VIP extends Customer {
	private int discount = 10;//預設9折優惠

    public VIP() {
    }

    public VIP(String id, String name, String password) throws MMDException {
        super(id, name, password);
    }

	public int getDiscount() {
		return discount;
	}

	public String getDiscountString() {	
		String discountStr = "";   //區域變數要宣告且給初始值
		
		int data = 100-this.discount;
		
		if(data%10==0) {
			discountStr = data/10 + "折";
		}else {
			discountStr = data + "折";
		}
		
		return discountStr;  
	}
	
	public void setDiscount(int discount) {
		if(discount>=0 && discount<=100) {
			this.discount = discount;
		}else {
			System.out.println("VIP折扣資料不正確: " +discount);
		}
	}

    @Override
    public String toString() {
        return super.toString() + ",\n折扣=" + discount + "折}";
    }
	
	
}

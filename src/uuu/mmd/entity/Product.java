/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.mmd.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Admin
 */
public class Product extends Object {

    private String id; //PrimaryKey(PKey)
    private String name; //required
    private String type; //required
    private List<Size> sizeList = new ArrayList<>(); //required
    private double unitPrice;//是售價也是定價 //required
    private int stock; //required
    private LocalDate launch_date = LocalDate.now(); //required
    private String description; //required
    private String outfit;
    private String activity;

    private Map<String, Color> colorMap = new TreeMap<>();
    //TreeMap的排序(依字串)與資料庫相同(hashMap排序是照hashCode)
    //Map集合(屬性)不能直接getter/setter
    //集合可以宣告後先給值(size=0)

    //public int status = 1;  //0:新產品, 1:已上架, -1:已停售
    public Product() {
    }

    public Product(String id, String name, String type, double unitPrice) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void addSizeList(Size size) {
        this.sizeList.add(size);
    }

    public List<Size> getSizeList() {
        return sizeList;
    }

    public LocalDate getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(LocalDate launch_date) {
        this.launch_date = launch_date;
    }

    public void setLaunch_date(String date) {
        if(date!=null){
            this.setLaunch_date(LocalDate.parse(date));
        }
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutfit() {
        return outfit;
    }

    public void setOutfit(String Outfit) {
        this.outfit = Outfit;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    //取得color物件 -> 取出全部顏色 (透過color_name(key))
    public Color getColor(String color_name) {
        return colorMap.get(color_name);
    }

    //取得color物件 -> 只取第一個顏色 (透過color_name(key))
    public Color getColor() {
        Color color = null;
        if (colorMap.keySet().size() > 0) {
            int i = 0;
            for (String color_name : colorMap.keySet()) { //把key從set集合一個一個出來撈出來,宣告為color_name(key)變數
                i++;
                color = colorMap.get(color_name);
                if (i == 1) {
                    break; //用break強制跳出迴圈
                }
            }
        }
        return color;
    }

    //把colol物件加進產品的colorMap (透過color_name(key))
    public void addColor(Color color) {
        this.colorMap.put(color.getColor_name(), color);
    }

    //取得colorMap的key的set集合
    public Set<String> getColorKeySet() {
        return colorMap.keySet();
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + "代號=" + id + ",\n"
                + "名稱=" + name + ",\n"
                + "款式=" + type + ",\n"
                + "尺寸=" + sizeList + ",\n"
                + "上假日期=" + launch_date + ",\n"
                + "庫存=" + stock + ",\n"
                + "售價=" + unitPrice + ",\n"
                + "描述=" + description + ",\n"
                + "colorMap=" + colorMap + ",\n"
                + "穿搭主題=" + outfit + ",\n"
                + "Sale活動=" + activity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

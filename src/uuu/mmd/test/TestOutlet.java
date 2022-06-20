package uuu.mmd.test;

import uuu.mmd.entity.Color;
import uuu.mmd.entity.Outlet;
import uuu.mmd.entity.Outlet;
import uuu.mmd.entity.Size;

public class TestOutlet {
	public static void main(String[] args) {
		Outlet outlet = new Outlet("AB9161","荷葉領排釦雪紡上衣","上衣",459,20);
                Color color = new Color();
                color.setColor_name("綠");
                outlet.addColor(color);
                System.out.println("outlet = " + outlet);
	}
	
	
}

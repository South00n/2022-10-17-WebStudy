package com.sist.change;

public class JspChange {
	private static String[] jsp = {
			"../main/home.jsp",
			"../seoul/location.jsp",
			"../seoul/nature.jsp",
			"../seoul/shop.jsp"
	};
	public static String change(int no) {
		return jsp[no];
	}
	
}

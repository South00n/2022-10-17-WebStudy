package com.sist.model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

public class FoodModel {
	public void foodListData(HttpServletRequest request, HttpServletResponse response) {
		
		FoodDAO dao = new FoodDAO();
		ArrayList<FoodVO> list = dao.foodListData();
		
		request.setAttribute("list", list);
	}
}

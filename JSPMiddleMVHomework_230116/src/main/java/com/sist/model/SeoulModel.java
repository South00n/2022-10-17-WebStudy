package com.sist.model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.SeoulDAO;
import com.sist.vo.SeoulVO;

public class SeoulModel {
	public void seoulListData(HttpServletRequest request, HttpServletResponse response) {
		
		SeoulDAO dao = new SeoulDAO();
		ArrayList<SeoulVO> list = dao.seoulListData();
		
		request.setAttribute("list", list);
	}
}

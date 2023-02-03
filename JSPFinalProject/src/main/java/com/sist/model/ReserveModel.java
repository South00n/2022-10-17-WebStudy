package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class ReserveModel {
	@RequestMapping("reserve/reserve_main.do")
	public String reserve_main(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("main_jsp", "../reserve/reserve_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
}

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	if(cookies != null) {
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().startsWith("f")) { // 프로젝트에서는 "food" 이런 식으로 다른 쿠키가 삭제되지않게
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0); // 저장기간을 없앤다 -> 삭제
				response.addCookie(cookies[i]);
			}
		}
	}
	// 이동
	response.sendRedirect("../include/main.jsp");
%>
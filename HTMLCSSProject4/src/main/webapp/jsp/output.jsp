<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 자바 영역 request -> 값 받기
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name"); // HTML에서 보낸거 받는 방법
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <b><%=name %></b>
</body>
</html>
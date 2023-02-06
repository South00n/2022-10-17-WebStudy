<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery"></script>
<script>
$(function(){
	$('.times').click(function(){
		let time=$(this.text());
		$('#r_time').text(time);
	})
})
</script>
</head>
<body>
	<c:forEach var="time" items="${rtime }">
		<span class="btn btn-sm btn-success">${time }</span>&nbsp;
	</c:forEach>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.infos').click(function(){
		let rno = $(this).attr("data-rno");
		$.ajax({
			type:'get',
			url:'../mypage/mypage_reserve_info.do',
			data:{"rno":rno},
			success:function(response) {
				$('#reserve_info').html(response);
			}
		})
	})
})
</script>
</head>
<body>
	<div class="wrapper row3">
	  <main class="container clear">
		  <h2 class="sectiontitle">예약 목록</h2>
		  <div style="height: 5px"></div>
		  <table class="table">
		  	<tr>
		  	  <th class="text-center">번호</th>
		  	  <th class="text-center"></th>
		  	  <th>업체명</th>
		  	  <th>주소</th>
		  	  <th class="text-center">예약일</th>
		  	  <th class="text-center">예약시간</th>
		  	  <th class="text-center">인원</th>
		  	  <th class="text-center"></th>
		  	</tr>
		  	<c:forEach var="vo" items="${list }">
		  	  <tr>
			  	  <th class="text-center">${vo.rno }</th>
			  	  <th class="text-center">
			  	    <img src="${vo.fvo.poster }" style="width:30px; height: 30px">
			  	  </th>
			  	  <td>${vo.fvo.name }</th>
			  	  <td>${vo.fvo.address}</th>
			  	  <td class="text-center">${vo.rdate }</th>
			  	  <td class="text-center">${vo.rtime }</th>
			  	  <td class="text-center">${vo.inwon }</th>
			  	  <td class="text-center">
			  	  	<c:if test="${vo.ok == 'y' }">
			  	  		<span class="btn btn-xs btn-success infos" data-rno="${vo.rno }">예약완료</span>
			  	  	</c:if>
			  	  	<c:if test="${vo.ok == 'n' }">
			  	  	<span class="btn btn-xs btn-default">예약대기</span>
			  	  	</c:if>
			  	  	<a href="../reserve/reserve_delete.do?rno=${vo.rno }" class="btn btn-xs btn-warning">취소</a>
			  	  </td>
		  	  </tr>
		  	</c:forEach>
		  </table>
		  <div style="height: 10px"></div>
		  <div id="reserve_info">
		  
		  </div>
	  </main>
	</div>
</body>
</html>
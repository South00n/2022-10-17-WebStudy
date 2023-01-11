<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<%
	String fno = request.getParameter("fno");
	// FoodVO 
	FoodVO vo = dao.foodDetailData(Integer.parseInt(fno));
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);

        var options = {
          title: 'My Daily Activities',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>
</head>
<body>
	<table class="table">
	  <tr>
	    <%
	    	String poster = vo.getPoster();
	    	poster = poster.replace("#", "&");
	    	StringTokenizer st = new StringTokenizer(poster, "^");
	    	while(st.hasMoreTokens()) {
	    %>
	    		<td class="text-center">
	    		  <img src="<%=st.nextToken() %>" style="width:100%">
	    		</td>
	   	<%
	    	}
	    %>
	  </tr>
	</table>
	<div style="height: 10px"></div>
	<div class="row">
	  <div class="col-sm-8">
	  	
	    <table class="table">
	      <tr>
	        <td colspan="2">
	          <h3><%=vo.getName() %>&nbsp;<span style="color:orange"><%=vo.getScore() %></span></h3>
	          
	        </td>
	      </tr>
	      <tr>
	        <th class="text-right" width=15%><span style="color:gray">주소</span></th>
	        <td width="85%"><%=vo.getAddress() %></td>
	        
	      </tr>
	      <tr>
	        <th class="text-right" width=15%><span style="color:gray">전화</span></th>
	        <td width="85%"><%=vo.getTel() %></td>
	      </tr>
	      <tr>
	        <th class="text-right" width=15%><span style="color:gray">음식종류</span></th>
	        <td width="85%"><%=vo.getType() %></td>
	      </tr>
	      <tr>
	        <th class="text-right" width=15%><span style="color:gray">가격대</span></th>
	        <td width="85%"><%=vo.getPrice() %></td>
	      </tr>
	      <tr>
	        <th class="text-right" width=15%><span style="color:gray">영업시간</span></th>
	        <td width="85%"><%=vo.getTime() %></td>
	      </tr>
	      <tr>
	        <th class="text-right" width=15%><span style="color:gray">주차</span></th>
	        <td width="85%"><%=vo.getParking() %></td>
	      </tr>
	      <%
	      	  if(!vo.getMenu().equals("no")) {
	      %>
	      
	      <tr>
	        <th class="text-right" width=15%><span style="color:gray">메뉴</span></th>
	        <td width="85%">
	          <ul>
	            <%
	              st= new StringTokenizer(vo.getMenu(), "원");
	              while(st.hasMoreTokens()) {
	            %>
	            	<li><%=st.nextToken() %>원</li>
	            <%	  
	              }
	            %>
	          </ul>
	        </td>
	      </tr>
	      <%
	      	  }
	      %>
	    <tr>
	      <td colspan="2" class="text-right">
	        <input type="button" class="btn btn-xs btn-success" value="찜하기">
	        <input type="button" class="btn btn-xs btn-info" value="좋아요">
	        <input type="button" class="btn btn-xs btn-warning" value="예약">
	        <input type="button" class="btn btn-xs btn-primary" value="목록" onclick="javascript:history.back()">
	      </td>
	    </tr>
	    </table>
	    <div style="height: 10px"></div>
	    <%
	    	String id = (String)session.getAttribute("id");
	    	if(id!=null) { // 로그인이 되었다면 (세션의 값의 존재여부)
	    %>
	    <table class="table">
	      <tr>
	        <td>
	         <form method=post action="reply_insert.jsp">
	          <%--
	          		session : 로그인, 댓글, 장바구니 => 서버에 저장, 저장시에 Object
	          		cookie : 브라우저에 저장 (보안 취약), 문자열만 저장 => 최신 방문
	          		NO      NOT NULL NUMBER       
					FNO              NUMBER       
					ID               VARCHAR2(20) 
					NAME    NOT NULL VARCHAR2(34) 
					MSG     NOT NULL CLOB         
					REGDATE          DATE         
					STAR             NUMBER
					주고 받기
					-------
					 ?
					 hidden     
	           --%>
	           <input type=hidden name=fno value="<%=vo.getFno() %>">
	          <textarea rows="4" cols="55" name="msg" style="float: left"></textarea>
	          <input type="submit" value="댓글쓰기" class="btn btn-sm btn-danger" style="float: left; height: 88px">	        
	         </form>
	        </td>
	      </tr>
	    </table>
	    <%
	  	  	}
	    %>
	  </div>
	  <div class="col-sm-4">
	  	<div id="piechart_3d" style="width: 400px; height: 400px;"></div>
	  </div>
	</div>
</body>
</html>
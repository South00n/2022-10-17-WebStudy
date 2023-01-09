<!-- 
	 171page (내장객체) 스프링과 관련 [**중요]
	 
	 1) 내부객체 (내장객체, 기본객체)
	 	=> 미리 객체를 생성한 다음에 사용이 가능
	 	=> 9개
	 	=> JSP 파일 => _jspService()에 필요한 코딩을 하는 파일
	 	public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response) {
	 		final javax.servlet.jsp.PageContext pageContext;
		    javax.servlet.http.HttpSession session = null;
		    final javax.servlet.ServletContext application;
		    final javax.servlet.ServletConfig config;
		    javax.servlet.jsp.JspWriter out = null;
		    final java.lang.Object page = this;
		    javax.servlet.jsp.JspWriter _jspx_out = null;
		    javax.servlet.jsp.PageContext _jspx_page_context = null;
		    
		    // JSP코딩 위치
	 	}
	 	
	 	** request
	 	** response
	 	** pageContext
	 	** session
	 	** application
	 	config
	 	** out
	 	page
	 	exception
	 	------------------------ 내부객체, 내장객체
	 	
	 	_jspService() { => 브라우저 화면에 출력해주는 메소드
	 		JSP 파일
	 	}
	 	
	 	1) 요청관련 데이터 관리, 사용자 브라우저 정보, 추가 기능
	 	   request : HttpServletRequest
	 	   			 ------------------
	 	   = 기능
	 	     1. 브라우저 정보 / (서버 정보)
	 	     	http://localhost:80/JSPBasicProject4/object/basic_jsp1.jsp => URL
	 	     	----------------	--------------------------------------
	 	     	    서버 정보			  	    사용자 요청 정보 (URI)
	 	     	    				----------------
	 	     	    				  ContextPath
	 	     	 **= getRequestURL() => URL 가져오기
	 	     	 **= getRequestURI() => URI 가져오기
	 	     	 **= getContextPath()
	 	     	 **= getRemoteAddr() => 사용자의 IP 가져오기 (조회수)
	 	     	 = getServerPort() => 서버포트 가져오기 (80)
	 	     	 = getServerInfo() => localhost
	 	     2. 요청 관련 정보
	 	         = 사용자가 보내준 데이터 (단일데이터) : getParameter()
	 	         = 사용자가 보내준 데이터 (다중데이터) => checkbox : getParameterValues()
	 	         = 사용자가 보내준 데이터 Parameter => getParameterNames() => key명을 가져오는법
	 	         = 브라우저 ==> Java (2byte)
	 	                 1byte => 2byte로 변경해서 보냄 (디코딩)
	 	           Java (2byte) ==> 브라우저
	 	                       2byte ==> 1byte로 변경해서 보냄 (인코딩)
	 	           https://www.google.com/search?q=%EC%9E%90%EB%B0%94&oq=wkqk&aqs=chrome.1.69i57j0i433i512j0i131i433i512j0i433i512l2j0i512l5.4552j0j7&sourceid=chrome&ie=UTF-8
	 	           브라우저로 전송
	 	           	 %EC%9E%90%EB%B0%94(자바) => byte[]변경 (인코딩)
	 	           	 ------------------
	 	           	   자바 => 한글 변환 byte[] => String (디코딩)
	 	           	   --------------------------------------
	 	           	   1) byte[] => String (브라우저에서 값을 받을때) : 디코딩
	 	           	      request.setCharacterEncoding("UTF-8"); ==> POST방식에서 디코딩
	 	           	   2) String => byte[] (브라우저로 값을 보낼때) : 인코딩
	 	           	      URLEncoder.encoder(데이터, "UTF-8") => 자바/자바스크립트가 동일
	 	           	   -------------------------------------------------- 처리방식 (GET/POST)마다 다르다
	 	           	                                                              --- 
	 	           	                                                             window 10 (자동처리) => server.xml
	 	           	                                                             63 => URIEncoding="UTF-8"로 바꿔야함 윈도우10 아니면
	 	         ** 데이터를 전송할때
	 	         	받는파일명?변수명=값
	 	         	------- ----- --
	 	         			 key  value
	 	            예) a.jsp?no=10
	 	              => a.jsp에서 값을 받는다
	 	                 request.getParameter("no"); ==> no의 값을 받는다
	 	                                       -- key
	 	                 a.jsp?id=admin&pwd=1234 ==> 값을 2개이상 보낼때 & 사용
	 	                 	   -------- --------
	 	                 request.getParmeter("id"); // admin
	 	                 request.getParameter("pwd"); // 1234
	 	                 *** 받는 모든 데이터값은 String
	 	                 a.jsp?hobby=a&hobby=b&hobby=c
	 	                       -------------------------
	 	                       String[] request.getParameterValues("hobby") ==> 배열에 저장
	 	                       
	 	     3. 추가기능 => 사용자가 보내준 데이터 + 필요한 데이터를 추가해서 전송 (MVC, Spring)
	 	           setAttribute(키, 값) ==> Object를 첨부 (ArrayList)
	 	           getAttribute(키)
	 	
	 	= 응답해주는 객체
	 	  response : HttpServletResponse
	 	  -------- JSP한개에서 1번만 response를 할 수 있다
	 	                          --------
	 	                              |
	 	                            HTML 파일 전송
	 	                            Cookie 전송
	 	                           --------------
	 	  = setHeader() : 파일 업로드, 다운로드시에 사용
	 	  = sendRedirect() : 서버에서 다른 파일로 이동 / forward()
	 	  ----------------------------------------------------------------------------
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 전송값 받기 (request) -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container {
	margin-top: 50px;
}
.row {
	width: 500px;
	margin: 0px auto;
}
h1 {
	text-align: center;
}
</style>
<%--
      파일명?
      ----
      <form action="파일명">
      				----
      $.ajax({
      	 url:'a.jsp'
      	 	  -----
      })
      
      axios.get('URL')
 --%>
</head>
<body>
	<div class="container">
	  <h1>개인정보 전송</h1>
	  <div class ="row">
	   <form method=post action="output.jsp">
	   <!-- 
	      method: get/post
	              --------
	      action: 받는 파일 지정
	      전송되는 데이터 : input / select / textarea => 입력과 관련된 데이터만 전송된다
	    -->
	    <table class="table">
	      <tr>
	        <th class="text-center" width="20%">이름</th>
	        <td width="80%">
	          <input type="text" name="name" class="input-sm" size=15>
	          <!-- request.getParameter("name") : 입력된 값을 읽어온다 
	               ?name=홍길동&sex=남자 ...
	               -----------------------
	                POST/GET => 값을 전송하거나 받는 경우 (Map) => 키/값
	          -->
	        </td>
	      </tr>
	      <tr>
	        <th class="text-center" width="20%">성별</th>
	        <td width="80%">
	          <input type="radio" name="sex" checked value="남자">남자
	          <input type="radio" name="sex" value="여자">여자
	          <!-- name이 동일 (그룹), 반드시 보낼 데이터를 설정한다 (value) -->
	        </td>
	      </tr>
	      <tr>
	        <th class="text-center" width=20%>지역</th>
	        <td width=80%>
	          <select name="loc" class="input-sm">
	            <option>서울</option>
	            <option>부산</option>
	            <option>제주</option>
	            <option>인천</option>
	            <option>경기</option>
	            <!-- 
	               <option>서울</option> option은 value값이없으면 tag사이값이 넘어감
	               <option value="seoul">서울</option> value값이 있으면 value값이 넘어감
	               
	               <option value="name">이름</option> 한글로 표시해야 가독성좋음 / 값을 넘길땐 value값으로
	               <option value="subject">제목</option>
	               <option value="content">내용</option>
	             -->
	          </select>
	        </td>
	      </tr>
	      <tr>
	        <th class="text-center" width=20%>취미</th>
	        <td width=80%>
	          <input type="checkbox" value="등산" name=hobby>등산
	          <input type="checkbox" value="여행" name=hobby>여행
	          <input type="checkbox" value="게임" name=hobby>게임
	          <input type="checkbox" value="낚시" name=hobby>낚시
	          <input type="checkbox" value="독서" name=hobby>독서
	          <!-- 전송값 : value, name은 동일해야된다 : String[] getParameterValues("hobby") -->
	        </td>
	      </tr>
	      <tr>
	        <th width=20% class="text-center">소개</th>
	        <td width=80%>
	          <textarea rows="8" cols="35" name="content"></textarea>
	        </td>
	      </tr>
	      <tr>
	        <td colspan="2" class="text-center">
	          <input type="submit" value="전송" class="btn btn-sm btn-danger">
	        </td>
	      </tr>
	    </table>
	   </form>
	  </div>
	</div>
</body>
</html>






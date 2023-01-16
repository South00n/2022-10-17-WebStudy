<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
        EL => <%= %>를 대체 (화면에 데이터 출력) : 표현식
        -- Spring까지 이어진다, 실무
        자바 제어문 : => JSTL
        ------------------------------------------ 자바를 최소화
        1) 복잡 (HTML/자바 섞여있어서)
        2) Front / Back => 공동작업이 가능하게 만든다
        ------------------------------------------
        공부할 내용
        1. 내장객체 (525page)
           1) requestScope : request.getAttribute()와 동일
           2) sessionScope : session.getAttribute()와 동일
           3) param : request.getParameter()와 동일 // 메소드 대신 내장객체를 이용해서 접근하게 만든것
           => 사용 방식
              <%= %> 이게 ${}로 바뀜
              
              <%
                  이렇게 출력
                  request.setAttribute("name",name)
                                        ---- key
              %>
              ${requestScope.name}로 출력
                             ---- key값이 같아야함
                ------------ 생략이 가능 ==> ${name}가능
                
              <%
                  String name="홍길동";
              %>  
              ${name} ==> 출력하지 않는다
                ---- 변수가 아니다
              <%= name%>
        2. 연산자
 --%>
 <%
     String name = "홍길동";
     // ${}를 이용해서 출력하기 위해선 => request, session => 추가적으로 담아서 출력 => setAttribute()
     request.setAttribute("name", "심청이");
     session.setAttribute("name", "박문수");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일반 변수일때 출력</h1>
이름 : <%=name %><br>
<h1>EL을 이용</h1>
이름 : ${requestScope.name} <%-- <%=request.getAttribute("name")%> --%>
<br>
이름 : ${name} <%-- requestScope를 생략할 수 있다 --%>
<br>
<h1>Session에 저장된 데이터 읽기</h1>
이름 : ${sessionScope.name}<br> <%-- sessionScope 생략은 가능하지만 우선순위가 request --%>
이름 : ${name } <%-- 생략을하면 request에 저장된 값을 가져온다 --%>
</body>
</html>










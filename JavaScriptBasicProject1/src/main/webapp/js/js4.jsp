<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
         비교연산자 ( === (권장) , ==, !== (권장), !=, <, >, <=, >=) boolean
            => 문자열도 포함
         // 제어문, 함수, 이벤트 처리 (태그제어), 내장객체
                                         ------ window,document, form, location, history
                                                                       -----------------
         // 화면 제어 => DOMScript => Jquery(Ajax), Vue, React
         // Spirng-Boot, Vue, React, JSON (스웨거)
            -----------  ---------- JSP (HTML => ThymeLeaf)
         == 는 경고를 받는다
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function() {
	let a = 10;
	let b = 5;
	console.log("a === b : " + (a===b))
	console.log("a !== b : " + (a!==b))
	console.log("a < b : " + (a<b))
	console.log("a > b : " + (a>b))
	console.log("a <= b : " + (a<=b))
	console.log("a >= b : " + (a>=b))
	
	let c = "Hello";
	let d = "JavaScript";
	console.log("c === d : " + (c===d))
	console.log("c !== d : " + (c!==d))
	console.log("c < d : " + (c<d))
	console.log("c > d : " + (c>d))
	console.log("c <= d : " + (c<=d))
	console.log("c >= d : " + (c>=d))
	// 비교연산자에 문자열을 포함하고 있다 ===> 유효성 검사 if(name === ""), session.id === name
	
	/*
	    논리연산자 ==> 296page
	    &&  ||
	    (조건) && (조건)
	    -----    -----
	     결과      결과
	      |        |
	      ----------
	           |
	         최종결과
	         
	      && : 범위에 포함 : 예약일, 예약일 아닌 날, 체크인, 체크아웃
	           직렬연산자 ==> 조건이 모두 true일때만 true
	      || : 병렬연산자 ==> 조건중에 한개 이상의 true => true
	      
	      
	*/
	let i = (6>7) && (6===7)
	console.log("i = " + i)
	i = (6<7) || (6==7)
	console.log("i = " + i)
}
</script>
</head>
<body>

</body>
</html>
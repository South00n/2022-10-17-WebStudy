<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      이항 연산자
      = 산술연산자 (+, -, *, /, %)
        / = 나누기
        --------
           자바 (정수/정수 = 정수)
           자바스크립트는 (정수/정수 = 실수)
      = 비교연산자 (===, !==, <, >, <=, >=) : 결과값 boolean => 조건문
                 ==,  != (혼용) => ===권장
                 숫자, 문자열 비교시에도 사용을 한다
      = 대입연산자 (=, +=, -=)
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function() {
	let a = 5;
	let b = 2;
	console.log("a + b =" + (a+b));
	console.log("a - b =" + (a-b));
	console.log("a * b =" + (a*b));
	console.log("a / b =" + (a/b));
	console.log("a % b =" + (a%b));
	/*
	    a%b ===> 양수
	   -a%b ===> 음수
	   a%-b ===> 양수
	   -a%-b ==> 음수  (부호는 왼쪽기준)
	*/
	// 문자열일 경우 => 산술처리 (문자열 결합) => +는 문자열 결합, 나머지 연산자는 자동으로 숫자 변환후에 연산 처리
	                                  //------------  -----------------------------------
	// + => 문자열결합, 산술 처리
	let c = "10";
	let d = '20';
	// string형
	console.log("c + d = " + c+d)
	// 자바와 다르다
	console.log("c - d = " + (c-d)) // 문자형 => 숫자형으로 변환 ==> -10
	console.log("c * d = " + (c*d))
	console.log("c / d = " + (c/d))
	console.log("c % d = " + (c%d))
	
	let e = "10"
	let f = 20;
	console.log("e + f = " + e+f) // 1020 => 문자열 + 숫자 => 문자열
	
	// 주의점
	let m = 'A';
	let n = 10;
	console.log("m+n=" + (m+n)) // A10
	console.log("m-n=" + (m-n)) // 연산 결과값이 없다
	
	let k = 10;
	let p = 0;
	console.log("k/p=" + (k/p)) // infinity => 0으로 나눌 수 없다 (293page)
	
	let x = 'AB'
	let z = 1;
	console.log(x-z)
	console.log(x*z)
	/*
	    undefined => 변수의 초기화가 안된 상태, 변수선언이 없는 경우
	                 let a
	                 console.log(j)
	    NaN       => 문자열 + 숫자 (연산처리가 안된 상태)
	    Infinity  => 0으로 나눌 경우
	    
	    숫자형으로 문자열일 경우
	    "10", '100' => +는 문자열 결합, 나머지 산술연산자는 숫자로 변환후에 처리가 된다
	    숫자형이 아닌 경우 'AB' - 1 = > NaN
	    10/0 => Infinity
	*/

}
</script>
</head>
<body>

</body>
</html>








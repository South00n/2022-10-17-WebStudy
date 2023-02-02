package com.sist.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// DispatcherServlet => Spring (Controller) => 동작 => 구분(어노테이션), 등록(XML)
/*
 *      MVC 동작 과정
 *      
 *      1. 요청 ==> DispatcherServlet을 찾는다 Controller
 *                ------------------ URL주소로 찾는다 => .do (list.do, insert.do, delete.do) => DispatcherServlet)
 *         ** Controller : 요청을 받는다 => Model로 전송 (요청내용 전송 => request)
 *                         ------------------------
 *                         Model 처리결과를 받는다 (request) => request.setAttribute()(여러개를 사용 할 수 있다)
 *                         = JSP로 request를 전송 출력 => forward
 *                         = 이미 존재하는 화면으로 이동 => sendRedirect
 *         ** Model : 요청에 대한 처리 (데이터베이스 처리, 크롤링, request에 값을 담아주는 역할)
 *         ** View : Controller 전송한 request에 담긴 데이터 출력
 *         MVC사용 목적 : 보안(자바로 써있어서 .class파일로 넘길 수 있기때문), 역할분담이 가능 (Front-End, Back-End) => 여러명이 동시 작업
 *                                                                           --------------------
 *                                                                                          |
 *                                                                                   SQL담당 / 자바로 처리담당 => MyBatis
 *                      => Front-End + Back-End => Full Stack
 *                      자바 => 확장성, 재사용, 변경이 쉽다...(JSP는 한번 사용 후 버린다)                                        SQL문장 전송
 *         JSP (링크를 걸거나, 데이터를 전송) =======> 누구한테 보낼건지 (DispatcherServlet을 거치고) ========> Model =======> DAO =======> Oracle
 *                                                                    |                <========  |    <=======     <=======
 *                                                                    |                           |  ArrayList,VO    결과값 읽기  
 *                                                                    |                    가지고온 데이터를 request에 담는다
 *                                                     Model에서 받는 request를 해당 JSP로 보내준다
 *                                                                    |
 *                                                                  request에 담긴 데이터를 출력한 화면을 브라우저로 전송
 *                          **** DispatcherServlet은 고정 (수정하는 순간 서버를 내려야하기때문에)
 *                          **** Model이 가지고 있는 모든 메소드를 자동으로 호출이 가능하게 만든다 => 어노테이션, (invoke())
 *                          **** 어노테이션은 밑에 있는 내용을 제어
 *                          @ => TYPE 클래스 구분자
 *                          public class A {
 *                              @ => FIELD 멤버번수 구분자  
 *                              B b;
 *                              @ => METHOD 메소드 구분자
 *                              public void display() {
 *                              
 *                              }
 *                              public void disp(@ => PARAMETER 매개변수 구분자 B b) {
 *                              
 *                              }
 *                          }
 *                          
 *           <a><form> , ajax           ().do
 *                                     -------
 *                                     폴더 구분
 *                                     food/list.do (컨트롤러를 호출)
 *                                     notice/list.do
 *                                     ....
 *                                     
 *           Controller 역할
 *           1) 사용자 요청 => 해당 Model클래스를 찾는다 ==> 등록된 Model클래스 로딩
 *                          ------------------- 등록 (XML) => Spring
 *           2) 요청 처리 ==> Model 메소드 호출 => invoke
 *           3) Model 메소드 호출후 결과값 받는다 (request, session)
 *           4) JSP를 찾아서 request를 전송 => forward()
 *                       
 *           ==> 음식점 서빙역할 (Controller) 
 *                |
 *              음식주문 ==> 서빙 ==> 주방에 요청 (Model)
 *                    <==     <==
 *                  음식넘겨줌 주문음식받기 
 *           ==> Controller => 배달부 => Dispatcher
 *                                     Spring : DispatcherServlet (모든 언어에서 사용이 가능) => 호환성 (등록 : XML)
 *                                     Struts : FilterDispatcher (자바전용)              
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> clsList = new ArrayList<String>(); // apllication.xml에 등록된 클래스를 모아둔다 => Model클래스 인식(메뉴판)
	// 초기화 => 사용자에게 서비스를 하기전에 (JSP로 request해서 화면 브라우저에 출력하기전에)
	// Model클래스를 가지고 있어야 한다
	public void init(ServletConfig config) throws ServletException {
		// Model클래스를 읽어 온다 => 일반 Window형식 => 경로 : 1) GIT 문제가 생길 수 있음 2) AWS(리눅스)
		try {
			// 1. XML파일을 가지고 온다
			// 1-1. XML의 파일 읽기 => 호환성 => 실제 톰캣이 인식하고있는 폴더에서 읽기 (주소값)
			URL url = this.getClass().getClassLoader().getResource("."); // . => 현재 폴더에서 읽어와라
			File file = new File(url.toURI()); 
			System.out.println(file.getPath()); // 파일 경로 (리얼 path)
			// C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPFinalProject\WEB-INF\classes
			String path = file.getPath();
			path = path.replace("\\", File.separator);
			path = path.substring(0, path.lastIndexOf(File.separator)); // File.separator => window면 \\ , max => / 인식
			path = path + File.separator + "application.xml";
			
			// 2. 클래스명을 읽는다
			// 2-1. XML 파싱 => JAXP(DOM, SAX:MyBatis, Spring:SAX), JAXB (1.8)
			/*
			 *    DOM : 메모리 저장 => 트리형식 (속도가 늦다) => 추가, 수정, 삭제가 가능
			 *    SAX : 한줄씩 읽어서 데이터만 추출 (속도가 빠르다) => 추가, 수정, 삭제가 불가능
			 *    스프링, 마이바티스 => 파싱이 이미 되었다
			 */
			// 2-2 XML 파싱 : 파싱 : 파싱 클래스 생성
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// html, xml, wml, hdml .... Factory패턴 (DriverManager) => Spring (클래스 관리자) 요청한 클래스를 넘겨주는
			// 2-3 파싱기를 가지고 온다
			DocumentBuilder db = dbf.newDocumentBuilder();
			// 2-4 XML을 파싱 후 메모리에 트리형태로 저장 
			Document doc = db.parse(new File(path));
			// 2-5 최상위 태그 읽기 (테이블명) => <beans>
			Element root=doc.getDocumentElement();
			// 2-6 같은 태그를 묶어서 데이터를 읽기 시작
	         NodeList node=root.getElementsByTagName("bean");
	         //System.out.println(node.toString());
	         // 3. ArrayList에 저장
	         for(int i=0; i<node.getLength(); i++)
	         {
	            Element bean=(Element)node.item(i);
	            String cls=bean.getAttribute("class");
	            System.out.println(cls);
	            clsList.add(cls);
	         }
	      }catch(Exception ex) {}
	   }
	   // 브라우저에 필요한 데이터를 전송 위치
	   // Model이 가지고 있는 메소드를 호출
	   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // TODO Auto-generated method stub
	      // 사용자 요청 => 등록된 모델의 해당 메소드를 찾는다 => 호출 => request, session=>JSP로 전송
		   try {
			   // 1. 사용자가 요청한 주소를 확인 => ?뒤에는 자동으로 request
			   String uri = request.getRequestURI();
			   System.out.println(uri);
			   // /JSPFinalProject/main.do  ==>  구분자 : main/main.do
			   uri = uri.substring(request.getContextPath().length() + 1);
			   System.out.println("uri : " + uri);
			   // uri에 해당하는 메소드를 찾아서 호출 => 결과값 jsp로 전송
			   for(String cls : clsList) { // 등록된 클래스안에서 해당 요청의 메소드를 호출한다
				   Class clsName = Class.forName(cls);
				   // 클래스위에 @Controller가 없는 경우에는 제외 => 스프링
				   if(clsName.isAnnotationPresent(Controller.class) == false) {
					   continue; // 다음 클래스를 가져와라
				   }
				   // 1) 메모리 할당 (클래스)
				   Object obj = clsName.getDeclaredConstructor().newInstance();
				   // 2) 메소드를 찾아서 요청처리
				   Method[] methods = clsName.getDeclaredMethods();
				   for(Method m : methods) {
					   // 어노테이션을 이용해서 메소드를 찾는다
					   RequestMapping rm = m.getAnnotation(RequestMapping.class);
					   if(rm.value().equals(uri)) {
						   // 찾은 경우에 => 메소드를 호출해라
						   String jsp = (String)m.invoke(obj, request, response); // 메소드명은 상관없이 어노테이션이 일치하는 메소드 호출 할 수 있다
						   // request => JSP로 전송
						   // sendRedirect() => request를 초기화
						   // request 전송 => forward
						   if(jsp == null) {
							   return; // String이 아니라 void로 넘겨도 가능 (ajax)
						   }
						   else if(jsp.startsWith("redirect:")) { // _ok_jsp => Controller에서 직접 처리
							   response.sendRedirect(jsp.substring(jsp.indexOf(":") + 1));
						   } else {
							   RequestDispatcher rd = request.getRequestDispatcher(jsp);
							   rd.forward(request, response);
						   }
						   return;
					   }
				   }
			   }
		   } catch (Exception e) {}
	   }

	}










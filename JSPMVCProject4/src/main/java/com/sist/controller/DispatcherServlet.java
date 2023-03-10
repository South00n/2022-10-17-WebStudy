package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

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

import java.util.*;
import com.sist.model.*;
import java.net.*;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> list = new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		// String path="C:\\webDev\\webStudy\\JSPMVCProject4\\src\\main\\webapp\\WEB-INF\\application.xml";
		try {
			// xml의 경로명을 읽을때 사용 => Mac/Window의 경로 호환
			URL url = this.getClass().getClassLoader().getResource(".");
			File file = new File(url.toURI());
			System.out.println(file.getPath());
			String path = file.getPath();
			path = path.replace("\\", File.separator);
			path = path.substring(0, path.lastIndexOf(File.separator));
			path = path + "\\" + "application.xml";
			System.out.println(path);
			
			// 1. XML 파서기
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			// Jsoup(HTML Parser), DocumentBuilder(XML Parser) => 실무에서 사용하지 않는다
			Document doc = db.parse(new File(path));
			// Root 태그 => beans
			Element beans = doc.getDocumentElement();
			System.out.println(beans.getTagName());
			NodeList node = beans.getElementsByTagName("bean");
			for(int i=0; i<node.getLength(); i++) {
				Element bean = (Element)node.item(i);
				String clsName = bean.getAttribute("class");
				System.out.println(clsName);
				list.add(clsName);
			}
			
		} catch (Exception e) {}
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// uri 받기
		try {
			// http://localhost:8080/JSPMVCProject4/main/main.do
			String uri = request.getRequestURI(); // /JSPMVCProject4/main/main.do
			uri = uri.substring(request.getContextPath().length() + 1);  // main/main.do
			
			// 클래스 로딩
			for(String cls : list) {
				Class clsName = Class.forName(cls);
				if(clsName.isAnnotationPresent(Controller.class) == false) { // 클래스위에 @Controller가 없으면
						continue; // 제외를 한다
				}
				Object obj = clsName.getDeclaredConstructor().newInstance(); // Model클래스의 객체 생성
				// 선언된 모든 메소드를 읽어온다
				Method[] methods = clsName.getDeclaredMethods();
				for (Method m : methods) {
					RequestMapping rm = m.getAnnotation(RequestMapping.class); // 메소드위에 @RequestMapping을 읽어온다
					if(rm.value().equals(uri)) {
						String jsp = (String)m.invoke(obj, request, response); //메소드호출
						RequestDispatcher rd = request.getRequestDispatcher(jsp);
						rd.forward(request, response);
						break;
					}
				}
				
			}
		} catch (Exception e) {}
	}

}

package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.FoodVO;
import com.sist.vo.SeoulVO;

public class FoodDAO {
	private Connection conn; // 미리 연결됨 (톰캣)
	private PreparedStatement ps; // 송수신
	// Connection의 주소 얻기
	public void getConnection() {
		// 탐색기 형식 => 탐색기 열기 => C드라이버 접근 => Connection 객체주소 가지고 오기
		try {
			
			Context init=new InitialContext();
	         Context c=(Context)init.lookup("java://comp/env");
	         DataSource ds=(DataSource)c.lookup("jdbc/oracle"); // 이름으로 객체찾기
	         conn=ds.getConnection();
		} catch (Exception e) {}
	}
	
	// 반환
	public void disConnection() {
		try {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
			// POOL에 반환 (POOL : Connection객체가 저장된 메모리 공간)
		} catch (Exception e) {}
	}
	
	// 기능
	/*
	FNO     NOT NULL NUMBER         
	NAME    NOT NULL VARCHAR2(1000) 
	SCORE   NOT NULL NUMBER(2,1)    
	ADDRESS NOT NULL VARCHAR2(1000) 
	TEL     NOT NULL VARCHAR2(20)   
	TYPE    NOT NULL VARCHAR2(50)   
	PRICE            VARCHAR2(60)   
	TIME             VARCHAR2(60)   
	PARKING          VARCHAR2(60)   
	MENU             VARCHAR2(1000) 
	HIT              NUMBER         
	POSTER           VARCHAR2(2000) 
	 */
	public ArrayList<FoodVO> foodListData() {
		ArrayList<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql = "SELECT fno,name,poster "
					+ "FROM food_location "
					+ "WHERE fno < 40";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));;
				vo.setPoster(rs.getString(3));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
}

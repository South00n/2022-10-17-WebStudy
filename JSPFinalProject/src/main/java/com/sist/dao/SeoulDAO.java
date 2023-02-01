package com.sist.dao;

import java.util.*;

import com.sist.vo.FoodVO;
import com.sist.vo.SeoulVO;

import java.sql.*;

public class SeoulDAO {
	private Connection conn;
	private PreparedStatement ps;
	

	// 기능
	// 1-1. 목록
	public ArrayList<SeoulVO> seoulListData(int page, int type) {
		ArrayList<SeoulVO> list = new ArrayList<SeoulVO>();
		String[] table = {"", "location", "nature", "shop", "hotel", "guest"};
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT no, title, poster, num "
						+ "FROM (SELECT no, title, poster, rownum as num "
						+ "FROM (SELECT no, title, poster "
						+ "FROM seoul_" + table[type] + " ORDER BY no ASC)) "
						+ "WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowSize = 20;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SeoulVO vo = new SeoulVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return list;
	}
	// 1-1-1 총페이지
	public int seoulTotalPage(int type) {
		int total = 0;
		String[] table = {"", "location", "nature", "shop", "hotel", "guest"};
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT CEIL(COUNT(*)/20.0) FROM seoul_" + table[type];
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return total;
	}
/*
	NO      NOT NULL NUMBER         
	TITLE   NOT NULL VARCHAR2(200)  
	POSTER  NOT NULL VARCHAR2(500)  
	MSG     NOT NULL VARCHAR2(4000) 
	ADDRESS NOT NULL VARCHAR2(300)  
	HIT              NUMBER
 */
	// 1-2. 상세
	public SeoulVO seoulDetailData(int no) {
		SeoulVO vo = new SeoulVO();
		try {
			// Trigger => Spring (실무)
			conn = CreateConnnection.getConnection();
			String sql = "UPDATE seoul_location SET "
						+ "hit = hit + 1 "
						+ "WHERE no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			sql = "SELECT no, title, poster, msg, address "
				+ "FROM seoul_location "
				+ "WHERE no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
			vo.setMsg(rs.getString(4));
			vo.setAddress(rs.getString(5));
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return vo;
	}
	
	// 인근 맛집 읽기
	public List<FoodVO> seoulFoodFindData(String addr) {
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT fno, poster, name, type, rownum "
					+ "FROM food_location "
					+ "WHERE address LIKE '%'||?||'%' AND rownum <= 12";
			ps = conn.prepareStatement(sql);
			ps.setString(1, addr);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				String poster = rs.getString(2);
				poster = poster.substring(0, poster.indexOf("^"));
				vo.setPoster(poster);
				vo.setName(rs.getString(3));
				vo.setType(rs.getString(4));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return list;
	}
	
}

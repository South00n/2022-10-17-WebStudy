package com.sist.dao;

import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class ReserveDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	public List<FoodVO> reserveFoodListData(String fd) {
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT fno, poster, name, address, tel, rownum "
					+ "FROM food_location "
					+ "WHERE rownum <= 50 AND type LIKE '%'||?||'%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, fd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				String poster = rs.getString(2);
				poster = poster.substring(0, poster.indexOf("^"));
				poster = poster.replace("#", "&");
				vo.setPoster(poster);
				vo.setName(rs.getString(3));
				String address = rs.getString(4);
				address = address.substring(0, address.lastIndexOf("지")).trim();
				vo.setAddress(address);
				vo.setTel(rs.getString(5));
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
	
	// 예약일 읽기
	public String reserveDayData(int fno) {
		String rdate = "";
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT reserve_day FROM good_location "
					+ "WHERE fno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			rdate = rs.getString(1);
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return rdate;
	}
	// 예약시간
	public String reserveTimeData(int dno) {
		String times = "";
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT time FROM project_reserve_date "
					+ "WHERE dno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			times = rs.getString(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return times;
	}
	
	public String reserveTimeRealData(int tno) {
		String times = "";
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT time FROM project_reserve_time "
					+ "WHERE tno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, tno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			times = rs.getString(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return times;
	}
	// 예약 정보 저장
	// 예약 승인
}

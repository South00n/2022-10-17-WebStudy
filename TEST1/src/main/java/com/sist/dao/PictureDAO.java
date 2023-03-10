package com.sist.dao;

import java.util.*;
import java.sql.*;


public class PictureDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private final String url = "jdbc:oracle:thin:@211.63.89.131:1521:XE";
	
	public PictureDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, "hr", "happy");
		} catch (Exception e) {}
	}
	
	public void disConnection() {
		try {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		} catch (Exception e) {}
	}
	
	public void pictureDetailInsert(PictureVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO god_picture_3(gpno, image, title, name, content, content2, info, code, image2) "
					+ "VALUES(gp_gpno_seq_3.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getImage());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getContent());
			ps.setString(5, vo.getContent2());
			ps.setString(6, vo.getInfo());
			ps.setString(7, vo.getCode());
			ps.setString(8, vo.getImage2());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	public void ExhibitionDetailInsert(ExhibitionVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO god_exhibition_3(geno, poster, title, title2, kind, period, loc, loc2, area, area2, item, host, url, price, time, hashtag, content) "
					+ "VALUES(ge_geno_seq_3.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getPoster());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getTitle2());
			ps.setString(4, vo.getKind());
			ps.setString(5, vo.getPeriod());
			ps.setString(6, vo.getLoc());
			ps.setString(7, vo.getLoc2());
			ps.setString(8, vo.getArea());
			ps.setString(9, vo.getArea2());
			ps.setString(10, vo.getItem());
			ps.setString(11, vo.getHost());
			ps.setString(12, vo.getUrl());
			ps.setString(13, vo.getPrice());
			ps.setString(14, vo.getTime());
			ps.setString(15, vo.getHashtag());
			ps.setString(16, vo.getContent());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
}

package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sist.vo.LikeVO;

public class LikeDAO {
	private Connection conn;
	private PreparedStatement ps;
	// 마지막
	/*
	 *  try {
			conn = CreateConnnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
	 */
	
	public void likeInsert(LikeVO vo)
	   {
	      try
	      {
	         conn=CreateConnnection.getConnection();
	         String sql="INSERT INTO project_like VALUES("
	                 +"(SELECT NVL(MAX(lno)+1,1) FROM project_like),?,?)";
	         ps=conn.prepareStatement(sql);
	         ps.setInt(1, vo.getNo());
	         ps.setString(2, vo.getId());
	         ps.executeUpdate();
	      }catch(Exception ex)
	      {
	         ex.printStackTrace();
	      }
	      finally
	      {
	         CreateConnnection.disConnection(conn, ps);
	      }
	   }

	
	public int likeCount() {
		int count = 0;
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT COUNT(*) FROM project_like";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return count;
	}
	
	// 누른거 비활성화
	public int myLikeCount(int no, String id) {
		int count = 0;
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT COUNT(*) FROM project_like "
					+ "WHERE no = ? AND id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return count;
	}
	
	public int foodLikeCount(int fno) {
		int count = 0;
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT COUNT(*) FROM project_like "
					+ "WHERE no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return count;
	}
}

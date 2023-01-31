package com.sist.temp;

import java.sql.*;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url ="jdbc:oracle:thin:@localhost:1521:XE";
			conn = DriverManager.getConnection(url,"hr","happy");
			conn.setAutoCommit(false);
			String sql = "INSERT INTO card VALUES(2,'park',30000)";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			sql = "INSERT INTO point VALUES(1,'park',300)";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {}
		} finally {
			try {
				if(conn != null) conn.close();
				if(ps != null) ps.close();
			} catch (Exception e2) {}
		}
	}

}

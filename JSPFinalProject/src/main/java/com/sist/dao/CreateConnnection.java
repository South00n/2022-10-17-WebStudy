/*
package com.sist.dao;

import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
// DAO마다 사용 => 공통으로 사용되는 부분은 클래스화 => 공통모듈
public class CreateConnnection {
	private static Connection conn;
	public static Connection getConnection() {
		try {
			Context init = new InitialContext(); // 탐색기 열기?
			Context c = (Context)init.lookup("java://comp.env");
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		} catch (Exception e) {}
		return conn;
	}
	
	public static void disConnection(Connection conn, PreparedStatement ps) {
		try {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		} catch (Exception e) {}
	}
	
}*/

package com.sist.dao;

import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
public class CreateConnnection {
   private static Connection conn;
   public static Connection getConnection()
   {
      try
      {
         Context init=new InitialContext();
         Context c=(Context)init.lookup("java://comp/env");
         DataSource ds=(DataSource)c.lookup("jdbc/oracle");
         conn=ds.getConnection();
      }catch(Exception ex) {}
      return conn;
   }
   public static void disConnection(Connection conn, PreparedStatement ps)
   {
      try
      {
         if(ps!=null) ps.close();
         if(conn!=null) conn.close();
      } catch(Exception ex) {}
   }
}

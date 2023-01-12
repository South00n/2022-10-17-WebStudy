package com.sist.dao;

import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// POOL안에서 Connection 주소를 얻어 온다
	public void getConnection() {
		
	}
	
	// Connection 사용이 종료 반환
	public void disConnection() {
		
	}
	// 사용
}

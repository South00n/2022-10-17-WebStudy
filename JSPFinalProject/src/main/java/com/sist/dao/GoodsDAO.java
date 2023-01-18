package com.sist.dao;

import java.util.*;

import com.sist.vo.GoodsVO;

import java.sql.*;

public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// 기능
	// 1. 전체 출력
	public ArrayList<GoodsVO> goodsAllListData(int page) {
		ArrayList<GoodsVO> list = new ArrayList<GoodsVO>();
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT no, goods_name, goods_poster, num "
					+ "FROM (SELECT no, goods_name, goods_poster, rownum as num "
					+ "FROM (SELECT /*+ INDEX_ASC(goods_all ga_no_pk)*/no, goods_name, goods_poster "
					+ "FROM goods_all)) "
					+ "WEHRE num = BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowSize = 20;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo = new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
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
	public int goodsAllTotalPage() {
		int total = 0;
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT CEIL(COUNT(*)/20.0) FROM goods_all";
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
	// 2. 특가
	// 3. 베스트
	// 4. 신상품
}

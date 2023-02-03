package com.sist.dao;
import java.util.*;

import com.sist.vo.GoodsVO;

import java.sql.*;

public class GoodsDAO {
   private Connection conn;
   private PreparedStatement ps;
   //전체 목록 출력
   public ArrayList<GoodsVO> goodsAllListData(int page){
      ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
      try {
         conn=CreateConnnection.getConnection();
         String sql="SELECT no,goods_name,goods_poster,num "
               + "FROM (SELECT no,goods_name,goods_poster,rownum as num "
               + "FROM (SELECT /*+ INDEX_ASC(goods_all ga_no_pk)*/no,goods_name,goods_poster "
               + "FROM goods_all)) "
               + "WHERE num BETWEEN ? AND ?";
         ps=conn.prepareStatement(sql);
         int rowSize=20;
         int start=rowSize*(page-1)+1;
         int end=rowSize*page;
         ps.setInt(1, start);
         ps.setInt(2, end);
         ResultSet rs=ps.executeQuery();
         while(rs.next()) {
            GoodsVO vo=new GoodsVO();
            vo.setNo(rs.getInt(1));
            vo.setGoods_name(rs.getString(2));
            vo.setGoods_poster(rs.getString(3));
            list.add(vo);
         }
         rs.close();
      } catch(Exception ex) {
         ex.printStackTrace();
      } finally {
         CreateConnnection.disConnection(conn, ps);
      }
      return list;
   }
   public int goodsAllTotalPage() {
      int total=0;
      try {
         conn=CreateConnnection.getConnection();
         String sql="SELECT CEIL(COUNT(*)/20.0) FROM goods_all";
         ps=conn.prepareStatement(sql);
         ResultSet rs=ps.executeQuery();
         rs.next();
         total=rs.getInt(1);
         rs.close();
      } catch(Exception ex) {
         ex.printStackTrace();
      } finally {
         CreateConnnection.disConnection(conn, ps);
      }
      return total;
   }
   // 2. 베스트
   public ArrayList<GoodsVO> goodsBestListData(int page){
	      ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
	      try {
	         conn=CreateConnnection.getConnection();
	         String sql="SELECT no,goods_name,goods_poster,num "
	               + "FROM (SELECT no,goods_name,goods_poster,rownum as num "
	               + "FROM (SELECT /*+ INDEX_ASC(goods_best ga_no_pk)*/no,goods_name,goods_poster "
	               + "FROM goods_best)) "
	               + "WHERE num BETWEEN ? AND ?";
	         ps=conn.prepareStatement(sql);
	         int rowSize=20;
	         int start=rowSize*(page-1)+1;
	         int end=rowSize*page;
	         ps.setInt(1, start);
	         ps.setInt(2, end);
	         ResultSet rs=ps.executeQuery();
	         while(rs.next()) {
	            GoodsVO vo=new GoodsVO();
	            vo.setNo(rs.getInt(1));
	            vo.setGoods_name(rs.getString(2));
	            vo.setGoods_poster(rs.getString(3));
	            list.add(vo);
	         }
	         rs.close();
	      } catch(Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         CreateConnnection.disConnection(conn, ps);
	      }
	      return list;
	   }
	   public int goodsBestTotalPage() {
	      int total=0;
	      try {
	         conn=CreateConnnection.getConnection();
	         String sql="SELECT CEIL(COUNT(*)/20.0) FROM goods_best";
	         ps=conn.prepareStatement(sql);
	         ResultSet rs=ps.executeQuery();
	         rs.next();
	         total=rs.getInt(1);
	         rs.close();
	      } catch(Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         CreateConnnection.disConnection(conn, ps);
	      }
	      return total;
	   }
	   // 3. 신상품
	   public ArrayList<GoodsVO> goodsNewListData(int page){
		      ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
		      try {
		         conn=CreateConnnection.getConnection();
		         String sql="SELECT no,goods_name,goods_poster,num "
		               + "FROM (SELECT no,goods_name,goods_poster,rownum as num "
		               + "FROM (SELECT /*+ INDEX_ASC(goods_new ga_no_pk)*/no,goods_name,goods_poster "
		               + "FROM goods_new)) "
		               + "WHERE num BETWEEN ? AND ?";
		         ps=conn.prepareStatement(sql);
		         int rowSize=20;
		         int start=rowSize*(page-1)+1;
		         int end=rowSize*page;
		         ps.setInt(1, start);
		         ps.setInt(2, end);
		         ResultSet rs=ps.executeQuery();
		         while(rs.next()) {
		            GoodsVO vo=new GoodsVO();
		            vo.setNo(rs.getInt(1));
		            vo.setGoods_name(rs.getString(2));
		            vo.setGoods_poster(rs.getString(3));
		            list.add(vo);
		         }
		         rs.close();
		      } catch(Exception ex) {
		         ex.printStackTrace();
		      } finally {
		         CreateConnnection.disConnection(conn, ps);
		      }
		      return list;
		   }
		   public int goodsNewTotalPage() {
		      int total=0;
		      try {
		         conn=CreateConnnection.getConnection();
		         String sql="SELECT CEIL(COUNT(*)/20.0) FROM goods_new";
		         ps=conn.prepareStatement(sql);
		         ResultSet rs=ps.executeQuery();
		         rs.next();
		         total=rs.getInt(1);
		         rs.close();
		      } catch(Exception ex) {
		         ex.printStackTrace();
		      } finally {
		         CreateConnnection.disConnection(conn, ps);
		      }
		      return total;
		   }
	  // 4. 특가
		   public ArrayList<GoodsVO> goodsSpecialListData(int page){
			      ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
			      try {
			         conn=CreateConnnection.getConnection();
			         String sql="SELECT no,goods_name,goods_poster,num "
			               + "FROM (SELECT no,goods_name,goods_poster,rownum as num "
			               + "FROM (SELECT /*+ INDEX_ASC(goods_new ga_no_pk)*/no,goods_name,goods_poster "
			               + "FROM goods_special)) "
			               + "WHERE num BETWEEN ? AND ?";
			         ps=conn.prepareStatement(sql);
			         int rowSize=20;
			         int start=rowSize*(page-1)+1;
			         int end=rowSize*page;
			         ps.setInt(1, start);
			         ps.setInt(2, end);
			         ResultSet rs=ps.executeQuery();
			         while(rs.next()) {
			            GoodsVO vo=new GoodsVO();
			            vo.setNo(rs.getInt(1));
			            vo.setGoods_name(rs.getString(2));
			            vo.setGoods_poster(rs.getString(3));
			            list.add(vo);
			         }
			         rs.close();
			      } catch(Exception ex) {
			         ex.printStackTrace();
			      } finally {
			         CreateConnnection.disConnection(conn, ps);
			      }
			      return list;
			   }
			   public int goodsSpecialTotalPage() {
			      int total=0;
			      try {
			         conn=CreateConnnection.getConnection();
			         String sql="SELECT CEIL(COUNT(*)/20.0) FROM goods_Special";
			         ps=conn.prepareStatement(sql);
			         ResultSet rs=ps.executeQuery();
			         rs.next();
			         total=rs.getInt(1);
			         rs.close();
			      } catch(Exception ex) {
			         ex.printStackTrace();
			      } finally {
			         CreateConnnection.disConnection(conn, ps);
			      }
			      return total;
			   }
			   
			   public GoodsVO goodsDetailData(int no)
			   {
				   GoodsVO vo=new GoodsVO();
				   try
				   {
					  conn=CreateConnnection.getConnection();
					  String sql="SELECT no,goods_poster,goods_price,goods_name,goods_delivery,goods_first_price,account,"
							    +"goods_discount,goods_sub "
							    +"FROM goods_all "
							    +"WHERE no=?"; // MVC => 모든 전송 (Model) => request.setAttribute()
					  ps=conn.prepareStatement(sql);
					  ps.setInt(1, no);
					  ResultSet rs=ps.executeQuery();
					  rs.next();
					  vo.setNo(rs.getInt(1));
					  vo.setGoods_poster(rs.getString(2));
					  vo.setGoods_price(rs.getString(3));
					  vo.setGoods_name(rs.getString(4));
					  vo.setGoods_delivery(rs.getString(5));
					  vo.setGoods_first_price(rs.getString(6));
					  vo.setAccount(rs.getInt(7));
					  vo.setGoods_discount(rs.getInt(8));
					  vo.setGoods_sub(rs.getString(9));
					  rs.close();
				   }catch(Exception ex)
				   {
					   ex.printStackTrace();
				   }
				   finally
				   {
					   CreateConnnection.disConnection(conn, ps);
				   }
				   return vo;
			   }
}
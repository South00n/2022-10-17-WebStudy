package com.sist.dao;

import java.util.*;

import com.sist.vo.FreeBoardVO;

import java.sql.*;

/*
 *   SELECT, DELETE, UPDATE, INSERT
 *   인라인뷰 / 조인 / 서브쿼리 ==> 이쪽 공부
 *   
 *            Collection
 *                |
 *        ----------------------
 *        |       |            |
 *      List     Set          Map
 *        |
 *     ArrayList
 *     Vector
 *     
 *     ==> Spring => 리턴형 (List)
 */
public class FreeBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// 목록 출력
	public List<FreeBoardVO> boardListData(int page) {
		List<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD'),hit, num "
					+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(project_freeboard pf1_no_pk)*/no, subject, name, regdate, hit "
					+ "FROM project_freeboard)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowSize = 10;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FreeBoardVO vo = new FreeBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
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
	
	public int boardTotalPage() {
		int total = 0;
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT CEIL(COUNT(*)/10.0) FROM project_freeboard";
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
	// 추가
	public void boardInsert(FreeBoardVO vo)
	{
		try
		{
			conn=CreateConnnection.getConnection();
			String sql="INSERT INTO project_freeboard(no,name,subject,content,pwd) "
					  +"VALUES(pf_no_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			// 실행명령 ==> commit ==> executeUpdate() => commit 포함
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

	// 상세보기
	// 수정
	// 삭제
	// 검색 => 다중 검색
	// 댓글 ===> 프로시저
	
}

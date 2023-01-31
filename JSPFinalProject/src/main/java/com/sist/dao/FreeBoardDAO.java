package com.sist.dao;

import java.util.*;

import com.sist.vo.BoardReplyVO;
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
	public FreeBoardVO boardDetailData(int no) {
		FreeBoardVO vo = new FreeBoardVO();
		try {
			conn = CreateConnnection.getConnection();
			// 조회수 증가
			String sql = "UPDATE project_freeboard SET "
					+ "hit = hit + 1 "
					+ "WHERE no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			///////////////////////////////////// 조회수 증가
			// 실제 게시물번호에 해당되는 데이터를 가지고 온다 * => DataBase에 출력 순서로 읽는다
			sql = "SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD'), hit "
				+ "FROM project_freeboard "
				+ "WHERE no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no); // IN OUT
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return vo;
	}
	// 수정
	public FreeBoardVO boardUpdateData(int no) {
		FreeBoardVO vo = new FreeBoardVO();
		try {
			conn = CreateConnnection.getConnection();
			// 조회수 증가
			String sql = "SELECT no, name, subject, content "
				+ "FROM project_freeboard "
				+ "WHERE no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no); // IN OUT
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return vo;
	}
	
	public boolean boardUpdate(FreeBoardVO vo) {
		boolean bCheck = false;
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT pwd FROM project_freeboard "
					+ "WHERE no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			if(db_pwd.equals(vo.getPwd())) {
				bCheck = true;
				sql = "UPDATE project_freeboard SET "
						+ "name = ?, subject = ?, content = ? "
						+ "WHERE no = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				ps.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		return bCheck;
	}
	// 삭제
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck = false;
		try {
			conn = CreateConnnection.getConnection();
			String sql = "SELECT pwd FROM project_freeboard "
					+ "WHERE no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			if(db_pwd.equals(pwd)) {
				bCheck = true;
				sql = "DELETE FROM project_freeboard "
					+ "WHERE no = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
		
		return bCheck;
	}
	
	// 검색 => 다중 검색
	// 댓글 pr_rno_seq
	public void replyInsert(BoardReplyVO vo) {
		try {
			conn = CreateConnnection.getConnection();
			String sql = "INSERT INTO project_reply(rno, bno, id, name, msg, group_id) "
						+ "VALUES(pr_rno_seq.nextval, ?, ?, ?, ?, (SELECT NVL(MAX(group_id)+1,1) FROM project_reply))";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getBno());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getMsg());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnnection.disConnection(conn, ps);
		}
	}
	// 댓글 읽기
	public List<BoardReplyVO> replyListData(int bno) {
		List<BoardReplyVO> list = new ArrayList<BoardReplyVO>();
		try {
			conn=CreateConnnection.getConnection();
			String sql = "SELECT rno, bno, id, name, msg, group_tab, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') "
						+ "FROM project_reply "
						+ "WHERE bno = ? " // 게시물에 해당되는 댓글을 가져와라
						+ "ORDER BY group_id DESC, group_step ASC";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BoardReplyVO vo = new BoardReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setBno(rs.getInt(2));
				vo.setId(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setMsg(rs.getString(5));
				vo.setGroup_tab(rs.getInt(6));
				vo.setDbday(rs.getString(7));
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








package com.company.annotation.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.company.annotation.board.BoardDO;
import com.company.annotation.common.JDBCUtil;

public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public List<BoardDO> getBoardList(String searchField, String searchText) {
		System.out.println("===> JDBC로 getBoardList() 메소드 처리");
		
		// 자료구조 => 가변 배열
		List<BoardDO> boardList = new ArrayList<BoardDO>();
		
		try {
			conn = JDBCUtil.getConnection();
			String where = "";
			if(searchField != null && searchText != null) {
				where = "where " + searchField + " like '%" + searchText + "%'";
			}
			String BOARD_CONDITION_LIST = "select * from board " + where + " order by seq desc";
			
			pstmt = conn.prepareStatement(BOARD_CONDITION_LIST);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardDO board = new BoardDO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				boardList.add(board);
			}
		} catch (Exception e) {
			System.out.println("getBoardList(): " + e);
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return boardList;
	}  // End getBoardList() ===============================================================
	
	public BoardDO getBoard(BoardDO boardDO) {
		System.out.println("===> JDBC로 getBoard() 메소드 처리");
		
		BoardDO board = null;
		
		try {
			conn = JDBCUtil.getConnection();
			
			// [순서 중요] 어느 제목을 클릭하면 먼저 조회수를 1 증가 시킨 후 SELECT 하자!!
			String UPDATE_BOARD = "update board set cnt=cnt+1 where seq=?";
			pstmt = conn.prepareStatement(UPDATE_BOARD);
			pstmt.setInt(1, boardDO.getSeq());
			pstmt.executeUpdate();
			
			// select 작업
			String BOARD_GET = "select * from board where seq=?";
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, boardDO.getSeq());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				board = new BoardDO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
		} catch (Exception e) {
			System.out.println("getBoard() : " + e);
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return board;
	}  // End getBoard() 메소드 ========================================================
	
	public void updateBoard(BoardDO boardDO) {
		System.out.println("===> JDBC로 updateBoard() 메소드 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			
			String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
			
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getContent());
			pstmt.setInt(3, boardDO.getSeq());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("updateBoard() : " + e);
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}  // End updateBoard() 메소드 =====================================================
	
	public void insertBoard(BoardDO boardDO) {
		System.out.println("===> JDBC로 insertBoard() 메소드 처리");
		
		String BOARD_INSERT = "insert into board(seq, title, writer, content) " 
								+ "values((select nvl(max(seq),0)+1 from board),?,?,?)";
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, boardDO.getTitle());
			pstmt.setString(2, boardDO.getWriter());
			pstmt.setString(3, boardDO.getContent());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertBoard() : " + e);
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}  // End insertBoard() 메소드 ====================================================
	
	public void deleteBoard(BoardDO boardDO) {
		System.out.println("===> JDBC로 deleteBoard() 메소드 처리");
		
		String BOARD_DELETE = "delete from board where seq=?";
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, boardDO.getSeq());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("deleteBoard() : " + e);
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}  // End deleteBoard() 메소드 ====================================================
}

package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.DBUtil3;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {

	private static JdbcBoardDaoImpl dao;
	
	// 생성자
	private JdbcBoardDaoImpl() {	
		
	}
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao == null) dao = new JdbcBoardDaoImpl();
		return dao;
	}
	
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		int cnt = 0; // 반환값이 저장될 변수
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append("INSERT INTO JDBC_BOARD(BOARD_NO, BOARD_TITLE, BOARD_WRITER");
			builder.append("                     , BOARD_DATE, BOARD_CNT, BOARD_CONTENT)");
			builder.append("VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, DEFAULT, ?)");
			
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT, BOARD_CONTENT");
			builder.append("  FROM JDBC_BOARD");
			
			String sql = builder.toString();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO();

				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardWriter = rs.getString("BOARD_WRITER");
				String boardDate = rs.getString("BOARD_DATE");
				int boardCnt = rs.getInt("BOARD_CNT");
				String boardContent = rs.getString("BOARD_CONTENT");
				
				boardVo.setBoard_no(boardNo);
				boardVo.setBoard_title(boardTitle);
				boardVo.setBoard_title(boardTitle);
				boardVo.setBoard_writer(boardWriter);
				boardVo.setBoard_date(boardDate);
				boardVo.setBoard_cnt(boardCnt);
				boardVo.setBoard_content(boardContent);
				
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
			if(rs != null) try {rs.close();} catch(SQLException e) {}
		}
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoard(String keyWord) {
		List<JdbcBoardVO> boardList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT, BOARD_CONTENT");
			builder.append("  FROM JDBC_BOARD");
			builder.append(" WHERE BOARD_TITLE LIKE '%'||?||'%'");
			
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyWord);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO();

				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardWriter = rs.getString("BOARD_WRITER");
				String boardDate = rs.getString("BOARD_DATE");
				int boardCnt = rs.getInt("BOARD_CNT");
				String boardContent = rs.getString("BOARD_CONTENT");
				
				boardVo.setBoard_no(boardNo);
				boardVo.setBoard_title(boardTitle);
				boardVo.setBoard_title(boardTitle);
				boardVo.setBoard_writer(boardWriter);
				boardVo.setBoard_date(boardDate);
				boardVo.setBoard_cnt(boardCnt);
				boardVo.setBoard_content(boardContent);
				
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
			if(rs != null) try {rs.close();} catch(SQLException e) {}
		}
		return boardList;
	}

	@Override
	public JdbcBoardVO getReadBoard(int boardNo) {
		JdbcBoardVO boardVo = new JdbcBoardVO();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil3.getConnection();

			StringBuilder builder = new StringBuilder();
			builder.append("SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT, BOARD_CONTENT");
			builder.append("  FROM JDBC_BOARD");
			builder.append(" WHERE BOARD_NO = ?");

			String sql = builder.toString();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				boardVo = new JdbcBoardVO();

				String boardTitle = rs.getString("BOARD_TITLE");
				String boardWriter = rs.getString("BOARD_WRITER");
				String boardDate = rs.getString("BOARD_DATE");
				int boardCnt = rs.getInt("BOARD_CNT");
				String boardContent = rs.getString("BOARD_CONTENT");

				boardVo.setBoard_no(boardNo);
				boardVo.setBoard_title(boardTitle);
				boardVo.setBoard_title(boardTitle);
				boardVo.setBoard_writer(boardWriter);
				boardVo.setBoard_date(boardDate);
				boardVo.setBoard_cnt(boardCnt);
				boardVo.setBoard_content(boardContent);

			}
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
			if(rs != null) try {rs.close();} catch(SQLException e) {}
		}
		return boardVo;
	}

	@Override
	public int updateBoard(Map<String, String> paramMap, int boardNo) {
		// Key값 정보 : 제목(title), 내용(content)
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil3.getConnection();

			StringBuilder builder = new StringBuilder();
			builder.append("UPDATE JDBC_BOARD");
			builder.append("   SET BOARD_TITLE = ?");
			builder.append("     , BOARD_CONTENT = ?");
			builder.append(" WHERE BOARD_NO = ?");
			
			String sql = builder.toString();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("title"));
			pstmt.setString(2, paramMap.get("content"));
			pstmt.setInt(3, boardNo);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return cnt;
	}

	@Override
	public int updateBoardCnt(int boardNo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil3.getConnection();

			StringBuilder builder = new StringBuilder();
			
			builder.append("UPDATE JDBC_BOARD ");
			builder.append("   SET BOARD_CNT = (SELECT BOARD_CNT + 1 FROM JDBC_BOARD WHERE BOARD_NO = ?) ");
			builder.append(" WHERE BOARD_NO = ?");
			
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardNo);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil3.getConnection();
			
			StringBuilder builder = new StringBuilder();
			builder.append("DELETE FROM JDBC_BOARD ");
			builder.append(" WHERE BOARD_NO = ?");
			
			String sql = builder.toString();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return cnt;
	}

	@Override
	public int getBoardCount(int BoardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int cnt = 0; // 회원ID개수가 저장될 변수

		try {
			conn = DBUtil3.getConnection();

			StringBuilder builder = new StringBuilder();
			builder.append("SELECT COUNT(BOARD_NO) AS CNT");
			builder.append("  FROM JDBC_BOARD");
			builder.append(" WHERE BOARD_NO = ?");

			String sql = builder.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BoardNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return cnt;
	}

}

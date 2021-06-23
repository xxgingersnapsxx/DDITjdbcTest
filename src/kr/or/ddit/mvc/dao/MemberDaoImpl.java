package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

//Impl = implements
public class MemberDaoImpl implements IMemberDao {
// TODO 수정, 과제
	@Override
	public int insertMember(MemberVO memVo) {
		int cnt = 0; // 반환값이 저장될 변수
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil3.getConnection();

			String sql = "INSERT INTO MYMEMBER (MEM_ID, MEM_PASS, MEM_NAME,MEM_TEL, MEM_ADDR) VALUES (?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int deletetMember(String memId) {
		int cnt = 0; // 반환값이 저장될 변수
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(memId + "회원 정보 삭제 성공!!");
			} else {
				System.out.println(memId + "회원은 없는 회원이거나 삭제에 실패했습니다.");
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
	         if(pstmt!=null)try {pstmt.close();} catch (SQLException e2) {}
	         if(conn!=null)try {conn.close();} catch (SQLException e2) {}
		}

		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0; // 반환값이 저장될 변수
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
         conn = DBUtil.getConnection();
	         
	         String sql = "UPDATE MYMEMBER SET MEM_PASS=?,"
	               + "MEM_NAME=?, MEM_TEL=?, MEM_ADDR=?"
	               + "WHERE MEM_ID=?";
				pstmt.setString(1, memVo.getMem_pass());
				pstmt.setString(2, memVo.getMem_name());
				pstmt.setString(3, memVo.getMem_tel());
				pstmt.setString(4, memVo.getMem_addr());
				pstmt.setString(5, memVo.getMem_id());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = new ArrayList<>();

		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT * FROM MYMEMBER";
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MemberVO memVo = new MemberVO();
				String memId = rs.getString("mem_id");
				String memPass = rs.getString("mem_pass");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				memVo.setMem_id(memId);
				memVo.setMem_pass(memPass);
				memVo.setMem_name(memName);
				memVo.setMem_tel(memTel);
				memVo.setMem_addr(memAddr);

				memList.add(memVo);
			}
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int cnt = 0; // 반환값이 저장될 변수
		int count = 0;  // 회원ID개수가 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

}

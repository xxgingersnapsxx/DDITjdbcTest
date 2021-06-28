//package kr.or.ddit.mvc.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import kr.or.ddit.mvc.vo.MemberVO;
//import kr.or.ddit.util.DBUtil3;
//
//public class MemberDaoImplNoSingleton implements IMemberDao {
//
//	@Override
//	public int insertMember(MemberVO memVo) {
//		int cnt = 0; // 반환값이 저장될 변수
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = DBUtil3.getConnection();
//			String sql = "insert into mymember (mem_id, mem_pass, mem_name,"
//					+ "mem_tel, mem_addr) values (?, ?, ?, ?, ?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memVo.getMem_id());
//			pstmt.setString(2, memVo.getMem_pass());
//			pstmt.setString(3, memVo.getMem_name());
//			pstmt.setString(4, memVo.getMem_tel());
//			pstmt.setString(5, memVo.getMem_addr());
//
//			cnt = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			cnt = 0;
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null)
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//				}
//			if (conn != null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//		}
//
//		return cnt;
//	}
//
//	@Override
//	public int deleteMember(String memId) {
//		int cnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = DBUtil3.getConnection();
//			String sql = "delete from mymember where mem_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memId);
//
//			cnt = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			cnt = 0;
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null)
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//				}
//			if (conn != null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//		}
//
//		return cnt;
//	}
//
//	@Override
//	public int updateMember(MemberVO memVo) {
//		int cnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = DBUtil3.getConnection();
//
//			String sql = "update mymember set mem_pass=? ," + "mem_name=?, mem_tel=?, mem_addr=? " + "where mem_id=? ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memVo.getMem_pass());
//			pstmt.setString(2, memVo.getMem_name());
//			pstmt.setString(3, memVo.getMem_tel());
//			pstmt.setString(4, memVo.getMem_addr());
//			pstmt.setString(5, memVo.getMem_id());
//
//			cnt = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			cnt = 0;
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null)
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//				}
//			if (conn != null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//		}
//
//		return cnt;
//	}
//
//	@Override
//	public List<MemberVO> getAllMemberList() {
//		List<MemberVO> memList = new ArrayList<>();
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			conn = DBUtil3.getConnection();
//
//			String sql = "select * from mymember";
//			stmt = conn.createStatement();
//
//			rs = stmt.executeQuery(sql);
//
//			while (rs.next()) {
//				MemberVO memVo = new MemberVO();
//				String memId = rs.getString("mem_id");
//				String memPass = rs.getString("mem_pass");
//				String memName = rs.getString("mem_name");
//				String memTel = rs.getString("mem_tel");
//				String memAddr = rs.getString("mem_addr");
//				memVo.setMem_id(memId);
//				memVo.setMem_pass(memPass);
//				memVo.setMem_name(memName);
//				memVo.setMem_tel(memTel);
//				memVo.setMem_addr(memAddr);
//
//				memList.add(memVo);
//			}
//
//		} catch (SQLException e) {
//			memList = null;
//			e.printStackTrace();
//		} finally {
//			if (rs != null)
//				try {
//					rs.close();
//				} catch (SQLException e) {
//				}
//			if (stmt != null)
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//				}
//			if (conn != null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//		}
//
//		return memList;
//	}
//
//	@Override
//	public int getMemberCount(String memId) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		int count = 0; // 회원ID개수가 저장될 변수
//
//		try {
//			conn = DBUtil3.getConnection();
//
//			String sql = "select count(*) cnt from mymember where mem_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memId);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				count = rs.getInt("cnt");
//			}
//
//		} catch (SQLException e) {
//			count = 0;
//			e.printStackTrace();
//		} finally {
//			if (rs != null)
//				try {
//					rs.close();
//				} catch (SQLException e) {
//				}
//			if (pstmt != null)
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//				}
//			if (conn != null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//		}
//
//		return count;
//	}
//
//	@Override
//	public int updateMember2(Map<String, String> paraMap) {
//		// Key값 정보 : 회원 ID(memId), 수정할 컬럼명(field), 수정할 데이터(data)
//		int cnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = DBUtil3.getConnection();
//
//			String sql = "UPDATE MYMEMBER SET " + paraMap.get("field") + " = ?  WHERE MEM_ID = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, paraMap.get("data"));
//			pstmt.setString(2, paraMap.get("memId"));
//
//			cnt = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			cnt = 0;
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null)
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//				}
//			if (conn != null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//		}
//
//		return cnt;
//	}
//
//	@Override
//	public void createExcelFile(List memList, String filePath) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}

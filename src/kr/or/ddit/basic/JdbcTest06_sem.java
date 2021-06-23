package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

// 선생님 파일 다시 받아와야해.... TODO

/*
	회원 관리 프로그램을 작성하시오. (MYMEMBER 테이블 이용)
	
	아래 메뉴의 기능을 모두 구현하시오. (CRUD기능 구현하기)
	
	메뉴예시)
		-- 작업 선택 --
		1. 자료 추가
		2. 자료 삭제
		3. 자료 수정
		4. 전체 자료 출력
		5. 자료 수정2
		0. 작업 끝.
	--------------------
	작업선택 > 
	
	처리조건)
	1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	3) 자료 수정에서는 '회원ID'는 변경하지 않는다.	
	
	4) 자료 수정2 ==> 원하는 항목만 선택해서 수정되도록 한다.
	  
	  MVC패턴에 대하여 조사해오기
	
*/
public class JdbcTest06_sem {
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new JdbcTest06_sem().memberStart();
	}
	
	private void memberStart() {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
				case 1 :			// 추가
					insertMember();
					break;
				case 2 :			// 삭제
					deleteMember();
					break;
				case 3 :			// 수정
					updateMember();
					break;
				case 4 :			// 전체 자료 출력
					displayAllMember(); break;
				case 5 :			// 전체 자료 출력
					updateMember(); break;
				case 0 :
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println("번호를 잘못 입력 했습니다.");
					System.out.println("다시 입력 하세요.");
			}
		}
	}
	
	// 회원 정보를 수정하는 메서드
	private void updateMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count == 0) { // 없는 회원ID를 입력했을 경우
			System.out.println(memId + "은(는) 없는 회원ID 입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		int num;	// 선택한 항목의 번호가 저장될 변수
		String updateField = null; // 수정할 컬럼명이 저장될 변수
		String updateTitle = null; // 
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1. 비밀번호   2. 회원 이름   3. 전화번호   4. 주소");
			System.out.println("----------------------------------------------------");
			System.out.println("수정 항목 선택 >> ");
			num = scan.nextInt();
			
			switch (num) {
			case 1 : updateField = "MEM_PASS"; updateTitle = "비밀번호";
					 break;
			case 2 : updateField = "MEM_NAME"; updateTitle = "회원이름";
				     break;
			case 3 : updateField = "MEM_TEL"; updateTitle = "전화번호";
					 break;
			case 4 : updateField = "MEM_ADDR"; updateTitle = "회원주소";
				     break;
			default:
				System.out.println("수정할 항목을 잘못 선택했습니다.");
				System.out.println("다시 선택하세요.");
			}
		} while (num < 1 || num > 4);
		
		System.out.println("수정할 내용을 입력하세요.");
		scan.nextLine();	// 입력 버퍼 비우기
		System.out.println("새로운 " + updateTitle + "  >> ");
		String updateData = scan.nextLine();	// 주소가 있으므로 nextLine
		
		try {
			conn = DBUtil.getConnection();

			String sql = "UPDATE MYMEMBER SET " + updateField + " = ? WHERE MEM_ID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("수정 작업 성공~~");
			} else {
				System.out.println("수정 작업 실패!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}	

		System.out.print("새로운 패스워드 >> ");
		String memPass = scan.next();
		
		System.out.print("새로운 회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.print("새로운 회원주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set mem_pass=? ,"
					+ "mem_name=?, mem_tel=?, mem_addr=? "
					+ "where mem_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memPass);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			pstmt.setString(5, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("수정 작업 성공~~~");
			}else {
				System.out.println("수정 작업 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
	}
	
	
	// 회원 정보를 삭제하는 메서드
	private void deleteMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("삭제할 회원ID >> ");
		String memId = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + "회원 정보 삭제 성공!!");
			}else {
				System.out.println(memId + "회원은 없는 회원이거나 삭제에 실패했습니다.");
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
	}
	
	
	// 회원 정보를 추가하는 메서드
	private void insertMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		int count = 0;	// 중복 여부를 검사하기 위한 변수 (회원ID개수가 저장될 변수)
		String memId = null;
		do {
			System.out.print("회원ID >> ");
			memId = scan.next();
			
			count = getMemberCount(memId);
			
			if(count>0) {
				System.out.println(memId + "은(는) 이미 등록된 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
			}
			
		}while(count>0);
		
		System.out.print("패스워드 >> ");
		String memPass = scan.next();
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember (mem_id, mem_pass, mem_name,"
					+ "mem_tel, mem_addr) values (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId + "회원 등록 성공");
			}else {
				System.out.println(memId + "회원 등록 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
	}
	
	// 매개변수로 지정한 회원ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;  // 회원ID개수가 저장될 변수
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
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
		
		return count;		
	}
	
	
	
	// 전체 회원 정보를 출력하는 메서드
	private void displayAllMember() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println(" ID   PASSWORD   이 름       전화번호        주  소");
		System.out.println("------------------------------------------");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memPass = rs.getString("mem_pass");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				System.out.println(memId + "\t" + memPass + "\t" +
						memName + "\t" + memTel + "\t" + memAddr);
			}
			System.out.println("------------------------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
	}
	
	// 메뉴를 출력하고 실행할 작업번호를 입력받아서 반환하는 메서드
	private int displayMenu() {
		System.out.println("    -- 작업 선택 --");
		System.out.println("    1. 자료 추가");
		System.out.println("    2. 자료 삭제");
		System.out.println("    3. 자료 수정");
		System.out.println("    4. 전체 자료 출력");
		System.out.println("    0. 작업 끝.");
		System.out.println("--------------------");
		System.out.print("작업 선택 >> ");
		return scan.nextInt();
	}

}

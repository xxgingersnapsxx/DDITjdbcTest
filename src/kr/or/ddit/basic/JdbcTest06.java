package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
// TODO 나는 finally Close 없음.. 선생님 코드 참고하기

import kr.or.ddit.util.DBUtil;

/*
	회원 관리 프로그램을 작성하시오. (MYMEMBER 테이블 이용)
	
	아래 메뉴의 기능을 모두 구현하시오. (CRUD 기능 구현하기)
	
	메뉴예시)
		-- 작업 선택 --
		1. 자료 추가
		2. 자료 삭제
		3. 자료 수정
		4. 전체 자료 출력
		5. 자료 수정 2
		0. 작업 끝.
	 	----------------
	 	작업선택 >
	 	
	 	처리조건)
	 	1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
	 	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	 	3) 자료 수정에서는 '회원ID'는 변경하지 않는다.
	 	4) 자료 수정 ==> 원하는 항목만 선택해서 수정되도록 한다.
*/
public class JdbcTest06 {
	Scanner scanner;
	Connection conn;
	String sql;
	StringBuilder builder;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	String memId = null;
	String memPass = null;
	String memName = null;
	String memTel = null;
	String memAddr = null;
	int ifExist;
	
	// 생성자
	public JdbcTest06() {
		scanner = new Scanner(System.in);
		conn = DBUtil.getConnection();
	}
	
	public static void main(String[] args) {
		new JdbcTest06().memberStart();
	}
	
	// 시작 메서드
	private void memberStart() {
		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: // 자료 추가
				insertMember();
				break;
			case 2: // 자료 삭제
				deleteMember();
				break;
			case 3: // 자료 수정
				updateMember();
				break;
			case 4: // 전체 자료 출력
				readAllMember();
				break;
			case 5: // 자료 수정
				updateMember2();
				break;
			case 0: // 작업 끝
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
				break;
			}
		}
	}
	
	// 메뉴 출력
	private int displayMenu() {
		System.out.println("-- 작업 선택 --");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("5. 전체 자료 출력");
		System.out.println("0. 작업 끝.");
		System.out.println("---------------");
		System.out.print("작업선택 > ");
		int num = scanner.nextInt();
		System.out.println();
		
		return num;
	}
	
	public void insertMember() {
		try {
			ifExist = 1;
			while (ifExist == 1) {
				System.out.print("추가할 회원 ID 입력 : ");
				memId = scanner.next();
				
				ifExist = ifExist(memId);
				if(ifExist == 1) {
					System.out.println("이미 존재하는 회원 아이디입니다. 다시 입력하세요.");
				}
			}
				
				System.out.print("사용할 비밀번호 입력 : ");
				memPass = scanner.next();
				System.out.print("이름 입력 : ");
				memName = scanner.next();
				System.out.print("전화번호 입력 : ");
				memTel = scanner.next();
				System.out.print("주소 입력 : ");
				memAddr = scanner.next();
				
				builder = new StringBuilder();
				builder.append("INSERT INTO MYMEMBER(MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR)");
				builder.append("  VALUES(?, ?, ?, ?, ?)");
				
				sql = builder.toString();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, memId);
				pstmt.setString(2, memPass);
				pstmt.setString(3, memName);
				pstmt.setString(4, memTel);
				pstmt.setString(5, memAddr);
				
				pstmt.executeUpdate();
				
				System.out.println();
				System.out.println("새 회원 등록 완료");
				System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteMember() {
		try {
			
			ifExist = 0;
			
			while (ifExist == 0) {
				System.out.print("삭제할 회원 ID 입력 : ");
				memId = scanner.next();
				
				ifExist = ifExist(memId);
				
				if(ifExist == 0) {
					System.out.println("존재하지 않는 회원 아이디입니다. 다시 입력하세요.");
				}
			}
			
			builder = new StringBuilder();
			builder.append("DELETE MYMEMBER");
			builder.append(" WHERE MEM_ID = ?");

			sql = builder.toString();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			System.out.println();
			System.out.println("회원 " + memId + " 삭제 완료");
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateMember() {
		try {
			ifExist = 0;
			
			while (ifExist == 0) {
				System.out.print("수정할 회원 ID 입력 : ");
				memId = scanner.next();
				
				ifExist = ifExist(memId);
				
				if(ifExist == 0) {
					System.out.println("존재하지 않는 회원 아이디입니다. 다시 입력하세요.");
				}
			}
		
			System.out.print("수정할 비밀번호 입력 : ");
			memPass = scanner.next();
			System.out.print("수정할 이름 입력 : ");
			memName = scanner.next();
			System.out.print("수정할 전화번호 입력 : ");
			memTel = scanner.next();
			System.out.print("수정할 주소 입력 : ");
			memAddr = scanner.next();

			builder = new StringBuilder();
			builder.append("UPDATE MYMEMBER");
			builder.append("   SET MEM_PASS = ?");
			builder.append("     , MEM_NAME = ?");
			builder.append("     , MEM_TEL = ?");
			builder.append("     , MEM_ADDR = ?");
			builder.append(" WHERE MEM_ID = ?");

			sql = builder.toString();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memPass);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			pstmt.setString(5, memId);

			pstmt.executeUpdate();

			System.out.println();
			System.out.println("회원 " + memId + " 수정 완료");
			System.out.println();

				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateMember2() {
		try {
			ifExist = 0;
			
			while (ifExist == 0) {
				System.out.print("수정할 회원 ID 입력 : ");
				memId = scanner.next();
				
				ifExist = ifExist(memId);
				
				if(ifExist == 0) {
					System.out.println("존재하지 않는 회원 아이디입니다. 다시 입력하세요.");
				}
			}
			while (true) {
				String menuName = null;
				String inputData = null;
				System.out.print("수정을 원하는 정보의 번호 입력(1.비밀번호 2.이름 3.전화번호 4.주소 0.종료) : ");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1: // 비밀번호 수정
					menuName = "MEM_PASS";
					System.out.print("수정할 비밀번호 입력 : ");
					inputData = scanner.next();
					updateSelection(menuName, inputData);
					System.out.println(memId + "비밀번호 수정 완료");
					break;
				case 2: // 이름 수정
					menuName = "MEM_NAME";
					System.out.print("수정할 이름 입력 : ");
					inputData = scanner.next();

					updateSelection(menuName, inputData);
					System.out.println(memId + "이름 수정 완료");
					break;
				case 3: // 전화번호 수정
					menuName = "MEM_TEL";
					System.out.print("수정할 전화번호 입력 : ");
					inputData = scanner.next();

					updateSelection(menuName, inputData);
					System.out.println(memId + "전화번호 수정 완료");
					break;
				case 4: // 주소 수정
					menuName = "MEM_ADDR";
					System.out.print("수정할 주소 입력 : ");
					inputData = scanner.next();
					
					updateSelection(menuName, inputData);
					System.out.println(memId + "주소 수정 완료");
					break;
				case 0: // 종료
					System.out.println();
					System.out.println("회원 " + memId + " 수정 완료");
					System.out.println();
					return;
				default:
					break;
				}
			}

			

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void readAllMember() {
		try {
			builder = new StringBuilder();
			builder.append("SELECT MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR");
			builder.append("  FROM MYMEMBER");
			
			sql = builder.toString();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("-- 모든 회원 정보 --");
//			System.out.println("MEM_ID \t MEM_PASS \t MEM_NAME \t MEM_TEL \t MEM_ADDR");
			
			while(rs.next()) {
				System.out.println(rs.getString("MEM_ID") + "\t" + rs.getString("MEM_PASS") + "\t" + rs.getString("MEM_NAME") + "\t" + rs.getString("MEM_TEL") + "\t" + rs.getString("MEM_ADDR"));
			}
			System.out.println("-----------------------------------------------------------");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ID로 존재하는 회원인지 조회
	 * @return boolean
	 */
	public int ifExist(String memId) {
		try {
			builder = new StringBuilder();
			builder.append("SELECT COUNT(MEM_ID) AS CNT");
			builder.append("  FROM MYMEMBER");
			builder.append(" WHERE MEM_ID LIKE ?");
			
			sql = builder.toString();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("CNT");
			}

			return ifExist = cnt;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ifExist = -1;
		} 
	}
	
	/**
	 * 업데이트 상세메뉴
	 */
	public void updateSelection(String menuName, String inputData) throws SQLException {
		builder = new StringBuilder();
		builder.append("UPDATE MYMEMBER");
		builder.append("   SET " + menuName + " = ?");
		builder.append(" WHERE MEM_ID = ?");

		sql = builder.toString();
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, inputData);
		pstmt.setString(2, memId);

		pstmt.executeUpdate();
	}
}

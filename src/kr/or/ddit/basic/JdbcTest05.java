package kr.or.ddit.basic;

import java.nio.file.attribute.AclEntry.Builder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	LPROD테이블에 새로운 데이터 추가하기
 	
 	상품 분류 코드(LPROD_GU), 상품 분류명(LPROD_NM)은 직접 입력받아서 처리하고,
 	LPROD_ID는 현재의 LPROD_ID중 제일 큰 값보다 1 크게 한다.
 	그리고 입력받은 상품 분류코드(LPROD_GU)가 이미 등록되어 있으면 다시 입력받아서 처리한다. 
 */
public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuilder builder = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SJS94", "java");
			
			int ifExist = 1;
			String sql = null;
			String lprodGu = null;
			
			while (ifExist != 0) {
				System.out.print("LPROD_GU 입력 : ");
				lprodGu = scan.next();
				lprodGu = lprodGu.substring(0, 1).toUpperCase() + lprodGu.substring(1);

				builder = new StringBuilder();
				builder.append("SELECT COUNT(LPROD_GU) AS CNT");
				builder.append("  FROM LPROD");
				builder.append(" WHERE LPROD_GU LIKE ?");
				
				sql = builder.toString();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, lprodGu);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					ifExist = rs.getInt("CNT");
				}
				if(ifExist == 1) {
					System.out.println("이미 존재하는 LPROD_GU 입니다. 다시 입력하세요.");
				}			}
			System.out.print("LPROD_NM 입력 : ");
			String lprodNm = scan.next();
			builder = new StringBuilder();
			builder.append(" INSERT INTO LPROD(LPROD_ID, LPROD_GU, LPROD_NM)");
			builder.append(" SELECT MAX(LPROD_ID) + 1, ?, ? FROM LPROD");
			
			sql = builder.toString();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, lprodGu);
			pstmt.setString(2, lprodNm);

			
			pstmt.executeUpdate();
			System.out.println("완료");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if(conn != null) {try {conn.close();} catch (SQLException e) {}}
			if(scan != null) {scan.close();}
		}
	}
}

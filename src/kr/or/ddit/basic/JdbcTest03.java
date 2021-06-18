package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

// 문제 2) Lprod_id 값을 2개 입력 받아서 두 값중 작은 값부터 큰 값 사이의 자료들을 출력하시오.
public class JdbcTest03 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("LPROD_ID 첫번째 값 입력 : ");
//		int fstLprodId = scanner.nextInt();
		
		int num1 = scanner.nextInt();
		
		System.out.print("LPROD_ID 두번째 값 입력 : ");
		int num2 = scanner.nextInt();
//		int sndLprodId = scanner.nextInt();

		// 변수 2개만 써서 값 바꾸기
		if(num1 > num2) {
			num1 = num1 + num2;
			num2 = num1 - num2;
			num1 = num1 - num2;
		}

		/* 
		int min = 0;
		int max = 0;

		if (fstLprodId > sndLprodId) {
			min = sndLprodId;
			max = fstLprodId;
		} else {
			min = fstLprodId;
			max = sndLprodId;
		}
		*/
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SJS94", "java");

			StringBuilder builder = new StringBuilder();
			builder.append("SELECT LPROD_ID, LPROD_GU, LPROD_NM");
			builder.append("  FROM LPROD");
			builder.append(" WHERE LPROD_ID BETWEEN ? AND ?");

			String sql = builder.toString();
			stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, min);
//			stmt.setInt(2, max);
			stmt.setInt(1, num1);
			stmt.setInt(2, num2);

			rs = stmt.executeQuery();

			System.out.println();
			System.out.println(" -- SQL문 처리 결과 -- ");
			System.out.println();

			while (rs.next()) {
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
				System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
				System.out.println("--------------------------------------");
			}

			System.out.println();
			System.out.println("출력 끝...");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}

	}

}

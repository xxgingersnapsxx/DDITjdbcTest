package kr.or.ddit.basic;
/*
 	문제) 사용자로부터 Lprod_id값을 입력받아 입력한 값보다
 		  Lprod_id값이 큰 자료를 출력하시오
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcTest02 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Scanner scanner = new Scanner(System.in);
		System.out.print("LPROD_ID 입력 : ");
		int lprodId = scanner.nextInt();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SJS94", "java");

			StringBuilder builder = new StringBuilder();
			builder.append("SELECT LPROD_ID, LPROD_GU, LPROD_NM");
			builder.append("  FROM LPROD");
			builder.append(" WHERE LPROD_ID > ?");

			String sql = builder.toString();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, lprodId);

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

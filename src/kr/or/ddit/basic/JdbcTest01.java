package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 	JDBC(Java DataBase Connectivity)라이브러리를 이용하여 DB자료 처리하기
 	
 	- 데이터베이스 처리 순서
 		1. 드라이버 로딩 ==> 라이브러리를 사용할 수 있게 메모리로 읽어들이는 작업
 			Class.forname("oracle.jdbc.driver.OracleDriver");
 			==> JDBC API 버전 4 이상에서는 생략할 수 있다.
 			==> 생략하면 getConnection()메서드에서 자동으로 로딩해준다.
 			
 		2. DB에 접속하기 ==> 접속이 완료되면 Connection 객체가 반환된다.
 			DriverManager.getConnection()메서드를 이용한다.
 			
 		3. 질의 ==> SQL문장을 DB서버로 보내서 결과를 얻어온다.
 			(Statement객체나 PreparedStatement객체를 이용해 작업한다.)
 		
 		4. 결과 처리 ==> 질의 결과를 받아서 원하는 작업을 수행한다.
 			1) SQL문이 select문일 경우에는 select한 결과가 ResultSet객체에 저장되어 반환된다.
 			2) SQL문이 select문이 아닐 경우에는 정수값이 반환된다.
 				(이 정수값은 해당 SQL문이 실행에 성공한 레코드 수를 말한다.)
 		
 		5. 사용한 자원을 반납한다. ==> 각 객체의 close()메서드를 실행한다.
 */
public class JdbcTest01 {
	public static void main(String[] args) {
		// DB작업에 필요한 변수들 선언
		Connection conn = null;
		Statement stmt = null;
		// PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SJS94", "java");
			
			// 3. 질의
			// 3-1. SQL문 작성
			String sql = "SELECT * FROM LPROD";

			// 3-2. Statement객체 또는 PreparedStatement객체 생성
			//		(Connection객체를 이용해 생성한다.)
			stmt = conn.createStatement();
			
			// 3-3. SQL문 실행
			//		(실행할 SQL문이 select문이기 때문에 결과가 ResultSet객체에 저장되어 반환된다.)
			rs = stmt.executeQuery(sql);
			
			// 4. 결과 처리 ==> 한 레코드씩 화면에 출력하기
			System.out.println(" -- SQL문 처리 결과 --");
			
			// ResultSet에 저장된 데이터를 차례로 꺼내오려면 반복문과 next()메서드를 이용한다.
			
			// rs.next() : ResultSet 객체는 iterator과 비슷한 모양, 
			//			   ResultSet 객체의 데이터를 가리키는 포인터를 다음번째 레코드 위치로 이동시키고
			//			   그곳에 데이터가 있으면 true를 없으면 false를 반환한다.
			while (rs.next()) {
				// 포인터가 가리키는 곳의 데이터를 가져오기
				// 형식1) rs.get자료형이름("컬럼명");
				// 형식2) rs.get자료형이름(컬럼번호); ==> 컬럼 번호는 1번부터 시작한다.
				// 형식3) rs.get자료형이름("컬럼의 alias명")
				
				System.out.println("LPROD_ID : " + rs.getInt("lprod_id")); // 컬럼명은 소문자로 작성해도 됨
				System.out.println("LPROD_GU : " + rs.getString(2)); // LPROD_GU는 2번째 컬럼
				System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
				System.out.println("--------------------------------------");
			}
			System.out.println("출력 끝...");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 사용했던 자원 반납
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
		}
	}
}

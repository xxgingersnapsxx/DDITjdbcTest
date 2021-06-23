package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC드라이버를 로딩하고 Connection 객체를 생성하여 반환하는 메서드로 구성된 class이다.

// dbinfo.properties파일의 내용을 읽어서 설정하는 방법
// 방법1) Properties 객체 이용하기
public class DBUtil2 {
	static Properties prop;	// Properties 객체
	
	static {	// static 초기화 블럭

		prop = new Properties(); // Properties객체 생성

		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		
		try {
			fin = new FileInputStream(f);	// 입력스트립 객체 생성
			prop.load(fin);	// 파일 내용 읽어와 추가하기
			
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("설정 파일 입출력 오류...");
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			// return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
			// "SJS94", "java");
			// 이렇게 하면 나중에 DB변경시 properties의 값만 바꿔 DB사용 가능
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("오라클 DB 연결 실패");
			return null;
		}
	}
}


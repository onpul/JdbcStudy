/*===================
     DBConn.java
===================*/

// ※ 싱글톤(singleton) 디자인 패턴을 이용한 Database 연결 객체 생성 전용 클래스
//    → DB 연결 과정이 가장 부하가 크기 때문에
//       한 번 연결된 객체를 계속 사용하는 것이 좋지 않을까...
// 개발에서의 디자인 패턴 → 계층 구조를 어떻게 설계하느냐

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	// 변수 선언(초기화하지 않은 상태)
	private static Connection dbConn; // 추상적인 연결
	/*
	- Connection은 java.sql 패키지의 인터페이스
	- Connection 타입의 변수 dbConn 선언
	*/
	
	// 메소드 정의 → 연결
	public static Connection getConnection() throws ClassNotFoundException, SQLException // 구체적인 연결
	{
		// 한 번 연결된 객체를 계속 사용
		// 즉, 연결되지 않은 경우에만 연결을 시도하겠다는 의미
		// → 싱글톤(디자인 패턴)
		if (dbConn == null)
		{
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			//-- 『localhost』 는 오라클 서버의 ip 주소를 기재하는 부분
			//   『1521』 은 오라클 리스너 Port Number
			//   『xe』는 오라클 SID(Express Edition 의 SID 는 xe)
			
			String user = "scott";
			//-- 오라클 사용자 계정 이름
			
			String pwd = "tiger";
			//-- 오라클 사용자 계정 암호
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			//-- OracleDriver 클래스에 대한 객체 생성(클래스 찾아줘~)
			// Q. 이 부분이 왜 OracleDriver 클래스에 대한 객체 생성일까?
			// forName() 메소드를 통해 데이터베이스와 연결할 드라이버 클래스를 찾아서 로드한다.
			
			dbConn = DriverManager.getConnection(url, user, pwd);
			//-- 오라클 서버 실제 연결
			//   갖고 있는 인자값(매개변수)은 오라클 주소, 계정명, 패스워드
					
		}
		
		return dbConn;
		//-- 구성된 연결 객체 반환
	}
	
	
	// getConnection() 메소드의 오버로딩 → 연결
	public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException
	{
		if (dbConn == null)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		
		return dbConn;
	}
	
	// 메소드 정의 → 연결 종료
	public static void close() throws SQLException
	{
		// dbConn 변수(멤버 변수)는
		// Database 가 연결된 상태일 경우 Connection 을 갖는다.
		// 연결되지 않은 상태라면... null 인 상태가 된다.
		if (dbConn != null)
		{
			// 연결 객체(dbConn)의 isClosed() 메소드를 통해 연결 상태 확인
			//-- 연결이 닫혀있는 경우 true 반환
			//   연결이 닫혀있지 않은 경우 false 반환
			if (!dbConn.isClosed())
			{
				dbConn.close();
				//-- 연결 객체의 close() 메소드 호출을 통해 연결 종료~!!!
			}
		}
		
		dbConn = null;
		//-- 연결 객체 초기화
	}
}

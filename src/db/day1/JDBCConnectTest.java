package db.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//JDBC : Java DataBase Connection, 자바와 데이터베이스의 연결(연동) 방식을 제공
//		ㄴ 라이브러리 추가: 오라클일 때, ojdbcxxx.jar (라이브러리, 자바와 오라클 연동하기 위한 프로그램의 일종)
//		ㄴ 오라클 서버와 연결하기 위한 정보(url, port, user, password)가 필요합니다.
public class JDBCConnectTest {	//연결 테스트용 프로그램
	public static void main(String[] args) {
		String url ="jdbc:oracle:thin:@//localhost:1521/xe";
		//localhost는 진짜 서버가 있을 때는 도메인네임 또는 ip 입니다. localhost는 로컬 컴퓨터가 서버일때입니다.
		String user="idev";
		String password = "1234";
		//jdbc 오라클 드라이버로 사용되는 클래스를 메모리에  로드하기
		String driver = "oracle.jdbc.driver.OracleDriver";	//패키지이름.클래스명
		try {
			Class.forName(driver);	//패키지명.클래스명 이 잘못되면 exception
			
			//데이터베이스 연결정보를 이용하여 Connection 타입 객체 생성합니다.
			//		ㄴ 데이터베이스와 관련된 모든 작업은 conn 이 참조하는 객체를 이용해서 합니다.
			Connection conn = DriverManager.getConnection(url, user, password);
			if(conn==null) {
				System.out.println("데이터베이스 서버에 연결되지 못했습니다.");
			}
			//conn 객체(즉, 데이터베이스 연결) 사용이 종료되면 
			conn.close();
			System.out.println("연결상태 확인 : " + conn);
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 오류 : "+e.getMessage());
		} catch(SQLException e2) {
			System.out.println("연결 URL, 사용자 계정정보 오류 : "+ e2.getMessage());
		}
	}
}

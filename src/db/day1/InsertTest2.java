package db.day1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest2 {	//오라클 idev 스키마 custom# 테이블에 고객을 추가하는 연습  
							// SQL insert 실행
	public static void main(String[] args) {
		
		Connection conn = OracleConnectUtil.connect();
		System.out.println("main메소드 확인용"+conn);

		String sql="INSERT INTO TBL_CUSTOM#" + 
				" (CUSTOM_ID, NAME, EMAIL, AGE, REG_DATE)" + 
//				" VALUES('sana', '최사나', 'unknown', 22, sysdate)"; //항상 동일한 값 ->변경필요
		" VALUES(?, ?, ?, ?, sysdate)"; //값부분에 ? ->해당값은 execute전에 대입
				
		
		//sql 을 실행할 객체 생성
		//
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			// 위 ? 에 해당하는 값을 전달. ? 순서를 1부터 시작하는 index로 지정합니다.
			System.out.println("데이터베이스에 저장합니다. 값 입력하세요");
			System.out.print("CUSTOM_ID 입력 -> ");
			String CUSTOM_ID=sc.nextLine();
			System.out.print("NAME 입력 -> ");
			String NAME = sc.nextLine();
			System.out.print("EMAIL 입력 -> ");
			String EMAIL = sc.nextLine();
			System.out.print("AGE 입력 -> ");
			int AGE = Integer.parseInt(sc.nextLine());
			pstmt.setString(1, CUSTOM_ID);
			pstmt.setString(2, NAME);
			pstmt.setString(3, EMAIL);
			pstmt.setInt(4, AGE);
			pstmt.execute();
			System.out.println("고객 1건이 새로 등록되었습니다.!");
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
		
	}
}

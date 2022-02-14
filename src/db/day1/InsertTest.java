package db.day1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {	//오라클 idev 스키마 custom# 테이블에 고객을 추가하는 연습  
							// SQL insert 실행
	public static void main(String[] args) {
		
		Connection conn = OracleConnectUtil.connect();
		System.out.println("main메소드 확인용"+conn);

		String sql="INSERT INTO TBL_CUSTOM#" + 
				" (CUSTOM_ID, NAME, EMAIL, AGE, REG_DATE)" + 
//				" VALUES('sana', '최사나', 'unknown', 22, sysdate)"; //항상 동일한 값 ->변경필요
		" VALUES(?, ?, ?, ?, sysdate)"; //값부분에 ? ->해당값은 execute전에 대입
				
		
		//sql 을 실행할 객체 생성
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			// 위 ? 에 해당하는 값을 전달. ? 순서를 1부터 시작하는 index로 지정합니다.
			pstmt.setString(1, "sana22");
			pstmt.setString(2, "이사나");
			pstmt.setString(3, "sana@gmail.com");
//			pstmt.setNString(parameterIndex, value); //오라클데이터 타입이 NVARCHAR2, NCHAR일때
			pstmt.setInt(4, 27);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
		
	}
}

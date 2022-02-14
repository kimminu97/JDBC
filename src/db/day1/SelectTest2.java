package db.day1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest2 {
//ResultSet 타입 쿼리 결과를 모두 출력하기 : rs.next() 반복
	public static void main(String[] args) {

		Connection conn = OracleConnectUtil.connect();
		String sql = "select * from tbl_custom#";
		PreparedStatement pstmt = null;
		int n=1;
		// select 쿼리는 반환결과데이터를 저장해야합니다.
		ResultSet rs = null; // 쿼리 반환결과 테이블의 데이터를 참조합니다. 반환결과는 행단위로 접근
		// insert, update, delete는 쿼리 실행한 반환 결과데이터가 없습니다.

		try {
			pstmt = conn.prepareStatement(sql);

//			pstmt.execute();	//insert,update,delete
			rs = pstmt.executeQuery(); // select

			// 결과 확인하기 : 테이블데이터가 7개 행이 있는 상태입니다.
			while (rs.next()) {
				
				System.out.println(n+"행 데이터 보기----------------");
				System.out.print("custom_id : "+ rs.getString("custom_id") + "\t");
				System.out.print("name : "+rs.getString("name") + "\t");
				System.out.print("email : "+rs.getString("email") + "\t");
				System.out.print("age : "+rs.getInt("age") + "\t");
				System.out.println("reg_date : "+rs.getTimestamp("reg_date") + "\t");
				n++;
			}

			pstmt.close();
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
	}

}

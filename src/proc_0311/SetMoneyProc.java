package proc_0311;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import db.day1.OracleConnectUtil;

//데이터베이스에서 만든 proc_set_money 프로시저를 jdbc 방식으로
//실행합니다.
public class SetMoneyProc {

	public static void main(String[] args) {

		Connection conn = OracleConnectUtil.connect();
		String sql = "{call proc_set_money(?,?,?,?)}";
		// 데이터베이스 프로시저{call 프로시저이름(매개변수?) }

		// 프로시저에 실행할 때 필요한 매개변수 선언
		String custom_id = "wonder";
		String pcode = "CJ-BABQ1";
		int quantity = 5;

		CallableStatement cstmt = null;
		try {
			// 프로시저 실행은 preparedstatment가 아닌 callabestatement
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, custom_id);
			cstmt.setString(2, pcode);
			cstmt.setInt(3, quantity);
			//4번째 매개변수는 OUT 임을 알려주기
			cstmt.registerOutParameter(4, Types.VARCHAR);
			//정수/실수일때는 Types.numeric, 날짜 Types.Date
			int result = cstmt.executeUpdate();
			//실행 후 프로시저 출력매개변수 가져와서 print
			System.out.println("실행결과 : " + cstmt.getString(4));
			cstmt.close();
		} catch (SQLException e) {
			System.out.println("실행오류 : " + e.getMessage());
		} finally {
			OracleConnectUtil.close(conn);
		}
	}

}

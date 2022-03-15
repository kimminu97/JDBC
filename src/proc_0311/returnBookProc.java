package proc_0311;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

import db.day1.OracleConnectUtil;

public class returnBookProc {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = OracleConnectUtil.connect();
		String sql = "{call return_book(?,?,?)}";

		// 프로시저에 실행할 때 필요한 매개변수 선언
		System.out.println("도서 대여 프로그램 실행");
		System.out.print("회원번호를 입력하세요 ->");
		int amem_idx=sc.nextInt();
		sc.nextLine();
		System.out.print("책 코드를 입력하세요 -> ");
		String abcode = sc.nextLine();
		System.out.print("반납날짜를 입력하세요 -> ");
		String now = sc.nextLine();
//		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		Date areturn_date = java.sql.Date.valueOf(now);
		
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, amem_idx);
			cstmt.setString(2, abcode);
			cstmt.setDate(3, areturn_date);
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

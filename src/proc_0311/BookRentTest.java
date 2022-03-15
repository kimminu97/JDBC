package proc_0311;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import db.day1.OracleConnectUtil;

public class BookRentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = OracleConnectUtil.connect();
		String sql="{ call return_book(?,?,?,?) }";				
		//데이터베이스 프로시저 {call 프로시저이름(매개변수?)}
		
		//프로시저에서 실행할 때 필요한 매개변수 선언
		String mem_idx;
		String bcode;
		String pdate;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("회원IDX 입력 --> " );
		mem_idx = sc.nextLine();
		System.out.print("도서 코드 입력 --> ");
		bcode = sc.nextLine();
		System.out.print("반납일자 --> ");
		pdate = sc.nextLine();//Date.valueOf(sc.nextLine());
		
		//프로시저 실행은 PreparedStatement 아니고 
		CallableStatement cstmt=null;
		
		try {
			
			cstmt = conn.prepareCall(sql);
			//매개변수값 설정
			cstmt.setString(1, mem_idx);
			cstmt.setString(2, pdate);
			cstmt.setString(3, bcode);
			
			//4번쨰 매개변수는 OUT 임을 알려주기 
			cstmt.registerOutParameter(4, Types.VARCHAR);
			// 정수/실수 일때는 Types.Numeric , 날짜 Types.Date
			
			cstmt.executeUpdate();		//프로시저 실행.
			
			// 실행 후 프로시저 출력 매개변수 가져와서 print
			System.out.println("실행 결과 : " + cstmt.getString(4));
			
			cstmt.close();
		}catch(SQLException e) {
			System.out.println("실행 오류 : " + e.getMessage());
		}finally {
			OracleConnectUtil.close(conn);
		}

	}

}
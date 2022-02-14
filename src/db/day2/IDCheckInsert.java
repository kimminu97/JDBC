package db.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db.day1.OracleConnectUtil;

public class IDCheckInsert {

	private static Connection conn = OracleConnectUtil.connect();
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String sql = "INSERT INTO TBL_CUSTOM#" + " (CUSTOM_ID, NAME, EMAIL, AGE, REG_DATE)"
				+ " VALUES(?, ?, ?, ?, sysdate)";

		System.out.println("고객을 등록합니다. 먼저 사용할 ID 입력하고 중복확인 합니다.");
		System.out.print("ID 입력 -> ");
		String id = sc.nextLine();
		while (true) {
			if (idCheck(id)) {
				System.out.println("사용할 수 있는 아이디입니다.");
				break;
			} else {
				System.out.println("중복된 id가 있습니다. 다른 id로 해주세요...");
				System.out.print("ID 입력 -> ");
				id = sc.nextLine();
			}
		}
		System.out.println("등록할 id : \""+id+"\"입니다.");
		
		System.out.print("NAME 입력 -> ");
		String NAME = sc.nextLine();
		System.out.print("EMAIL 입력 -> ");
		String EMAIL = sc.nextLine();
		System.out.print("AGE 입력 -> ");
		int AGE = Integer.parseInt(sc.nextLine());
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, NAME);
			pstmt.setString(3, EMAIL);
			pstmt.setInt(4, AGE);
			pstmt.execute();
			System.out.println("고객님 환영합니다.!!");
			pstmt.close();
			
		
		}catch(SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
	}

	public static boolean idCheck(String id) {
		boolean result = false;
		String sql ="select * from tbl_custom# where custom_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(!rs.next())
				result = true;
			pstmt.close();
		}catch(SQLException e) {
			System.out.println("SQL 실행 오류 : "+e.getMessage());
		}
		return result;	//중복되면 false, 중복없으면 true
	}

}

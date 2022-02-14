package db.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import db.day1.OracleConnectUtil;

public class UpdateTest {

	public static void main(String[] args) {

		Connection conn = OracleConnectUtil.connect();
		PreparedStatement pstmt;

		String sql = "UPDATE TBL_CUSTOM# SET EMAIL=?" + "WHERE CUSTOM_ID=?";
		Scanner sc = new Scanner(System.in);

		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("고객의 email을 수정합니다");
			System.out.print("수정하려는 고객의 ID를 입력하세요 -> ");
			String custom_id = sc.nextLine();
			while (true) {
				if (!IDCheckInsert.idCheck(custom_id)) {
					pstmt.setString(2, custom_id);
					System.out.print("새로운 email을 입력하세요 -> ");
					String email = sc.nextLine();
					pstmt.setString(1, email);
					pstmt.execute();
					System.out.println("수정되었습니다.");
					break;
				}else {
					System.out.println("존재하지 않는 고객 ID입니다 다시 입력하세요");
					System.out.print("수정하려는 고객의 ID를 입력하세요 -> ");
					custom_id = sc.nextLine();
				}
			}
		} catch (SQLException e) {
			System.out.println("sql 실행 오류 : " + e.getMessage());
		}

	}

}

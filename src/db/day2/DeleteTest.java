package db.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import db.day1.OracleConnectUtil;

public class DeleteTest {

	public static void main(String[] args) {

		Connection conn = OracleConnectUtil.connect();
		PreparedStatement pstmt;
		String sql = "DELETE FROM TBL_CUSTOM#" + "WHERE CUSTOM_ID=?";
		Scanner sc = new Scanner(System.in);
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("고객정보를 삭제합니다.");
			System.out.print("탈퇴하려는 고객의 ID를 입력하세요 -> ");
			String custom_id = sc.nextLine();
			pstmt.setString(1, custom_id);
			while (true) {
				if (!IDCheckInsert.idCheck(custom_id)) {
					pstmt.setString(1, custom_id);
					pstmt.execute();
					System.out.println("고객정보를 삭제하였습니다.");
				} else {
					System.out.println("존재하지 않는 고객의 ID입니다. 다시 입력해주세요");
					System.out.print("탈퇴하려는 고객의 ID를 입력하세요 -> ");
					custom_id = sc.nextLine();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
	}

}

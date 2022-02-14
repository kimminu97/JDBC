package db.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.day1.OracleConnectUtil;
import db.vo.Custom;

public class VOClassTest {
	//SelectTest2.java를 vo 이용하여 자바변수에 저장하는 연습
	public static void main(String[] args) {
		
		Connection conn = OracleConnectUtil.connect();
		String sql = "select * from tbl_custom#";
		PreparedStatement pstmt = null;
		ResultSet rs=null;
//		int n=1;
		List<Custom> customs = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // select

			while (rs.next()) {
				//select 조회 결과를 한명씩 가져와서 List에 추가
				//getXXX(인덱스) : 인덱스는 select 뒤에 나열된 컬럼의 순서입니다.
				customs.add(new Custom(rs.getString(1),
						rs.getNString(2), 
						rs.getString(3), 
						rs.getInt(4),
						rs.getDate(5)));
				
				/*
				System.out.println(n+"행 데이터 보기----------------");
				System.out.print("custom_id : "+ rs.getString("custom_id") + "\t");
				System.out.print("name : "+rs.getString("name") + "\t");
				System.out.print("email : "+rs.getString("email") + "\t");
				System.out.print("age : "+rs.getInt("age") + "\t");
				System.out.println("reg_date : "+rs.getTimestamp("reg_date") + "\t");
				n++;
				*/
			}
			System.out.println("List에 저장된 값 확인 ----------------");
			System.out.println(customs);
			System.out.println("List의 데이터를 1개씩 출력");
			for (int i = 0; i < customs.size(); i++) {
				System.out.print(String.format("%-3s", i+1));
				System.out.println(customs.get(i));
			}

			pstmt.close();
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
	}
}

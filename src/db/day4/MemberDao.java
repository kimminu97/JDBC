package db.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.day1.OracleConnectUtil;
import db.vo.Member;

public class MemberDao {
	private static MemberDao memberdao = new MemberDao();
	private MemberDao() {};
	public static MemberDao getMemMonDao() {
		return memberdao;
	}
	
	//회원매출조회
	public List<? extends Object> SelectSales() {
		Connection conn = OracleConnectUtil.connect();
		PreparedStatement pstmt = null;
		String sql = "SELECT mt.custno 회원번호 ,custname 회원성명," + 
				"DECODE(grade,'A','VIP','B','일반','C','직원') AS \"고객등급\" , p.\"매출\" " + 
				"FROM MEMBER_TBL_02 mt ," + 
				"(SELECT CUSTNO ,SUM(price) \"매출\" FROM MONEY_TBL_02 mt GROUP BY CUSTNO) P" + 
				"WHERE mt.CUSTNO =p.custno ORDER BY \"매출\" DESC";
		// 기본키컬럼 조회결과는 0또는 1개 행입니다.
		ResultSet rs = null;
		List<String> mList = new ArrayList<>();
		Member member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mList.add(rs.getString(1));
				mList.add(rs.getString(2));
				mList.add(rs.getString(3));
				mList.add(rs.getString(4));
			}
			pstmt.close();
		}catch(SQLException e) {
			System.out.println("SQL 실행 오류: "+ e.getMessage());
		}
		return mList;
	}
}

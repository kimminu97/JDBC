package db.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.day1.OracleConnectUtil;
import db.vo.SaleByCustom;

public class SaleByDao {
//v_by_custno view 에서 select 쿼리 실행하는 메소드 정의
	//싱글턴 객체 생성코드
	private static SaleByDao saleByDao = new SaleByDao();
	private SaleByDao() {}
	public static SaleByDao getSaleByDao() {
		return saleByDao;
	}
	
	public  List<SaleByCustom> selectByCustno() {
		Connection conn = OracleConnectUtil.connect();
		String sql ="SELECT * FROM V_BY_CUSTNO";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SaleByCustom vo = null;
		List<SaleByCustom> sList =new ArrayList<SaleByCustom>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				vo = new SaleByCustom();
				vo.setCntno(rs.getInt(1));
				vo.setCustname(rs.getString(2));
				vo.setGrade(rs.getString(3));
				vo.setSales(rs.getInt(4));
				sList.add(vo);
			}
			pstmt.close();
		}catch(SQLException e) {
			System.out.println("SQL 실행오류 : "+ e.getMessage());
		}
		OracleConnectUtil.close(conn);
		return sList;
	}
}

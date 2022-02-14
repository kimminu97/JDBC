package db.day3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.day1.OracleConnectUtil;
import db.vo.Custom;
import db.vo.Product;

public class ProductDao {
	// 변수명 한꺼번에 바꾸기 : alt + shift + r
//singleton

	private static ProductDao productDao = new ProductDao();

	private ProductDao() {
	}

	public static ProductDao getproductDao() {
		return productDao;
	}

	// insert
	public void insert(Product vo) {
		Connection conn = OracleConnectUtil.connect();
		String sql = "INSERT INTO TBL_PRODUCT#" + "(PCODE, CATEGORY, PNAME, PRICE)" + "VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPcode());
			pstmt.setString(2, vo.getCategory());
			pstmt.setString(3, vo.getPname());
			pstmt.setInt(4, vo.getPrice());
			pstmt.execute();
			System.out.println("상품 1건이 등록되었습니다.");
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
	}

	// update
	public void update(Product vo) {
		Connection conn = OracleConnectUtil.connect();
		String sql = "UPDATE TBL_PRODUCT#" + "SET CATEGORY=?, PNAME=?, PRICE=?" + "WHERE PCODE=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCategory());
			pstmt.setString(2, vo.getPname());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setString(4, vo.getPcode());
			pstmt.execute();
			System.out.println("상품 1건이 수정되었습니다.");
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
	}

	// delete
	public void delete(String pcode) {
		Connection conn = OracleConnectUtil.connect();
		String sql = "DELETE FROM TBL_PRODUCT#" + "WHERE PCODE=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pcode);
			System.out.println("상품 1건이 삭제되었습니다.");
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);

	}

	// selectAll
	public List<Product> selectAll() {
		Connection conn = OracleConnectUtil.connect();
		String sql = "Select * from tbl_product#";
		List<Product> products = new ArrayList<>();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				products.add(new Product(rs.getString(1), // 방법1) 커스텀생성자
						rs.getString(2), rs.getString(3), rs.getInt(4)));
				pstmt.close();
			}
			System.out.println("상품이 조회되었습니다.");
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
		return products;
	}

	// selectOne
	public Product selectOne(String pcode) {
		Connection conn = OracleConnectUtil.connect();
		String sql = "Select * from tbl_product# where pcode=?";
		ResultSet rs = null;
		Product vo = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			pstmt.setString(1, pcode);
			if (rs.next()) { // 조회결과가 1개 행 있으면
				vo = new Product();
				vo.setPcode(rs.getString(1));
				vo.setCategory(rs.getString(2));
				vo.setPname(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				pstmt.close();
			}

			System.out.println("상품이 조회되었습니다.");
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
		return vo;
	}
	
	public Product selectCategory(String category) {
		Connection conn = OracleConnectUtil.connect();
		String sql = "Select * from tbl_product# where category=?";
		ResultSet rs = null;
		Product vo = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			pstmt.setString(1, category);
			if (rs.next()) { // 조회결과가 1개 행 있으면
				vo = new Product();
				vo.setPcode(rs.getString(1));
				vo.setCategory(rs.getString(2));
				vo.setPname(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				pstmt.close();
			}

			System.out.println("상품이 카테고리별로 조회되었습니다.");
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
		return vo;
	}

}

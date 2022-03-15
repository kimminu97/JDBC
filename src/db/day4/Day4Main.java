package db.day4;

import java.util.List;

import db.vo.SaleByCustom;

public class Day4Main {

	public static void main(String[] args) {

		SaleByDao saleDao = SaleByDao.getSaleByDao();
		List<SaleByCustom> list = saleDao.selectByCustno();
		//제목도 출력위치 String.format으로 하세요
		System.out.println(String.format("%-7s%-10s%-10s\t%-10s",
				"고객번호","고객성명","고객등급","총매출금액"));
		for(SaleByCustom vo : list)
			System.out.println(vo);
		
		MemberDao memberDao = MemberDao.getMemMonDao();
		List<? extends Object> mlist = memberDao.SelectSales();
		System.out.println(String.format("%-7s%-10s%-10s\t%-10s",
				"고객번호","고객성명","고객등급","총매출금액"));
		for(Object md : mlist)
			System.out.println(md);
	}

}

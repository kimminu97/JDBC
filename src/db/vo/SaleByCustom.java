package db.vo;

public class SaleByCustom {
	//v_by_custno view에 있는 4개의 컬럼과 메핑될 필드 선언
	private int cntno;
	private String custname;
	private String grade;
	private int sales;
	
	public SaleByCustom(int cntno, String custname, String grade, int sales) {
		super();
		this.cntno = cntno;
		this.custname = custname;
		this.grade = grade;
		this.sales = sales;
	}
	public SaleByCustom() {
		// TODO Auto-generated constructor stub
	}
	public int getCntno() {
		return cntno;
	}
	public void setCntno(int cntno) {
		this.cntno = cntno;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	@Override
	public String toString() {
//		return cntno + " " + custname + " " + grade + " " + sales;
		return String.format("%-7s%-10s%-10s\t%-10s",cntno,custname,grade,sales);
	}
	
}

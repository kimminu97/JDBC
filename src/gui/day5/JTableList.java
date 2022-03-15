package gui.day5;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class JTableList extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultTableModel dm;
	JTable jt;
	String[] cols = {"English","Korean","Level","Write date"};
	
	public JTableList() {
		List<Word> list = new ArrayList<>();
		list.add(new Word("kkk", "와이", 1, LocalDate.parse("2021-05-01")));
		list.add(new Word("yyy", "케이", 2, LocalDate.parse("2021-05-01")));
		String find="";
		
		//테이블 컴포넌트 생성
		dm = new DefaultTableModel(null, cols);   //cols는 테이블 제
		jt = new JTable(dm);
		
		String[] data = new String[4];
		
		for(int i=0;i<list.size();i++) {
			Word temp = list.get(i);
			boolean flag;
			if(find.equals("")) flag =true;
			else
				flag = temp.getEnglish().equals(find);
			
			if(flag) {
				data[0] = temp.getEnglish();
				data[1] = temp.getKorean();
				data[2] = String.valueOf(temp.getLevel());
				data[3] = temp.getWday().toString();
				dm.addRow(data);
			}
		}
	
		Container ctn = getContentPane();   
		JScrollPane jsp = new JScrollPane(jt);  

		JLabel la1 = new JLabel("검색 단어");
		la1.setBounds(10,10,100,30);

		JTextField jtf1 = new JTextField();
		jtf1.setBounds(120,10,200,30);
		
		String[] temp = {"english","korean"};
		JComboBox<String> jc = new JComboBox<>(temp);
		jc.setBounds(330, 10, 100, 30);
		
		JButton btn = new JButton("단어 검색");
		btn.setBounds(440, 10, 100, 30);
		
		
		btn.addActionListener(new ActionListener() {   //ActionListener 익명 구현 클래스
			                 
			@Override   
			public void actionPerformed(ActionEvent e) {
				dm = new DefaultTableModel(null, cols);
				jt.setModel(dm);
				for(int k=dm.getRowCount()-1;k>=0;k--)    //기존 데이터 테이블 삭제
					dm.removeRow(k);
				
				String find = jtf1.getText();
				for(int i=0;i<list.size();i++) {           //새로운 결과데이터 추가 보여주기
					Word temp = list.get(i);
					boolean flag;
					if(find.equals("")) flag =true;
					else 
						if(jc.getSelectedIndex() == 0)
						flag = temp.getEnglish().equals(find);
						else
						flag = temp.getKorean().equals(find);
					
					if(flag) {
						data[0] = temp.getEnglish();
						data[1] = temp.getKorean();
						data[2] = String.valueOf(temp.getLevel());
						data[3] = temp.getWday().toString();
						dm.addRow(data);
					}
				}
				
			}
		});
		

		jsp.setBounds(10, 60, 550, 500);
		ctn.setLayout(null);
		ctn.add(btn);ctn.add(la1); ctn.add(jtf1);add(jc);
		ctn.add(jsp);
		
		pack();
		
		
		
		setBounds(500,50,600,600);
		setTitle("WordBook List");
		setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new JTableList();
	}
}

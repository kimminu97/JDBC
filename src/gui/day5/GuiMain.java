package gui.day5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//지금까지는 CLI방식으로 command에서 실행됩니다.
//자바에는 awt,swing,javaFX 등을 이용해서 GUI (windows 프로그램) 방식 프로그램을 만듭니다.
public class GuiMain extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	// 화면을 구성하는 요소(컴포넌트) 변수 선언
	JLabel la1, la2, la3, la4, la5; // 레이블
	JTextField tf1, tf2, tf3; // 텍스트입력
	JButton bt1, bt2, bt3, bt4; // 실행버튼
	JList<String> jlist;

	public GuiMain() {
		init(); // 윈도우(화면)를 구성하는 요소를 생성합니다.
	}

	private void init() {
		// 컴포넌트의 크기와 위치 정하기
		la1 = new JLabel("English");
		la1.setBounds(50, 50, 100, 30); // x좌표,y좌표,너비,높이

		tf1 = new JTextField();
		tf1.setBounds(150, 50, 150, 20);

		la2 = new JLabel("Korean");
		la2.setBounds(50, 100, 100, 20);

		tf2 = new JTextField();
		tf2.setBounds(150, 100, 150, 20);

		la3 = new JLabel("검색어");
		la3.setBounds(50, 150, 100, 20);

		tf3 = new JTextField();
		tf3.setBounds(150, 150, 150, 20);

		bt1 = new JButton("단어추가");
		bt1.setBounds(50, 200, 90, 40);

		bt2 = new JButton("파일저장");
		bt2.setBounds(150, 200, 90, 40);

		bt3 = new JButton("단어검색");
		bt3.setBounds(250, 200, 90, 40);

		bt4 = new JButton("종료");
		bt4.setBounds(350, 200, 90, 40);

		// 버튼에게 action listener가 어디에 있는지 알려주기
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);

		la4 = new JLabel("레벨 LEVEL");
		la4.setBounds(350, 50, 100, 30);

		la5 = new JLabel();
		la5.setBounds(50, 250, 400, 30);

		// 리스트 생성 -1)리스트 내용 생성
		DefaultListModel<String> dlist = new DefaultListModel<String>();
		dlist.addElement("easy");
		dlist.addElement("standard");
		dlist.addElement("difficult");

		// 2)Jlist에 리스트 내용 적용
		jlist = new JList<String>(dlist);
		jlist.setBounds(350, 80, 75, 95);

		this.add(la1); // 윈도우(화면)에 la1컴포넌트 추가
		this.add(la2);
		this.add(la3);
		add(la4);
		add(la5); // this 생략가능합니다.
		this.add(tf1);
		this.add(tf2);
		this.add(tf3);
		this.add(bt1);
		this.add(bt2);
		this.add(bt3);
		this.add(bt4);
		add(jlist);

		this.setLayout(null);
		this.setSize(500, 350); // 윈도우 크기
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GuiMain();

	}

	// ActionListener 인터페이스의 추상메소드를 구현해야 합니다.
	@Override
	public void actionPerformed(ActionEvent e) { // 이벤트(사건) 일어난 객체정보가 인자입니다.
		// 어떤 버튼이 클릭되어 이벤트가 생겼는지 검사하고 그에 따라 다른 명령을 실행시킵니다.
		if (e.getSource() == bt1) {
			JOptionPane.showMessageDialog(this, "단어추가 입니다");
			la5.setText(tf1.getText() + " : " + tf2.getText() + "(" + jlist.getSelectedValue() + ")");
		} else if (e.getSource() == bt2) {
			JOptionPane.showMessageDialog(this, "파일저장 입니다.");
		} else if (e.getSource() == bt3) {
			if (tf3.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "검색할 단어를 입력해주세요.");
			}else {
				new TestKiosk();
			}
		} else if (e.getSource() == bt4) {
			JOptionPane.showMessageDialog(this, "프로그램 종료 입니다");
			dispose(); // 윈도우 화면 리소스 해제
			System.exit(0); // 종료
		}

	}

}

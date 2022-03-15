package gui.day5;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TestKiosk {
	private JFrame frame;

	JLabel lbltotalP = new JLabel("0원"); // 0원
	JLabel lbltotal_c_cart = new JLabel("0원");

	public TestKiosk() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JPanel panel_cart = new JPanel();
		panel_cart.setBounds(0, 0, 474, 691);
		frame.getContentPane().add(panel_cart);
		panel_cart.setLayout(null);
		panel_cart.setVisible(true);

		JLabel lblNewLabel = new JLabel("총 금액");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setBounds(90, 510, 148, 68);
		panel_cart.add(lblNewLabel);

		JButton btnconfirm = new JButton("결제하기");
		btnconfirm.setBounds(299, 600, 97, 23);
		panel_cart.add(btnconfirm);

	
		JTextArea textArea = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);
		textArea.setBounds(33, 29, 404, 429);
		textArea.setEditable(false);	//입력 못하고 데이터 출력용 입니다.
		textArea.setText("제품명\t\t제품단가\t수량\n\n");
		panel_cart.add(textArea);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		TestKiosk window = new TestKiosk();
	}

}

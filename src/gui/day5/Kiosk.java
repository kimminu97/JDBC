package gui.day5;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;

public class Kiosk {

   private JFrame frame;
   String[] menu = {"치즈피자","불고기피자","파인애플피자","새우피자","레전드피자","양고기피자"};
   String[] side = {"치즈추가","햄추가"};
   int[] cost = {8000,9000,13000,15000,20000,23000};
   int[] side_cost = {500,700};
   int side_total =0;
   int total_price = 0;
   int c_count =0;
   int h_count =0;
   int count1 = 0;
   int count2 = 0;
   
   String btnString1 = "";
   String btnString2 = "";
   String btnString3 = "";
   String btnString4 = "";
   String btnString5 = "";
   String btnString6 = "";
   
   
   String c_choose= "";
   String h_choose= "";
   
   JLabel lbltotalP = new JLabel("0\uC6D0");
   
   JLabel lbltotal_c_cart = new JLabel("0\uC6D0");
   
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Kiosk window = new Kiosk();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public Kiosk() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   
   
   private void initialize() {
      frame = new JFrame();
      frame.setBounds(100, 100, 480, 720);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);
      
      JPanel panel_cart = new JPanel();
      panel_cart.setBounds(0, 0, 474, 691);
      frame.getContentPane().add(panel_cart);
      panel_cart.setLayout(null);
      panel_cart.setVisible(false);
      
      JLabel lblNewLabel = new JLabel("\uCD1D \uAE08\uC561");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
      lblNewLabel.setBounds(90, 510, 148, 68);
      panel_cart.add(lblNewLabel);
      
      JButton btnconfirm = new JButton("\uACB0\uC81C\uD558\uAE30");
      btnconfirm.setBounds(299, 600, 97, 23);
      panel_cart.add(btnconfirm);
      
      
      lbltotalP.setFont(new Font("굴림", Font.BOLD, 20));
      lbltotalP.setHorizontalAlignment(SwingConstants.RIGHT);
      lbltotalP.setBounds(224, 527, 142, 37);
      panel_cart.add(lbltotalP);
      
      JTextArea textArea = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);
      textArea.setBounds(33, 29, 404, 429);
      textArea.setEditable(false);
      textArea.setText("제품명\t\t제품단가\t수량\n\n");
      panel_cart.add(textArea);
      
      JPanel panel_Menu = new JPanel();
      panel_Menu.setBounds(0, 0, 474, 691);
      frame.getContentPane().add(panel_Menu);
      panel_Menu.setLayout(null);
      
      JPanel panel_Topping = new JPanel();
      panel_Topping.setBackground(Color.ORANGE);
      panel_Topping.setBounds(0, 0, 474, 477);
      frame.getContentPane().add(panel_Topping);
      panel_Topping.setLayout(null);
      panel_Topping.setVisible(false);
      
      JLabel lblAddCorn = new JLabel("\uCE58\uC988 \uCD94\uAC00");
      lblAddCorn.setHorizontalAlignment(SwingConstants.CENTER);
      lblAddCorn.setBounds(73, 88, 125, 47);
      panel_Topping.add(lblAddCorn);
      
      
      JLabel lblAddPot = new JLabel("\uD584 \uCD94\uAC00");
      lblAddPot.setHorizontalAlignment(SwingConstants.CENTER);
      lblAddPot.setBounds(73, 195, 125, 22);
      panel_Topping.add(lblAddPot);
      
      
      JSpinner spinner_corn = new JSpinner();
      spinner_corn.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
      spinner_corn.setBounds(269, 95, 84, 47);
      spinner_corn.addChangeListener(new ChangeListener() {

         @Override
         public void stateChanged(ChangeEvent e) {               //스피너 값 증가/감소 시 결제 전 화면에서 해당 메뉴가 추가될 수 있도록 설정. 
            if (count1 < (int) spinner_corn.getValue()) {
           
               side_total += side_cost[0];
               lbltotal_c_cart.setText(textLavel(side_total));
               lbltotalP.setText(textLavel(total_price));
               count1 = (int) spinner_corn.getValue();
            }else if(count1 > (int) spinner_corn.getValue()) {
              
               side_total -= side_cost[0];
               lbltotal_c_cart.setText(textLavel(side_total));
               lbltotalP.setText(textLavel(total_price));
               count1 = (int) spinner_corn.getValue();
            }

         }
         
      });
      panel_Topping.add(spinner_corn);
      
      JSpinner spinner_pot = new JSpinner();
      spinner_pot.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
      spinner_pot.setBounds(269, 195, 84, 47);
      spinner_pot.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent e) {
            
            if (count2 < (int) spinner_pot.getValue()) {
              
               side_total += side_cost[1];
               lbltotal_c_cart.setText(textLavel(side_total));
               lbltotalP.setText(textLavel(total_price));
               count2 = (int) spinner_pot.getValue();
            }else if(count2 > (int) spinner_pot.getValue()) {
             
               side_total -= side_cost[1];
               lbltotal_c_cart.setText(textLavel(side_total));
               lbltotalP.setText(textLavel(total_price));
               count2 = (int) spinner_pot.getValue();
            }            
            
         }
         
      });   
      
      panel_Topping.add(spinner_pot);
      
      JLabel lbltotal_cart = new JLabel("\uCD94\uAC00\uB41C \uAE08\uC561");
      lbltotal_cart.setFont(new Font("굴림", Font.BOLD, 12));
      lbltotal_cart.setBounds(114, 307, 84, 22);
      panel_Topping.add(lbltotal_cart);
      
      JLabel lblNewLabel_3 = new JLabel("\uD1A0\uD551 \uCD94\uAC00 \uC635\uC158");
      lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 16));
      lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3.setBounds(154, 10, 139, 47);
      panel_Topping.add(lblNewLabel_3);
      
      JLabel lblNewLabel_2 = new JLabel("700\uC6D0");
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setBounds(73, 220, 125, 15);
      panel_Topping.add(lblNewLabel_2);
      
      JLabel lblNewLabel_2_1 = new JLabel("500\uC6D0");
      lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2_1.setBounds(73, 121, 125, 15);
      panel_Topping.add(lblNewLabel_2_1);
      
      lbltotal_c_cart.setFont(new Font("굴림", Font.BOLD, 12));
      lbltotal_c_cart.setHorizontalAlignment(SwingConstants.RIGHT);
      lbltotal_c_cart.setBounds(272, 307, 81, 28);
      panel_Topping.add(lbltotal_c_cart);
      
      JButton btnDecide1 = new JButton("치즈 피자");
      btnDecide1.setIcon(new ImageIcon("./image/1.jpg"));
      btnDecide1.setBounds(52, 58, 147, 95);
      panel_Menu.add(btnDecide1);
      
      JButton btnDecide2 = new JButton("\uD53C\uC7902");
      btnDecide2.setIcon(new ImageIcon("./image/2.jpg"));
      btnDecide2.setBounds(253, 58, 147, 95);
      panel_Menu.add(btnDecide2);
      
      JButton btnDecide3 = new JButton("\uD53C\uC7903");
      btnDecide3.setIcon(new ImageIcon("./image/3.png"));
      btnDecide3.setBounds(52, 221, 147, 95);
      panel_Menu.add(btnDecide3);
      
      JButton btnDecide4 = new JButton("\uD53C\uC7904");
      btnDecide4.setIcon(new ImageIcon("./image/4.jpg"));
      btnDecide4.setBounds(253, 221, 147, 95);
      panel_Menu.add(btnDecide4);
      
      JButton btnDecide5 = new JButton("\uD53C\uC7905");
      btnDecide5.setIcon(new ImageIcon("./image/5.png"));
      btnDecide5.setBounds(52, 382, 147, 95);
      panel_Menu.add(btnDecide5);
      
      JButton btnDecide6 = new JButton("\uD53C\uC7906");
      btnDecide6.setIcon(new ImageIcon("./image/6.jpg"));
      btnDecide6.setBounds(253, 382, 147, 95);
      panel_Menu.add(btnDecide6);
      
      JLabel lblpc1 = new JLabel(textLavel(8000));
      lblpc1.setHorizontalAlignment(SwingConstants.CENTER);
      lblpc1.setBounds(52, 151, 108, 29);
      panel_Menu.add(lblpc1);
      
      JLabel lblpcl2 = new JLabel(textLavel(9000));
      lblpcl2.setHorizontalAlignment(SwingConstants.CENTER);
      lblpcl2.setBounds(253, 151, 108, 29);
      panel_Menu.add(lblpcl2);
      
      JLabel lblpc33 = new JLabel(textLavel(13000));
      lblpc33.setHorizontalAlignment(SwingConstants.CENTER);
      lblpc33.setBounds(52, 315, 108, 29);
      panel_Menu.add(lblpc33);
      
      JLabel lblpc4 = new JLabel(textLavel(15000));
      lblpc4.setHorizontalAlignment(SwingConstants.CENTER);
      lblpc4.setBounds(253, 316, 108, 29);
      panel_Menu.add(lblpc4);
      
      JLabel lblpc5 = new JLabel(textLavel(20000));
      lblpc5.setHorizontalAlignment(SwingConstants.CENTER);
      lblpc5.setBounds(52, 477, 108, 29);
      panel_Menu.add(lblpc5);
      
      JLabel lblpc6 = new JLabel(textLavel(23000));
      lblpc6.setHorizontalAlignment(SwingConstants.CENTER);
      lblpc6.setBounds(253, 478, 108, 29);
      panel_Menu.add(lblpc6);
      
      JSpinner spinner1 = new JSpinner();
      spinner1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
      spinner1.setBounds(155, 151, 44, 29);
      spinner1.addChangeListener(new ChangeListener() {
         
         @Override
         public void stateChanged(ChangeEvent e) {            //피자 메뉴의 스피너 값이 0보다 커지는 경우, btnString1에 임시저장되도록 설정.
            if((int)spinner1.getValue()>0)
            btnString1 = menu[0]+"\t\t"+textLavel(cost[0])+"\t"+(int)spinner1.getValue()+"\n\n";      
         }
      });
      panel_Menu.add(spinner1);
      
      JSpinner spinner2 = new JSpinner();
      spinner2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
      spinner2.setBounds(356, 151, 44, 29);
      spinner2.addChangeListener(new ChangeListener() {
         
         @Override
         public void stateChanged(ChangeEvent e) {
            if((int)spinner2.getValue()>0)
               btnString2 = menu[1]+"\t\t"+textLavel(cost[1])+"\t"+(int)spinner2.getValue()+"\n\n";
            
         }
      });
      panel_Menu.add(spinner2);
      
      JSpinner spinner4 = new JSpinner();
      spinner4.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
      spinner4.setBounds(356, 313, 44, 29);
      spinner4.addChangeListener(new ChangeListener() {
         
         @Override
         public void stateChanged(ChangeEvent e) {
            if((int)spinner4.getValue()>0)
               btnString4 = menu[3]+"\t\t"+textLavel(cost[3])+"\t"+(int)spinner4.getValue()+"\n\n";
               
         }
      });
      
      JSpinner spinner3 = new JSpinner();
      spinner3.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
      spinner3.setBounds(155, 313, 44, 29);
      spinner3.addChangeListener(new ChangeListener() {
         
         @Override
         public void stateChanged(ChangeEvent e) {
            if((int)spinner3.getValue()>0)
               btnString3 = menu[2]+"\t\t"+textLavel(cost[2])+"\t"+(int)spinner3.getValue()+"\n\n";
            
         }
      });
      panel_Menu.add(spinner3);
      panel_Menu.add(spinner4);
      
      JSpinner spinner5 = new JSpinner();
      spinner5.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
      spinner5.setBounds(155, 475, 44, 29);
      spinner5.addChangeListener(new ChangeListener() {
         
         @Override
         public void stateChanged(ChangeEvent e) {
            if((int)spinner5.getValue()>0)
               btnString5 = menu[4]+"\t\t"+textLavel(cost[4])+"\t"+(int)spinner5.getValue()+"\n\n";
            
         }
      });
      panel_Menu.add(spinner5);
      
      JSpinner spinner6 = new JSpinner();
      spinner6.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
      spinner6.setBounds(356, 475, 44, 29);
      spinner6.addChangeListener(new ChangeListener() {
         
         @Override
         public void stateChanged(ChangeEvent e) {
            if((int)spinner6.getValue() > 0)
               btnString6 = menu[5]+"\t\t"+textLavel(cost[5])+"\t"+(int)spinner6.getValue()+"\n\n";
            
         }
      });
      panel_Menu.add(spinner6);
      
      JLabel lblpazzaName0 = new JLabel(menu[0]);
      lblpazzaName0.setHorizontalAlignment(SwingConstants.CENTER);
      lblpazzaName0.setBounds(52, 189, 147, 22);
      panel_Menu.add(lblpazzaName0);
      
      JLabel lblpazzaName0_1 = new JLabel(menu[1]);
      lblpazzaName0_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblpazzaName0_1.setBounds(253, 189, 147, 22);
      panel_Menu.add(lblpazzaName0_1);
      
      JLabel lblpazzaName0_2 = new JLabel(menu[2]);
      lblpazzaName0_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblpazzaName0_2.setBounds(52, 350, 147, 22);
      panel_Menu.add(lblpazzaName0_2);
      
      JLabel lblpazzaName0_3 = new JLabel(menu[3]);
      lblpazzaName0_3.setHorizontalAlignment(SwingConstants.CENTER);
      lblpazzaName0_3.setBounds(253, 352, 147, 22);
      panel_Menu.add(lblpazzaName0_3);
      
      JLabel lblpazzaName0_4 = new JLabel(menu[4]);
      lblpazzaName0_4.setHorizontalAlignment(SwingConstants.CENTER);
      lblpazzaName0_4.setBounds(52, 516, 147, 22);
      panel_Menu.add(lblpazzaName0_4);
      
      JLabel lblpazzaName0_5 = new JLabel(menu[5]);
      lblpazzaName0_5.setHorizontalAlignment(SwingConstants.CENTER);
      lblpazzaName0_5.setBounds(253, 517, 147, 22);
      panel_Menu.add(lblpazzaName0_5);
      
      
      
      btnDecide1.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {         // 피자메뉴의 spinner 값이 0보다 커지는 경우, 해당 피자 버튼을 클릭 시 토핑 선택 화면이 출력되도록 설정.
            if((int)spinner1.getValue() > 0) {
            panel_Topping.setVisible(true);
            panel_Menu.setVisible(false);

            
            btnString1 = menu[0]+"\t\t"+textLavel(cost[0])+"\t"+(int)spinner1.getValue()+"\n\n";
            
            }
         }      
      });
      
      btnDecide2.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            if((int)spinner2.getValue() > 0) {
            panel_Topping.setVisible(true);
            panel_Menu.setVisible(false);

            
            btnString2 = menu[1]+"\t\t"+textLavel(cost[1])+"\t"+(int)spinner2.getValue()+"\n\n";
            
            }
         }      
      });
      
      btnDecide3.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            if ((int)spinner3.getValue() > 0) {
               panel_Topping.setVisible(true);
               panel_Menu.setVisible(false);

               
               btnString3 = menu[2]+"\t\t"+textLavel(cost[2])+"\t"+(int)spinner3.getValue()+"\n\n";
               
            }
            
         }      
      });
      
      btnDecide4.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println(spinner4.getValue());
            if((int)spinner4.getValue() > 0) {
            panel_Topping.setVisible(true);
            panel_Menu.setVisible(false);

            
            btnString4 = menu[3]+"\t\t"+textLavel(cost[3])+"\t"+(int)spinner4.getValue()+"\n\n";
            
            }
            
         }      
      });
      
      btnDecide5.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            if((int)spinner5.getValue() > 0) {
            panel_Topping.setVisible(true);
            panel_Menu.setVisible(false);

            
            btnString5 = menu[4]+"\t\t"+textLavel(cost[4])+"\t"+(int)spinner5.getValue()+"\n\n";

            }
         }      
      });
      
      btnDecide6.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            if((int)spinner6.getValue() > 0) {
            panel_Topping.setVisible(true);
            panel_Menu.setVisible(false);

            
            btnString6 = menu[5]+"\t\t"+textLavel(cost[5])+"\t"+(int)spinner6.getValue()+"\n\n";

            }
         }      
      });
      JButton btnChoose = new JButton("\uC120\uD0DD");
      btnChoose.setBounds(284, 374, 113, 41);
      panel_Topping.add(btnChoose);
      btnChoose.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {         //토핑 선택화면에서 선택 버튼 누르면 다시 메인메뉴 화면으로 돌아가도록 설정.
            panel_Topping.setVisible(false);
            panel_Menu.setVisible(true);
            if ((int)spinner_corn.getValue()>0) {            //spinner 값이 증가/ 감소 할때마다 추가되는 금액이 출력되로록 설정.
               c_count +=(int)spinner_corn.getValue();
               c_choose = side[0] + "\t\t" + textLavel(side_cost[0]) +"\t"+c_count+"\n\n";
               
            }
            if ((int)spinner_pot.getValue()>0) {
               h_count +=(int)spinner_pot.getValue();
               h_choose = side[1] + "\t\t" + textLavel(side_cost[1]) +"\t"+h_count+"\n\n";
              
            }
            side_total += (side_cost[1]*h_count);
            side_total += (side_cost[0]*c_count);
            spinner_corn.setValue(0);
            spinner_pot.setValue(0);
            
         }      
      });
      
      JButton btncart = new JButton("Cart");
      btncart.setBounds(259, 552, 141, 68);
      panel_Menu.add(btncart);
      btncart.addActionListener(new ActionListener() { 
         @Override
         public void actionPerformed(ActionEvent e) {
            panel_cart.setVisible(true);
            panel_Menu.setVisible(false);
            panel_Topping.setVisible(false);
            
            total_price =(cost[0]*(int)spinner1.getValue())         //Cart 버튼 클릭시, 장바구니 화면이 출력되고, 주문한 메뉴의 총 금액이 출력되도록 설정.
                      +(cost[1]*(int)spinner2.getValue())
                      +(cost[2]*(int)spinner3.getValue())
                      +(cost[3]*(int)spinner4.getValue())
                      +(cost[4]*(int)spinner5.getValue())
                      +(cost[5]*(int)spinner6.getValue())
                      +side_total;
            
            textArea.append(btnString1+btnString2+btnString3+btnString4+btnString5+btnString6+c_choose+h_choose);
            lbltotalP.setText(textLavel(total_price));
         }
         
      
         
      });
      JButton btnClean = new JButton("\uB4A4\uB3CC\uC544\uAC00\uAE30");      //뒤로가기버튼 클릭 시, 모든 값 초기화되도록 설정.
      btnClean.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            btnString1 = "";               
            btnString2 = "";
            btnString3 = "";
            btnString4 = "";
            btnString5 = "";
            btnString6 = "";
            c_choose = "";
            h_choose = "";
            c_count = 0;
            h_count = 0;
            spinner1.setValue(0);
            spinner2.setValue(0);
            spinner3.setValue(0);
            spinner4.setValue(0);
            spinner5.setValue(0);
            spinner6.setValue(0);
            total_price =0;
            side_total =0;
            count1 =0;
            count2 =0;
            textArea.setText("제품명\t\t제품단가\t수량\n\n");
            panel_cart.setVisible(false);
            panel_Menu.setVisible(true);
            panel_cart.setVisible(false);
            
         }
         
      });
      btnClean.setBounds(59, 600, 97, 23);
      panel_cart.add(btnClean);
      
      
      btnconfirm.addActionListener(new ActionListener() { 
             @Override
             public void actionPerformed(ActionEvent e) {         //결제 버튼 클릭 시, 포장/매장 선택 후 결제. OK 버튼을 누르면 결제한 금액과 함께 안내 메시지 출력.
                
                if (total_price >0) { // 결제할 금액이 0원보다 커야 결제버튼 활성화되도록 설정.
               
               String[] st = {"포장", "매장"};
                JOptionPane.showInputDialog(null,"이대로 주문하시겠습니까?\n 포장여부를 체크해주세요.", "결제완료", JOptionPane.QUESTION_MESSAGE, null, st, "포장");
                                           
                JOptionPane.showMessageDialog(null, "결제 완료 되었습니다.\n"+ "결제 금액은 "+ total_price+" 원 입니다.");
                btnString1 = "";
                btnString2 = "";
                btnString3 = "";
                btnString4 = "";
                btnString5 = "";
                btnString6 = "";
                c_choose = "";
                h_choose = "";
                c_count = 0;
                h_count = 0;   
                total_price =0;
                side_total =0;
                count1 =0;
                count2 =0;
                spinner1.setValue(0);
                spinner2.setValue(0);
                spinner3.setValue(0);
                spinner4.setValue(0);
                spinner5.setValue(0);
                spinner6.setValue(0);
                textArea.setText("제품명\t\t제품단가\t수량\n\n");
                panel_cart.setVisible(false);
                panel_Menu.setVisible(true);
                panel_cart.setVisible(false);
                }
                else {            //결제할 금액이 0원인경우, 결제하기 버튼이 클릭되지 않도록 설정.
                     JOptionPane.showMessageDialog(null, "결제할 내용이 없습니다."); 
                  }

             }      
          });
       }

   String textLavel(int num) {         //출력되는 숫자 3개마다 ,로 나뉘어 출력되도록 설정.
      DecimalFormat dc = new DecimalFormat("#,###,###,###");
      String d = dc.format(num);
      return d+"원";
   }
}
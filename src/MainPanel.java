import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class MainPanel extends JPanel {

	
	public MainPanel(){
			setLayout(new BorderLayout());
			add(new NorthPanel(),BorderLayout.NORTH);
			add(new CenterPanel(),BorderLayout.CENTER);
		}
	}
	class NorthPanel extends JPanel{
		private GamePanel gamePanel=null;
		private lv2GamePanel gamePanellv2=null;
		private lv3GamePanel gamePanellv3=null;
		private TextSource textSource = null;
		public NorthPanel() {
			setBackground(Color.BLACK);
			ImageIcon ver1 = new ImageIcon("ver1.png");
			ImageIcon ver2 = new ImageIcon("ver2.png");
			ImageIcon ver3 = new ImageIcon("ver3.png");
			JButton imageBtn1 = new JButton("Ver1",ver1);
			imageBtn1.addActionListener(new ActionListener() { //버튼 이벤트 발생시
	            public void actionPerformed(ActionEvent e) {
	            	gamePanel = new GamePanel();
	        		//getContentPane().add(gamePanel);
	            	setVisible(false);
	            	moveToLevel1();
	            }
	         });
			JButton imageBtn2 = new JButton("Ver2",ver2);
			imageBtn2.addActionListener(new ActionListener() { //버튼 이벤트 발생시
	            public void actionPerformed(ActionEvent e) {
	            	gamePanellv2 = new lv2GamePanel();
	        		//getContentPane().add(gamePanel);
	            	setVisible(false);
	            	moveToLevel2();
	            }
	         });
			JButton imageBtn3 = new JButton("Ver3",ver3);
			imageBtn3.addActionListener(new ActionListener() { //버튼 이벤트 발생시
	            public void actionPerformed(ActionEvent e) {
	            	gamePanellv3 = new lv3GamePanel();
	        		//getContentPane().add(gamePanel);
	            	setVisible(false);
	            	moveToLevel3();
	            }
	         });
			imageBtn1.setPreferredSize(new Dimension(300, 350));
			imageBtn2.setPreferredSize(new Dimension(300, 350));
			imageBtn3.setPreferredSize(new Dimension(300, 350));
			add(imageBtn1);
			add(imageBtn2);
			add(imageBtn3);
		}
		public void moveToLevel3() {
	        if (gamePanellv3 == null) {
	        	gamePanellv3 = new lv3GamePanel(); // Assuming lv2GamePanel is your next level panel
	        }

	        SwingUtilities.invokeLater(() -> {
	            Container parent = getParent();
	            if (parent != null) {
	                parent.removeAll();
	                parent.add(gamePanellv3);
	                parent.revalidate();
	                parent.repaint();
	            }
	        });
	    }
		public void moveToLevel2() {
	        if (gamePanellv2 == null) {
	        	gamePanellv2 = new lv2GamePanel(); // Assuming lv2GamePanel is your next level panel
	        }

	        SwingUtilities.invokeLater(() -> {
	            Container parent = getParent();
	            if (parent != null) {
	                parent.removeAll();
	                parent.add(gamePanellv2);
	                parent.revalidate();
	                parent.repaint();
	            }
	        });
	    }
		public void moveToLevel1() {
	        if (gamePanel == null) {
	            gamePanel = new GamePanel(); // Assuming lv2GamePanel is your next level panel
	        }

	        SwingUtilities.invokeLater(() -> {
	            Container parent = getParent();
	            if (parent != null) {
	                parent.removeAll();
	                parent.add(gamePanel);
	                parent.revalidate();
	                parent.repaint();
	            }
	        });
	    }
	}	
	
	class CenterPanel extends JPanel{
		private GamePanel gamePanel=null;
		private AddWord addword=null;
		private boolean passWFlag=false, idFlag=false;
		
		public CenterPanel() {
			setBackground(Color.BLACK);
			//setLayout(new GridLayout(15,1,1,1));
			setLayout(null);
			JLabel login =new JLabel("로그인");
			login.setForeground(Color.WHITE);
			JLabel ID =new JLabel("ID");
			ID.setForeground(Color.WHITE);
			JLabel Password =new JLabel("PASSWORD");
			Password.setForeground(Color.WHITE);
			JTextField textField1 = new JTextField(16);
			textField1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JTextField tf = (JTextField)e.getSource();
					String id=tf.getText();
					if(id=="") idFlag=false;
					else idFlag=true;
					}
				});
			JTextField textField2 = new JTextField(16);
			textField2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JTextField tf = (JTextField)e.getSource();
					String passW=tf.getText();
					if(passW=="") passWFlag=false;
					else passWFlag=true;
					}
				});
			login.setSize(100,50); ID.setSize(100,50); 
			login.setLocation(300,10); ID.setLocation(300,30);
			Password.setSize(100,50); textField1.setSize(300,20);
			Password.setLocation(300,50); textField1.setLocation(400,45);
			textField2.setSize(300,20);
			textField2.setLocation(400,65);
			add(login);
			add(ID);
			add(Password);
			add(textField1);
			add(textField2);
			JButton start = new JButton("Start");
			start.setSize(100,50);
			start.setLocation(300,100);
			add(start);
			start.addActionListener(new ActionListener() { //버튼 이벤트 발생시
	           public void actionPerformed(ActionEvent e) {
	   			if(idFlag==true||passWFlag==true) {
	   				gamePanel = new GamePanel();
	   				//getContentPane().add(gamePanel);
	   				JOptionPane.showMessageDialog(getParent(), "Game Start");
	   				setVisible(false);
	   				moveToLevel1();
	   				}
	   			else {
	   				JOptionPane.showMessageDialog(getParent(), "ID와 PASSWORD를 입력하시오");
	   				}
	            }
	           
	         });

			JButton adwd = new JButton("단어 추가");
			adwd.setSize(100,50);
			adwd.setLocation(450,100);
			add(adwd);
			adwd.addActionListener(new ActionListener() { //버튼 이벤트 발생시
	            public void actionPerformed(ActionEvent e) {
	            	addword = new AddWord();
	            	setVisible(false);
	            	moveToAddWord();
	            }
	         });
			JButton exit = new JButton("EXIT");
			exit.setSize(100,50);
			exit.setLocation(600,100);
			add(exit);
			exit.addActionListener(new ActionListener() { //버튼 이벤트 발생시
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
	            }
	         });
			}
		public void moveToAddWord() {
	        if (addword == null) {
	        	addword = new AddWord(); // Assuming lv2GamePanel is your next level panel
	        }

	        SwingUtilities.invokeLater(() -> {
	            Container parent = getParent();
	            if (parent != null) {
	                parent.removeAll();
	                parent.add(addword);
	                parent.revalidate();
	                parent.repaint();
	            }
	        });
	    }
		public void moveToLevel1() {
	        if (gamePanel == null) {
	            gamePanel = new GamePanel(); // Assuming lv2GamePanel is your next level panel
	        }

	        SwingUtilities.invokeLater(() -> {
	            Container parent = getParent();
	            if (parent != null) {
	                parent.removeAll();
	                parent.add(gamePanel);
	                parent.revalidate();
	                parent.repaint();
	            }
	        });
	    }
}

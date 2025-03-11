import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;

public class AddWord extends JPanel {
	private JTextField wordInput = new JTextField(10);
	private ImageIcon icon = new ImageIcon("addword.png");
	private Image img = icon.getImage();
	private MainPanel mainPanel = new MainPanel();
	private TextSource textSource =new TextSource(null);
	public AddWord(){
		setLayout(null);
		 wordInput.setSize(300,50);
		 wordInput.setLocation(250,250);
		 add(wordInput);
		 JButton save =new JButton("Save");
		 save.setSize(100,50);
		 save.setLocation(580,250);
		 save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//wordInput  = (JTextField)e.getSource();
					String word=wordInput.getText();
					textSource.wordVector.add(word);
					if(word.equals("")) {
						JOptionPane.showMessageDialog(null, "단어를 입력하시오");
					}
					else {
						JOptionPane.showMessageDialog(null, "저장되었습니다.");
						wordInput.setText("");
					}
				}
			});
		 add(save);
		 JButton back =new JButton("Back");
		 back.setSize(100,50);
		 back.setLocation(690,250);
		 back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moveToMainPanel();
				}
			});
		 add(back);
	 }   
	   @Override
	   public void paintComponent(Graphics g) { // 그려질 때마다 자동 호출
	       super.paintComponent(g);
	       g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); // 이미지 꽉 차게 그리기
	     }
	   
	   public void moveToMainPanel() {
	        SwingUtilities.invokeLater(() -> {
	            Container parent = getParent();
	            if (parent != null) {
	                parent.removeAll();
	                parent.add(mainPanel);
	                parent.revalidate();
	                parent.repaint();
	            }
	        });
	    }
}

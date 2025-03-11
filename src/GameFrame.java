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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class GameFrame extends JFrame {
	private Container c= getContentPane();
	private MainPanel mainPanel = new MainPanel();
	public static int SCORE=0;
	public GameFrame() {
		setTitle("뇌지컬 100");
		setSize(1000,600);
		makeMenu();
		new BGM();
		setContentPane(mainPanel);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임 윈도우 닫으면 프로그램 종료
		setResizable(false); //프레임 크기 조절 불가
	}
	private void makeMenu() {
		JMenuBar mb = new JMenuBar();
		this.setJMenuBar(mb);
		
		JMenu fileMenu = new JMenu("Option");
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
		mb.add(fileMenu);

		}


}

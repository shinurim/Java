import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class lv3GameGround extends JPanel{
	private ScorePanel scorePanel = null;
	private JTextField textInput = new JTextField(20);
	private JLabel[] label = new JLabel[15];
	private TextSource textSource = new TextSource(null);
	private MyThread th = new MyThread();
	private ImageIcon icon = new ImageIcon("lv3.jpg");
	private Image img = icon.getImage();
	private ImageIcon icon1 = new ImageIcon("bg1.png");
	private Image img1 = icon1.getImage();
	private ImageIcon icon2 = new ImageIcon("bg2.png");
	private Image img2 = icon2.getImage();
	private ImageIcon icon3 = new ImageIcon("lv3img1.png");
	private Image img3 = icon3.getImage();
	private ImageIcon icon4 = new ImageIcon("lv3img2.png");
	private Image img4 = icon4.getImage();
    private TimeBar timeBar = new TimeBar();
    private lv3GamePanel gamePanel=null;
    private int time = 150; // Initial health
    private int decreaseRate = 4; // Health decrease rate
	private MainPanel mainPanel =null;
	private ItemPanel itemPanel;
	private boolean timeFlag=false;
	
    class TimeBar extends JLabel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GREEN);
            int width = (int) (((double) (this.getWidth())) / 150 * time);
            if (width > 0) {
                g.fillRect(0, 0, width, this.getHeight());
            }
        }

        synchronized public void decreaseHealth() {
            if (time <= 0) {
            	moveToMainPanel();
                return; // Health cannot go below 0
            }

            time -= decreaseRate;
            repaint();
            notify();
        }
        synchronized public void increaseHealth() {
            time += 10;
            repaint();
            notify();
        }
    }

    class HealthDecreaseThread extends Thread {
        private TimeBar timeBar;

        public HealthDecreaseThread(TimeBar timeBar) {
            this.timeBar = timeBar;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    sleep(1000); // Decrease health every 500 milliseconds
                    timeBar.decreaseHealth();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

	public lv3GameGround(ScorePanel scorePanel,ItemPanel itemPanel) {
		this.itemPanel=itemPanel;
		this.scorePanel = scorePanel;
		setLayout(null);

		for(int i=0;i<15;i++) {
			int n = textSource.wordVector.size();
			int index = (int)(Math.random()*n);
			String word =textSource.wordVector.get(index);
			label[i] = new JLabel(word);
			label[i].setSize(100,20);
			int x= ((int)(Math.random()*700));
			label[i].setForeground(Color.BLACK);
			label[i].setLocation(x,10);
			add(label[i]);
		}

		timeBar.setBackground(Color.LIGHT_GRAY);
		timeBar.setOpaque(true);
		timeBar.setBounds(0, 0, 750, 30);
        add(timeBar);

        setSize(350, 200);
        setVisible(true);

        HealthDecreaseThread healthDecreaseThread = new HealthDecreaseThread(timeBar);
        healthDecreaseThread.start();
        
		textInput.setSize(500,20);
		textInput.setLocation(30,500);
		add(textInput);
		textInput.addActionListener(new ActionListener() {
			boolean scoreflag = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField)e.getSource();
				String text=tf.getText();
				if(text.equals(itemPanel.itemStr)) {
					switch(itemPanel.itemIndex) {
						case 0:
							scorePanel.increase();
							scorePanel.increase();
							break;
					case 1:
						for(int i=0;i<15;i++) {
							int n = textSource.wordVector.size();
							int index = (int)(Math.random()*n);
							String word =textSource.wordVector.get(index);
							label[i].setText(word);
							int x= ((int)(Math.random()*700));
							label[i].setLocation(x,10);
							add(label[i]);
						}
						break;
					case 2:
						scorePanel.healthBar.increaseHealth();
						break;
					case 3:
						timeFlag=true;
						break;
					}
					tf.setText("");
					itemPanel.randomItem();
					repaint();
				}
				else{for(int i=0;i<10;i++) {
					if(text.equals(label[i].getText())) {
						scorePanel.increase();
						String word = textSource.next();
						label[i].setText(word);
						int x= ((int)(Math.random()*700));
						label[i].setLocation(x,0);
						tf.setText("");
						scoreflag = true;
					}
				}
				if(scoreflag==false) {
					scorePanel.decrease();
					scorePanel.healthBar.decreaseHealth();
					tf.setText("");
					}
				scoreflag=false;
				}
				}
			});

			textSource = new TextSource(this);
			th= new MyThread();
			th.start();	
		}
	synchronized public void moveToMainPanel() {
	     if (mainPanel == null) {
	    	 mainPanel = new MainPanel(); // Assuming lv2GamePanel is your next level panel
	        }
	   
       SwingUtilities.invokeLater(() -> {
           Container parent = getParent();
           if (parent != null) {
        	   //JOptionPane.showMessageDialog(null, "점수 :"+ Integer.toString(GameFrame.SCORE));
               parent.removeAll();
               parent.add(mainPanel);
               parent.revalidate();
               parent.repaint();
           }
       });
	}
	public void paintComponent(Graphics g) { // 그려질 때마다 자동 호출
	     super.paintComponent(g);
	    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); // 이미지 꽉 차게 그리기

	    if (scorePanel.health>=140) {
	    	repaint();
	    	g.drawImage(img1, 400, 350, 300, 100, this);
	    	g.drawImage(img4, 100, 350, 300, 100, this);
	    }
	    else if(scorePanel.health>=120&&scorePanel.health<140) {
	    	repaint(); 
	    	g.drawImage(img2, 100, 350, 300, 100, this);
	    	g.drawImage(img3, 400, 350, 300, 100, this);
	    }
	    else if(scorePanel.health>=90&&scorePanel.health<120) {
	    	repaint(); 
	    	g.drawImage(img1, 400, 350, 300, 100, this);
	    	g.drawImage(img4, 100, 350, 300, 100, this);
	    	}
	    else if(scorePanel.health>=70&&scorePanel.health<90) {
	    	repaint(); 
	    	g.drawImage(img2, 100, 350, 300, 100, this);
	    	g.drawImage(img3, 400, 350, 300, 100, this);
	    	}
	    else if(scorePanel.health>=50&&scorePanel.health<70) {
	    	repaint(); 
	    	g.drawImage(img1, 400, 350, 300, 100, this);
	    	g.drawImage(img4, 100, 350, 300, 100, this);
	    	}
	    else if(scorePanel.health>=30&&scorePanel.health<50) {
	    	repaint(); 
	    	g.drawImage(img2, 100, 350, 300, 100, this);
	    	g.drawImage(img3, 400, 350, 300, 100, this);
	    	}
	    else if(scorePanel.health<30) {
	    	repaint(); 
	    	g.drawImage(img1, 400, 350, 300, 100, this);
	    	g.drawImage(img4, 100, 350, 300, 100, this);
	    	}
	   }
	class MyThread extends Thread{
		public void run() {
			while(true) {
				if(timeFlag==true) {
					try {
						sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					timeFlag=false;
				}
				for(int i=0;i<15;i++) {
					for(int j=i;j<15;j++) {
						label[j].setLocation(label[j].getX(),label[j].getY()+10);
						if(label[j].getY()==550)
							scorePanel.healthBar.decreaseHealth();
        		        if(scorePanel.flag==true)
        		        	moveToMainPanel();
						try {
							sleep(30);//1초
						} catch (InterruptedException e) {
							return;
						}
					}
				}
			}
		}
	}
}

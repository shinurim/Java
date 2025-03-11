import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ScorePanel extends JPanel{

	private JLabel scoreLabel ;
    public HealthBar healthBar = new HealthBar();
    public boolean flag=false;
    public int health = 150; // Initial health
    class HealthBar extends JLabel {
        private int decreaseRate =3; // Health decrease rate
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            int width = (int) (((double) (this.getWidth())) / 150 * health);
            if (width > 0) {
                g.fillRect(0, 0, width, this.getHeight());
            }
        }

        synchronized public void decreaseHealth() {
            if (health <= 0) {
            	flag=true;
            	//moveToMainPanel();
                return; // Health cannot go below 0
            }
            health -= decreaseRate;
            repaint();
            notify();
            
        }
        synchronized public void increaseHealth() {
            health += 10;
            repaint();
            notify();
            
        }
	}
    class HealthDecreaseThread extends Thread {
        private HealthBar healthBar;

        public HealthDecreaseThread(HealthBar healthBar) {
            this.healthBar = healthBar;
        }

        	@Override
        	synchronized public void run() {
        		while (true) {
        			try {
        				sleep(1000); // Decrease health every 500 milliseconds
        				healthBar.decreaseHealth();
        			} catch (InterruptedException e) {
        				return;
        			}
            	}
        	}
        	
        }
        
    public ScorePanel() {
    	scoreLabel = new JLabel(Integer.toString(GameFrame.SCORE));
		setBackground(Color.CYAN);
		setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new FlowLayout());
        JLabel scorel = new JLabel("점수");
        JLabel combo = new JLabel("COMBO");
        infoPanel.add(scorel);
        infoPanel.add(scoreLabel);
        JPanel infoPanel2 = new JPanel(new FlowLayout());
        infoPanel2.add(combo);
        
        add(infoPanel, BorderLayout.NORTH);
        add(infoPanel2, BorderLayout.CENTER);
        add(healthBar, BorderLayout.SOUTH);
        
		healthBar.setBackground(Color.LIGHT_GRAY);
        healthBar.setOpaque(true);
        healthBar.setBounds(0, 0, 250, 20);
        add(healthBar);
        
        HealthDecreaseThread healthDecreaseThread = new HealthDecreaseThread(healthBar);
        healthDecreaseThread.start();
	}

	public void increase() {
		GameFrame.SCORE+=10;
		scoreLabel.setText(Integer.toString(GameFrame.SCORE));
	}
	public void decrease() {
		GameFrame.SCORE-=5;
		if(GameFrame.SCORE<0)
			GameFrame.SCORE=0;
		scoreLabel.setText(Integer.toString(GameFrame.SCORE));
	}
}
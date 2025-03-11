import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemPanel extends JPanel{
	private ImageIcon[] icon = new ImageIcon[4];
	private JLabel itemLabel = null;
	private JLabel jItem =new JLabel("ITEM");
	public String itemStr;
	public int itemIndex;
	private TextSource textSource = new TextSource(null);
	public ItemPanel() {
		setBackground(Color.WHITE);
		setLayout(null);
		jItem.setSize(200,20);
		jItem.setLocation(10,10);
		add(jItem);

		icon[0]=new ImageIcon("star.png");
		icon[1]=new ImageIcon("bomb.png");
		icon[2]=new ImageIcon("redbull.png");
		icon[3]=new ImageIcon("stop.png");
		
		randomItem();
	}
	public void randomItem() {
		int i=(int)(Math.random()*4);
		itemIndex=i;
		
		removeAll();
		add(jItem);
		JLabel item= new JLabel(icon[i]);
		item.setSize(200,200);
		item.setLocation(10,10);
		add(item);
		
		int n = textSource.wordVector.size();
		int index = (int)(Math.random()*n);
		String word =textSource.wordVector.get(index);
		this.itemStr=word;
		itemLabel = new JLabel(word);
		itemLabel.setSize(100,20);
		if(i==2) itemLabel.setLocation(85,250);
		else itemLabel.setLocation(85,200);
		int x= ((int)(Math.random()*700));
		add(itemLabel);
		
		repaint();
	}

}
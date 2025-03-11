import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class lv2GamePanel extends JPanel{
	private ScorePanel scorePanel =new ScorePanel();
	private ItemPanel itemPanel =new ItemPanel();
		public lv2GamePanel() {
			setLayout(new BorderLayout());
			splitPanel();
		}
		private void splitPanel() {
			JSplitPane hPane = new JSplitPane();
			hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			hPane.setDividerLocation(750);
			add(hPane);
			
			JSplitPane vPane = new JSplitPane();
			vPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			vPane.setDividerLocation(300);
			vPane.setTopComponent(itemPanel);
			vPane.setBottomComponent(scorePanel);
			
			hPane.setRightComponent(vPane);
			hPane.setLeftComponent(new lv2GameGround(scorePanel,itemPanel));
		}
}
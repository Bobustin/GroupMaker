package groupmaker.core;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GroupMakerGUI {

	JFrame frame = new JFrame();
	JPanel selectionPanel = new JPanel();
	JLabel test = new JLabel("Test");
	public GroupMakerGUI()
	{
		frame.setVisible(true);
		frame.setTitle("Group Maker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectionPanel.add(test);
		frame.add(selectionPanel);
	}
	
}

package groupmaker.core;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GroupMakerGUI {

	JFrame frame;
	JPanel selectionPanel,displayPanel;
	JLabel test;
	public GroupMakerGUI()
	{
		frame = new JFrame();
		selectionPanel  = new JPanel();
		displayPanel = new JPanel();
		test  = new JLabel("Test");
		
		
		frame.setVisible(true);
		frame.setTitle("Group Maker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectionPanel.add(test);
		frame.add(selectionPanel);
	}
	
}

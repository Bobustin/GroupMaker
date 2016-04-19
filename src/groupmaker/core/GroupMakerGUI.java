package groupmaker.core;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GroupMakerGUI {

	JFrame frame;
	JPanel selectionPanel,displayPanel;
	JLabel test;
	private ArrayList<ArrayList<String>> groups;
	private int groupsize = 3;
	private ArrayList<String> names;
	private LayerTableModel ltm;
	private GroupMakerLogic groupmaker;
	public GroupMakerGUI(ArrayList<String> n)
	{
		groupmaker = new GroupMakerLogic();
		names = n;
		frame = new JFrame();
		selectionPanel  = new JPanel(new GridLayout(3,1));
		selectionPanel.setSize(200,800);
		
		displayPanel = new JPanel();
		test  = new JLabel("Test");
		
		Object[][] data = new Object[names.size()][2];
		String[] columns = {"Boolean", "Names"};
		
		
		
		for(int x = 0;x < names.size();x++)
		{
			data[x][1] = names.get(x);
			data[x][0] = true;
		}
		ltm = new LayerTableModel(data,columns);
		JTable selection = new JTable(ltm);
		
		JButton generate = new JButton("Generate");
		generate.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
            	generateGroups();
            //	refreshDisplayPanel();
            }
        });      
		
		frame.setVisible(true);
		frame.setTitle("Group Maker");
		frame.setSize(1000,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		selectionPanel.add(test);
		selectionPanel.add(selection);
		selectionPanel.add(generate);
		
		frame.add(selectionPanel);
		frame.add(displayPanel);
		
		
	}
	public void generateGroups()
	{
		ArrayList<String> list = new ArrayList<String>();
    	for(int x = 0;x < names.size();x++)
    	{
    		if((boolean)ltm.getValueAt(x,0) == true)
    		{
    			list.add((String) ltm.getValueAt(x,1));
    		}
    	}
    	groups = groupmaker.makeGroups(groupsize,list);
    	System.out.println("Generated");
    	System.out.println(groups);
	}
	public void refreshDisplayPanel()
	{
		for(int x = 0;x < groups.size(); x++)
		{
			String names = "";
			for(int y = 0; y < groups.get(x).size();y++)
			{
				names += groups.get(x).get(y);
			}
			JLabel label = new JLabel(names);
			displayPanel.add(label);
		}
		displayPanel.repaint();
	}
}

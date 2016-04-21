package groupmaker.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.media.sound.Toolkit;

public class GroupMakerGUI {

	JFrame frame;
	JPanel selectionPanel,displayPanel;
	JLabel test;
	JTable selection;
	JLabel label = new JLabel();
	private ArrayList<ArrayList<String>> groups;
	private int groupsize = 3;
	private ArrayList<String> names;
	private LayerTableModel ltm;
	private GroupMakerLogic groupmaker;
	private boolean added = false;
	public GroupMakerGUI(ArrayList<String> n)
	{
		groupmaker = new GroupMakerLogic();
		names = n;
		frame = new JFrame();
		selectionPanel  = new JPanel(new GridLayout(5,1));
		selectionPanel.setSize(200,800);
		
		displayPanel = new JPanel();
		test  = new JLabel("Test");
		setTable();
		
		JButton generate = new JButton("Generate");
		generate.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
            	generateGroups();
            	refreshDisplayPanel();
            }
        });      
		
		JButton sizeSelect = new JButton("GroupSize");
		sizeSelect.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	groupsize = Integer.parseInt(JOptionPane.showInputDialog("Enter Group Size:"));
            }
        }); 
		
		JButton fileSelect = new JButton("Select File");
		fileSelect.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	try {
					selectFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        }); 
		
		frame.setVisible(true);
		frame.setTitle("Group Maker");
	//	frame.setSize(1000,800);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		selectionPanel.add(test);
		selectionPanel.add(sizeSelect);
		selectionPanel.add(fileSelect);
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
		String names = "";
		for(int x = 0;x < groups.size(); x++)
		{
			names += "Group " + String.valueOf(x+1) + ": ";
			for(int y = 0; y < groups.get(x).size();y++)
			{
				names += groups.get(x).get(y);
				if(y < groups.get(x).size() - 1)
				{
					names += ", ";
				}
			}
			names += " ";
		}
		displayPanel.remove(label);
		label = new JLabel(names);
		displayPanel.add(label);
		displayPanel.revalidate();
	//	displayPanel.repaint();
		selectionPanel.revalidate();
		selectionPanel.repaint();
	}
	public void setTable()
	{
		if(added == false)
		{
			added = true;
		}
		else
		{
			selectionPanel.remove(selection);
		}
		
		Object[][] data = new Object[names.size()][2];
		String[] columns = {"Boolean", "Names"};
		
		for(int x = 0;x < names.size();x++)
		{
			data[x][1] = names.get(x);
			data[x][0] = true;
		}
		ltm = new LayerTableModel(data,columns);
		selection = new JTable(ltm);
		System.out.println("Table made");
		selectionPanel.add(selection);
		selectionPanel.revalidate();
	}
	public void selectFile() throws IOException
	{
		String filepath = JOptionPane.showInputDialog("Enter File Name:");
		ArrayList<String> n = new ArrayList<String>();		
		BufferedReader reader = new BufferedReader(new FileReader(filepath));
		String line;
		while((line=reader.readLine())!=null){
			n.add(line);
		}
		reader.close();
		names = n;
		System.out.println(filepath);
		setTable();
	}
}

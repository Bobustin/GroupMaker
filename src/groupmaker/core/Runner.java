package groupmaker.core;

import java.util.ArrayList;
import java.io.*;

import javax.swing.SwingUtilities;

public class Runner 
{
	public static void main(String args[])
	{
		//testIO();
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				ArrayList<String> names = new ArrayList<String>();
				try {
					names = getStudentNames("test.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
				new GroupMakerGUI(names);
			}
		});
	}
	
	public static void testIO(){
		try{
			ArrayList<String> names = getStudentNames("test.txt");
			System.out.println(names);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getStudentNames(String filepath) throws IOException{
		ArrayList<String> names = new ArrayList<String>();		
		BufferedReader reader = new BufferedReader(new FileReader(filepath));
		String line;
		while((line=reader.readLine())!=null){
			names.add(line);
		}
		reader.close();
		return names;
	}
}

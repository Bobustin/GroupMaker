package groupmaker.core;

import java.util.ArrayList;
import java.util.Random;

public class GroupMakerLogic {
	
	private ArrayList<ArrayList<String>> groups;
	private int numGroup;
	
	public ArrayList<ArrayList<String>> makeGroups(int gSize, ArrayList<String> sNames)
	{
		groups = new ArrayList<ArrayList<String>>();
		Random rand = new Random();
		
		numGroup = (int)Math.ceil((sNames.size() * 1.0) / (gSize * 1.0));
		for(int x=0;x<numGroup;x++)
		{
			groups.add(new ArrayList<String>());
		}
		
		while(sNames.size() > 0)
		{
			ArrayList<String> t = groups.get(rand.nextInt(numGroup));
			if(t.size() < gSize)
			{
				t.add(sNames.remove(0));
			}
		}
		
		return groups;
	}

}

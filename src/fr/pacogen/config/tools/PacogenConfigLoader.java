package fr.pacogen.config.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class PacogenConfigLoader extends ConfigLoader {

	
	
	private Scanner sc;

	public PacogenConfigLoader(String inputFile) throws FileNotFoundException
	{
	

		
		HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>() ;
		LinkedList<LinkedList<String>> lllst = new LinkedList<LinkedList<String>>() ;
		
		sc = new Scanner(new File(inputFile));
		
		String currStr = sc.nextLine() ;
		String[] ft = currStr.split(",");
		
		for (int i = 0; i < ft.length; i++) {
			String key = ft[i] ;
			key = key.trim() ;
		//	key = key.replace("_","") ;
			key = key.replace("/","") ;
			key = key.replace("+","") ;
			key = key.replace(" ","") ;
			key = key.toLowerCase() ;
			ft[i] = key ;
		}
		
		while(sc.hasNext())	
		{
			currStr = sc.next() ;
			
			String[] tabStr = currStr.split(",") ;
			lllst.add(new LinkedList<String>(Arrays.asList(tabStr))) ;
		}
		
		LinkedList<LinkedList<String>> s2 =	transpose(lllst) ;
		for (int i = 0; i < s2.size(); i++) {
			map.put(ft[i],s2.get(i)) ;
		}
	
		super.setConfigMap(map);
	}

	private LinkedList<LinkedList<String>> transpose(LinkedList<LinkedList<String>> lllst) {
		LinkedList<LinkedList<String>>  res = new LinkedList<LinkedList<String>> ();
		
		for(int i = 0 ; i < lllst.get(0).size() ; i ++)
		{
		res.add(new LinkedList<String>()) ;	
		}
				
		for(int i = 0 ; i < lllst.get(0).size() ; i ++)
		{
			for (int j = 0 ; j < lllst.size() ; j ++)
			{
				res.get(i).add(j, lllst.get(j).get(i))  ;
			}					
	}
		return res;		
	}
	

}

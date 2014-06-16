package fr.pacogen.config.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class SPLCATConfigLoader  extends ConfigLoader {
	
	
	private Scanner sc;

	public SPLCATConfigLoader(String inputFile) throws FileNotFoundException
	{
	
		HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>() ;
		sc = new Scanner(new File(inputFile));
		sc.nextLine() ;
		
		while(sc.hasNext())
			
		{
			String currStr = sc.nextLine() ;
			String[] tabStr = currStr.split(";") ;
			String key = tabStr[0];
			
			key = key.trim() ;
		//	key = key.replace("_","") ;
			key = key.toLowerCase() ;

			
			LinkedList<String> values = new LinkedList<String>() ;
			
			for (int i = 1 ; i < tabStr.length ; i++)
			{
				String elem = tabStr[i];
			
				elem = elem.replace("-", "0") ;
				elem = elem.replace("X", "1") ;
				values.add(elem) ;
			}
			
			map.put(key, values) ;
		}
		super.setConfigMap(map);
	}


}

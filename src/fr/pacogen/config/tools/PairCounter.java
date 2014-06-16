package fr.pacogen.config.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class PairCounter {

	private HashMap<String, LinkedList<String>> map ;
	private String pairdata = "" ;
	
	
	public PairCounter(ConfigLoader cf) 
	{
		map = cf.getConfigMap() ;
	}
	
	public int getPairNumber()
	{	
		return nbpaire(map) ;
	
	}
	
	
	public void getPairData(String csvFile) throws IOException
	{
		if(pairdata.equals(""))
		{
			getPairNumber() ;
		}
		
		File f = new File(csvFile) ;
		if(f.exists())
		{
			f.delete();
		}
		FileWriter fw = new FileWriter(f);
		fw.write(pairdata);
		fw.close() ;
		
	}
	
	
	private int nbpaire(HashMap<String, LinkedList<String>> m)
	{
				Object[] s = m.keySet().toArray() ;
				Arrays.sort(s);

		int res =  0 ;	
		for (int i = 0; i < s.length- 1; i++) {
			for (int j = i +1; j < s.length  ; j++) {		
				pairdata += "(" + s[i] +"," + s[j] +");";
			LinkedList<String> l1 = m.get(s[i]);
			LinkedList<String> l2 =  m.get(s[j]);
			
			
			
	
			res += checkPair(l1,l2);
			pairdata += "\n" ;
			}	
		}
		
		
		return res ;
	}
	
	

	private int checkPair(LinkedList<String> t1, LinkedList<String> t2)
	{
	
		LinkedList<String> data = new LinkedList<String>() ;
		Boolean cpl1 =false ;
		Boolean cpl2 =false ;
		Boolean cpl3 =false ;
		Boolean cpl4 =false ;
		int res = 0 ;
		
		
		
		for (int i = 0; i < t2.size(); i++) {
		String	t1elem = t1.get(i);
		String	t2elem = t2.get(i);			
			
			if(t1elem.equals("0") && t2elem.equals("0") && !cpl1)
			{
			res ++ ;
			cpl1 = true ;
			data.add("(0,0);") ;
						 	
			}else 	if(t1elem.equals("1") && t2elem.equals("1")  && !cpl2)
			{
			res ++ ;
			cpl2 = true ;
			data.add("(1,1);") ;
		
				
			}else 	if(t1elem.equals("1") && t2elem.equals("0")  && !cpl3)
			{
			res ++ ;
			cpl3 = true ;
			data.add("(1,0);") ;
	
				
			}else 	if(t1elem.equals("0") && t2elem.equals("1") && !cpl4)
			{
			res ++ ;
			cpl4 = true ;
			data.add("(0,1);") ;
			}
		
	}
		Collections.sort(data);	
		
		for (String string : data) {
			//	System.out.println(string);
					pairdata += string ;
				}
		return res;
		
	}
	
	class PairList  {
		
		protected String ft1 ;
		protected String ft2 ;
		
	}
	
	
	class Couple {
		
		protected String ft1 ;
		protected String ft2 ;
		
	}
	
}

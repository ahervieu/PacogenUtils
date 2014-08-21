package fr.pacogen.analyzer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class analyzer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File data = new File("./data/summary.txt") ;
		Scanner sc = new Scanner(data) ;

		File output = new File("./summaryXPRes.rtf") ;
		FileWriter fl = new FileWriter(output);
		
		while(sc.hasNextLine())
		{
			String currLine = sc.nextLine() ;
			if(currLine.contains("Loading"))
			{
				
				String FileName = currLine.substring(16,currLine.length());
				fl.write("\n");
				fl.write(FileName +";") ;
			}
			else if(currLine.contains("Done"))
			{
		//		Done. Size: 18, time: 462 milliseconds
			currLine = currLine.replace("Done. Size: " , "");
			currLine = currLine.replace(" time: ", "");				
			currLine = currLine.replace(" milliseconds", "");					
			currLine = currLine.replace(",", ";");	
			fl.write(currLine) ;
				//todo : aller à la ligne recuperer le nom du fichier  
			}
		}
		fl.close();
		sc.close();
	}


}

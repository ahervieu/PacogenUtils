package fr.pacogen.launcher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fr.pacogen.adapter.SplotAdapter;

public class Launcher {

	private String Labeling ;
	private String ModelFile ;
	private String MatrixSize ;
	
	public Launcher(String labeling, String modelFile, String matrixSize) {
		super();
		Labeling = labeling;
		ModelFile = modelFile;
		MatrixSize = matrixSize;
	}
	
	public void launch() throws IOException
	{
		File modelFile = new File(ModelFile) ;
		
		SplotAdapter SA = new SplotAdapter(ModelFile) ;
		SA.parse();
		String FTList ="[" + SA.getModel().getPrologFeature() + "]" ;
		String ctrModel = SA.getModel().getPrologCtr() + "," + SA.getModel().getRoot().getPrologName()+ "#=1" ;
		String ctrCtc = SA.getModel().getPrologCCT() ;
		
		if(!ctrCtc.equals(""))
		{
			ctrModel = "[" + ctrModel +"," +ctrCtc + "]" ;
		}else
		{
			ctrModel = "[" + ctrModel +"]" ;
		}
		
		String PLModel = "["+ FTList + "," +ctrModel +"," + Labeling + "," + MatrixSize + ",\'" + modelFile.getName() + "\',\'" + SA.getModel().getName() +"\']." ;
		
		
		File f = new java.io.File("model.txt") ;
		FileWriter fw = new FileWriter(f) ;
		fw.write(PLModel);
		fw.close();
		
		File fileRes = new java.io.File( modelFile.getName()+".csv") ;
		FileWriter fwZ = new FileWriter(fileRes) ;
		fwZ.write(SA.getModel().getIds());
		fwZ.close();
		
		
		
	}
	
	
}

package fr.pacogen.config.tools;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class ConfigLoader {

	private HashMap<String, LinkedList<String>> configMap ;
	
	private String configFile ;
	

	public HashMap<String, LinkedList<String>> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(HashMap<String, LinkedList<String>> configMap) {
		this.configMap = configMap;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}
	public void printData()
	{
		HashMap<String, LinkedList<String>> map = this.getConfigMap() ;
		System.out.println(map.toString());
		
	}
	
}

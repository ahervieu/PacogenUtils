package fr.pacogen.config.tools.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import fr.pacogen.config.tools.PacogenConfigLoader;
import fr.pacogen.config.tools.PairCounter;
import fr.pacogen.config.tools.SPLCATConfigLoader;

public class SPLCATConfigTest {

	@Test
	public void test() throws IOException {
		SPLCATConfigLoader pcf = new SPLCATConfigLoader("/Users/Aymeric/Documents/workspace/Pacogen_Utils/data/REAL-FM-2.xml.ca2.csv") ;
		PairCounter pc = new PairCounter(pcf) ;
		System.out.println(pc.getPairNumber());
		pc.getPairData("/Users/Aymeric/Documents/workspace/Pacogen_Utils/data/SplcatData.csv");
	}

}

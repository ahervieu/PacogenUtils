package fr.pacogen.config.tools.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import fr.pacogen.config.tools.PacogenConfigLoader;
import fr.pacogen.config.tools.PairCounter;

public class PacogenConfigTest {

	@Test
	public void test() throws IOException {
		PacogenConfigLoader pcf = new PacogenConfigLoader("/Users/Aymeric/Documents/workspace/Pacogen_Utils/data/Weather Station") ;
		PairCounter pc = new PairCounter(pcf) ;
		System.out.println(pc.getPairNumber());
		pc.getPairData("/Users/Aymeric/Documents/workspace/Pacogen_Utils/data/pacoData.csv");
	}

}

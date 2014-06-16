package fr.pacogen.adapter.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.pacogen.adapter.SplotAdapter;

public class SplotAdatperTest {

	@Test
	public void test() {
		SplotAdapter SA = new SplotAdapter("/Users/Aymeric/Documents/workspace/Pacogen_Utils/data/REAL-FM-17.xml");
		SA.parse();
		System.out.println(SA.getModel().getPrologCtr());
		System.out.println(SA.getModel().getPrologFeature());
		System.out.println(SA.getModel().getPrologCCT());
	}

}

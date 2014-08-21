package fr.pacogen.launcher.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import fr.pacogen.launcher.Launcher;

public class LauncherTest {


	@Test
	public void test() throws IOException {
		Launcher l = new Launcher("[ff]","/Users/Aymeric/Documents/workspace/Pacogen_Utils/data/model_20120419_1397354511.xml","8") ;
		l.launch();
	}

}

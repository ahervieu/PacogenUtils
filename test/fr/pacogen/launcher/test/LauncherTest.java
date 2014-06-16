package fr.pacogen.launcher.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import fr.pacogen.launcher.Launcher;

public class LauncherTest {


	@Test
	public void test() throws IOException {
		Launcher l = new Launcher("[ff]","/Users/Aymeric/Documents/workspace/Pacogen_Utils/data/model_20091225_1547989376.xml","9") ;
		l.launch();
	}

}

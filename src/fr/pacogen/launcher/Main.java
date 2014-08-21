package fr.pacogen.launcher;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String fileName = args[0] ;
		String labelling = args[1] ;
		String size = args[2] ;
		Launcher l = new Launcher(labelling, fileName, size);
		l.launch();
	}

}

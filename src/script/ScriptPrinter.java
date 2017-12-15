package script;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ScriptPrinter {
	
	public static void printMainCharacters(Script script) throws FileNotFoundException {
		ArrayList<Persona> mainCharacters = script.getMainCharacters();
		PrintWriter out = new PrintWriter(new File("data/characters.txt"));
		out.println(script.getName());
		for(Persona persona : mainCharacters) {
			out.println(persona.getName());
			out.println(persona.getImage());
		}
		out.close();
	}

}

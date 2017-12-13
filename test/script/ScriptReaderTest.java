package script;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import org.junit.Test;

public class ScriptReaderTest {
	
	ScriptReader sr = new ScriptReader();
	Script script;
	@Test
	public void testReadScript() throws IOException, GeneralSecurityException {
		script = sr.readScript(ScriptScraper.scrapeScript("http://www.imsdb.com/scripts/Titanic.html"), "Titanic");
		assertNotNull(script.getContent());
		ArrayList<Persona> mainCharacters = script.getMainCharacters();
		Boolean contain = false;
		for(Persona p : mainCharacters) {
			if(p.getName().equals("ROSE"));
			contain = true;
		}
		assertTrue(contain);
		assertTrue(script.getName().equals("Titanic"));
	}

	 
}

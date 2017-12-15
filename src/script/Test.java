package script;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.security.GeneralSecurityException;

import script.Relationships.Relationship;

 

 
 

public class Test {

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		 ScriptReader sr = new ScriptReader();
		 String content = ScriptScraper.scrapeScript("http://www.imsdb.com/scripts/Life-of-Pi.html");
		 
		 Script script = sr.readScript(content, "Life of pi");
		 
		 ScriptPrinter.printMainCharacters(script);
		 
	}

}

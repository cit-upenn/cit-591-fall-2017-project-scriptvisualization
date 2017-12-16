package script;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.security.GeneralSecurityException;

import script.Relationships.Relationship;

 

 
 

public class Test {

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		 ScriptReader sr = new ScriptReader();
		 String content = ScriptScraper.scrapeScript("http://www.imsdb.com/scripts/No-Country-for-Old-Men.html");
		 
		 Script script = sr.readScript(content, "No Country for Old Men");
		 
		 ScriptPrinter.printMainCharacters(script);
		 
	}

}

package script;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.security.GeneralSecurityException;

import script.Relationships.Relationship;

 

 
 

public class Test {

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		 ScriptReader sr = new ScriptReader();
		 String content = ScriptScraper.scrapeScript("http://www.imsdb.com/scripts/Batman.html");
		 
		 Script script = sr.readScript(content, "batman");
		 
		 ScriptPrinter.printMainCharacters(script);
		 ScriptScraper ss = new ScriptScraper();
		 ss.getMoviesFromGenre(Genre.MUSICAL);
		 System.out.println(ss.getMovieList().toString());
		  
		 
	}

}

package script;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class DataPrinterTester {
	public static void main(String[] args) throws IOException, GeneralSecurityException {
//		String urlName = "http://www.imsdb.com/scripts/Pearl-Harbor.html";
		String urlName = "http://www.imsdb.com/scripts/Titanic.html";
		ScriptScraper ss = new ScriptScraper();
		ScriptReader sr = new ScriptReader();
//		Script script = sr.readScript(ScriptScraper.scrapeScript(urlName), "Pearl Harbor");
		Script script = sr.readScript(ScriptScraper.scrapeScript(urlName), "Titanic");
		DataPrinter dp = new DataPrinter();
		
//		dp.printPersonality(script);
				
//		dp.printRelation(script);
		
//		dp.printKeywords(script);
		
//		dp.printMainPhotos(script);
		
		dp.printTimeLine(script);
		
		
	}
}

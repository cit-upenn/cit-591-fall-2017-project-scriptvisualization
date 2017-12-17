package script;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class DataPrinterTester {
	public static void main(String[] args) throws IOException, GeneralSecurityException {
		String urlName = "http://www.imsdb.com/scripts/Pearl-Harbor.html";
		ScriptScraper ss = new ScriptScraper();
		ScriptReader sr = new ScriptReader();
		Script script = sr.readScript(ScriptScraper.scrapeScript(urlName), "Pearl Harbor");
		String content = ss.scrapeScript(urlName);
		DataPrinter dp = new DataPrinter();
		
//		dp.printPersonality(script);
		
//		dp.printHeader(content);
		
//		dp.printRelation(sr);
		
//		dp.printKeywords(content);
		
//		dp.printMainPhotos(script);
		
		
	}
}


package script;


import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.BeforeClass;
import org.junit.Test;

public class DataPrinterTest {

 private static DataPrinter  dp;
 private static Script script;
 
 @BeforeClass
	public static void setUp() throws IOException, GeneralSecurityException {
		dp = new DataPrinter();
		ScriptReader sr = new ScriptReader();
		script = sr.readScript(ScriptScraper.scrapeScript("http://www.imsdb.com/scripts/Titanic.html"), "titanic");
	}
 
	@Test(expected=IllegalArgumentException.class)
	public void testNullPrintPersonality() {
		dp.printPersonality(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullPrintRelation() {
		dp.printRelation(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullPrintKeywords() throws IOException {
		dp.printKeywords(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullPrintMainPhotos() throws IOException {
		dp.printMainPhotos(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullPrintTimeLine() throws IOException {
		dp.printTimeLine(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullPrintOccurrences() throws IOException {
		dp.printOccurrences(null);
	}
	
	@Test
	public void testPrintPersonality() {
		dp.printPersonality(script);
	}

	@Test 
	public void testPrintRelation() {
		dp.printRelation(script);
	}

	@Test
	public void testPrintKeywords() throws IOException {
		dp.printKeywords(script);
	}

	@Test 
	public void testPrintMainPhotos() throws IOException {
		dp.printMainPhotos(script);
	}

	@Test
	public void testPrintTimeLine() throws IOException {
		dp.printTimeLine(script);
	}

	@Test 
	public void testPrintOccurrences() throws IOException {
		dp.printOccurrences(script);
	}

}

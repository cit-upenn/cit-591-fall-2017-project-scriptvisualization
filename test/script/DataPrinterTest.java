package script;


import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class DataPrinterTest {

 private DataPrinter dp;
	
	@Before
	public void setUp() {
		dp = new DataPrinter();
		
	}
	@Test(expected=IllegalArgumentException.class)
	public void testPrintPersonality() {
		dp.printPersonality(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrintRelation() {
		dp.printRelation(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrintKeywords() throws IOException {
		dp.printKeywords(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrintMainPhotos() throws IOException {
		dp.printMainPhotos(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrintTimeLine() throws IOException {
		dp.printTimeLine(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPrintOccurrences() throws IOException {
		dp.printOccurrences(null);
	}

}

package script;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ScriptScraperTest {
	
	private ScriptScraper ss;
	
	@Before
	public void setUp() {
		ss = new ScriptScraper();
		
	}
	@Test
	public void testScrapeScript() throws IOException {
		String content = ScriptScraper.scrapeScript("http://www.imsdb.com/scripts/La-La-Land.html");
		assertTrue(content.contains("LA LA LAND") && content.contains("Damien Chazelle")&&content.contains("MIA"));
	 
	}
	
	@Test
	public void testGetMoviesFromSearchKey() throws IOException {
		ss.getMoviesFromSearchKey("days");
		assertEquals(ss.getMovieList().keySet().size(), 7);
	}
	
	@Test
	public void testGetMoviesFromGenre() throws IOException {
		ss.getMoviesFromGenre(Genre.WESTERN);
		assertTrue(ss.getMovieList().containsKey("El Mariachi"));
	}
	
	@Test
	public void testGetScriptName() {
		assertEquals(ScriptScraper.getScriptName("http://www.imsdb.com/scripts/La-La-Land.html"), "La La Land");
	}

}

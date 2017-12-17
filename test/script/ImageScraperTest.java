package script;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Test;

public class ImageScraperTest {

	@Test
	public void testGetImageUrlsFromGoogle() throws GeneralSecurityException, IOException {
		assertTrue(ImageScraper.getImageUrlsFromGoogle("mia la la land").get(0).length() != 0);
	}

	@Test
	public void testGetPostPathFromTMDB() throws IOException {
		assertEquals(ImageScraper.getPostPathFromTMDB("la la land"), "https://image.tmdb.org/t/p/w1280/ylXCdC106IKiarftHkcacasaAcb.jpg");
	}

	@Test
	public void testGetImageGivenUrl() throws IOException {
		assertNotNull(ImageScraper.getImageGivenUrl("https://image.tmdb.org/t/p/w1280/ylXCdC106IKiarftHkcacasaAcb.jpg"));
	}
}

 
import java.awt.Image;
import java.io.IOException;
 
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
 
import com.google.api.services.customsearch.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

public class ImageScraper {
	
	private final String searchEngineID = "016310474112609901486:dlk05n5m1fm";
	private final String APIkey = "AIzaSyBsUA3Jt08xohiLevUttDAG5SYpg75kCdE";
	
	public List<String> getImageUrlsFromGoogle(String searchQuery) throws GeneralSecurityException, IOException {
		 
        List<String> images = new LinkedList<>();
        Customsearch cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null) 
        					.setApplicationName("scriptVisualization")
        					.setGoogleClientRequestInitializer(new CustomsearchRequestInitializer(APIkey)) 
        					.build();

        //Set search parameter
        Customsearch.Cse.List list = cs.cse().list(searchQuery).setCx(searchEngineID).setSearchType("image"); 

        //Execute search
        Search result = list.execute();
        List<Result> results = result.getItems();
        if(results == null) return null;
        for(Result r : results) {
        		images.add(r.getLink());
        }
        return images;
         
	}
	
	public Image getImageGivenUrl(String link) throws IOException {
		URL url = new URL(link);
		Image image = ImageIO.read(url);
		return image;
	}

}

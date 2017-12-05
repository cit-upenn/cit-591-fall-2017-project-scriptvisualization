 
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
 
import com.google.api.services.customsearch.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
 
import org.jsoup.select.Elements;
/**
 * This class retrieves images from Google using Google Customer Search API
 * @author yueyin
 *
 */
public class ImageScraper {
	
	private final String searchEngineID = "016310474112609901486:dlk05n5m1fm";
	private final String googleAPIkey = "AIzaSyBsUA3Jt08xohiLevUttDAG5SYpg75kCdE";
	private final String moviedbKey = "edf1b9d248b7dee1398bb1159e9f19cc";
	
	
	/**
	 * Get a list of images' urls given search key
	 * @param searchQuery
	 * @return list of urls
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	public List<String> getImageUrlsFromGoogle(String searchQuery) throws GeneralSecurityException, IOException {
		 
        List<String> images = new LinkedList<>();
        Customsearch cs = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null) 
        					.setApplicationName("scriptVisualization")
        					.setGoogleClientRequestInitializer(new CustomsearchRequestInitializer(googleAPIkey)) 
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
	
	/**
	 * This function get the url of the movie post from the movie db
	 * @param moviename
	 * @return
	 * @throws IOException
	 */
	public String getPostPathFromTMDB(String moviename) throws IOException{
		
		String query = "https://api.themoviedb.org/3/search/movie?api_key=" + moviedbKey + "&query=" + moviename;
		System.out.println(query);
		Document doc = Jsoup.connect(query).ignoreContentType(true).get();
		Elements json = doc.getElementsByTag("body");
		String result = "";
		if(!json.hasText()) return null;
		result = json.text();
		JSONObject o = new JSONObject(result);
		 
		JSONArray movies = o.getJSONArray("results");
		if(movies.isNull(0)) return null;
		JSONObject movie = (JSONObject) movies.get(0);
		String path = movie.getString("poster_path");
		return "https://image.tmdb.org/t/p/w1280" + path;
		
	}
	
	/**
	 * get image given url
	 * @param link
	 * @return image
	 * @throws IOException
	 */
	public Image getImageGivenUrl(String link) throws IOException {
		URL url = new URL(link);
		Image image = ImageIO.read(url);
		return image;
	}

}

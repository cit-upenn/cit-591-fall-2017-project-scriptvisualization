package script;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * This class can scrape scripts from http://www.imsdb.com/ 
 * @author yueyin
 *
 */
public class ScriptScraper {
	
	private String scriptName;
	private HashMap<String, String> movieList;
	private HashMap<String, BufferedImage> moviePosts;

	/**
	 * This constructor initializes instance variables
	 */
	public ScriptScraper() {
		movieList = new HashMap<>();
		moviePosts = new HashMap<>();
	}
	
	
	/**
	 * Extract script from given url, return string of the script 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String scrapeScript(String url) throws IOException {
		if(!url.contains("html")) return null;
		Document doc = Jsoup.connect(url).get();
		//remove empty tags
		for (Element element : doc.select("*")) {
		    if (!element.hasText()) {
		        element.remove();
		    }
		}
		//remove script tags
		doc.getElementsByTag("script").remove();
		//remove title tags
		doc.getElementsByTag("title").remove();
		Elements scriptContent = doc.getElementsByTag("pre");
		StringBuilder scripts = new StringBuilder();
		for(Element script : scriptContent) {
			scripts.append(script.html());
		}
		//clean format
		return scripts.toString().replaceAll("[\\n]{2,}", "\n\n").replaceAll("</b>|<pre>|</pre>", "").replaceAll("\\n[\\s]+\\n", "\n\n").trim(); 
	}
	
	/**
	 * get all available movies along with their scripts given searchKey
	 * @param searchKey
	 * @return movie name and script url
	 * @throws IOException
	 */
	public void getMoviesFromSearchKey(String searchKey) throws IOException{
		Document moviesPage = Jsoup.connect("http://www.imsdb.com/search.php?query="+ searchKey).get();
		getAvailableMovies(moviesPage);
	}
	
	
	/**
	 * get all available movies along with their scripts given genre
	 * @param genre
	 * @return movie name and script url
	 * @throws IOException
	 */
	public void getMoviesFromGenre(Genre genre) throws IOException{
		Document moviesPage = Jsoup.connect("http://www.imsdb.com/genre/"+ genre).get();
		getAvailableMovies(moviesPage);
	}
	
	/**
	 * this function finds all movies with available scripts from the retrieved html and put the movie name and script url into a hash map
	 * put movie name and movie poster into another hashmap
	 * @throws IOException
	 */
	private void getAvailableMovies(Document moviesPage) throws IOException {
		Elements tables = moviesPage.getElementsByAttributeValue("valign", "top");
		Elements childrenOfTable = tables.last().children();
		for(Element child : childrenOfTable) {
			if(child.tagName() == "p") {
				String url = getUrlFromElement(child);
				Document doc = Jsoup.connect(url).get();
				Elements links = doc.getElementsByAttributeValueMatching("href", "/(scripts|transcripts)/.+.html");
				if(links != null && !links.isEmpty()) {
					String scriptLink = "http://www.imsdb.com" + links.first().attr("href");
					String scriptName = getScriptName(scriptLink);
					movieList.put(scriptName, scriptLink);
					String postpath = ImageScraper.getPostPathFromTMDB(scriptName);
					if(postpath == null) continue;
					BufferedImage post = ImageScraper.getImageGivenUrl(postpath);
					moviePosts.put(scriptName, post);
				}
			}
		}

	}
	
	/**
	 * Get the name of the movie from given url
	 * @return
	 */
	public static String getScriptName(String url) {
		String[] splitUrl = url.split("/");
		return splitUrl[splitUrl.length - 1].replaceAll(".html", "").replaceAll("-", " ");
	}
	
 

	/**
	 * get a url from a <a> tag
	 * @param child
	 * @return value of href attribute
	 * @throws IOException
	 */
	private String getUrlFromElement(Element child) throws IOException {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile("href=\"(.*\\.html)\"");
		Matcher match = p.matcher(child.html());
		if(match.find()) {
			String movieLink = "http://www.imsdb.com" + match.group(1);
			return movieLink ;
		}
		return null;
	}
	

	/**
	 * @return the scriptName
	 */
	public String getScriptName() {
		return scriptName;
	}


	/**
	 * @return the movieList
	 */
	public HashMap<String, String> getMovieList() {
		return movieList;
	}
	

	/**
	 * @return the moviePosts
	 */
	public HashMap<String, BufferedImage> getMoviePosts() {
		return moviePosts;
	}
	
	
}

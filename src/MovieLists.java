import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This class get a list of movies from search key, and map movie name to the url of its embedded script  
 * @author yueyin
 *
 */
public class MovieLists {
	
	Document moviesPage;
	HashMap<String, String> availableMovies;
	
	/**
	 * The constructor initializes all instance variables given searchKey
	 * @param searchKey
	 * @throws IOException
	 */
	public MovieLists(String searchKey) throws IOException {
		availableMovies = new HashMap<>();
		moviesPage = Jsoup.connect("http://www.imsdb.com/search.php?query="+ searchKey).get();
		getAvailableMovies();
	}
	
	/**
	 * The constructor initializes all instance variables given genre
	 * @param searchKey
	 * @throws IOException
	 */
	public MovieLists(Genre genre) throws IOException {
		availableMovies = new HashMap<>();
		moviesPage = Jsoup.connect("http://www.imsdb.com/genre/"+ genre).get();
		getAvailableMovies();
	}

	/**
	 * this function finds all movies with available scripts from the retrieved html and put the movie name and script url into a hash map
	 * @throws IOException
	 */
	private void getAvailableMovies() throws IOException {
		Elements tables = moviesPage.getElementsByAttributeValue("valign", "top");
		Elements childrenOfTable = tables.last().children();
		for(Element child : childrenOfTable) {
			if(child.tagName() == "p") {
				String url = getUrlFromElement(child);
				putScript(child.text(), url);
			}
		}

	}

	/**
	 * this function extract link of script from html and store movie name and url in a hashmap
	 * @param movieName
	 * @param url
	 * @throws IOException
	 */
	private void putScript(String movieName, String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.getElementsByAttributeValueMatching("href", "/(scripts|transcripts)/.+.html");
		if(links != null && !links.isEmpty()) {
			String scriptLink = "http://www.imsdb.com" + links.first().attr("href");
			availableMovies.put(movieName, scriptLink);
		}
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
	 * print all available movie names and url of their script
	 */
	public void printMovieList() {
		for(String s : availableMovies.keySet()) {
			System.out.println(s +"\n" + availableMovies.get(s));
		}
	}

}

package script;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

	/**
	 * Extract script from given url, return string of the script 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public String scrapeScript(String url) throws IOException {
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
		return scripts.toString().replaceAll("[\\n]+", "\n").replaceAll("</b>|<pre>|</pre>", "").replaceAll("\\n[\\s]+\\n", "\n").trim(); 
	}
	
	
	/**
	 * Get the name of the movie from given url
	 * @return
	 */
	public String getScriptName(String url) {
		String[] splitUrl = url.split("/");
		return splitUrl[splitUrl.length - 1].replaceAll(".html", "");
	}
	
	/**
	 * get all available movies along with their scripts given searchKey
	 * @param searchKey
	 * @return movie name and script url
	 * @throws IOException
	 */
	public HashMap<String, String> getMoviesFromSearchKey(String searchKey) throws IOException{
		Document moviesPage = Jsoup.connect("http://www.imsdb.com/search.php?query="+ searchKey).get();
		return getAvailableMovies(moviesPage);
	}
	
	/**
	 * get all available movies posts along with their scripts given searchKey
	 * @param searchKey
	 * @return movie name and script url
	 * @throws IOException
	 */
	public HashMap<String, Image> getMoviesPostsFromSearchKey(String searchKey) throws IOException{
		Document moviesPage = Jsoup.connect("http://www.imsdb.com/search.php?query="+ searchKey).get();
		HashMap<String, Image> movies = new HashMap<>();
		Elements tables = moviesPage.getElementsByAttributeValue("valign", "top");
		Elements childrenOfTable = tables.last().children();
		for(Element child : childrenOfTable) {
			if(child.tagName() == "p") {
				String url = getUrlFromElement(child);
				Document doc = Jsoup.connect(url).get();
				Elements links = doc.getElementsByAttributeValueMatching("href", "/(scripts|transcripts)/.+.html");
				if(links != null && !links.isEmpty()) {
					String scriptLink = "http://www.imsdb.com" + links.first().attr("href");
					String movieName = getScriptName(scriptLink);
					Image post = ImageScraper.getImageGivenUrl(ImageScraper.getPostPathFromTMDB(movieName));
					movies.put(movieName, post);
				}
			}
		}
		return movies;
	}
	
	/**
	 * get all available movies along with their scripts given genre
	 * @param genre
	 * @return movie name and script url
	 * @throws IOException
	 */
	public HashMap<String, String> getMoviesFromSearchKey(Genre genre) throws IOException{
		Document moviesPage = Jsoup.connect("http://www.imsdb.com/genre/"+ genre).get();
		return getAvailableMovies(moviesPage);
	}
	
	/**
	 * this function finds all movies with available scripts from the retrieved html and put the movie name and script url into a hash map
	 * @throws IOException
	 */
	private HashMap<String, String> getAvailableMovies(Document moviesPage) throws IOException {
		HashMap<String, String> movies = new HashMap<>();
		Elements tables = moviesPage.getElementsByAttributeValue("valign", "top");
		Elements childrenOfTable = tables.last().children();
		for(Element child : childrenOfTable) {
			if(child.tagName() == "p") {
				String url = getUrlFromElement(child);
				Document doc = Jsoup.connect(url).get();
				Elements links = doc.getElementsByAttributeValueMatching("href", "/(scripts|transcripts)/.+.html");
				if(links != null && !links.isEmpty()) {
					String scriptLink = "http://www.imsdb.com" + links.first().attr("href");
					movies.put(getScriptName(scriptLink), scriptLink);
				}
			}
		}
		return movies;

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
	
	
	//========================test function
	public void writeScriptToFile(String content, String scriptName) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(scriptName);
		out.print(content);
		out.close();
	}
	
	/**
	 * print all available movie names and url of their script
	 */
	public void printMovieList(HashMap<String, String> availableMovies) {
		for(String s : availableMovies.keySet()) {
			System.out.println(s +"\n" + availableMovies.get(s));
		}
	}
	
	
}

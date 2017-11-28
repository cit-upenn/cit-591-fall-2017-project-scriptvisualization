import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * This class can extract a script from given url and write it out to a txt file
 * @author yueyin
 *
 */
public class ScriptScraper {
	
	String url;
	Script script;
	String scriptName;
	
	/**
	 * The constructor initialize url, scripname and script
	 * @param url
	 * @throws IOException
	 */
	public ScriptScraper(String url) throws IOException {
		this.url = url;
		scriptName = getScriptName();
		script = scrapeScript(url);
		 
	}

	/**
	 * Extract script from given url, create  and return an object of Script out of the scraped content
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private Script scrapeScript(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		//remove emty tags
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
		return new Script(scripts.toString().replaceAll("[\\n]+", "\n").replaceAll("</b>|<pre>|</pre>", "").replaceAll("\\n[\\s]+\\n", "\n").trim()); 
	}
	

	/**
	 * Write the script to a file, file name is the movie name
	 * @throws FileNotFoundException
	 */
	public void writeScriptToFile() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(scriptName + ".txt");
		out.print(script.getContent());
		out.close();
	}
	
	/**
	 * mutate scriptScaper with a new url
	 * @param url
	 * @throws IOException
	 */
	public void scrape(String url) throws IOException {
		this.url = url;
		scriptName = getScriptName();
		script = scrapeScript(url);
	}
	
	
	/**
	 * Get the name of the movie from given url
	 * @return
	 */
	private String getScriptName() {
		String[] splitUrl = url.split("/");
		return splitUrl[splitUrl.length - 1].replaceAll(".html", "");
		
	}
}

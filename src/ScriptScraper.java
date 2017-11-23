import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScriptScraper {
	
	String url;
	Script script;
	String scriptName;
	
	public ScriptScraper(String url) throws IOException {
		this.url = url;
		scriptName = getScriptName();
		script = scrapeScript(url);
		 
	}

	private Script scrapeScript(String url) throws IOException {
		 
		Document doc = Jsoup.connect(url).get();
		
		Elements scriptContent = doc.getElementsByTag("pre");
		
		StringBuilder scripts = new StringBuilder();
		for(Element script : scriptContent) {
			scripts.append(script.html());
		}
		return new Script(scripts.toString());
	}
	
	public void writeScriptToFile() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(scriptName + ".txt");
		out.print(script.getContent());
		out.close();
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	private String getScriptName() {
		String[] splitUrl = url.split("/");
		return splitUrl[splitUrl.length - 1].replaceAll(".html", "");
		
	}
}

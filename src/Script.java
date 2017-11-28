import java.util.ArrayList;

/**
 * This class represents a script, a script has content and charaters
 * @author yueyin
 *
 */
public class Script {
	
	String content;
	ArrayList<Persona> characters;
	
	public Script(String content) {
		this.content = content;
		ScrapeReader scriptReader = new ScrapeReader(content);
		characters = scriptReader.getCharacters();
	}
	
	public String getContent() {
		return content;
	}

}

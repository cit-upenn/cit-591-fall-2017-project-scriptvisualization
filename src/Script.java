import java.util.ArrayList;

/**
 * This class represents a script
 * @author yueyin
 *
 */
public class Script {
	
	String content;
	ArrayList<Persona> characters;
	
	public Script(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

}

package script;
 

/**
 * This class represents a script, a script has content and charaters
 * @author yueyin
 *
 */
public class Script {
	
	String content;
	Relationships relationgraph;
	
	public Script(String content) {
		this.content = content;
		
		 
	}
	
	public String getContent() {
		return content;
	}

}

/**
 * This class represents a chunk of a script
 * @author yueyin
 *
 */
public class ScriptChunk {
	
	String name;
	String dialogue;
	String narrative;
	
	public ScriptChunk(String name, String dialogue, String narrative) {
		super();
		this.name = name;
		this.dialogue = dialogue;
		this.narrative = narrative;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the dialogue
	 */
	public String getDialogue() {
		return dialogue;
	}

	/**
	 * @return the narrative
	 */
	public String getNarrative() {
		return narrative;
	}
	
	
	
	

}

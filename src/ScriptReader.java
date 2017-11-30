import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 * This class takes in content of a script and analyze it's content
 * @author yueyin
 *
 */
public class ScriptReader {
	
	ArrayList<ScriptChunk> scriptChunks;
	Relationships relationgraph;
	ArrayList<String> stoplist;
	
	/**
	 * The constructor initialize instance variables and build a graph out of the given script
	 * @param content
	 */
	public ScriptReader(String content) {
		// TODO Auto-generated constructor stub
		scriptChunks = new ArrayList<>();
		splitScriptToChunks(content);
		this.relationgraph = new Relationships();
		stoplist = new ArrayList<String>();
		addStoplist();
		analysizeChunks();
		
	}
	
	/**
	 * This function add some words that are impossible to be a name
	 */
	private void addStoplist() {
		// TODO Auto-generated method stub
		stoplist.add("omit");
		stoplist.add("dissovle");
		stoplist.add("fade");
		stoplist.add("ext");
		stoplist.add("int");
		stoplist.add("day");
		stoplist.add("...");
		stoplist.add("cut");
	}

	/**
	 * This function analyzes the script chunk by chunk
	 */
	private void analysizeChunks() {
		 Persona prev = null;
		 for(ScriptChunk chunk : scriptChunks) {
			 //continue if the name is invalid
			 if(!isValidName(chunk.name)) {
				 prev = null;
				 continue;
			 }
			 Persona curr = relationgraph.createVertex(chunk.name);
			 curr.getLines().add(chunk.dialogue);
			 if(prev != null && prev != curr) {
				 double relation = 0.5;
				 relationgraph.createEdge(prev, curr, relation);
			 }
			 prev = curr;
		 }
		
	}

	//determine whether the name is valid
	private boolean isValidName(String name) {
		 
		// TODO Auto-generated method stub
		String lowerName = name.toLowerCase();
		for(String s : stoplist) {
			if(lowerName.contains(s)) return false;
		}
		//if contains number, return false;
		return true;
	}


	/**
	 * This method but whole script into chunks. Each chunk is composed of name, dialogue and narrative
	 * @param content
	 */
	private void splitScriptToChunks(String content) {
		String[] chunks = content.split("<b>");
		for(String chunk : chunks) {
			//System.out.print("==========================\n"+ chunk);
			if(chunk.length() == 0) continue;
			chunk = chunk.replaceAll("\\(.+\\)", "").replaceAll("\\n[\\s]+\\n", "\n");
			String[] splitChunk = chunk.split("\\n");
			String name = splitChunk[0].trim();
			StringBuilder dialog = new StringBuilder();
			StringBuilder narra = new StringBuilder();
			int previousCountSpace = Integer.MIN_VALUE;
			int linNumber = 1;
			for(; linNumber < splitChunk.length; linNumber++) {
				int countSpace = countSpace(splitChunk[linNumber]);
				if(countSpace < previousCountSpace) break;
				dialog.append(splitChunk[linNumber].trim() + " ");
				previousCountSpace = countSpace;
			}
			for(;linNumber < splitChunk.length; linNumber++) {
				narra.append(splitChunk[linNumber].trim()+ " ");
			}
			
			ScriptChunk schunk = new ScriptChunk(name, dialog.toString(), narra.toString());
			scriptChunks.add(schunk);
			 
		}
	}

	/**
	 * This method count whitespace a string starts with
	 * @param string
	 * @return
	 */
	private int countSpace(String string) {
		// TODO Auto-generated method stub
		int countSpace = 0;
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == ' ') countSpace++;
			if(string.charAt(i) == '\t') countSpace += 4;
			else break;
		}
		return countSpace;
	}

	
	/**
	 * This class represents a chunk of a script
	 * @author yueyin
	 *
	 */
	private class ScriptChunk {
		
		String name;
		String dialogue;
		String narrative;
		
		public ScriptChunk(String name, String dialogue, String narrative) {
			this.name = name;
			this.dialogue = dialogue;
			this.narrative = narrative;
		}
		
	}

}

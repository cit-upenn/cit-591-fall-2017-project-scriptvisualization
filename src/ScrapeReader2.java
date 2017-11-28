import java.util.ArrayList;
/**
 * This class takes in content of a script and analyze it's content
 * @author yueyin
 *
 */
public class ScrapeReader2 {
	
	ArrayList<ScriptChunk> scriptChunks = new ArrayList<>();
	
	public ScrapeReader2(String content) {
		// TODO Auto-generated constructor stub
		splitScriptToChunks(content);
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
			//对【0】进行判断，不合适的chunk省掉
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
			System.out.println("name " + schunk.name);
			System.out.println("dia " + schunk.dialogue);
			System.out.println("narr " + schunk.narrative);
			System.out.println("=============");
			 
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

	public ArrayList<Persona> getCharacters() {
		// TODO Auto-generated method stub
		return null;
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

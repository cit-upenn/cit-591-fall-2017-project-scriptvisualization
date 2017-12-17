package script;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import apiCall.WatsonAnalyzer;
import apiCall.WatsonCaller;

/**
 * This class takes in content of a script and analyze it's content
 * 
 * @author yueyin
 *
 */
public class ScriptReader {

	ArrayList<ScriptChunk> scriptChunks;
	Relationships relationgraph;
	ArrayList<String> stoplist;
	String scriptName;
	WatsonCaller wc = new WatsonCaller();
	WatsonAnalyzer wa = new WatsonAnalyzer();

	/**
	 * This function returns an analyzed script
	 * 
	 * @param content
	 * @return Script
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public Script readScript(String content, String scriptName) throws IOException, GeneralSecurityException {
		this.scriptName = scriptName;
		scriptChunks = new ArrayList<>();
		splitScriptToChunks(content);
		this.relationgraph = new Relationships();
		stoplist = new ArrayList<String>();
		addStoplist();
		analysizeChunks();
		BufferedImage poster = ImageScraper.getImageGivenUrl(ImageScraper.getPostPathFromTMDB(scriptName));
		// changed mainCharacters to type ArrayList
		ArrayList<Persona> mainCharacters = getMainCharacters();
		HashMap<String, HashMap<String, Double>> naturalLangUnderstanding = wa
				.naturalLangAnalyzer(wc.NaturalLangUnderstanding(content));
		Script script = new Script(scriptName, content, relationgraph, poster, mainCharacters,
				naturalLangUnderstanding);

		return script;
	}

	/**
	 * get top 10 occurrence and set personal image
	 * 
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	private ArrayList<Persona> getMainCharacters() throws IOException, GeneralSecurityException {
		Set<Persona> characterName = getRelationgraph().getGraph().vertexSet();
		ArrayList<Persona> characters = new ArrayList<Persona>();
		ArrayList<Persona> mainRoles = new ArrayList<Persona>();
		for (Persona p : characterName) {
			characters.add(p);
		}
		Collections.sort(characters);

		for (int i = 0; i < 10; i++) {
			Persona curr = characters.get(i);
			//List<String> images = ImageScraper.getImageUrlsFromGoogle(curr.getName() + " " + scriptName);
			int index = 0;
			BufferedImage personaImage;
			/*
			while (true) {
				String url = images.get(index++);
				if (url != null) {
					curr.setImage(url);
					break;
				}

			}
			*/

			mainRoles.add(curr);
		}
		return mainRoles;
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
		stoplist.add("close");
		stoplist.add("med");
		stoplist.add("-");
		stoplist.add("shot");
	}

	/**
	 * This function analyzes the script chunk by chunk
	 * 
	 * @throws IOException
	 */
	private void analysizeChunks() throws IOException {
		Persona prev = null;
//		 for (int i = 0; i < 200; i++) {
		for (ScriptChunk chunk : scriptChunks) {
//			 ScriptChunk chunk = scriptChunks.get(i);
			// continue if the name is invalid
			if (!isValidName(chunk.name)) {
				prev = null;
				continue;
			}
			Persona curr = relationgraph.createVertex(chunk.name);
			curr.getLines().add(chunk.dialogue);
			if (prev != null && prev != curr) {
				double relation = 0;
				// need to get relation here.param: chunk.dialogue
				try {
					relation = wc.getRelationshipIndicator(chunk.dialogue).getSentiment().getDocument().getScore();
//					relation = wa.relationshipAnalyzer(wc.getRelationshipIndicator(chunk.dialogue)).get("sentiment")
//							.get("general");
				}
				// catch something like unsupported text language
				// e.g. this exception would catch April 14, 1912.
				catch (com.ibm.watson.developer_cloud.service.exception.BadRequestException bre) {
					relation = 0;
				} catch (com.ibm.watson.developer_cloud.service.exception.ServiceResponseException sre) {
					relation = 0;
				}

				relationgraph.createEdge(prev, curr, relation);
			}
			prev = curr;
		}

	}

	// determine whether the name is valid
	private boolean isValidName(String name) {

		// TODO Auto-generated method stub
		String lowerName = name.toLowerCase();
		for (String s : stoplist) {
			if (lowerName.contains(s))
				return false;
		}
		// if contains number, return false;
		if (lowerName.matches(".*\\d.*"))
			return false;
		return true;
	}

	/**
	 * This method but whole script into chunks. Each chunk is composed of name,
	 * dialogue and narrative
	 * 
	 * @param content
	 */
	private void splitScriptToChunks(String content) {
		String[] chunks = content.split("<b>");
		for (String chunk : chunks) {
			if (chunk.length() == 0)
				continue;
			chunk = chunk.replaceAll("\\(.+\\)", "");
			String[] splitChunk = chunk.split("\\n");
			if (splitChunk.length < 2)
				continue;
			String name = splitChunk[0].trim();
			StringBuilder dialog = new StringBuilder();
			StringBuilder narra = new StringBuilder();
			int linNumber = 1;
			while (splitChunk[linNumber].length() == 0)
				linNumber++;
			for (; linNumber < splitChunk.length; linNumber++) {
				if (splitChunk[linNumber].length() == 0)
					break;
				dialog.append(splitChunk[linNumber]);
			}
			for (; linNumber < splitChunk.length; linNumber++) {
				narra.append(splitChunk[linNumber]);
			}
			ScriptChunk schunk = new ScriptChunk(name, dialog.toString(), narra.toString());
			scriptChunks.add(schunk);

		}
	}

	/**
	 * This class represents a chunk of a script
	 * 
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "name= " + name + "\ndialogue= " + dialogue + "\nnarrative= " + narrative;
		}

	}

	public Relationships getRelationgraph() {
		return relationgraph;
	}

	public void setRelationgraph(Relationships relationgraph) {
		this.relationgraph = relationgraph;
	}

}

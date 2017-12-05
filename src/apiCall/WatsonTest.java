/**
 * extraneous declarations of variables and sorts are only here for now for testing purposes.
 * will be rearranged.
 */
package apiCall;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;

import script.Persona;
import script.ScriptReader;
import script.ScriptScraper;

public class WatsonTest {
	public static void main(String[] args) throws IOException {

		// String urlName = "http://www.imsdb.com/scripts/" + scriptName.split(" ")[0] +
		// "-" + scriptName.split(" ")[1]
		// + ".html";
		String urlName = "http://www.imsdb.com/scripts/Pearl-Harbor.html";
		ScriptScraper ss = new ScriptScraper();
		ScriptReader sr = new ScriptReader(ss.scrapeScript(urlName));
		Set<Persona> characterName = sr.getRelationgraph().getGraph().vertexSet();
		ArrayList<Persona> characters = new ArrayList<Persona>();
		ArrayList<Persona> mainRoles = new ArrayList<Persona>();
		for (Persona p : characterName) {
			characters.add(p);
		}
		Collections.sort(characters);

		for (int i = 0; i < 10; i++) {
			mainRoles.add(characters.get(i));
		}

		// System.out.println(mainRoles.get(0).getName());
		// String s = mainRoles.get(0).getLines().get(0).toString();

		HashMap<String, HashMap<String, Double>> lineEmotionTone = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, HashMap<String, Double>> lineLangTone = new HashMap<String, HashMap<String, Double>>();

		WatsonCaller wc = new WatsonCaller();
		WatsonAnalyzer wa = new WatsonAnalyzer();

		for (String s : mainRoles.get(0).getLines()) {
			DocumentAnalysis chunkTone = wc.getToneOfLines(s);
			HashMap<String, Double> lineEmoScore = wa.lineEmotionToneAnalyzer(chunkTone);
			lineEmotionTone.put(s, lineEmoScore);
			HashMap<String, Double> lineLangScore = wa.lineLangToneAnalyzer(chunkTone);
			lineLangTone.put(s, lineLangScore);
		}
		PrintWriter pw = new PrintWriter("testEmoReport.txt");
		PrintWriter pw2 = new PrintWriter("testLangReport.txt");
		// to get emotion tone timeline of a character
		for (String s : lineEmotionTone.keySet()) {
			pw.println(s);
			pw.println(lineEmotionTone.get(s));
		}
		pw.close();
		// to get language tone timeline of a character
		for (String s : lineLangTone.keySet()) {
			pw2.println(s);
			pw2.println(lineLangTone.get(s));
		}
		pw2.close();

	}
}

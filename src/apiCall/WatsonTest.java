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

import script.Persona;
import script.Script;
import script.ScriptReader;
import script.ScriptScraper;

public class WatsonTest {
	public static void main(String[] args) throws IOException {

		// String urlName = "http://www.imsdb.com/scripts/" + scriptName.split(" ")[0] +
		// "-" + scriptName.split(" ")[1]
		// + ".html";
		String urlName = "http://www.imsdb.com/scripts/Pearl-Harbor.html";
		ScriptScraper ss = new ScriptScraper();
//		ScriptReader sr = new ScriptReader(ss.scrapeScript(urlName));
		ScriptReader sr = new ScriptReader();
		Script script = sr.readScript(ss.scrapeScript(urlName), "Pearl Harbor");
//		System.out.println(script.getContent());
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

		// for (String s : mainRoles.get(0).getLines()) {
		// DocumentAnalysis chunkTone = wc.getToneOfLines(s);
		// HashMap<String, Double> lineEmoScore = wa.lineEmotionToneAnalyzer(chunkTone);
		// lineEmotionTone.put(s, lineEmoScore);
		// HashMap<String, Double> lineLangScore = wa.lineLangToneAnalyzer(chunkTone);
		// lineLangTone.put(s, lineLangScore);
		// }
		// PrintWriter pw = new PrintWriter("testEmoReport.txt");
		// PrintWriter pw2 = new PrintWriter("testLangReport.txt");
		PrintWriter pw3 = new PrintWriter("sampleNaturalLangReport.txt");
		// // to get emotion tone timeline of a character
		// for (String s : lineEmotionTone.keySet()) {
		// pw.println(s);
		// pw.println(lineEmotionTone.get(s));
		// }
		// pw.close();
		// // to get language tone timeline of a character
		// for (String s : lineLangTone.keySet()) {
		// pw2.println(s);
		// pw2.println(lineLangTone.get(s));
		// }
		// pw2.close();

		String s = mainRoles.get(0).getLines().toString();
		// wa.personalityAnalyzer(wc.getPersonality(s));

//		String content = ss.scrapeScript(urlName);
//		HashMap<String, HashMap<String, Double>> naturalLangUnderstanding = wa
//				.naturalLangAnalyzer(wc.NaturalLangUnderstanding(content));
//		for (String str: naturalLangUnderstanding.keySet()) {
//			System.out.println(str);
//			for (String str2: naturalLangUnderstanding.get(str).keySet()) {
//				System.out.print("\t" + str2 + "\t");
//				System.out.println(naturalLangUnderstanding.get(str).get(str2));
//			}
//		}
		
		
//		pw3.println(naturalLangUnderstanding);
//		pw3.close();
		// wa.naturalLangAnalyzer(wc.NaturalLangUnderstanding(content));

	}
}

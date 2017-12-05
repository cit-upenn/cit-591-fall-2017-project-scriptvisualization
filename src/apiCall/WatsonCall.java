package apiCall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;

import script.Persona;
import script.ScriptReader;
import script.ScriptScraper;

public class WatsonCall {
	public WatsonCall() {

	}

	public void getToneOfLines(String scriptName) throws IOException {
		String urlName = "http://www.imsdb.com/scripts/" + scriptName.split(" ")[0] + "-" + scriptName.split(" ")[1] + ".html";
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
		// mainRoles contain top 10 characters with most occurrences.
		StringBuilder sb = new StringBuilder();
//		PrintWriter pw = new PrintWriter(new File("test.txt"));
		for (Persona p : mainRoles) {
			System.out.println(p.getName());
			for (String s : p.getLines()) {
				sb.append(s + "\n");
			}
			final String VERSION_DATE = "2016-05-19";
			ToneAnalyzer service = new ToneAnalyzer(VERSION_DATE);
			service.setUsernameAndPassword("028da7b1-3878-4521-8063-3cd09e5684c5", "lFCpPJceVbT4");
			int sentenceCount = sb.toString().split("[!?.]+").length;
			System.out.println(sentenceCount);
//			ToneOptions tonOptions = new ToneOptions.Builder().text(sb.toString()).build();
//			ToneAnalysis tone = service.tone(tonOptions).execute();
			//pw.println(tone);
//			pw.close();
			//System.out.println(tone);
			//System.out.println("==================================================================");
		}

	}
}

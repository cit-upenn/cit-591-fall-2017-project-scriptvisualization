package apiCall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;

import script.Persona;
import script.ScriptReader;
import script.ScriptScraper;

public class WatsonTest {
	public static void main(String[] args) throws IOException {
//		String urlName = "http://www.imsdb.com/scripts/" + scriptName.split(" ")[0] + "-" + scriptName.split(" ")[1]
//				+ ".html";
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
		
		System.out.println(mainRoles.get(0).getName());
		String s = mainRoles.get(0).getLines().get(0).toString();
		
		WatsonCaller wc = new WatsonCaller();
		DocumentAnalysis chunkTone = wc.getToneOfLines(s);
		
		WatsonAnalyzer wa = new WatsonAnalyzer();
		wa.lineToneAnalyzer(chunkTone);
		
		
		
	}
}

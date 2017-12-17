/**
 * extraneous declarations of variables and sorts are only here for now for testing purposes.
 * will be rearranged.
 */
package apiCall;

import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Trait;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;

import script.Persona;
import script.Script;
import script.ScriptReader;
import script.ScriptScraper;

public class WatsonTester {
	public static void main(String[] args) throws IOException, GeneralSecurityException {

		// String urlName = "http://www.imsdb.com/scripts/" + scriptName.split(" ")[0] +
		// "-" + scriptName.split(" ")[1]
		// + ".html";
		String urlName = "http://www.imsdb.com/scripts/Pearl-Harbor.html";
		// String urlName = "http://www.imsdb.com/scripts/La-La-Land.html";
		ScriptScraper ss = new ScriptScraper();
		// ScriptReader sr = new ScriptReader(ss.scrapeScript(urlName));
		ScriptReader sr = new ScriptReader();
		Script script = sr.readScript(ScriptScraper.scrapeScript(urlName), "Pearl Harbor");
		// Script script = sr.readScript(ScriptScraper.scrapeScript(urlName), "La La
		// Land");
		// System.out.println(script.getContent());

		Set<Persona> characterName = sr.getRelationgraph().getGraph().vertexSet();
		ArrayList<Persona> characters = new ArrayList<Persona>();
		ArrayList<Persona> mainRoles = new ArrayList<Persona>();
		for (Persona p : characterName) {
			characters.add(p);
		}
		Collections.sort(characters);

		for (int i = 0; i < 5; i++) {
			mainRoles.add(characters.get(i));
		}

		// System.out.println(mainRoles.get(0).getName());
		// String s = mainRoles.get(0).getLines().get(0).toString();

		HashMap<Integer, HashMap<String, Double>> lineEmotionTone = new HashMap<Integer, HashMap<String, Double>>();
		HashMap<String, HashMap<String, Double>> lineLangTone = new HashMap<String, HashMap<String, Double>>();

		WatsonCaller wc = new WatsonCaller();
		WatsonAnalyzer wa = new WatsonAnalyzer();

		String content = ss.scrapeScript(urlName);

		StringBuilder sb = new StringBuilder();
		for (String s : mainRoles.get(0).getLines()) {
			sb.append(s);
		}
		Profile profile = wc.getPersonality(sb.toString());
		// adventurousness.put(profile.getPersonality(), value)
		JSONObject report = new JSONObject();
		report.put("name", "personalityReport");
		JSONArray reportEntry = new JSONArray();
		JSONObject personality = new JSONObject();
		personality.put("name", "personality");
		JSONArray children = new JSONArray();
		for (Trait t : profile.getPersonality()) {
			JSONArray gc = new JSONArray();
			for (Trait tt : t.getChildren()) {
				JSONObject gcEntry = new JSONObject();
				gcEntry.put("name", tt.getName());
				gcEntry.put("size", tt.getPercentile());
				gc.add(gcEntry);
			}
			JSONObject cEntry = new JSONObject();
			cEntry.put("name", t.getName());
			cEntry.put("children", gc);
			children.add(cEntry);
		}
		personality.put("children", children);
		
		reportEntry.add(personality);
		
		JSONObject values = new JSONObject();
		values.put("name", "values");
		JSONArray vEntry = new JSONArray();
		
		for (Trait t2: profile.getValues()) {
			JSONObject vChildren = new JSONObject();
			vChildren.put("name", t2.getName());
			vChildren.put("size", t2.getPercentile());
			vEntry.add(vChildren);
		}
		
		values.put("children", vEntry);
		reportEntry.add(values);

		JSONObject needs = new JSONObject();
		needs.put("name", "needs");
		JSONArray nEntry = new JSONArray();
		for (Trait t3: profile.getNeeds()) {
			JSONObject nChildren = new JSONObject();
			nChildren.put("name", t3.getName());
			nChildren.put("size", t3.getPercentile());
			nEntry.add(nChildren);
		}
		needs.put("children", nEntry);
		reportEntry.add(needs);
		
		
		report.put("children", reportEntry);
		
//		System.out.println(report);
		try {
		 FileWriter fileWriter = new FileWriter("personality.json");
		 fileWriter.write(report.toJSONString()); 
		 fileWriter.flush(); 
		 } 
		catch (Exception e) { e.printStackTrace(); }
		

		/*
		 * PrintWriter pw = new PrintWriter("genInfo.txt"); Double sentiment =
		 * wc.getGeneralSentiment(content); pw.println(script.getName());
		 * pw.print("#sentiment" + "\t"); pw.println(sentiment); AnalysisResults cgJson
		 * = wc.getCategoriess(content); for (CategoriesResult cr:
		 * cgJson.getCategories()) { pw.print("#"); pw.print(cr.getLabel() + "\t");
		 * pw.println(cr.getScore()); } pw.close();
		 */

		// System.out.println(wc.getKeywords(content));
		// AnalysisResults kwJson = wc.getKeywords(content);
		// for (KeywordsResult kr: kwJson.getKeywords()) {
		// System.out.println(kr.getRelevance());
		// System.out.println(kr.getText());
		// }
		/*
		 * JSONObject tier = new JSONObject(); JSONArray nodes = new JSONArray(); for
		 * (Persona p: links.vertexSet()) { JSONObject names = new JSONObject();
		 * 
		 * names.put("id", p.getName()); // names.put("id",
		 * links.getEdgeSource(r).getName()); nodes.add(names); } tier.put("nodes",
		 * nodes); JSONArray linkages = new JSONArray(); for (Relationship r:
		 * links.edgeSet()) { JSONObject entry = new JSONObject(); entry.put("source",
		 * links.getEdgeSource(r).getName()); entry.put("target",
		 * links.getEdgeTarget(r).getName()); entry.put("value", r.relation);
		 * linkages.add(entry); } tier.put("links", linkages);
		 */
		/*
		 * JSONArray keywords = new JSONArray(); for (KeywordsResult kr:
		 * kwJson.getKeywords()) { JSONObject entry = new JSONObject();
		 * entry.put("size", kr.getRelevance()); entry.put("text", kr.getText());
		 * keywords.add(entry); } JSONObject list = new JSONObject();
		 * list.put("keywords", keywords); System.out.println(list); try { FileWriter
		 * fileWriter = new FileWriter("keywords.json");
		 * fileWriter.write(list.toJSONString()); fileWriter.flush(); } catch (Exception
		 * e) { e.printStackTrace(); }
		 */

		// System.out.println(mainRoles.get(0));
		
		  int lineCount = 0; for (String s : mainRoles.get(0).getLines()) {
		  
		  DocumentAnalysis chunkTone = wc.getToneOfLines(s);
		  HashMap<String, Double> lineEmoScore = wa.lineEmotionToneAnalyzer(chunkTone);
		  lineEmotionTone.put(lineCount++, lineEmoScore); 
		  HashMap<String, Double> lineLangScore = wa.lineLangToneAnalyzer(chunkTone); 
		  lineLangTone.put(s, lineLangScore); 
		  }
		 
		// PrintWriter pw = new PrintWriter("testEmoReport.txt");
		// PrintWriter pw2 = new PrintWriter("testLangReport.txt");
		// to get emotion tone timeline of a character
		// for (String s : lineEmotionTone.keySet()) {
		// pw.println(s);
		// pw.println(lineEmotionTone.get(s));
		// }
		// pw.close();
		// PrintWriter System.out = new PrintWriter("EmotionTimeline.tsv");

		// for (String s: mainRoles.get(0).getLines()) {
		// System.out.println();
		// }

		
		  for (int i: lineEmotionTone.keySet()) { 
			  if(lineEmotionTone.get(i).containsKey("Anger")) { 
				  System.out.println("1" + "\t"+ (i+1) + "\t" + lineEmotionTone.get(i).get("Anger") * 100);
				  System.out.println("2" + "\t" + (i+1) + "\t" + 49); 
				  System.out.println("3" +"\t" + (i+1) + "\t" + 49); 
				  System.out.println("4" + "\t" + (i+1) + "\t" + 49); 
				  System.out.println("5" + "\t" + (i+1) + "\t" + 49); 
			} else if (lineEmotionTone.get(i).containsKey("Fear")) { 
				System.out.println("2" + "\t"+ (i+1) + "\t" + lineEmotionTone.get(i).get("Fear") * 100);
				System.out.println("1" + "\t" + (i+1) + "\t" + 49); 
				System.out.println("3" + "\t" + (i+1) + "\t" + 49); 
				System.out.println("4" + "\t" + (i+1) + "\t" + 49); 
				System.out.println("5" + "\t" + (i+1) + "\t" + 49); 
				}else if (lineEmotionTone.get(i).containsKey("Joy")) { 
					System.out.println("3" + "\t"+ (i+1) + "\t" + lineEmotionTone.get(i).get("Joy") * 100);
					System.out.println("2" + "\t" + (i+1) + "\t" + 49); 
					System.out.println("1" + "\t" + (i+1) + "\t" + 49); 
					System.out.println("4" + "\t" + (i+1) + "\t" + 49); 
					System.out.println("5" + "\t" + (i+1) + "\t" + 49); 
				}else if (lineEmotionTone.get(i).containsKey("Sadness")) { 
					System.out.println("4" +"\t"+ (i+1) + "\t" + lineEmotionTone.get(i).get("Sadness") * 100);
					System.out.println("2" + "\t" + (i+1) + "\t" + 49); 
					System.out.println("3" +"\t" + (i+1) + "\t" + 49); 
					System.out.println("1" + "\t" + (i+1) + "\t" + 49); 
					System.out.println("5" + "\t" + (i+1) + "\t" + 49); 
				}else if (lineEmotionTone.get(i).containsKey("Disgust")) { 
					System.out.println("5" + "\t"+ (i+1) + "\t" + lineEmotionTone.get(i).get("Disgust") * 100);
					System.out.println("2" + "\t" + (i+1) + "\t" + 49); 
					System.out.println("3" +"\t" + (i+1) + "\t" + 49); 
					System.out.println("4" + "\t" + (i+1) + "\t" + 49); 
					System.out.println("1" + "\t" + (i+1) + "\t" + 49); 
				} else {
					System.out.println("1" + "\t" + (i+1) + "\t" + 49); 
					System.out.println("2" + "\t" + (i+1) + "\t" + 49); 
					System.out.println("3" + "\t" + (i+1) + "\t" + 49); 
					System.out.println("4" + "\t" + (i+1) + "\t" + 49);
					System.out.println("5" + "\t" + (i+1) + "\t" + 49); 
					} 
			  }
		 
		// System.out.close();
		// System.out.println(lineEmotionTone);

		// // to get language tone timeline of a character
		// for (String s : lineLangTone.keySet()) {
		// pw2.println(s);
		// pw2.println(lineLangTone.get(s));
		// }
		// pw2.close();

		// String s = mainRoles.get(0).getLines().toString();
		// wa.personalityAnalyzer(wc.getPersonality(s));

		// String content = ss.scrapeScript(urlName);
		// HashMap<String, HashMap<String, Double>> naturalLangUnderstanding = wa
		// .naturalLangAnalyzer(wc.NaturalLangUnderstanding(content));
		// for (String str: naturalLangUnderstanding.keySet()) {
		// System.out.println(str);
		// for (String str2: naturalLangUnderstanding.get(str).keySet()) {
		// System.out.print("\t" + str2 + "\t");
		// System.out.println(naturalLangUnderstanding.get(str).get(str2));
		// }
		// }

		// wa.naturalLangAnalyzer(wc.NaturalLangUnderstanding(content));

	}
}

package script;
 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.jgrapht.graph.SimpleGraph;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsResult;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Trait;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;

import apiCall.WatsonAnalyzer;
import apiCall.WatsonCaller;
import script.Relationships.Relationship;

/**
 * This class print out personlaity of mainCharacters, graph of relationship, keywords of whole script and poster address of 8 main characters, emotional time line of three main characters
 * @author yueyin
 *
 */
public class DataPrinter {
	private static WatsonCaller wc;
	private static WatsonAnalyzer wa;

	public DataPrinter() {
		wc = new WatsonCaller();
		wa = new WatsonAnalyzer();
	}

	/**
	 * This method prints out json file of protagonist's personality.
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void printPersonality(Script script) {
		System.out.println("start printing personality");
		StringBuilder sb = new StringBuilder();
		for (String s : script.getMainCharacters().get(0).getLines()) {
			sb.append(s);
		}

		Profile profile = wc.getPersonality(sb.toString());
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

		for (Trait t2 : profile.getValues()) {
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
		for (Trait t3 : profile.getNeeds()) {
			JSONObject nChildren = new JSONObject();
			nChildren.put("name", t3.getName());
			nChildren.put("size", t3.getPercentile());
			nEntry.add(nChildren);
		}
		needs.put("children", nEntry);
		reportEntry.add(needs);

		report.put("children", reportEntry);

		// System.out.println(report);
		try {
			FileWriter fileWriter = new FileWriter("data/personality.json");
			fileWriter.write(report.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("finish printing personality");
	}

	/**
	 * This method prints out relationship json file.
	 * 
	 * @param sr
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void printRelation(Script script) {
		System.out.println("start printing relation");
		SimpleGraph<Persona, Relationship> links = script.getRelationgraph().graph;
		JSONObject tier = new JSONObject();
		JSONArray nodes = new JSONArray();
		for (Persona p : links.vertexSet()) {
			JSONObject names = new JSONObject();

			names.put("id", p.getName());
			if (p.equals(script.getMainCharacters().get(0)) || p.equals(script.getMainCharacters().get(1)) || p.equals(script.getMainCharacters().get(2))) {
				names.put("group", 1);
			} else if (script.getMainCharacters().contains(p)) {
				names.put("group", 2);
			}
			// names.put("id", links.getEdgeSource(r).getName());
			nodes.add(names);
		}
		tier.put("nodes", nodes);
		JSONArray linkages = new JSONArray();
		for (Relationship r : links.edgeSet()) {
			JSONObject entry = new JSONObject();
			entry.put("source", links.getEdgeSource(r).getName());
			entry.put("target", links.getEdgeTarget(r).getName());
			entry.put("value", r.relation);

			linkages.add(entry);

		}
		tier.put("links", linkages);
//		System.out.println(tier);

		try {
			FileWriter fileWriter = new FileWriter("data/relationship.json");
			fileWriter.write(tier.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("finish printing relation");
	}

	/**
	 * This method prints out keywords json file
	 * 
	 * @param content
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void printKeywords(Script script) throws IOException {
		System.out.println("start printing keywords");
		AnalysisResults kwJson = wc.getKeywords(script.getContent());

		JSONArray keywords = new JSONArray();
		for (KeywordsResult kr : kwJson.getKeywords()) {
			JSONObject entry = new JSONObject();
			entry.put("size", kr.getRelevance());
			entry.put("text", kr.getText());
			keywords.add(entry);
		}
		JSONObject list = new JSONObject();
		list.put("keywords", keywords);
		try {
			FileWriter fileWriter = new FileWriter("data/keywords.json");
			fileWriter.write(list.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("finish printing keywords");

	}

	/**
	 * This method prints out a txt file prints out header of a script. #general
	 * overall sentiment of a given script; #categories, script contents into a
	 * hierarchy, each with a score.
	 * 
	 * @param script
	 * @throws IOException
	 */
	public void printMainPhotos(Script script) throws IOException {
		System.out.println("start print main photos");
		ArrayList<Persona> mainCharacters = script.getMainCharacters();
		PrintWriter out = new PrintWriter(new File("data/charactersPhotos.txt"));
		out.println(script.getName());
		for (Persona persona : mainCharacters) {
			out.println(persona.getName());
			out.println(persona.getImage());
		}
		Double sentiment = wc.getGeneralSentiment(script.getContent());
		out.print("#sentiment" + "\t");
		out.println(sentiment);
		AnalysisResults cgJson = wc.getCategoriess(script.getContent());
		for (CategoriesResult cr : cgJson.getCategories()) {
			out.print("#");
			out.print(cr.getLabel() + "\t");
			out.println(cr.getScore());
		}
		System.out.println("end print main photos");
		
		out.close();
	}
	
	/**
	 * This method print emotional time line of three main characters
	 * @param script
	 * @throws IOException
	 */

	public void printTimeLine(Script script) throws IOException {
		System.out.println("start pringting timeline");
		
		for (int i = 0; i < 3; i++) {
			int lineCount = 0;
			HashMap<Integer, HashMap<String, Double>> lineEmotionTone = new HashMap<Integer, HashMap<String, Double>>();
			HashMap<String, HashMap<String, Double>> lineLangTone = new HashMap<String, HashMap<String, Double>>();
			HashMap<String, Double> lineEmoScore;
			HashMap<String, Double> lineLangScore;
			for (String s : script.getMainCharacters().get(i).getLines()) {
				DocumentAnalysis chunkTone = wc.getToneOfLines(s);
				 lineEmoScore = wa.lineEmotionToneAnalyzer(chunkTone);
				lineEmotionTone.put(lineCount++, lineEmoScore);
				 lineLangScore = wa.lineLangToneAnalyzer(chunkTone);
				lineLangTone.put(s, lineLangScore);
			}
			if (i == 0) {
			timelineFormat(lineEmotionTone, "data/protagonist.tsv");
			} else if (i == 1){
				timelineFormat(lineEmotionTone, "data/secondMain.tsv");
			} else {
				timelineFormat(lineEmotionTone, "data/thirdMain.tsv");
			}
		}
		System.out.println("finish printing timeline");
	}

	private void timelineFormat(HashMap<Integer, HashMap<String, Double>> lineEmotionTone, String filepath)
			throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(filepath);
		pw.println("emotion" + "\t" + "line" + "\t" + "value");
		for (int i : lineEmotionTone.keySet()) {
			if (lineEmotionTone.get(i).containsKey("Anger")) {
				pw.println("1" + "\t" + (i + 1) + "\t" + lineEmotionTone.get(i).get("Anger") * 100);
				pw.println("2" + "\t" + (i + 1) + "\t" + 0);
				pw.println("3" + "\t" + (i + 1) + "\t" + 0);
				pw.println("4" + "\t" + (i + 1) + "\t" + 0);
				pw.println("5" + "\t" + (i + 1) + "\t" + 0);
			} else if (lineEmotionTone.get(i).containsKey("Fear")) {
				pw.println("2" + "\t" + (i + 1) + "\t" + lineEmotionTone.get(i).get("Fear") * 100);
				pw.println("1" + "\t" + (i + 1) + "\t" + 0);
				pw.println("3" + "\t" + (i + 1) + "\t" + 0);
				pw.println("4" + "\t" + (i + 1) + "\t" + 0);
				pw.println("5" + "\t" + (i + 1) + "\t" + 0);
			} else if (lineEmotionTone.get(i).containsKey("Joy")) {
				pw.println("3" + "\t" + (i + 1) + "\t" + lineEmotionTone.get(i).get("Joy") * 100);
				pw.println("2" + "\t" + (i + 1) + "\t" + 0);
				pw.println("1" + "\t" + (i + 1) + "\t" + 0);
				pw.println("4" + "\t" + (i + 1) + "\t" + 0);
				pw.println("5" + "\t" + (i + 1) + "\t" + 0);
			} else if (lineEmotionTone.get(i).containsKey("Sadness")) {
				pw.println("4" + "\t" + (i + 1) + "\t" + lineEmotionTone.get(i).get("Sadness") * 100);
				pw.println("2" + "\t" + (i + 1) + "\t" + 0);
				pw.println("3" + "\t" + (i + 1) + "\t" + 0);
				pw.println("1" + "\t" + (i + 1) + "\t" + 0);
				pw.println("5" + "\t" + (i + 1) + "\t" + 0);
			} else if (lineEmotionTone.get(i).containsKey("Disgust")) {
				pw.println("5" + "\t" + (i + 1) + "\t" + lineEmotionTone.get(i).get("Disgust") * 100);
				pw.println("2" + "\t" + (i + 1) + "\t" + 0);
				pw.println("3" + "\t" + (i + 1) + "\t" + 0);
				pw.println("4" + "\t" + (i + 1) + "\t" + 0);
				pw.println("1" + "\t" + (i + 1) + "\t" + 0);
			} else {
				pw.println("1" + "\t" + (i + 1) + "\t" + 0);
				pw.println("2" + "\t" + (i + 1) + "\t" + 0);
				pw.println("3" + "\t" + (i + 1) + "\t" + 0);
				pw.println("4" + "\t" + (i + 1) + "\t" + 0);
				pw.println("5" + "\t" + (i + 1) + "\t" + 0);
			}
		}
		pw.close();
	}
	
	
	public void printOccurrences(Script script) throws IOException {
		  System.out.println("start printing occurrences");
		  PrintWriter pw = new PrintWriter("data/occurrences.csv");
		  pw.println("name,occurences");
		  for (Persona p: script.getMainCharacters()) {
		   pw.println(p.getName() + "," + p.getOccurrence());
		  }
		  pw.close();
		  System.out.println("finish printing occurrences");
	}

}

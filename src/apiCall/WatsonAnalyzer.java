package apiCall;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsResult;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneCategory;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;

public class WatsonAnalyzer {
	public WatsonAnalyzer() {

	}

	public HashMap<String, Double> lineEmotionToneAnalyzer(DocumentAnalysis jsonData) throws IOException {

		// lineScore. Key: tone name (Anger, Fear). Value: score.
		// hashmap of a single paragraph of a character's lines
		HashMap<String, Double> lineScore = new HashMap<String, Double>();

		for (ToneCategory tc : jsonData.getToneCategories()) {
			if (tc.getCategoryId().equals("emotion_tone")) {
				for (ToneScore ts : tc.getTones()) {
					if (ts.getScore() >= 0.5) {
						// System.out.println(ts.getToneName());
						// System.out.println(ts.getScore());
						lineScore.put(ts.getToneName(), ts.getScore());
						// System.out.println("======");
					}
				}
			}
		}
		return lineScore;
	}

	public HashMap<String, Double> lineLangToneAnalyzer(DocumentAnalysis jsonData) throws IOException {

		HashMap<String, Double> lineScore = new HashMap<String, Double>();

		for (ToneCategory tc : jsonData.getToneCategories()) {
			if (tc.getCategoryId().equals("language_tone")) {
				for (ToneScore ts : tc.getTones()) {
					if (ts.getScore() >= 0.5) {
						lineScore.put(ts.getToneName(), ts.getScore());
					}
				}
			}
		}
		return lineScore;
	}

	public void personalityAnalyzer(Profile jsonData) throws FileNotFoundException {
		//HashMap<>
	}

	public HashMap<String, HashMap<String, Double>> naturalLangAnalyzer(
			HashMap<String, AnalysisResults> analysisResults) {
		// System.out.println(results);
		HashMap<String, HashMap<String, Double>> naturalLangUnderstanding = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, Double> sentiment = new HashMap<String, Double>();
		HashMap<String, Double> categories = new HashMap<String, Double>();
		HashMap<String, Double> emotion = new HashMap<String, Double>();
		HashMap<String, Double> keywords = new HashMap<String, Double>();
		HashMap<String, Double> entities = new HashMap<String, Double>();
		HashMap<String, Double> concepts = new HashMap<String, Double>();

		sentiment.put("general", analysisResults.get("sentiment").getSentiment().getDocument().getScore());

		for (CategoriesResult cr : analysisResults.get("categories").getCategories()) {
			categories.put(cr.getLabel(), cr.getScore());
		}

		emotion.put("anger", analysisResults.get("emotion").getEmotion().getDocument().getEmotion().getAnger());
		emotion.put("disgust", analysisResults.get("emotion").getEmotion().getDocument().getEmotion().getDisgust());
		emotion.put("fear", analysisResults.get("emotion").getEmotion().getDocument().getEmotion().getFear());
		emotion.put("joy", analysisResults.get("emotion").getEmotion().getDocument().getEmotion().getJoy());
		emotion.put("sadness", analysisResults.get("emotion").getEmotion().getDocument().getEmotion().getSadness());

		for (KeywordsResult kr : analysisResults.get("keywords").getKeywords()) {
			keywords.put(kr.getText(), kr.getRelevance());
		}

		for (EntitiesResult er : analysisResults.get("entities").getEntities()) {
			entities.put(er.getText(), er.getRelevance());
		}

		for (ConceptsResult cr : analysisResults.get("concepts").getConcepts()) {
			concepts.put(cr.getText(), cr.getRelevance());
		}

		naturalLangUnderstanding.put("sentiment", sentiment);
		naturalLangUnderstanding.put("categories", categories);
		naturalLangUnderstanding.put("emotion", emotion);
		naturalLangUnderstanding.put("keywords", keywords);
		naturalLangUnderstanding.put("entities", entities);
		naturalLangUnderstanding.put("concepts", concepts);

		return naturalLangUnderstanding;

	}

	public HashMap<String, HashMap<String, Double>> relationshipAnalyzer(
			HashMap<String, AnalysisResults> analysisResults) {
		// System.out.println(results);
		HashMap<String, HashMap<String, Double>> relationIndicator = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, Double> sentiment = new HashMap<String, Double>();
//		HashMap<String, Double> emotion = new HashMap<String, Double>();

		sentiment.put("general", analysisResults.get("sentiment").getSentiment().getDocument().getScore());

//		emotion.put("anger", analysisResults.get("emotion").getEmotion().getDocument().getEmotion().getAnger());
//		emotion.put("disgust", analysisResults.get("emotion").getEmotion().getDocument().getEmotion().getDisgust());
//		emotion.put("fear", analysisResults.get("emotion").getEmotion().getDocument().getEmotion().getFear());
//		emotion.put("joy", analysisResults.get("emotion").getEmotion().getDocument().getEmotion().getJoy());
//		emotion.put("sadness", analysisResults.get("emotion").getEmotion().getDocument().getEmotion().getSadness());

		relationIndicator.put("sentiment", sentiment);
//		relationIndicator.put("emotion", emotion);

		return relationIndicator;

	}

}

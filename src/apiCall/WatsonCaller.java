package apiCall;

import java.io.IOException;
import java.util.HashMap;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;
import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

public class WatsonCaller {
	public WatsonCaller() {

	}

	public DocumentAnalysis getToneOfLines(String contentToAnalyze) throws IOException {

		final String VERSION_DATE = "2016-05-19";
		ToneAnalyzer service = new ToneAnalyzer(VERSION_DATE);
		service.setUsernameAndPassword("028da7b1-3878-4521-8063-3cd09e5684c5", "lFCpPJceVbT4");
		ToneOptions tonOptions = new ToneOptions.Builder().text(contentToAnalyze).build();
		ToneAnalysis tone = service.tone(tonOptions).execute();
		DocumentAnalysis toneDoc = tone.getDocumentTone();
		// System.out.println(tone.getDocumentTone());
		// pw.println(toneDoc);
		// pw.close();
		return toneDoc;

	}

	public Profile getPersonality(String personaLines) {
		PersonalityInsights service = new PersonalityInsights("2016-10-19");
		service.setUsernameAndPassword("bc376c0c-56ba-4316-a58f-53e9f410cb06", "b655eZV0Q0bi");
		ProfileOptions options = new ProfileOptions.Builder().text(personaLines).build();
		Profile profile = service.profile(options).execute();
		return profile;
	}

	public HashMap<String, AnalysisResults> NaturalLangUnderstanding(String scriptContent) throws IOException {
		HashMap<String, AnalysisResults> naturalLangResult = new HashMap<String, AnalysisResults>();

		final String VERSION_DATE = "2017-02-27";
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(VERSION_DATE);
		service.setUsernameAndPassword("c064bda6-1714-41d0-9505-a171205ff5c2", "CLlzzIrsDyK2");

		// PrintWriter pw1 = new PrintWriter("SampleNaturalLangEntities.txt");
		// PrintWriter pw2 = new PrintWriter("SampleNaturalLangKeywords.txt");
		// PrintWriter pw3 = new PrintWriter("SampleNaturalLangSentiments.txt");
		// PrintWriter pw4 = new PrintWriter("SampleNaturalLangCategories.txt");
		// PrintWriter pw5 = new PrintWriter("SampleNaturalLangConcepts.txt");
		// PrintWriter pw6 = new PrintWriter("SampleNaturalLangEmotion.txt");

		// Review the overall sentiment and targeted sentiment of the content.
		SentimentOptions sentiments = new SentimentOptions.Builder().build();
		Features stFeatures = new Features.Builder().sentiment(sentiments).build();
		AnalyzeOptions stParam = new AnalyzeOptions.Builder().text(scriptContent).features(stFeatures).build();
		AnalysisResults stResults = service.analyze(stParam).execute();
		naturalLangResult.put("sentiment", stResults);
		// pw3.println(stResults);
		// System.out.println(stResults);

		// Analyze the overall emotion and the targeted emotion of the content.
		EmotionOptions emotion = new EmotionOptions.Builder().build();
		Features emFeatures = new Features.Builder().emotion(emotion).build();
		AnalyzeOptions emParam = new AnalyzeOptions.Builder().text(scriptContent).features(emFeatures).build();
		AnalysisResults emResult = service.analyze(emParam).execute();
		naturalLangResult.put("emotion", emResult);
		// pw6.println(emResult);
		// System.out.println(emResult);

		// Classify content into a hierarchy that's five levels deep with a score.
		CategoriesOptions categories = new CategoriesOptions();
		Features cgFeatures = new Features.Builder().categories(categories).build();
		AnalyzeOptions cgParam = new AnalyzeOptions.Builder().text(scriptContent).features(cgFeatures).build();
		AnalysisResults cgResults = service.analyze(cgParam).execute();
		naturalLangResult.put("categories", cgResults);
		// System.out.println(cgResults);
		// pw4.println(cgResults);

		// Determine important keywords ranked by relevance.
		KeywordsOptions keywords = new KeywordsOptions.Builder().sentiment(false).emotion(false).limit(5).build();
		Features kwFeatures = new Features.Builder().keywords(keywords).build();
		AnalyzeOptions kwParam = new AnalyzeOptions.Builder().text(scriptContent).features(kwFeatures).build();
		AnalysisResults kwResults = service.analyze(kwParam).execute();
		naturalLangResult.put("keywords", kwResults);
		// pw2.println(kwResults);
		// System.out.println(kwResults);

		// Extract people, companies, organizations, cities, geographic features, and
		// other information from the content.
		EntitiesOptions entities = new EntitiesOptions.Builder().sentiment(true).emotion(true).limit(3).build();
		Features etFeatures = new Features.Builder().entities(entities).build();
		AnalyzeOptions etParam = new AnalyzeOptions.Builder().text(scriptContent).features(etFeatures).build();
		AnalysisResults etResults = service.analyze(etParam).execute();
		naturalLangResult.put("entities", etResults);
		// pw1.println(etResults);

		// System.out.println(etResults);

		// Identifies general concepts that may not be directly referenced in the text.
		ConceptsOptions concepts = new ConceptsOptions.Builder().limit(3).build();
		Features ccFeatures = new Features.Builder().concepts(concepts).build();
		AnalyzeOptions ccParam = new AnalyzeOptions.Builder().text(scriptContent).features(ccFeatures).build();
		AnalysisResults ccResults = service.analyze(ccParam).execute();
		naturalLangResult.put("concepts", ccResults);
		// pw5.println(ccResults);
		// System.out.println(ccResults);

		// pw1.close();
		// pw2.close();
		// pw3.close();
		// pw4.close();
		// pw5.close();
		// pw6.close();

		// return results;
		return naturalLangResult;
	}
	
	public HashMap<String, AnalysisResults> getRelationshipIndicator(String scriptContent) throws IOException {
		HashMap<String, AnalysisResults> relationshipIndicator = new HashMap<String, AnalysisResults>();

		final String VERSION_DATE = "2017-02-27";
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(VERSION_DATE);
		service.setUsernameAndPassword("c064bda6-1714-41d0-9505-a171205ff5c2", "CLlzzIrsDyK2");


		// Review the overall sentiment and targeted sentiment of the content.
		SentimentOptions sentiments = new SentimentOptions.Builder().build();
		Features stFeatures = new Features.Builder().sentiment(sentiments).build();
		AnalyzeOptions stParam = new AnalyzeOptions.Builder().text(scriptContent).features(stFeatures).build();
		AnalysisResults stResults = service.analyze(stParam).execute();
		relationshipIndicator.put("sentiment", stResults);

		// Analyze the overall emotion and the targeted emotion of the content.
//		EmotionOptions emotion = new EmotionOptions.Builder().build();
//		Features emFeatures = new Features.Builder().emotion(emotion).build();
//		AnalyzeOptions emParam = new AnalyzeOptions.Builder().text(scriptContent).features(emFeatures).build();
//		AnalysisResults emResult = service.analyze(emParam).execute();
//		relationshipIndicator.put("emotion", emResult);


		return relationshipIndicator;
	}
}

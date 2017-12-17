/**
 * This class calls IMB Watson API to analyze contents.
 * Three APIs are used:
 * Natural Language Processing, Tone Analyzer, Personality Insight.
 * @author syou
 */
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

import script.Secret;

public class WatsonCaller {
	/**
	 * constructor
	 */
	public WatsonCaller() {

	}

	/**
	 * This method gets the tone of a chunk of lines.
	 * @param contentToAnalyze a chunk of character's lines
	 * @return JSON object containing tone analysis results
	 * @throws IOException 
	 */
	public DocumentAnalysis getToneOfLines(String contentToAnalyze) throws IOException {

		final String VERSION_DATE = "2016-05-19";
		ToneAnalyzer service = new ToneAnalyzer(VERSION_DATE);
		service.setUsernameAndPassword(Secret.watsonToneUserName, Secret.watsonTonePassword);
		ToneOptions tonOptions = new ToneOptions.Builder().text(contentToAnalyze).build();
		ToneAnalysis tone = service.tone(tonOptions).execute();
		DocumentAnalysis toneDoc = tone.getDocumentTone();
		// System.out.println(tone.getDocumentTone());
		// pw.println(toneDoc);
		// pw.close();
		return toneDoc;

	}

	/**
	 * This method gets the personality analysis of a character
	 * @param personaLines a character's all lines
	 * @return JSON object containing a character's personality report
	 */
	public Profile getPersonality(String personaLines) {
		PersonalityInsights service = new PersonalityInsights("2016-10-19");
		service.setUsernameAndPassword(Secret.watsonPersonalityUserName, Secret.watsonPersonalityPassword);
		ProfileOptions options = new ProfileOptions.Builder().text(personaLines).build();
		Profile profile = service.profile(options).execute();
		return profile;
	}


	/**
	 * This method gets the score of sentiments of a chunk of lines.
	 * This score is used to calculate the relationship between two characters.
	 * @param scriptContent a chunk of lines between two characters
	 * @return JSON object containing sentiment report
	 * @throws IOException
	 */
	public AnalysisResults getRelationshipIndicator(String scriptContent) throws IOException {

		final String VERSION_DATE = "2017-02-27";
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(VERSION_DATE);
		service.setUsernameAndPassword(Secret.watsonNatLanUserName, Secret.watsonNatLanPassword);


		// Review the overall sentiment and targeted sentiment of the content.
		SentimentOptions sentiments = new SentimentOptions.Builder().build();
		Features stFeatures = new Features.Builder().sentiment(sentiments).build();
		AnalyzeOptions stParam = new AnalyzeOptions.Builder().text(scriptContent).features(stFeatures).build();
		AnalysisResults stResults = service.analyze(stParam).execute();
//		relationshipIndicator.put("sentiment", stResults);

		// Analyze the overall emotion and the targeted emotion of the content.
//		EmotionOptions emotion = new EmotionOptions.Builder().build();
//		Features emFeatures = new Features.Builder().emotion(emotion).build();
//		AnalyzeOptions emParam = new AnalyzeOptions.Builder().text(scriptContent).features(emFeatures).build();
//		AnalysisResults emResult = service.analyze(emParam).execute();
//		relationshipIndicator.put("emotion", emResult);


		return stResults;
	}
	
	/**
	 * This method gets the keywords of a script
	 * @param scriptContent the whole script
	 * @return JSON object containing keywords data
	 * @throws IOException
	 */
	public AnalysisResults getKeywords(String scriptContent) throws IOException {

		final String VERSION_DATE = "2017-02-27";
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(VERSION_DATE);
		service.setUsernameAndPassword(Secret.watsonNatLanUserName, Secret.watsonNatLanPassword);


		KeywordsOptions keywords = new KeywordsOptions.Builder().sentiment(false).emotion(false).limit(60).build();
		Features kwFeatures = new Features.Builder().keywords(keywords).build();
		AnalyzeOptions kwParam = new AnalyzeOptions.Builder().text(scriptContent).features(kwFeatures).build();
		AnalysisResults kwResults = service.analyze(kwParam).execute();
//		naturalLangResult.put("keywords", kwResults);


		return kwResults;
	}
	
	/**
	 * This method gets categories of a script
	 * @param scriptContent the whole script
	 * @return JSON object containing data of categories
	 * @throws IOException
	 */
	public AnalysisResults getCategoriess(String scriptContent) throws IOException {

		final String VERSION_DATE = "2017-02-27";
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(VERSION_DATE);
		service.setUsernameAndPassword(Secret.watsonNatLanUserName, Secret.watsonNatLanPassword);


		CategoriesOptions categories = new CategoriesOptions();
		Features cgFeatures = new Features.Builder().categories(categories).build();
		AnalyzeOptions cgParam = new AnalyzeOptions.Builder().text(scriptContent).features(cgFeatures).build();
		AnalysisResults cgResults = service.analyze(cgParam).execute();

		return cgResults;
	}
	
	/**
	 * This method gets the overall sentiment of a script
	 * @param scriptContent the whole script
	 * @return JSON object with data of general sentiment
	 * @throws IOException
	 */
	public double getGeneralSentiment(String scriptContent) throws IOException {
		final String VERSION_DATE = "2017-02-27";
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(VERSION_DATE);
		service.setUsernameAndPassword(Secret.watsonNatLanUserName, Secret.watsonNatLanPassword);
		
		SentimentOptions sentiments = new SentimentOptions.Builder().build();
		Features stFeatures = new Features.Builder().sentiment(sentiments).build();
		AnalyzeOptions stParam = new AnalyzeOptions.Builder().text(scriptContent).features(stFeatures).build();
		AnalysisResults stResults = service.analyze(stParam).execute();
		
		return stResults.getSentiment().getDocument().getScore();
	}
	
}

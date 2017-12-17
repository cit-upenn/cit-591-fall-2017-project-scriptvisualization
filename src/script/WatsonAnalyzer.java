package script;
/**
 * This class analyzes JSON data
 */
import java.io.IOException;
import java.util.HashMap;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneCategory;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;

public class WatsonAnalyzer {
	public WatsonAnalyzer() {

	}

	/**
	 * This method analyzes emotion tone of JSON data
	 * @param jsonData
	 * @return
	 * @throws IOException
	 */
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

	/**
	 * This method analyzes language tone of JSON data
	 * @param jsonData
	 * @return
	 * @throws IOException
	 */
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

}

package scriptReaderV1;

import java.io.IOException;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;

public class NaturalLangUnderstanding {
	public NaturalLangUnderstanding() {

	}

	public AnalysisResults getNaturalLangUnderstanding(String inputFile) throws IOException {
		ScriptReader sr = new ScriptReader();
		final String VERSION_DATE = "2017-02-27";
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(VERSION_DATE);
		service.setUsernameAndPassword("c064bda6-1714-41d0-9505-a171205ff5c2", "CLlzzIrsDyK2");

		String text = sr.getLines(inputFile).get("DANNY");

		EntitiesOptions entities = new EntitiesOptions.Builder().sentiment(true).limit(1).build();
		Features features = new Features.Builder().entities(entities).build();
		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();
		AnalysisResults results = service.analyze(parameters).execute();
		return results;
	}
}

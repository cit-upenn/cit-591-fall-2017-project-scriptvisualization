package scriptReaderV1;

import java.io.IOException;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

public class LineToneAnalysis {
	public LineToneAnalysis() {
		
	}

	public ToneAnalysis getLinesTone(String inputFile) throws IOException {
		ScriptReader sr = new ScriptReader();
		final String VERSION_DATE = "2016-05-19";
		ToneAnalyzer service = new ToneAnalyzer(VERSION_DATE);
		service.setUsernameAndPassword("028da7b1-3878-4521-8063-3cd09e5684c5", "lFCpPJceVbT4");

//		String text = sr.getLines(inputFile).get(sr.getNames(inputFile).keySet());
		String text = sr.getLines(inputFile).get("DANNY");

		// Call the service and get the tone
		ToneOptions tonOptions = new ToneOptions.Builder().text(text).build();
		ToneAnalysis tone = service.tone(tonOptions).execute();
		// System.out.println(tone);
		return tone;
	}
}

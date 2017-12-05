package apiCall;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

import script.Persona;
import script.ScriptReader;
import script.ScriptScraper;

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
}

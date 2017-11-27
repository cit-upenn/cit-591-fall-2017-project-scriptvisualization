package scriptReaderV1;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		String inputFile = "Pearl-Harbor.txt";
		ScriptReader sr = new ScriptReader();
		System.out.println(sr.getNames(inputFile));
		sr.getLines(inputFile);
		
//		LineToneAnalysis lta = new LineToneAnalysis();
//		System.out.println(alt.getLinesTone(inputFile));
//		PrintWriter pw = new PrintWriter("SampleToneOutput.txt");
//		pw.println(lta.getLinesTone(inputFile));
//		pw.close();
		
//		PersonalityInsightsAnalysis pia = new PersonalityInsightsAnalysis();
//		PrintWriter pw1 = new PrintWriter("SamplePersonalityOutput.txt");
//		pw1.println(pia.getPersonality(inputFile));
//		pw1.close();
		
		NaturalLangUnderstanding nlu = new NaturalLangUnderstanding();
		PrintWriter pw2 = new PrintWriter ("SampleNaturalLangUnderstanding.txt");
		//pw2.println(nlu.getNaturalLangUnderstanding(inputFile));
		pw2.close();
	}
}

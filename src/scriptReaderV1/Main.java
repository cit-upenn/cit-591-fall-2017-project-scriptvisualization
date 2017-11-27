package scriptReaderV1;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		String inputFile = "Pearl-Harbor.txt";
		ScriptReader sr = new ScriptReader();
		System.out.println(sr.getNames(inputFile));
		
		LineToneAnalysis lta = new LineToneAnalysis();
//		System.out.println(alt.getLinesTone(inputFile));
		PrintWriter pw = new PrintWriter("SampleToneOutput.txt");
		pw.println(lta.getLinesTone(inputFile));
		pw.close();
	}
}

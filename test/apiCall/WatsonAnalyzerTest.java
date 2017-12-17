package apiCall;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import script.WatsonAnalyzer;
import script.WatsonCaller;

public class WatsonAnalyzerTest {
	
	String test = "Bullshit.  We've played with each other, pushed each other.  " + 
			"This is different. Like you want to prove that you're better than me.  Who's that for -- Evelyn?" + 
			"I understand how it could happen.  I know why any guy would love her.  And I can't blame you that " +
			"it happened.  You thought I was dead, she was grieving, you were trying to help her. " + 
			"I'm so sorry for what you must've gone through, but I'm back. " +
			"Maybe not.  But I need you to know.  I love you.  And I will come back.  I'll find a way.  " +
			"And then we'll get a chance to know if what I felt the first moment I saw you, and every minute since then, is real. " + 
			"Maybe I've assumed too much.  Has something changed? I'm afraid to ask what.  And I'm afraid not to. Have you fallen in love? \n";
	WatsonCaller wcaller = new WatsonCaller();
	WatsonAnalyzer wanalyzer = new WatsonAnalyzer();
	
	 
	@Test
	public void testLineEmotionToneAnalyzer() throws IOException {
		assertTrue(wanalyzer.lineEmotionToneAnalyzer(wcaller.getToneOfLines(test)).size() > 0);
	}

	@Test
	public void testLineLangToneAnalyzer() throws IOException {
		assertTrue(wanalyzer.lineLangToneAnalyzer(wcaller.getToneOfLines(test)).size() > 0);
	}




}

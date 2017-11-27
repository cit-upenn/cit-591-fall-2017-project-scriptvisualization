package scriptReaderV1;

import java.io.IOException;

import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;

public class PersonalityInsightsAnalysis {
	public PersonalityInsightsAnalysis() {
		
	}
	
	public Profile getPersonality(String inputFile) throws IOException {
		ScriptReader sr = new ScriptReader();
	    PersonalityInsights service = new PersonalityInsights("2016-10-19");
	    service.setUsernameAndPassword("bc376c0c-56ba-4316-a58f-53e9f410cb06", "b655eZV0Q0bi");
	    //this text is just a test for now
	    String text = sr.getLines(inputFile).get("DANNY");
	    
	    ProfileOptions options = new ProfileOptions.Builder().text(text).build();
	    Profile profile = service.profile(options).execute();

	    return profile;
//	    System.out.println(profile);
	}
}

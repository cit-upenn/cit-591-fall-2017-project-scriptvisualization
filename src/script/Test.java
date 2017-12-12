package script;

import java.io.IOException;
import java.security.GeneralSecurityException;

import script.Relationships.Relationship;

 

 
 

public class Test {

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		// TODO Auto-generated method stub
	 
			ScriptReader sr = new ScriptReader();
			sr.readScript(ScriptScraper.scrapeScript("http://www.imsdb.com/scripts/Titanic.html"), "titanic");
			/* String s = " SEBASTIAN (CONT'D)\n" + 
			 		"                Welcome to Seb's.\n" + 
			 		"\n" + 
			 		"      More applause. Sebastian sits at the piano. Looks at the keys.\n" + 
			 		"\n" + 
			 		"      He seems uncertain -- perhaps unsure what to play. He looks at\n" + 
			 		"      Mia. Takes the sight in. Beat. Then looks at his fellow\n" + 
			 		"      musicians. Murmurs to them. Then turns back to the keys --";
			 StringBuilder dialog = new StringBuilder();
				StringBuilder narra = new StringBuilder();
				String[] splitChunk = s.split("\\n");
				int linNumber = 1;
				while(splitChunk[linNumber].length() == 0) linNumber++;
				for (; linNumber < splitChunk.length; linNumber++) {
					 if(splitChunk[linNumber].length() == 0) break;
					 dialog.append(splitChunk[linNumber]);
				}
				for (; linNumber < splitChunk.length; linNumber++) {
					 narra.append(splitChunk[linNumber]);
				}
				System.out.println(dialog.toString());
				System.out.println(narra.toString());*/
		 
		 
	}

}

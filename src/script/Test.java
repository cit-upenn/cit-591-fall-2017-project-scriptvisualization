package script;

import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.jgrapht.graph.SimpleGraph;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
//		 System.out.println(sr.getRelationgraph().graph);
//		 for (Relationship r: sr.getRelationgraph().graph.edgeSet() ) {
//			 System.out.println(sr.getRelationgraph().graph.getEdgeSource(r));
//			 System.out.println(sr.getRelationgraph().graph.getEdgeTarget(r));
//			 System.out.println("======");
//		 }
			
			
//		 System.out.println(sr.getRelationgraph().graph.getEdgeSource(e));
//			SimpleGraph<Persona, Relationship> links = sr.getRelationgraph().graph;
//			
//			System.out.println(links.vertexSet());
//			for (Persona p: links.vertexSet()) {
//				System.out.println(p);
//			}
	/*
			JSONObject tier = new JSONObject();
			JSONArray nodes = new JSONArray();
			for (Persona p: links.vertexSet()) {
				JSONObject names = new JSONObject();
				
				names.put("id", p.getName());
//				names.put("id", links.getEdgeSource(r).getName());
				nodes.add(names);
			}
			tier.put("nodes", nodes);
			JSONArray linkages = new JSONArray();
			for (Relationship r: links.edgeSet()) {
				JSONObject entry = new JSONObject();
				entry.put("source", links.getEdgeSource(r).getName());
				entry.put("target", links.getEdgeTarget(r).getName());
				entry.put("value", r.relation);

				
				linkages.add(entry);

			}
			tier.put("links", linkages);
			

			

			// writing the JSONObject into a file(info.json)
			try {
				FileWriter fileWriter = new FileWriter("links.json");
				fileWriter.write(tier.toJSONString());
				fileWriter.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.println(tier);
		*/	

			
		
			
		 
	}

}

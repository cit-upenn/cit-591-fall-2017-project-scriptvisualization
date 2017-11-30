import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

 

 
 

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ScriptScraper ss = new ScriptScraper();
			ScriptReader ar = new ScriptReader(ss.scrapeScript("http://www.imsdb.com/scripts/Titanic.html"));
			Set<Persona> persons = ar.relationgraph.graph.vertexSet();
			ArrayList<Persona> main = new ArrayList<>();
			for(Persona p : persons) {
				if(p.getOccurrence() > 50) {
					main.add(p);
					 
				}
			}
			
			
			
			for(int i = 0; i <main.size() - 1; i++) {
				for(int j = i + 1; j < main.size(); j++) {
					
					
					Set<Relationships.Relationship> edges = new HashSet<>();
					edges.addAll(ar.relationgraph.graph.getAllEdges(main.get(j),main.get(i)));
					if(!edges.isEmpty()) {
					System.out.println("==================");
					
					System.out.println(main.get(i));
					for(Relationships.Relationship edge : edges) {
						System.out.print("******");
						System.out.print(edge);
						System.out.println("*********");
					}
					System.out.println(main.get(j));
					System.out.println("==================");
					}
				}
			}
			 
			
			
		 
			
			/*for(Relationships.Relationship r : edges) {
				System.out.println(edges);
			}*/
			//ss.printMovieList(ss.getMoviesFromSearchKey("day"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 
	}

}

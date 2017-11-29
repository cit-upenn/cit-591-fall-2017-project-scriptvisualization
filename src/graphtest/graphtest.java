package graphtest;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
 

public class graphtest {
	SimpleGraph<Persona, Relationship> graph = new SimpleGraph<Persona, Relationship>(Relationship.class);
	
	public void add() {
		Persona p1 = new Persona("Rose");
		Persona p2 = new Persona("Jack");
		graph.addVertex(p1);
		graph.addVertex(p2);
		Relationship edge = new Relationship(1, 5.0);
		graph.addEdge(p1, p2, edge);
		System.out.println(graph.containsEdge(p1, p2));
	}
	public static void main(String[] args) {
		graphtest test = new graphtest();
		test.add();
		
	}
	
	public class Relationship extends DefaultEdge{
		int close;
		double relation;
		public Relationship(int close, double score) {
			this.close = close;
			relation = score;
		}
	}

}

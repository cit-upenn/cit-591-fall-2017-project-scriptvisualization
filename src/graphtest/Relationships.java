package graphtest;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

 

public class Relationships {
	
	SimpleGraph<Persona, Relationship> graph;
	
	public Relationships() {
		graph = new SimpleGraph<Persona, Relationship>(Relationship.class);
	}
	
	private class Relationship extends DefaultEdge{
		int contacts;
		double relation;
		public Relationship(int contacts, double relation) {
			this.contacts = contacts;
			this.relation = relation;
		}
	}

}

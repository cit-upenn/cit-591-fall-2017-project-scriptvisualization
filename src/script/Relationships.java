package script;



import java.util.HashMap;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
/**
 * This class represents relationships among characters in a movie script. A graph is used to keep track of characterand
 * relationships between two characters
 * @author yueyin
 *
 */

public class Relationships {
	
	SimpleGraph<Persona, Relationship> graph;
	//assume there are no duplicated names in a movie script
	HashMap<String, Persona> persons;
	
	/**
	 * The constructor initializes graph and hashmap
	 */
	public Relationships() {
		graph = new SimpleGraph<Persona, Relationship>(Relationship.class);
		persons = new HashMap<>();
	}
	
	/**
	 * This is edge class. Each edge has contacts that records how many times two nodes encounter and relation that 
	 * records how their relationship is
	 * @author yueyin
	 *
	 */
	public static class Relationship extends DefaultEdge{
		private static final long serialVersionUID = 1L;
		int contacts;
		double relation;
		public Relationship(int contacts, double relation) {
			this.contacts = contacts;
			this.relation = relation;
		}
		
		public String toString() {
			return "contacts = " + contacts + " relation = " + relation;
		}
	}

	/**
	 * This class create a vertex of type Persona given name, create a new vertex if it dosen't exist. return the vertex is 
	 * it exists
	 * @param name
	 * @return Persona
	 */
	public Persona createVertex(String name) {
		// TODO Auto-generated method stub
		if(persons.containsKey(name)) {
			Persona curr = persons.get(name);
			curr.setOccurrence(curr.getOccurrence() + 1);
			return curr;
		}
		else {
			Persona newPersona = new Persona(name);
			persons.put(name, newPersona);
			graph.addVertex(newPersona);
			return newPersona;
		}
	}

	/**
	 * Create edge between two vertex 
	 * create new one if it doesn't exist, update old one if it exists
	 * @param prev
	 * @param curr
	 * @param relation
	 */
	public void createEdge(Persona prev, Persona curr, double relation) {
		// TODO Auto-generated method stub
		Relationship edge;
		if(graph.containsEdge(prev, curr)) {
			edge = graph.getEdge(prev, curr);
			edge.contacts++;
			edge.relation += relation;
		}else {
			edge = new Relationship(1, relation);
			graph.addEdge(prev, curr, edge);
		}
		
	}

	public SimpleGraph<Persona, Relationship> getGraph() {
		return graph;
	}

	public void setGraph(SimpleGraph<Persona, Relationship> graph) {
		this.graph = graph;
	}


	 

}

package script;

import static org.junit.Assert.*;

import org.junit.Test;

public class RelationshipsTest {
	
	private Relationships relation = new Relationships();
	@Test
	public void testCreateVertex() {
		 relation.createVertex("mia");
		 relation.createVertex("tom");
		 relation.createVertex("MIA");
		 relation.createVertex("mia");
		 assertEquals(relation.graph.vertexSet().size(), 3);
	}

	@Test
	public void testCreateEdge() {
		 Persona p1 = relation.createVertex("mia");
		 Persona p2 = relation.createVertex("tom");
		 relation.createEdge(p1, p2, 1);
		 relation.createEdge(p1, p2, 2);
		 assertTrue(relation.graph.edgeSet().size() == 1 && relation.graph.getEdge(p1, p2).relation == 3);
	}

}

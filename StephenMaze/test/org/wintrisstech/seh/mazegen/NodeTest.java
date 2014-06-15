package org.wintrisstech.seh.mazegen;

import java.util.Set;

import org.junit.Test;
import org.wintrisstech.seh.mazegen.Edge;
import org.wintrisstech.seh.mazegen.MazeGenerator;
import org.wintrisstech.seh.mazegen.Node;

import static org.junit.Assert.*;

public class NodeTest {

	Node node_0_0 = new Node(0, 0);
	Node node_0_1 = new Node(0, 1);
	Node node_1_0 = new Node(1, 0);
	Node node_1_1 = new Node(1, 1);

	@Test
	public void testNode() {
		assertNotNull(node_0_0);
		assertNotNull(node_0_1);
		assertNotNull(node_1_0);
		assertNotNull(node_1_1);
	}

	@Test
	public void testGetRow() {
		assertEquals(0, node_0_0.getRow());
		assertEquals(0, node_0_1.getRow());
		assertEquals(1, node_1_0.getRow());
		assertEquals(1, node_1_1.getRow());
	}

	@Test
	public void testGetCol() {
		assertEquals(0, node_0_0.getCol());
		assertEquals(1, node_0_1.getCol());
		assertEquals(0, node_1_0.getCol());
		assertEquals(1, node_1_1.getCol());
	}

	@Test
	public void testGetAjacent() {
		
		Edge e1 = new Edge(node_0_0, node_0_1);
		Edge e2 = new Edge(node_0_0, node_1_0);
		Edge e3 = new Edge(node_0_1, node_1_0);
		
		assertEquals(2, node_0_0.getAdjacent().size());
		assertTrue(node_0_0.getAdjacent().contains(e1));
		assertTrue(node_0_0.getAdjacent().contains(e2));
		assertFalse(node_0_0.getAdjacent().contains(e3));
		
		assertEquals(2, node_0_1.getAdjacent().size());
		assertTrue(node_0_1.getAdjacent().contains(e1));
		assertFalse(node_0_1.getAdjacent().contains(e2));
		assertTrue(node_0_1.getAdjacent().contains(e3));

		assertEquals(2, node_1_0.getAdjacent().size());		
		assertFalse(node_1_0.getAdjacent().contains(e1));
		assertTrue(node_1_0.getAdjacent().contains(e2));
		assertTrue(node_1_0.getAdjacent().contains(e3));
		
		node_0_0.addAdjacent(e1);
		node_0_0.addAdjacent(e1);
		node_0_0.addAdjacent(e1);
		node_0_0.addAdjacent(e1);
		assertEquals(2, node_0_0.getAdjacent().size());
	}

	@Test
	public void testSetConnected() {
		node_0_0.setConnected(true);
		node_0_1.setConnected(true);
		node_1_0.setConnected(false);
		
		assertTrue(node_0_0.isConnected());
		assertTrue(node_0_1.isConnected());
		assertFalse(node_1_0.isConnected());
		assertFalse(node_1_1.isConnected());
	}

	@Test
	public final void testAdjacentEdges() {
		MazeGenerator generator = new MazeGenerator(10, 20);
		assertEquals(10, generator.getNumRows());
		assertEquals(20, generator.getNumCols());
		generator.initialize();
		Set<Edge> adj = generator.getNode(9, 19).getAdjacent();
		assertEquals(2, adj.size());
		Set<Edge> adj2 = generator.getNode(1, 0).getAdjacent();
		assertEquals(3, adj2.size());
		Set<Edge> adj3 = generator.getNode(1, 1).getAdjacent();
		assertEquals(4, adj3.size());
	}

}
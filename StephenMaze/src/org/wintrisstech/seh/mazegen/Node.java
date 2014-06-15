package org.wintrisstech.seh.mazegen;

import java.util.HashSet;
import java.util.Set;

public class Node {
	private Set<Edge> adjacentEdges = new HashSet<Edge>();
	private boolean connected = false;
	private final int row, col;
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public Set<Edge> getAdjacent() {
		return adjacentEdges;
	}
	public void addAdjacent(Edge e) {
		adjacentEdges.add(e);
	}
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}

package org.wintrisstech.seh.mazegen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Node {
	private HashMap<Direction, Edge> adjacent = new HashMap<Direction, Edge>();
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
	public HashMap<Direction, Edge> getAdjacent() {
		return new HashMap<Direction, Edge>(adjacent);
	}
	public void addAdjacent(Direction direct, Edge e) {
		adjacent.put(direct, e);
	}
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}

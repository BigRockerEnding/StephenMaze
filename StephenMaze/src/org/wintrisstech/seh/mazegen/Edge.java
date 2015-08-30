package org.wintrisstech.seh.mazegen;
import java.util.Random;


public class Edge implements Comparable<Edge>{
	private final Node u, v;
	private final int cost;
	public Edge(Node nodeU, Node nodeV) {
		this.u = nodeU;
		this.v = nodeV;
		Random r = new Random();
		this.cost = r.nextInt(50) + 1;
		Direction direct;
		nodeU.addAdjacent(this);
		nodeV.addAdjacent(this);
	}
	public Node getU() {
		return u;
	}
	public Node getV() {
		return v;
	}
	public int getCost() {
		return cost;
	}
	@Override
	public int compareTo(Edge other) {
		return this.cost - other.cost;
	}
}

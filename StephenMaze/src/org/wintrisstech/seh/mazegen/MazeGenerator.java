package org.wintrisstech.seh.mazegen;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MazeGenerator {
	// Important Vars
	private final int numRows, numCols;
	private final Node[][] nodes;
	private final Edge[][] hzEges;
	private final Edge[][] vtEges;
	public MazeGenerator(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.nodes = new Node[numRows][numCols];
		this.hzEges = new Edge[numRows][numCols - 1];
		this.vtEges = new Edge[numRows - 1][numCols];
	}
	public static void main(String[] args) {
		MazeGenerator mazer = new MazeGenerator(50, 50);
		mazer.initialize();
		List<Edge> maze = mazer.runPrim();
		PathFinder pathFinder = new PathFinder(maze, mazer.nodes[0][0], mazer.nodes[mazer.numRows - 1][mazer.numCols - 1]);
		List<Edge> path = pathFinder.apply();
		PathFinder pathFinder2 = new PathFinder(maze, mazer.nodes[0][mazer.numCols -1], mazer.nodes[mazer.numRows - 1][0]);
		path.addAll(pathFinder2.apply());
		MazePanel panel = new MazePanel(mazer.numRows, mazer.numCols, maze, path);
		panel.display();
	}
	// Initialize the Stuff
	public void initialize() {
		// nodes
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				Node node = new Node(i, j);
				nodes[i][j] = node;
			}
		}
		// hEges
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols - 1; j++) {
				Edge edge = new Edge(nodes[i][j], nodes[i][j + 1]);
				hzEges[i][j] = edge;
			}
		}
		// vEges
		for (int i = 0; i < numRows - 1; i++) {
			for (int j = 0; j < numCols; j++) {
				Edge edge = new Edge(nodes[i][j], nodes[i + 1][j]);
				vtEges[i][j] = edge;
			}
		}
	}
	// Return Selected Edges
	public List<Edge> runPrim() {
		List<Edge> result = new ArrayList<Edge>();
		PriorityQueue<Edge> edgesQueue = new PriorityQueue<Edge>();
		/*
		 * edgesQueue contains all edges between a connected node and a non-connected node.
		 * edgesQueue contains no edges between two non-connected nodes.
		 */
		Node root = nodes[0][0];
		root.setConnected(true);
		edgesQueue.addAll(root.getAdjacent());
		while (!edgesQueue.isEmpty()) {
			Edge next = edgesQueue.remove();
			Node u = next.getU(), v = next.getV();
			if(!(u.isConnected() && v.isConnected())) {
				Node w = u.isConnected()? v : u;
				w.setConnected(true);
				edgesQueue.addAll(w.getAdjacent());
				result.add(next);
			}
		}
		return result;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumCols() {
		return numCols;
	}
	public Node getNode(int row, int col) {
		return nodes[row][col];
	}
	public Edge getHzEge(int row, int col) {
		return hzEges[row][col];
	}
	public Edge getVtEge(int row, int col) {
		return vtEges[row][col];
	}
}

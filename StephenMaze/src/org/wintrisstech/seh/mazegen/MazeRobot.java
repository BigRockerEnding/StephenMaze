package org.wintrisstech.seh.mazegen;

import java.util.Stack;



public class MazeRobot {
	private MazeGenerator mazerGen;
	private MazePanel mazerPanel;
	private Direction direct;
	
	private int row = 0, col = 0;
	
	private Stack<Edge> path;
	
	public MazeRobot(MazeGenerator gen, MazePanel panel) {
		this.mazerGen = gen;
		this.mazerPanel = panel;
		
		this.direct = Direction.EAST;
		
		this.path = new Stack<Edge>();
	}
	
	public void moveAlong() {
		if (row == mazerGen.getNumRows() - 1 && col == mazerGen.getNumCols() - 1) return;
		
		turnOver();
		advanceFoward();
	}

	private void turnOver() {
		direct = direct.getRight();
		Node n = mazerGen.getNode(row, col);
		while (n.getAdjacent().get(direct) == null || !n.getAdjacent().get(direct).isConnected()) {
			
		}
	}
	
	private void advanceFoward() {
		
	}
}

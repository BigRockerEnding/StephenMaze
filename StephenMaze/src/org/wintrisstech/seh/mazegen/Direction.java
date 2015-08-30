package org.wintrisstech.seh.mazegen;

public enum Direction {

	NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1);
	
	private final int deltaRow, deltaCol;
	Direction(int deltaRow, int deltaCol) {
		this.deltaRow = deltaRow;
		this.deltaCol = deltaCol;
	}

    public int getDeltaRow() {
		return deltaRow;
	}
    
	public int getDeltaCol() {
		return deltaCol;
	}

	public Direction getRight() {
		return values()[(this.ordinal() + 1) % 4];
	}

	public Direction getLeft() {
		return values()[(this.ordinal() + 3) % 4];
	}

    // Optional: Create an app that shows a maze on a rectangular grid, and traces a "robot"
    // that moves like a right wall hugger.

}

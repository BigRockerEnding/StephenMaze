package org.wintrisstech.seh.mazegen;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
	private final List<Edge> maze;
	private final Node start;
	private final Node finish;
	private boolean done = false;
	private List<Edge> result = new ArrayList<Edge>();

	public PathFinder(List<Edge> maze, Node start, Node finish) {
		this.maze = maze;
		this.start = start;
		this.finish = finish;
	}

	public List<Edge> apply() {
		if (done) {
			return result;
		} else {
			findPath(null, start, finish);
			return result;
		}
	}

	/**
	 * If there is a path from start to finish without passing through
	 * comingFrom, then this method sets done to true and adds all the edges
	 * from start to finish to result.
	 * <p>
	 * If there isn't a path from start to finish without passing through
	 * comingFrom, then this method returns without making changes to done or
	 * result.
	 * 
	 * @param comingFrom
	 *            The edge that we're coming from.
	 * @param start
	 *            The node we're starting from.
	 * @param finish
	 *            The node we want to reach.
	 */
	private void findPath(Edge comingFrom, Node start, Node finish) {
		if (start == finish) {
			done = true;
			return;
		}
		for (Edge e : start.getAdjacent()) {
			if (e != comingFrom && maze.contains(e)) {
				Node w = e.getU() == start ? e.getV() : e.getU();
				findPath(e, w, finish);
				if (done) {
					result.add(e);
					break;
				}
			}
		}
	}
}

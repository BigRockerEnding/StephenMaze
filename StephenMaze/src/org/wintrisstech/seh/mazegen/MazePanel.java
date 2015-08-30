package org.wintrisstech.seh.mazegen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MazePanel extends JPanel implements Runnable, ActionListener {
	private final BufferedImage mazeImage;
	private final Timer ticker;
	private final Graphics2D mazeImageG;
	private int numEdgesPainted = 0;
	private final int numRows, numCols;
	private final List<Edge> maze;
	private final List<Edge> path;
	private final static int CELL_WIDTH = 10;
	private final static int CENTER_OFFSET = CELL_WIDTH / 2;

	public MazePanel(int numRows, int numCols, List<Edge> maze, List<Edge> path) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.maze = maze;
		this.path = path;
		this.mazeImage = new BufferedImage(numCols * CELL_WIDTH, numRows
				* CELL_WIDTH, BufferedImage.TYPE_INT_ARGB);
		this.ticker = new Timer(1, this);
		this.mazeImageG = mazeImage.createGraphics();
	}

	@Override
	public void run() {
		setPreferredSize(new Dimension(CELL_WIDTH * numCols, CELL_WIDTH
				* numRows));
		JFrame frame = new JFrame("Stephen Maze");
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		mazeImageG.setColor(Color.BLACK);
		mazeImageG.fillRect(0, 0, getWidth(), getHeight());
		mazeImageG.setColor(Color.WHITE);
		mazeImageG.setStroke(new BasicStroke(CELL_WIDTH - 1));
		ticker.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(mazeImage, null, 0, 0);
	}

	private void drawEdge(Graphics2D g, Edge edge) {
		g.drawLine(edge.getU().getCol() * CELL_WIDTH + CENTER_OFFSET, edge
				.getU().getRow() * CELL_WIDTH + CENTER_OFFSET, edge.getV()
				.getCol() * CELL_WIDTH + CENTER_OFFSET, edge.getV().getRow()
				* CELL_WIDTH + CENTER_OFFSET);
	}

	public void display() {
		SwingUtilities.invokeLater(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (numEdgesPainted == maze.size()) {
			ticker.stop();
			mazeImageG.setColor(Color.BLUE);
			mazeImageG.setStroke(new BasicStroke(1));
			for (Edge edge : path) {
				drawEdge(mazeImageG, edge);
			}
			repaint();
		} else {
			Edge edge2Paint = maze.get(numEdgesPainted);
			drawEdge(mazeImageG, edge2Paint);
			numEdgesPainted++;
			repaint();
		}
	}

}

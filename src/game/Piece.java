package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class Piece {
	private int x;
	private int y;
	private PieceShape shape;

	public Piece(int x, int y, PieceShape shape) {
		this.x = x;
		this.y = y;
		this.shape = shape;
	}

	public void render(Graphics g) {
		char[][] shape = this.shape.getShape();
		
		for (int row = 0; row < shape.length; row++) {
			for (int col = 0; col < shape[0].length; col++) {
				int rectX = (this.x + col) * 30;
				int rectY = (this.y + row) * 30;
				int rectWidth = 30;
				int rectHeight = 30;

				if (shape[row][col] == '2') {
					g.setColor(Color.blue);
					g.fillRect(rectX, rectY, rectWidth, rectHeight);
				}
			}
		}
	}

	private void move() {
		this.y++;
	}

	public void tick() {
		this.move();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void rotate() {
		this.shape.rotate();
	}

	public char[][] getShape() {
		return this.shape.getShape();
	}
}

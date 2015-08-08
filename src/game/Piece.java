package game;

import java.awt.Color;
import java.awt.Graphics;

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
					g.setColor(Color.black);
					g.drawRect(rectX, rectY, rectWidth, rectHeight);
				}
			}
		}
	}

	public void tick() {
		this.moveDown();
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

	public void undoRotate() {
		this.shape.undoRotate();
	}

	public void moveLeft() {
		this.x--;
	}

	public void moveRight() {
		this.x++;
	}

	public void moveDown() {
		this.y++;
	}

	public void moveUp() {
		this.y--;
	}

	public char[][] getShape() {
		return this.shape.getShape();
	}
}

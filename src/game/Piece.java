package game;

import java.awt.Color;
import java.awt.Graphics;

public class Piece {	
	
	private static final int RECT_WIDTH = 30;
	private static final int RECT_HEIGHT = 30;

	private int x;
	private int y;

	private PieceShape shape;	

	public Piece(int x, int y, PieceShape shape) {
		this.setX(x);
		this.setY(y);

		this.setShape(shape);		
	}

	public void render(Graphics g) {
		char[][] shape = this.getShape();

		for (int row = 0; row < shape.length; row++) {
			for (int col = 0; col < shape[0].length; col++) {
				int rectX = (this.x + col) * Piece.RECT_WIDTH;
				int rectY = (this.y + row) * Piece.RECT_HEIGHT;

				if (shape[row][col] == '2') {
					g.setColor(Color.blue);
					g.fillRect(rectX, rectY, Piece.RECT_WIDTH, Piece.RECT_HEIGHT);
					g.setColor(Color.black);
					g.drawRect(rectX, rectY, Piece.RECT_WIDTH, Piece.RECT_HEIGHT);
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

	private void setX(int x) {
		this.x = x;
	}

	private void setY(int y) {
		this.y = y;
	}

	public void rotate() {
		this.shape.rotate();
	}

	public void undoRotate() {
		this.shape.undoRotate();
	}

	public void movePieceToStartingPoing() {
		this.setX(Game.STARTING_PIECE_X);
		this.setY(Game.STARTING_PIECE_Y);
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
	
	private void setShape(PieceShape shape) {
		this.shape = shape;
	}
}

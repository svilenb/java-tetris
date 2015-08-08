package game;

import java.awt.Graphics;
import java.util.Random;
import java.awt.*;

public class Field {
	private char[][] field;
	private Random random;

	public Field(int fieldHeight, int fieldWidth) {
		this.random = new Random();
		this.initializeField(fieldHeight, fieldWidth);
	}

	private void initializeField(int fieldHeight, int fieldWidth) {
		this.field = new char[fieldHeight][fieldWidth];

		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[0].length; col++) {
				if (row > 8 && this.random.nextInt(11) <= 5) {
					this.field[row][col] = '1';
				} else {
					this.field[row][col] = '0';
				}
			}
		}
	}

	public void render(Graphics g) {
		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				if (this.field[row][col] == '0') {
					g.setColor(Color.lightGray);
				} else if (this.field[row][col] == '1') {
					g.setColor(Color.darkGray);
				}

				g.fillRect(col * 30, row * 30, 30, 30);
			}
		}
	}

	public boolean isPieceFallen(Piece piece) {
		char[][] pieceShape = piece.getShape();
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		for (int row = 0; row < pieceShape.length; row++) {
			for (int col = 0; col < pieceShape[row].length; col++) {
				boolean isPieceBrick = pieceShape[row][col] == '2';
				boolean isFieldBrick;
				boolean isRowInField = pieceY + row + 1 < this.field.length && pieceY + row + 1 >= 0;
				boolean isColInField = pieceX + col < this.field[pieceY + row].length && pieceX + col >= 0;

				if (isRowInField && isColInField && this.field[pieceY + row + 1][pieceX + col] == '1') {
					isFieldBrick = true;
				} else {
					isFieldBrick = false;
				}

				if (isPieceBrick && isFieldBrick) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isPieceIntoBrick(Piece piece) {
		char[][] pieceShape = piece.getShape();
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		for (int row = 0; row < pieceShape.length; row++) {
			for (int col = 0; col < pieceShape[row].length; col++) {
				boolean isPieceBrick = pieceShape[row][col] == '2';
				boolean isFieldBrick;
				boolean isRowInField = pieceY + row + 1 < this.field.length && pieceY + row >= 0;
				boolean isColInField = pieceX + col < this.field[pieceY + row].length && pieceX + col >= 0;

				if (isRowInField && isColInField && this.field[pieceY + row][pieceX + col] == '1') {
					isFieldBrick = true;
				} else {
					isFieldBrick = false;
				}

				if (isPieceBrick && isFieldBrick) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean doesPieceTouchesLeftWall(Piece piece) {
		char[][] pieceShape = piece.getShape();
		int pieceX = piece.getX();

		for (int row = 0; row < pieceShape.length; row++) {
			for (int col = 0; col < pieceShape[row].length; col++) {
				boolean isPieceBrick = pieceShape[row][col] == '2';
				boolean isNextToWall = pieceX + col == 0;

				if (isPieceBrick && isNextToWall) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean doesPieceTouchesRightWall(Piece piece) {
		char[][] pieceShape = piece.getShape();
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		for (int row = 0; row < pieceShape.length; row++) {
			for (int col = 0; col < pieceShape[row].length; col++) {
				boolean isPieceBrick = pieceShape[row][col] == '2';
				boolean isNextToWall = pieceX + col == this.field[pieceY + row].length - 1;

				if (isPieceBrick && isNextToWall) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean doesPieceTouchesBottom(Piece piece) {
		char[][] pieceShape = piece.getShape();
		int pieceY = piece.getY();

		for (int row = 0; row < pieceShape.length; row++) {
			for (int col = 0; col < pieceShape[row].length; col++) {
				boolean isPieceBrick = pieceShape[row][col] == '2';
				boolean isNextToBottom = pieceY + row == this.field.length - 1;

				if (isPieceBrick && isNextToBottom) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isPieceOut(Piece piece) {
		char[][] pieceShape = piece.getShape();
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		for (int row = 0; row < pieceShape.length; row++) {
			for (int col = 0; col < pieceShape[row].length; col++) {
				boolean isPieceBrick = pieceShape[row][col] == '2';
				boolean isRowInField = pieceY + row + 1 < this.field.length && pieceY + row + 1 >= 0;
				boolean isColInField = pieceX + col < this.field[pieceY + row].length && pieceX + col >= 0;

				if (isPieceBrick && (!isRowInField || !isColInField)) {
					return true;
				}
			}
		}

		return false;
	}

	public void placePiece(Piece piece) {
		char[][] pieceShape = piece.getShape();
		int pieceX = piece.getX();
		int pieceY = piece.getY();

		for (int row = 0; row < pieceShape.length; row++) {
			for (int col = 0; col < pieceShape[row].length; col++) {
				if (pieceShape[row][col] == '2') {
					this.field[pieceY + row][pieceX + col] = '1';
				}
			}
		}
	}

	public void destroyFullRows() {
		for (int row = 0; row < this.field.length; row++) {
			boolean isFullRow = true;
			for (int col = 0; col < this.field[row].length; col++) {
				if (this.field[row][col] == '0') {
					isFullRow = false;
					break;
				}
			}

			if (isFullRow) {
				this.shiftRows(row);
			}
		}
	}

	public void shiftRows(int startingRow) {
		for (int row = startingRow; row > 0; row--) {
			for (int col = 0; col < this.field[row].length; col++) {
				this.field[row][col] = this.field[row - 1][col];
			}
		}
	}
}

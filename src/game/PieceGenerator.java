package game;

import java.util.Random;

public final class PieceGenerator {
	private static final Random random = new Random();

	private PieceGenerator() {
	}

	public static Piece generatePiece(int x, int y) {
		int randomNumber = random.nextInt(7);

		if (randomNumber == 0) {
			return new Piece(x, y, new PieceShapeI());
		} else if (randomNumber == 1) {
			return new Piece(x, y, new PieceShapeJ());
		} else if (randomNumber == 2) {
			return new Piece(x, y, new PieceShapeL());
		} else {
			return new Piece(x, y, new PieceShapeS());
		}
	}

	public static Piece generatePiece() {
		return generatePiece(4, 0);
	}
}

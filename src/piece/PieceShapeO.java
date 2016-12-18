package piece;

import game.FieldSquare;

public class PieceShapeO extends PieceShape {

	private static final FieldSquare[][][] shapes = {
			{ 
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY } 
			} 
		};

	public PieceShapeO() {
		super();
	}

	@Override
	protected FieldSquare[][][] getShapes() {
		return shapes;
	}
}

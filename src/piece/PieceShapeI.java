package piece;

import java.awt.Color;

import game.FieldSquare;

public class PieceShapeI extends PieceShape {
	private static final Color COLOR = Color.RED;
	private static final FieldSquare[][][] SHAPES = {
			{ 
				{ FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY } 
			},
			{ 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.PIECE }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY } 
			} 
	};

	public PieceShapeI() {
		super();
	}

	@Override
	public Color getColor() {
		return PieceShapeI.COLOR;
	}
	
	@Override
	protected FieldSquare[][][] getShapes() {
		return PieceShapeI.SHAPES;
	}	
}

package piece;

import java.awt.Color;

import game.FieldSquare;

public class PieceShapeZ extends PieceShape {
	private static final Color COLOR = Color.CYAN;
	private static final FieldSquare[][][] SHAPES = {
			{ 
				{ FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY },
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY },
				{ FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }
			},
			{ 
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY },
				{ FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY } 
			}
	};

	public PieceShapeZ() {
		super();
	}

	@Override
	public Color getColor() {
		return PieceShapeZ.COLOR;
	}
	
	@Override
	protected FieldSquare[][][] getShapes() {
		return PieceShapeZ.SHAPES;
	}
}

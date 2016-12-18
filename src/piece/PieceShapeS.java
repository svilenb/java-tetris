package piece;

import java.awt.Color;

import game.FieldSquare;

public class PieceShapeS extends PieceShape {
	private static final Color COLOR = Color.GREEN;	
	private static final FieldSquare[][][] SHAPES = {
			{ 
				{ FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY },
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY } 
			},
			{ 
				{ FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY }, 
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }
			} 
	};

	public PieceShapeS() {
		super();
	}
	
	@Override
	public Color getColor() {
		return PieceShapeS.COLOR;
	}
	
	@Override
	protected FieldSquare[][][] getShapes() {
		return PieceShapeS.SHAPES;
	}
}

package piece;

import java.awt.Color;
import game.FieldSquare;

public class PieceShapeO extends PieceShape {
	private static final Color COLOR = Color.PINK;	
	private static final FieldSquare[][][] SHAPES = {
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
	public Color getColor() {
		return PieceShapeO.COLOR;
	}
	
	@Override
	protected FieldSquare[][][] getShapes() {
		return PieceShapeO.SHAPES;
	}
}

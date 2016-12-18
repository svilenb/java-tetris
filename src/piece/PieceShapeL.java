package piece;

import game.FieldSquare;

public class PieceShapeL extends PieceShape {
	private static final FieldSquare[][][] shapes = {
			{ 
				{ FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY } 
			},
			{ 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.EMPTY },
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY },
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY } 
			},
			{ 
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY },
				{ FieldSquare.EMPTY, FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY },
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY } 
			},
			{ 
				{ FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.PIECE, FieldSquare.EMPTY }, 
				{ FieldSquare.PIECE, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY }, 
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY },
				{ FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY, FieldSquare.EMPTY } 
			}
	};

	public PieceShapeL() {
		super();
	}

	@Override
	protected FieldSquare[][][] getShapes() {
		return shapes;
	}
}

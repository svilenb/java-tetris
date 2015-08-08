package game;

public class PieceShapeO extends PieceShape {

	private static final char[][][] shapes = {
			{ { '0', '2', '2', '0' }, { '0', '2', '2', '0' }, { '0', '0', '0', '0' }, { '0', '0', '0', '0' } } };

	public PieceShapeO() {
		super();
	}

	@Override
	protected char[][][] getShapes() {
		return shapes;
	}
}

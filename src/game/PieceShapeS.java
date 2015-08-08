package game;

public class PieceShapeS extends PieceShape {
	private static final char[][][] shapes = {
			{ { '0', '2', '2', '0' }, { '2', '2', '0', '0' }, { '0', '0', '0', '0' }, { '0', '0', '0', '0' } },
			{ { '2', '0', '0', '0' }, { '2', '2', '0', '0' }, { '0', '2', '0', '0' }, { '0', '0', '0', '0' } } };

	public PieceShapeS() {
		super();
	}

	@Override
	protected char[][][] getShapes() {
		return shapes;
	}
}

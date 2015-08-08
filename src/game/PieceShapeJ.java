package game;

public class PieceShapeJ extends PieceShape {
	private static final char[][][] shapes = {
			{ { '0', '2', '0', '0' }, { '0', '2', '0', '0' }, { '2', '2', '0', '0' }, { '0', '0', '0', '0' } },
			{ { '2', '2', '2', '0' }, { '0', '0', '2', '0' }, { '0', '0', '0', '0' }, { '0', '0', '0', '0' } },
			{ { '2', '2', '0', '0' }, { '2', '0', '0', '0' }, { '2', '0', '0', '0' }, { '0', '0', '0', '0' } },
			{ { '2', '0', '0', '0' }, { '2', '2', '2', '0' }, { '0', '0', '0', '0' }, { '0', '0', '0', '0' } } };

	public PieceShapeJ() {
		super();
	}

	@Override
	protected char[][][] getShapes() {
		return shapes;
	}
}

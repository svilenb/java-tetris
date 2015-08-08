package game;

public class PieceShapeZ extends PieceShape {
	private static final char[][][] shapes = {
			{ { '0', '2', '0', '0' }, { '2', '2', '0', '0' }, { '2', '0', '0', '0' }, { '0', '0', '0', '0' } },
			{ { '2', '2', '0', '0' }, { '0', '2', '2', '0' }, { '0', '0', '0', '0' }, { '0', '0', '0', '0' } } };

	public PieceShapeZ() {
		super();
	}

	@Override
	protected char[][][] getShapes() {
		return shapes;
	}
}

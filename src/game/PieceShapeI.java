package game;

public class PieceShapeI extends PieceShape {
	private static final char[][][] shapes = {
			{ { '0', '2', '0', '0' }, { '0', '2', '0', '0' }, { '0', '2', '0', '0' }, { '0', '2', '0', '0' } },
			{ { '0', '0', '0', '0' }, { '2', '2', '2', '2' }, { '0', '0', '0', '0' }, { '0', '0', '0', '0' } } };

	public PieceShapeI() {
		super();
	}

	@Override
	protected char[][][] getShapes() {
		return shapes;
	}
}

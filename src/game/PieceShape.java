package game;

import java.util.Arrays;

public abstract class PieceShape {
	private int currentShapeIndex;

	public PieceShape() {
		this.setCurrentShapeIndex(0);
	}

	protected int getCurrentShapeIndex() {
		return currentShapeIndex;
	}

	protected void setCurrentShapeIndex(int currentShapeIndex) {
		this.currentShapeIndex = currentShapeIndex;
	}

	protected abstract char[][][] getShapes();

	public char[][] getShape() {
		char[][][] shapes = this.getShapes();
		char[][] currentShape = shapes[this.getCurrentShapeIndex()];
		char[][] result = new char[currentShape.length][];

		for (int i = 0; i < currentShape.length; i++) {
			result[i] = Arrays.copyOf(currentShape[i], currentShape[i].length);
		}

		return result;
	}

	public void rotate() {
		int currentShapeIndex = this.getCurrentShapeIndex();
		char[][][] shapes = this.getShapes();

		if (currentShapeIndex == shapes.length - 1) {
			this.setCurrentShapeIndex(0);
		} else {
			this.setCurrentShapeIndex(currentShapeIndex + 1);
		}
	}

	public void undoRotate() {
		int currentShapeIndex = this.getCurrentShapeIndex();
		char[][][] shapes = this.getShapes();

		if (currentShapeIndex == 0) {
			this.setCurrentShapeIndex(shapes.length - 1);
		} else {
			this.setCurrentShapeIndex(currentShapeIndex - 1);
		}
	}
}

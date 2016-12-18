package piece;

import java.awt.Color;
import java.util.Arrays;

import game.FieldSquare;

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

	protected abstract FieldSquare[][][] getShapes();	
	
	public abstract Color getColor();
	
	public int getHeight() {		
		return this.getCurrentShape().length;
	}
	
	public int getWidth() {		
		return this.getCurrentShape()[0].length;
	}
	
	private FieldSquare[][] getCurrentShape () {
		FieldSquare[][][] shapes = this.getShapes();	
		FieldSquare [][] shape = shapes[this.getCurrentShapeIndex()];
		
		return shape;
	}
	
	public void rotate() {
		int currentShapeIndex = this.getCurrentShapeIndex();
		FieldSquare[][][] shapes = this.getShapes();

		if (currentShapeIndex == shapes.length - 1) {
			this.setCurrentShapeIndex(0);
		} else {
			this.setCurrentShapeIndex(currentShapeIndex + 1);
		}
	}

	public void undoRotate() {
		int currentShapeIndex = this.getCurrentShapeIndex();
		FieldSquare[][][] shapes = this.getShapes();

		if (currentShapeIndex == 0) {
			this.setCurrentShapeIndex(shapes.length - 1);
		} else {
			this.setCurrentShapeIndex(currentShapeIndex - 1);
		}
	}
	
	public boolean isPieceBrick(int row, int col) {
		FieldSquare[][] currentShape = this.getCurrentShape();
		
		return currentShape[row][col].equals(FieldSquare.PIECE);
	}
}

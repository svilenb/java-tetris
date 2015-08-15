package game;

import display.Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	private Game game;
		
	public InputHandler(Display display, Game game) {
		display.getCanvas().addKeyListener(this);
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_SPACE) {
			game.rotatePiece();
		} else if (keyCode == KeyEvent.VK_DOWN) {
			game.movePieceDown();
		} else if (keyCode == KeyEvent.VK_LEFT) {
			game.movePieceLeft();
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			game.movePieceRight();
		} else if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} else if (keyCode == KeyEvent.VK_P) {
			game.pause();
		} else if (keyCode == KeyEvent.VK_S) {
			game.resume();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}

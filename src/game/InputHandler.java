package game;

import display.Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	public InputHandler(Display display) {
		display.getCanvas().addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_UP) {
			
		} else if (keyCode == KeyEvent.VK_DOWN) {
			
		} else if (keyCode == KeyEvent.VK_LEFT) {
			
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			
		} else if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
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

package game;

import display.Display;
import states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
	private Display display;
	private int width;
	private int height;
	private int fieldHeight = 20;
	private int fieldWidth = 10;
	private String title;
	private boolean running = false;
	private Thread thread;
	@SuppressWarnings("unused")
	private InputHandler inputHandler;
	private BufferStrategy bs;
	private Graphics g;
	private State gameState;
	private State menuState;
	private State settingsState;
	private Field field;
	private Piece piece;

	public Game(String title) {
		this.width = 400;
		this.height = 600;
		this.fieldHeight = 20;
		this.fieldWidth = 10;
		this.title = title;
		this.field = new Field(this.fieldHeight, this.fieldWidth);
		this.piece = PieceGenerator.generatePiece();
	}

	private void init() {
		display = new Display(this.title, this.width, this.height);
		this.inputHandler = new InputHandler(this.display, this);
		gameState = new GameState();
		menuState = new MenuState();
		settingsState = new SettingsState();

		// Setting the currentState to gameState because we do not have
		// any more states set up
		// StateManage.setCurrentState(gameState);
	}

	// The method that will update all the variables
	private void tick() {
		// Checks if a State exists and tick()
		// if (StateManager.getState() != null) {
		// StateManager.getState().tick();
		// }

		if (this.field.isPieceFallen(this.piece)) {
			this.field.placePiece(this.piece);
			this.piece = PieceGenerator.generatePiece();
		} else {
			this.piece.tick();
		}
	}

	// The method that will draw everything on the canvas
	private void render() {
		// Setting the bufferStrategy to be the one used in our canvas
		// Gets the number of buffers that the canvas should use.
		this.bs = display.getCanvas().getBufferStrategy();
		// If our bufferStrategy doesn't know how many buffers to use
		// we create some manually
		if (bs == null) {
			// Create 2 buffers
			display.getCanvas().createBufferStrategy(2);
			// returns out of the method to prevent errors
			return;
		}
		// Instantiates the graphics related to the bufferStrategy
		g = this.bs.getDrawGraphics();
		// Clear the screen at every frame
		g.clearRect(0, 0, this.width, this.height);
		// Beginning of drawing things on the screen

		g.drawLine(300, 0, 300, 600);

		this.field.render(g);
		this.piece.render(g);

		// Checks if a State exists and render()
		// if (StateManager.getState() != null){
		// StateManager.getState().render(this.g);
		// }

		// End of drawing objects

		// Enables the buffer
		bs.show();
		// Shows everything stored in the Graphics object
		g.dispose();
	}

	@Override
	public void run() {
		this.init();

		while (running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}

			this.tick();
			this.render();
		}

		// Calls the stop method to ensure everything has been stopped
		this.stop();
	}

	// Creating a start method for the Thread to start our game
	// Synchronized is used because our method is working with threads
	// so we ensure ourselves that nothing will go bad
	public synchronized void start() {
		// If the game is running exit the method
		// This is done in order to prevent the game to initialize
		// more than enough threads
		if (running) {
			return;
		}
		// Setting the while-game-loop to run
		running = true;
		// Initialize the thread that will work with "this" class (game.Game)
		thread = new Thread(this);
		// The start method will call start the new thread and it will call
		// the run method in our class
		thread.start();
	}

	// Creating a stop method for the Thread to stop our game
	public synchronized void stop() {
		// If the game is not running exit the method
		// This is done to prevent the game from stopping a
		// non-existing thread and cause errors
		if (!running) {
			return;
		}
		running = false;
		// The join method stops the current method from executing and it
		// must be surrounded in try-catch in order to work
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void rotatePiece() {
		this.piece.rotate();
		this.piece.render(this.bs.getDrawGraphics());
		bs.show();
		// Shows everything stored in the Graphics object
		g.dispose();
	}

	public void movePieceLeft() {
		if (!this.field.doesPieceTouchesLeftWall(this.piece)) {
			this.piece.moveLeft();
			this.piece.render(this.bs.getDrawGraphics());
			bs.show();
			// Shows everything stored in the Graphics object
			g.dispose();
		}

	}

	public void movePieceRight() {
		if (!this.field.doesPieceTouchesRightWall(this.piece)) {
			this.piece.moveRight();
			this.piece.render(this.bs.getDrawGraphics());
			bs.show();
			// Shows everything stored in the Graphics object
			g.dispose();
		}
	}
}

package game;

import display.Display;
import states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

	private static final int NEXT_PIECE_X = 11;
	private static final int NEXT_PIECE_Y = 1;

	public static final int STARTING_PIECE_X = 4;
	public static final int STARTING_PIECE_Y = 0;
	
	private static final int DISPLAY_WIDTH = 450;
	private static final int DISPLAY_HEIGHT = 600;

	private static final int FIELD_WIDTH = 10;
	private static final int FIELD_HEIGHT = 20;

	private Display display;
	private int displayWidth;
	private int displayHeight;
	private int fieldHeight;
	private int fieldWidth;
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
	private Piece nextPiece;
	private boolean paused;

	public Game(String title) {
		this.setTitle(title);

		this.setDisplayWidth(Game.DISPLAY_WIDTH);
		this.setDisplayHeight(Game.DISPLAY_HEIGHT);

		this.setFieldHeight(Game.FIELD_HEIGHT);
		this.setFieldWidth(Game.FIELD_WIDTH);

		this.field = new Field(this.getFieldHeight(), this.getFieldWidth());
		this.setPiece(PieceGenerator.generatePiece());
		this.setNextPiece(PieceGenerator.generatePiece(Game.NEXT_PIECE_X, Game.NEXT_PIECE_Y));
	}

	private int getDisplayWidth() {
		return displayWidth;
	}

	private void setDisplayWidth(int width) {
		this.displayWidth = width;
	}

	private int getDisplayHeight() {
		return displayHeight;
	}

	private void setDisplayHeight(int height) {
		this.displayHeight = height;
	}

	private int getFieldHeight() {
		return fieldHeight;
	}

	private void setFieldHeight(int fieldHeight) {
		this.fieldHeight = fieldHeight;
	}

	private int getFieldWidth() {
		return fieldWidth;
	}

	private void setFieldWidth(int fieldWidth) {
		this.fieldWidth = fieldWidth;
	}

	private String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	private boolean isRunning() {
		return running;
	}

	private void setRunning(boolean running) {
		this.running = running;
	}

	private Piece getPiece() {
		return piece;
	}

	private void setPiece(Piece piece) {
		this.piece = piece;
	}

	private Piece getNextPiece() {
		return nextPiece;
	}

	private void setNextPiece(Piece nextPiece) {
		this.nextPiece = nextPiece;
	}

	private boolean isPaused() {
		return paused;
	}

	private void setPaused(boolean paused) {
		this.paused = paused;
	}

	private void init() {
		display = new Display(this.title, this.displayWidth, this.displayHeight);
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

		if (this.paused) {
			return;
		}

		Piece currentPiece = this.getPiece();
		
		if (this.field.isPieceFallen(currentPiece)) {
			this.field.placePiece(currentPiece);
			this.field.destroyFullRows();
			this.swithToNextPiece();			
		} else {
			currentPiece.tick();
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
		g.clearRect(0, 0, this.displayWidth, this.displayHeight);
		// Beginning of drawing things on the screen

		g.drawLine(300, 0, 300, 600);

		this.field.render(g);
		this.piece.render(g);
		this.nextPiece.render(g);

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

		while (this.running) {
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
		if (this.running) {
			return;
		}
		// Setting the while-game-loop to run
		this.running = true;
		// Initialize the thread that will work with "this" class (game.Game)
		this.thread = new Thread(this);
		// The start method will call start the new thread and it will call
		// the run method in our class
		this.thread.start();
	}

	// Creating a stop method for the Thread to stop our game
	public synchronized void stop() {
		// If the game is not running exit the method
		// This is done to prevent the game from stopping a
		// non-existing thread and cause errors
		if (!this.running) {
			return;
		}
		this.running = false;
		// The join method stops the current method from executing and it
		// must be surrounded in try-catch in order to work
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		this.paused = true;
	}

	public void resume() {
		this.paused = false;
	}

	private void swithToNextPiece() {
		
		Piece nextPiece = this.getNextPiece();
				
		nextPiece.movePieceToStartingPoing();
									
		this.setPiece(nextPiece);
				
		this.setNextPiece(PieceGenerator.generatePiece(Game.NEXT_PIECE_X, Game.NEXT_PIECE_Y));		
	}

	public void rotatePiece() {
		this.piece.rotate();
		if (this.field.isPieceOut(this.piece) || this.field.isPieceIntoBrick(this.piece)) {
			this.piece.undoRotate();
		} else {
			this.render();
		}
	}

	public void movePieceLeft() {
		if (!this.field.doesPieceTouchesLeftWall(this.piece)) {
			this.piece.moveLeft();
			if (this.field.isPieceIntoBrick(this.piece)) {
				this.piece.moveRight();
			} else {
				this.render();
			}
		}
	}

	public void movePieceRight() {
		if (!this.field.doesPieceTouchesRightWall(this.piece)) {
			this.piece.moveRight();
			if (this.field.isPieceIntoBrick(this.piece)) {
				this.piece.moveLeft();
			} else {
				this.render();
			}
		}
	}

	public void movePieceDown() {
		if (!this.field.doesPieceTouchesBottom(this.piece)) {
			this.piece.moveDown();
			if (this.field.isPieceIntoBrick(this.piece)) {
				this.piece.moveUp();
			} else {
				this.render();
			}
		}
	}
}

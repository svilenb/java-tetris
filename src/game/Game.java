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
	
	private static final int DISPLAY_WIDTH = 420;
	private static final int DISPLAY_HEIGHT = 600;

	private static final int FIELD_WIDTH = 10;
	private static final int FIELD_HEIGHT = 20;

	private Display display;
	private String title;
	private boolean running = false;
	private Thread thread;
	@SuppressWarnings("unused")
	private InputHandler inputHandler;
	private BufferStrategy bs;
	private Graphics graphics;
	private State gameState;
	private State menuState;
	private State settingsState;
	private Field field;
	
	private Piece currentPiece;
	private Piece nextPiece;
	
	private boolean paused;

	public Game(String title) {
		this.setTitle(title);

		this.field = new Field(Game.FIELD_HEIGHT, Game.FIELD_WIDTH);
		
		this.setCurrentPiece(PieceGenerator.generatePiece());
		this.setNextPiece(PieceGenerator.generatePiece(Game.NEXT_PIECE_X, Game.NEXT_PIECE_Y));
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

	private Piece getCurrentPiece() {
		return currentPiece;
	}

	private void setCurrentPiece(Piece piece) {
		this.currentPiece = piece;
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
		this.display = new Display(this.getTitle(), Game.DISPLAY_WIDTH, Game.DISPLAY_HEIGHT);
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

		if (this.isPaused()) {
			return;
		}

		Piece currentPiece = this.getCurrentPiece();
		
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
		if (this.bs == null) {
			// Create 2 buffers
			this.display.getCanvas().createBufferStrategy(2);
			// returns out of the method to prevent errors
			return;
		}

		// Instantiates the graphics related to the bufferStrategy
		this.graphics = this.bs.getDrawGraphics();
		
		// Clear the screen at every frame
		this.graphics.clearRect(0, 0, this.display.getWidth(), this.display.getHeight());
		// Beginning of drawing things on the screen

		this.graphics.drawLine(300, 0, 300, 600);

		this.field.render(this.graphics);
		this.currentPiece.render(this.graphics);
		this.nextPiece.render(this.graphics);

		// Checks if a State exists and render()
		// if (StateManager.getState() != null){
		// StateManager.getState().render(this.g);
		// }

		// End of drawing objects

		// Enables the buffer
		bs.show();
		// Shows everything stored in the Graphics object
		this.graphics.dispose();
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
		if (this.isRunning()) {
			return;
		}
		// Setting the while-game-loop to run
		this.setRunning(true);
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
		if (!this.isRunning()) {
			return;
		}
		
		this.setRunning(false);
		
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
		// get next piece
		Piece nextPiece = this.getNextPiece();				
		// move it to the staring position of the field
		nextPiece.movePieceToStartingPoing();									
		// assign the current piece to be the old next piece
		this.setCurrentPiece(nextPiece);				
		// create new next piece
		this.setNextPiece(PieceGenerator.generatePiece(Game.NEXT_PIECE_X, Game.NEXT_PIECE_Y));		
	}

	public void rotatePiece() {
		this.currentPiece.rotate();
		if (this.field.isPieceOut(this.currentPiece) || this.field.isPieceIntoBrick(this.currentPiece)) {
			this.currentPiece.undoRotate();
		} else {
			this.render();
		}
	}

	public void movePieceLeft() {
		if (!this.field.doesPieceTouchLeftWall(this.currentPiece)) {
			this.currentPiece.moveLeft();
			if (this.field.isPieceIntoBrick(this.currentPiece)) {
				this.currentPiece.moveRight();
			} else {
				this.render();
			}
		}
	}

	public void movePieceRight() {
		if (!this.field.doesPieceTouchRightWall(this.currentPiece)) {
			this.currentPiece.moveRight();
			if (this.field.isPieceIntoBrick(this.currentPiece)) {
				this.currentPiece.moveLeft();
			} else {
				this.render();
			}
		}
	}

	public void movePieceDown() {
		if (!this.field.doesPieceTouchesBottom(this.currentPiece)) {
			this.currentPiece.moveDown();
			if (this.field.isPieceIntoBrick(this.currentPiece)) {
				this.currentPiece.moveUp();
			} else {
				this.render();
			}
		}
	}
}

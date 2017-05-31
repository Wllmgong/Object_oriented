package reversi;

import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

/**
 * 
 * Name:William Gong Section:0303 ID:113510311 Directory ID:wgong1 Honor Code:I
 * pledge on my honor that i have not give or received any unauthorized
 * assistance on this assignment
 * 
 * Reversi is game using black and white pieces that traps each other to
 * increase each color's numbers. The game starts with two white and two black
 * pieces at the center on a board that is 7x7. The goal of the game is either
 * to make the Opponent to either run out of moves or having the most number of
 * pieces on the board when the game ends. The pieces can only be placed next to
 * each other and of opposite color so 1 or more opposite color is trapped
 * between two pieces of the same color. It can occur in a total of 8 directions
 * and changes the color of any piece that is trapped.
 *
 */
public class Reversi {

	private Piece[][] board; // The array that shows the board and contain
								// the pieces
	private Piece move; // Contain which color's turn

	/**
	 * the default constructor for the class. Creates the board and set the
	 * starting move
	 */
	public Reversi() {
		board = new Piece[8][8]; // Creates a blank array
		for (int i = 0; i <= 7; i++)
			// creates the board and fills it
			for (int j = 0; j <= 7; j++)
				board[i][j] = Piece.NONE;
		move = Piece.BLACK; // black starts the game
	}

	/**
	 * It is a shallow copy constructor that copies the exact board from another
	 * object
	 * 
	 * @param otherGame
	 */
	public Reversi(Reversi otherGame) {
		for (int i = 0; i <= 7; i++)
			// shallow copy
			for (int j = 0; j <= 7; j++)
				board[i][j] = otherGame.board[i][j];

		move = otherGame.move; // sets the turn
	}

	/**
	 * it takes a piece and replace the piece for more. It sets whose turn to
	 * place the pieces.
	 * 
	 * @param type
	 * @throws IllegalArgumentException
	 */
	public void setTurn(Piece type) throws IllegalArgumentException {
		if (type.isNone()) // check if type is valid
			throw new IllegalArgumentException();
		move = type; // set move equals type
	}

	/**
	 * return the piece, which returns the turn
	 * 
	 * @return
	 */
	public Piece getTurn() {
		return move;

	}

	/**
	 * it creates a pieces black or white at the location row and column checks
	 * it row and column is within the size of the board, if it goes outside of
	 * the bound, it return the NoSuchLementException checks to see if type is
	 * valid
	 * 
	 * @param row
	 * @param col
	 * @param type
	 * @throws NoSuchElementException
	 */
	public void setSquare(int row, int col, Piece type)
			throws NoSuchElementException {

		if (row < 0 || row > 7 || col < 0 || col > 7)
			// checking if row and column are within the bound, if not
			// throws an exception
			throw new NoSuchElementException();

		board[row][col] = type; // set the location as the piece

	}

	/**
	 * it returns the piece at row and column. If its outside of the bound, it
	 * return a NoSuchElementException() Then returns the piece at that location
	 * 
	 * @param row
	 * @param col
	 * @return
	 * @throws NoSuchElementException
	 */
	public Piece getSquare(int row, int col) throws NoSuchElementException {

		if (row < 0 || row > 7 || col < 0 || col > 7)
			// checking if row and column are within the bound, if not
			// throws an exception
			throw new NoSuchElementException();

		return board[row][col]; // returns the piece at the location
	}

	/**
	 * it iterates through the board and counts the pieces for the specific
	 * color piece.
	 * 
	 * @param type
	 * @return
	 */
	public int count(Piece type) {
		int count = 0;
		for (int i = 0; i <= 7; i++)
			// iterating through the code
			for (int j = 0; j <= 7; j++)
				if (board[i][j].equals(type))// check if the piece is the same
					count++; // increase the count
		return count; // return the count
	}

	/**
	 * This method creates a new board that replaces the old one. This board
	 * also creates 4 black and white pieces at the center. Type sets the turn
	 * for the new game to start with
	 * 
	 * @param type
	 * @throws IllegalArgumentException
	 */
	public void reset(Piece type) throws IllegalArgumentException {

		if (type.isNone()) // check if type is not null nor is blank
			throw new IllegalArgumentException();

		Piece[][] boardReplace = new Piece[8][8];
		// creates a new board
		for (int i = 0; i <= 7; i++)
			// fills the board with blank pieces
			for (int j = 0; j <= 7; j++)
				boardReplace[i][j] = Piece.NONE;

		boardReplace[4][3] = Piece.WHITE;
		boardReplace[3][4] = Piece.WHITE;
		boardReplace[3][3] = Piece.BLACK;
		boardReplace[4][4] = Piece.BLACK;

		// places the 4 pieces at the center
		board = boardReplace; // replace the board
		move = type;
	}

	/**
	 * the toString method, it prints out every location of the board w stands
	 * for white, b stands for black, and - stands for blank also prints out the
	 * numbers showing the locations of the grid
	 */
	public String toString() {
		String print = "";
		for (int i = 7; i >= 0; i--) {// iterating through the board
			print = print + i; // prints the row numbering
			for (int j = 0; j <= 7; j++) {
				print = print + " " + board[i][j].toString();
				// the piece's tostring method is used
			}
			print = print + "\n"; // creates a new line

		}
		print = print + "  0 1 2 3 4 5 6 7\n"; // prints the column numbering
		return print;
	}

	/**
	 * This is a helper method that checks in the up direction to find if there
	 * is any same color piece. If there is one that exist then it stops at the
	 * first one and checks the pieces in between the two if opposite color
	 * pieces exist in between. The location start at row and column. It returns
	 * an array that contains the location of the pieces on the other side if
	 * its true or return -1 as the location of the array
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private int[] checkUp(int row, int col) {
		boolean k = true;
		int[] loc = new int[2];// stores the location
		loc[0] = -1;
		loc[1] = -1;
		int r = row;
		int c = col;
		if (move.isBlack() && r < 6) {// check if the move is for black piece
			if (board[r + 1][c].isWhite()) {// check is its next to a white
											// piece
				r++;
				while (r <= 7 && k) {
					// the while loop goes until its out of the
					// board size or false is called
					if (board[r][c].isNone()) {
						// then check the next piece to
						// see if its white or black, if
						// not then returns false
						k = false;
					}

					if (board[r][c].isBlack()) {// if one of piece is black then
												// it stores the location
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					r++;
				}
			}
		}
		if (move.isWhite() && r < 6) {// check if the move is for white piece
			if (board[r + 1][c].isBlack()) {// check is its next to a black
				// piece
				r++;
				while (r <= 7 && k) {
					// the while loop goes until its out of the
					// board size or false is called
					if (board[r][c].isNone()) {
						// then check the next piece to
						// see if its white or black, if
						// not then returns false
						k = false;
					}

					if (board[r][c].isWhite()) {
						// if one of piece is black then
						// it stores the location
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					r++;
				}

			}
		}

		return loc;
		// the following 7 helper methods
	}

	/**
	 * This is a helper method that checks in the down direction to find if
	 * there is any same color piece. If there is one that exist then it stops
	 * at the first one and checks the pieces in between the two if opposite
	 * color pieces exist in between. The location start at row and column. It
	 * returns an array that contains the location of the pieces on the other
	 * side if its true or return -1 as the location of the array
	 * 
	 */
	private int[] checkDown(int row, int col) {
		boolean k = true;
		int[] loc = new int[2];
		loc[0] = -1;
		loc[1] = -1;
		int r = row;
		int c = col;

		if (move.isBlack() && r > 0) {
			if (board[r - 1][c].isWhite()) {
				r--;
				while (r >= 0 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isBlack()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					r--;
				}
			}
		}
		if (move.isWhite() && r > 0) {
			if (board[r - 1][c].isBlack()) {
				r--;
				while (r >= 0 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isWhite()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					r--;
				}

			}
		}

		return loc;

	}

	/**
	 * This is a helper method that checks in the left direction to find if
	 * there is any same color piece. If there is one that exist then it stops
	 * at the first one and checks the pieces in between the two if opposite
	 * color pieces exist in between. The location start at row and column. It
	 * returns an array that contains the location of the pieces on the other
	 * side if its true or return -1 as the location of the array
	 * 
	 */

	private int[] checkLeft(int row, int col) {
		boolean k = true;
		int[] loc = new int[2];
		loc[0] = -1;
		loc[1] = -1;
		int r = row;
		int c = col;

		if (move.isBlack() && c > 0) {
			if (board[r][c - 1].isWhite()) {
				c--;
				while (c >= 0 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isBlack()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c--;
				}

			}
		}
		if (move.isWhite() && c > 0) {
			if (board[r][c - 1].isBlack()) {
				c--;
				while (c >= 0 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isWhite()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c--;
				}

			}
		}

		return loc;

	}

	/**
	 * This is a helper method that checks in the right direction to find if
	 * there is any same color piece. If there is one that exist then it stops
	 * at the first one and checks the pieces in between the two if opposite
	 * color pieces exist in between. The location start at row and column. It
	 * returns an array that contains the location of the pieces on the other
	 * side if its true or return -1 as the location of the array
	 * 
	 */
	private int[] checkRight(int row, int col) {
		boolean k = true;
		int[] loc = new int[2];
		loc[0] = -1;
		loc[1] = -1;
		int r = row;
		int c = col;

		if (move.isBlack() && c <= 6) {
			if (board[r][c + 1].isWhite()) {
				c++;
				while (c <= 7 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isBlack()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c++;
				}
			}
		}
		if (move.isWhite() && c <= 6) {
			if (board[r][c + 1].isBlack()) {
				c++;
				while (c <= 7 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isWhite()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c++;
				}
			}
		}

		return loc;

	}

	/**
	 * This is a helper method that checks in the diagonal up right direction to
	 * find if there is any same color piece. If there is one that exist then it
	 * stops at the first one and checks the pieces in between the two if
	 * opposite color pieces exist in between. The location start at row and
	 * column. It returns an array that contains the location of the pieces on
	 * the other side if its true or return -1 as the location of the array
	 * 
	 */
	private int[] checkDiagonalUpRight(int row, int col) {
		boolean k = true;
		int[] loc = new int[2];
		loc[0] = -1;
		loc[1] = -1;
		int r = row;
		int c = col;

		if (move.isBlack() && c <= 6 && r <= 6) {
			if (board[r + 1][c + 1].isWhite()) {
				c++;
				r++;
				while (c <= 7 && r <= 7 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isBlack()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c++;
					r++;
				}
			}
		}
		if (move.isWhite() && c <= 6 && r <= 6) {
			if (board[r + 1][c + 1].isBlack()) {
				c++;
				r++;
				while (c <= 7 && r <= 7 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isWhite()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c++;
					r++;
				}

			}
		}

		return loc;

	}

	/**
	 * This is a helper method that checks in the down left direction to find if
	 * there is any same color piece. If there is one that exist then it stops
	 * at the first one and checks the pieces in between the two if opposite
	 * color pieces exist in between. The location start at row and column. It
	 * returns an array that contains the location of the pieces on the other
	 * side if its true or return -1 as the location of the array
	 * 
	 */
	private int[] checkDiagonalDownLeft(int row, int col) {
		boolean k = true;
		int[] loc = new int[2];
		loc[0] = -1;
		loc[1] = -1;
		int r = row;
		int c = col;

		if (move.isBlack() && c > 0 && r > 0) {
			if (board[r - 1][c - 1].isWhite()) {
				c--;
				r--;
				while (c >= 0 && r >= 0 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isBlack()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c--;
					r--;
				}

			}
		}
		if (move.isWhite() && c > 0 && r > 0) {
			if (board[r - 1][c - 1].isBlack()) {
				c--;
				r--;
				while (c >= 0 && r >= 0 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isWhite()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c--;
					r--;
				}

			}
		}

		return loc;

	}

	/**
	 * This is a helper method that checks in the diagonal down right direction
	 * to find if there is any same color piece. If there is one that exist then
	 * it stops at the first one and checks the pieces in between the two if
	 * opposite color pieces exist in between. The location start at row and
	 * column. It returns an array that contains the location of the pieces on
	 * the other side if its true or return -1 as the location of the array
	 * 
	 */
	private int[] checkDiagonalDownRight(int row, int col) {
		boolean k = true;
		int[] loc = new int[2];
		loc[0] = -1;
		loc[1] = -1;
		int r = row;
		int c = col;

		if (move.isBlack() && c <= 6 && r > 0) {
			if (board[r - 1][c + 1].isWhite()) {
				c++;
				r--;
				while (c <= 7 && r >= 0 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isBlack()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c++;
					r--;
				}

			}
		}
		if (move.isWhite() && c <= 6 && r > 0) {
			if (board[r - 1][c + 1].isBlack()) {
				c++;
				r--;
				while (c <= 7 && r >= 0 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isWhite()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c++;
					r--;
				}

			}
		}

		return loc;

	}

	/**
	 * This is a helper method that checks in the diagonal up left direction to
	 * find if there is any same color piece. If there is one that exist then it
	 * stops at the first one and checks the pieces in between the two if
	 * opposite color pieces exist in between. The location start at row and
	 * column. It returns an array that contains the location of the pieces on
	 * the other side if its true or return -1 as the location of the array
	 * 
	 */
	private int[] checkDiagonalUpLeft(int row, int col) {
		boolean k = true;
		int[] loc = new int[2];
		loc[0] = -1;
		loc[1] = -1;
		int r = row;
		int c = col;

		if (move.isBlack() && c > 0 && r <= 6) {
			if (board[r + 1][c - 1].isWhite()) {
				c--;
				r++;
				while (c >= 0 && r <= 7 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isBlack()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c--;
					r++;
				}

			}
		}
		if (move.isWhite() && c > 0 && r <= 6) {
			if (board[r + 1][c - 1].isBlack()) {
				c--;
				r++;
				while (c >= 0 && r <= 7 && k) {
					if (board[r][c].isNone()) {
						k = false;
					}

					if (board[r][c].isWhite()) {
						loc[0] = r;
						loc[1] = c;
						k = false;
					}
					c--;
					r++;
				}

			}
		}

		return loc;

	}

	/**
	 * the validMove method checks if the location row and column is valid and
	 * fulfills all the conditions 1)it has to be adjacent to an opposite color
	 * piece 2)it has to trap at least one opposite color piece in between two
	 * same same color piece 3) has to be a valid location on the board to be
	 * placed 4)the type pieces is either white or black then it returns either
	 * true or false depending if the location fulfills the conditions
	 * 
	 * @param row
	 * @param col
	 * @param type
	 * @return
	 */
	public boolean validMove(int row, int col, Piece type) {
		if (type.isNone()) // check if type is not empty
			return false;

		if (row < 0 || row > 7 || col < 0 || col > 7)// the location is on the
			// board
			return false;

		if (board[row][col].isBlack() || board[row][col].isWhite())
			// check is there isnt a piece already on the location
			return false;

		int[] locRight = checkRight(row, col);
		int[] locLeft = checkLeft(row, col);
		int[] locUp = checkUp(row, col);
		int[] locDown = checkDown(row, col);
		int[] locDiagonalDownRight = checkDiagonalDownRight(row, col);
		int[] locDiagonalUpLeft = checkDiagonalUpLeft(row, col);
		int[] locDiagonalDownLeft = checkDiagonalDownLeft(row, col);
		int[] locDiagonalUpRight = checkDiagonalUpRight(row, col);
		// Receives each location for every direction

		if (locRight[0] == -1 && locLeft[0] == -1 && locUp[0] == -1
				&& locDown[0] == -1 && locDiagonalDownRight[0] == -1
				&& locDiagonalUpLeft[0] == -1 && locDiagonalDownLeft[0] == -1
				&& locDiagonalUpRight[0] == -1) {
			// checks each location, if there is one location that doesnt return
			// -1
			// then the move is valid, if not it return false
			return false;
		}

		return true;

	}

	/**
	 * the turnPiece is a helper method takes in the location of the two same
	 * color piece at two different location. Then flips all opposite pieces to
	 * the other color in between the two locations. It checks every direction
	 * first then it flips the pieces. The row and column location is the where
	 * the newest pieces to be placed and the array contains the location of
	 * where the piece of the opposite side.
	 * 
	 * @param row
	 * @param col
	 * @param arr
	 */
	private void turnPiece(int row, int col, int[] arr) {
		int[] loc = arr;
		// Diagonal Down Right
		if (loc[0] - row > 0 && loc[1] - col > 0) {
			int rowDis = loc[0] - row;// the distance between the two pieces
			int k = 0;
			while (k <= rowDis) {// loops until all pieces are flip
				this.setSquare(row + k, col + k, move);
				k++;
			}
		}
		// Diagonal Up Left
		else if (row - loc[0] > 0 && col - loc[1] > 0) {
			int rowDis = row - loc[0];// the distance between the two pieces
			int k = 0;
			while (k <= rowDis) {// loops until all pieces are flip
				this.setSquare(row - k, col - k, move);
				k++;
			}
		}
		// Diagonal Up Right
		else if (loc[0] - row > 0 && col - loc[1] > 0) {
			int rowDis = loc[0] - row;// the distance between the two pieces
			int k = 0;
			while (k <= rowDis) {// loops until all pieces are flip
				this.setSquare(row + k, col - k, move);
				k++;
			}
		}
		// Diagonal Down Left
		else if (row - loc[0] > 0 && loc[1] - col > 0) {
			int rowDis = row - loc[0];// the distance between the two pieces
			int k = 0;
			while (k <= rowDis) {// loops until all pieces are flip
				this.setSquare(row - k, col + k, move);
				k++;
			}
		}
		// Down
		else if (loc[0] - row > 0 && loc[1] - col == 0) {
			int rowDis = loc[0] - row;// the distance between the two pieces
			int k = 0;
			while (k <= rowDis) {// loops until all pieces are flip
				this.setSquare(row + k, col, move);
				k++;
			}
		}
		// Up
		else if (row - loc[0] > 0 && loc[1] - col == 0) {
			int rowDis = row - loc[0];// the distance between the two pieces
			int k = 0;
			while (k <= rowDis) {// loops until all pieces are flip
				this.setSquare(row - k, col, move);
				k++;
			}
		}
		// right
		else if (loc[0] - row == 0 && loc[1] - col > 0) {
			int colDis = loc[1] - col;// the distance between the two pieces
			int k = 0;
			while (k <= colDis) {// loops until all pieces are flip
				this.setSquare(row, col + k, move);
				k++;
			}
		}
		// left
		else if (loc[0] - row == 0 && col - loc[1] > 0) {
			int colDis = col - loc[1];// the distance between the two pieces
			int k = 0;
			while (k <= colDis) {// loops until all pieces are flip
				this.setSquare(row, col - k, move);
				k++;
			}
		}
	}

	/**
	 * It is the same method as the move(int, int, piece) method, except that it
	 * uses the current move piece instead of a new one.
	 * 
	 * @param row
	 * @param col
	 */
	public void move(int row, int col) {
		move(row, col, move);// call the move(int, int, piece) method

	}

	/**
	 * This method places a new piece at the location row and column, and sets
	 * the new piece color. It check if placing the new piece at the location is
	 * valid then it finds all of the possible locations for the other piece to
	 * trap the opposite color piece. Then if the locations returns a valid
	 * location, then it flips all of the pieces in between. Then its changes
	 * the move pieces color.
	 * 
	 * @param row
	 * @param col
	 * @param type
	 */
	public void move(int row, int col, Piece type) {

		if (!(type.isNone())) {// check if type is valid
			move = type;

			if (validMove(row, col, type)) {
				// uses the method valid move to check if the move location
				// is valid
				int[] locRight = checkRight(row, col);
				int[] locLeft = checkLeft(row, col);
				int[] locUp = checkUp(row, col);
				int[] locDown = checkDown(row, col);
				int[] locDiagonalDownRight = checkDiagonalDownRight(row, col);
				int[] locDiagonalUpLeft = checkDiagonalUpLeft(row, col);
				int[] locDiagonalDownLeft = checkDiagonalDownLeft(row, col);
				int[] locDiagonalUpRight = checkDiagonalUpRight(row, col);
				// if the move location is valid, then it finds all of the
				// possible locations for the piece to be paired up with
				if (!(locRight[0] == -1))
					turnPiece(row, col, locRight);

				if (!(locLeft[0] == -1))
					turnPiece(row, col, locLeft);

				if (!(locUp[0] == -1))
					turnPiece(row, col, locUp);

				if (!(locDown[0] == -1))
					turnPiece(row, col, locDown);

				if (!(locDiagonalDownRight[0] == -1))
					turnPiece(row, col, locDiagonalDownRight);

				if (!(locDiagonalDownLeft[0] == -1))
					turnPiece(row, col, locDiagonalDownLeft);

				if (!(locDiagonalUpLeft[0] == -1))
					turnPiece(row, col, locDiagonalUpLeft);

				if (!(locDiagonalUpRight[0] == -1))
					turnPiece(row, col, locDiagonalUpRight);
				// then it flips all of the pieces thats trapped to the opposite
				// color.
				if (move.isBlack()) // sets the next turn
					setTurn(Piece.WHITE);
				else
					setTurn(Piece.BLACK);

			}
		}

	}

}

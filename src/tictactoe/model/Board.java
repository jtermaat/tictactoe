package tictactoe.model;

public class Board {
	int[][] boardSpaces;
	private boolean computerFirst;
	private boolean isX;
	MoveSeries moves;
	
	final static int X = 1;
	final static int O = -1;
	final static int EMPTY = 0;
	
	final static int size = 3;
	
	final static char X_CHAR = 'X';
	final static char O_CHAR = 'O';
	final static char EMPTY_CHAR = ' ';
	
	public final static String NO_WINNER = "none";
	public final static String X_WINNER = "X wins";
	public final static String O_WINNER = "O wins";
	
	public Board(boolean computersTurn) {
		boardSpaces = new int[size][size];
		for (int i = 0;i<size;i++) {
			for (int j = 0;j<size;j++) {
				boardSpaces[i][j] = EMPTY;
			}
		}
		computerFirst = true;
		isX = true;
		moves = new MoveSeries(computersTurn);
	}
	
	public void buildWithSeries() {
//		computerFirst = series.isComputerFirst();
//		int playerSymbol = X
		int playerSymbol = X;
		for (Move move : moves.getAllMoves()) {
			boardSpaces[move.getX()][move.getY()] = playerSymbol;
			playerSymbol = playerSymbol * -1;
		}
	}
	
	// Returns true if move made successfully.
	public boolean makeMove(int xLocation, int yLocation) {
		if (isEmpty(xLocation, yLocation)) {
			moves.addMove(new Move(xLocation, yLocation));
			buildWithSeries();
			isX = !isX;
			System.out.println("series: " + moves);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isEmpty(int xLocation, int yLocation) {
		return boardSpaces[xLocation][yLocation] == EMPTY;
	}

	public char getLocation(int x, int y) {
		if (boardSpaces[x][y] == X) {
			return X_CHAR;
		} else if (boardSpaces[x][y] == O) {
			return O_CHAR;
		} else {
			return EMPTY_CHAR;
		}
	}
	
	public boolean gameOver() {
		return gameOverHorizontal() || gameOverVertical() || gameOverDiagonal();
	}
	
	public boolean gameTied() {
		boolean tied = true;
		for (int i = 0;i<3;i++) {
			for (int j = 0;j<3;j++) {
				if (boardSpaces[i][j] == EMPTY) {
					tied = false;
				}
			}
		}
		return tied;
	}
	
	private boolean gameOverVertical() {
		for (int i = 0;i<3;i++) {
			int space = boardSpaces[i][0];
			boolean match = (space != EMPTY);
			for (int j = 0;j<3;j++) {
				if (space != boardSpaces[i][j]) {
					match = false;
				}
			}
			if (match) {
				return match;
			}
		}
		return false;
	}
	
	private boolean gameOverHorizontal() {
		for (int i = 0;i<3;i++) {
			int space = boardSpaces[0][i];
			boolean match = (space != EMPTY);
			for (int j = 0;j<3;j++) {
				if (space != boardSpaces[j][i]) {
					match = false;
				}
			}
			if (match) {
				return match;
			}
		}
		return false;
	}
	
	private boolean gameOverDiagonal() {
		if (boardSpaces[0][0] == boardSpaces[1][1] &&
				boardSpaces[0][0] == boardSpaces[2][2] &&
				boardSpaces[0][0] != EMPTY) 
		{
			return true;
		}
		if (boardSpaces[2][0] == boardSpaces[1][1] &&
				boardSpaces[2][0] == boardSpaces[0][2] &&
				boardSpaces[2][0] != EMPTY) {
			return true;
		}
		return false;
	}

	public boolean isComputerFirst() {
		return computerFirst;
	}

	public void setComputerFirst(boolean computerFirst) {
		this.computerFirst = computerFirst;
	}
	
	public MoveSeries getMoveSeries() {
		return moves;
	}
}

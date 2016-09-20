package tictactoe.view;

import tictactoe.controller.ComputerAI;
import tictactoe.controller.FileManager;
import tictactoe.model.Board;

public class Main {
	public static void main(String[] args) {
		boolean gameover = false;
		Display display = new Display();
		Board board = new Board(false);
		ComputerAI ai = new ComputerAI();
		FileManager fileManager = new FileManager();
		
		while (!gameover) {
			ai.makeMove(board);
			if (board.gameOver()) {
				display.printComputerWins();
				gameover = true;
			} else if (board.gameTied()) {
				display.printTied();
				fileManager.writeMovesToFile(FileManager.DEFAULT_FILENAME, board.getMoveSeries(), true);
				gameover = true;
			}	else {
		
				display.printBoard(board);
				display.printPlayerPrompt();
				boolean success = false; 
				while(!success) {
					success = display.receiveKeyboardInput(board);
				}
				if (board.gameOver()) {
					display.printPlayerWins();
					fileManager.writeMovesToFile(FileManager.DEFAULT_FILENAME, board.getMoveSeries(), false);
					gameover = true;
				}
			}
		}
	}

}

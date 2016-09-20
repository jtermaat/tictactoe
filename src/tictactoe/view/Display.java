package tictactoe.view;

import java.util.Scanner;

import tictactoe.model.Board;

public class Display {
	
	Scanner keyboardIn;
	
	public Display() {
		keyboardIn = new Scanner(System.in);
	}
	
	public void printPlayerPrompt() {
		System.out.println();
		System.out.println("Select an open space.  Specify with comma separated coordinates, such as 1,1 for the center space");
		System.out.println("Your selection: ");
	}
	
	public boolean receiveKeyboardInput(Board board) {
		String received = keyboardIn.nextLine();
		String[] coordinates = received.split(",");
		int coordinateOne = 0;
		int coordinateTwo = 0;
		try {
			coordinateOne = Integer.parseInt(coordinates[0]);
			coordinateTwo = Integer.parseInt(coordinates[1]);
			if (coordinateOne < 0 ||
					coordinateOne > 2 ||
					coordinateTwo < 0 ||
					coordinateTwo > 2) {
				throw new ArrayIndexOutOfBoundsException();
			}
		} catch (Exception e) {
			System.out.println();
			System.out.println("Invalid input, try again.");
			return false;
		}
		return board.makeMove(coordinateOne, coordinateTwo);
	}

	public void printBoard(Board board) {
		if (board.isComputerFirst()) {
			System.out.println("Computer is X");
			System.out.println("Player is O");
		} else {
			System.out.println("Player is X");
			System.out.println("Computer is O");
		}
		System.out.println();
		
		for (int i = 0;i<6;i++) {
			if (i % 2 == 0) {
				// print play in spaces
				for (int j = 0;j<5;j++) {
					if (j % 2 == 0) {
						int x = (int)((double)j / (2.0));
						int y = (int)((double)i / (2.0));
						System.out.print(board.getLocation(x,y));
					} else {
						System.out.print("|");
					}
				}
			} else {
				for (int j = 0;j<5;j++) {
					if (j % 2 == 0) {
						if (i < 5) {
							System.out.print("_");
						} else {
							System.out.print(" ");
						}
					} else {
						System.out.print("|");
					}
				}
			} 
			System.out.println();
		}
	}

	public void printComputerWins() {
		System.out.println();
		System.out.println("The computer wins!");
		System.out.println();
		
	}
	
	public void printPlayerWins() {
		System.out.println();
		System.out.println("The player wins!");
		System.out.println();
	}

	public void printTied() {
		System.out.println();
		System.out.println("The game is a tie!");
		System.out.println();
		
	}
}

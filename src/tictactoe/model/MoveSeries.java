package tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class MoveSeries {
	private boolean computerFirst;
	List<Move> allMoves;
	
	private static boolean DEFAULT_COMPUTER_FIRST = true;
	
	public MoveSeries(boolean computerFirst) {
		this.setComputerFirst(computerFirst);
		allMoves = new ArrayList<Move>();
	}
	
	public MoveSeries(List<Move> allMoves) {
		this.allMoves = allMoves;
		computerFirst = DEFAULT_COMPUTER_FIRST;	
	}
	
	public void addMove(Move move) {
		allMoves.add(move);
	}
	
	public MoveSeries rotatedNinetyDegrees() {
		MoveSeries rotated = new MoveSeries(isComputerFirst());
		for (Move move : allMoves) {
			rotated.addMove(move.getRotatedNinety());
		}
		return rotated;
	}
	
	public List<Move> getAllMoves() {
		return allMoves;
	}
	
	public List<Move> getMoves(boolean firstPlayer) {
		List<Move> newMoves = new ArrayList<Move>();
		if (firstPlayer) {
			for (int i = 0;i<allMoves.size();i++) {
				if (firstPlayer) {
					if (isEven(i)) {
						newMoves.add(allMoves.get(i));
					}
				} else {
					if (!isEven(i)) {
						newMoves.add(allMoves.get(i));
					}
				}
			}
		}
		return newMoves;
	}
	
	private boolean isEven(int i) {
		return i % 2 == 0;
	}

	public boolean isComputerFirst() {
		return computerFirst;
	}

	public void setComputerFirst(boolean computerFirst) {
		this.computerFirst = computerFirst;
	}
	
	public void removeLastMove() {
		if (allMoves.size() > 0) {
			allMoves.remove(allMoves.size()-1);
		}
	}
	
	public static MoveSeries buildFromString(String input) {
		String[] parts = input.split("-");
		List<Move> moves = new ArrayList<Move>();
		for (String part : parts) {
			Move move = Move.buildFromString(part);
			moves.add(move);
		}
		return new MoveSeries(moves);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0;i<allMoves.size() - 1;i++) {
			sb.append(allMoves.get(i) + "-");
		}
		if (allMoves.size() > 0) {
			sb.append(allMoves.get(allMoves.size() - 1));
		}
		return sb.toString();
	}
}

package tictactoe.controller;

import java.util.ArrayList;
import java.util.List;

import tictactoe.controller.tree.PlayNode;
import tictactoe.controller.tree.PlayTree;
import tictactoe.model.Board;
import tictactoe.model.Move;
import tictactoe.model.MoveSeries;

public class ComputerAI {
	PlayTree tree;
	
	public ComputerAI() {
		FileManager fileManager = new FileManager();
		List<MoveSeries> allSeries = fileManager.readFile(FileManager.DEFAULT_FILENAME);
		tree = new PlayTree();
		tree.setFailures(allSeries);
	}

	public void makeMove(Board board) {
		MoveSeries existingMoves = board.getMoveSeries();
		PlayNode node = getPlayNodeFromMoves(existingMoves);
		List<Move> availableMoves = node.getNonFailingMoves();
//		if (availableMoves.)
		board.makeMove(availableMoves.get(0).getX(), availableMoves.get(0).getY());
	}
	
	public PlayNode getPlayNodeFromMoves(MoveSeries series) {
		List<Move> moves = new ArrayList<Move>();
		moves.addAll(series.getAllMoves());
		return tree.getPlayNodeFromMoves(moves);
	}

}

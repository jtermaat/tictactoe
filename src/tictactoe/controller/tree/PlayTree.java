package tictactoe.controller.tree;

import java.util.ArrayList;
import java.util.List;

import tictactoe.model.Move;
import tictactoe.model.MoveSeries;

public class PlayTree {
	List<Move> allPossibleMoves;
	List<PlayNode> startNodes;
	List<PlayNode> leaves; // End nodes.
	
	public PlayTree() {
		allPossibleMoves = new ArrayList<Move>();
		startNodes = new ArrayList<PlayNode>();
		leaves = new ArrayList<PlayNode>();
		for (int i = 0;i<3;i++) {
			for (int j = 0;j<3;j++) {
				allPossibleMoves.add(new Move(i,j));
			}
		}
		for (Move move : allPossibleMoves) {
			startNodes.add(new PlayNode(allPossibleMoves, move, null, leaves));
		}
	}
	
	public void setFailures(List<MoveSeries> losingMoveSeries) {
		for (MoveSeries series : losingMoveSeries) {
			setFailure(series);
		}
		// This ensures that the AI doesn't get stuck at a dead-end.
		for (PlayNode node : leaves) {
			node.setWinningByChildrenIfNotLosing();
		}
		for (PlayNode node : startNodes) {
			node.setLosingByChildrenToLosing();
		}
	}
	
	public void setFailure(MoveSeries series) {
		List<Move> movesToFailure = series.getAllMoves();
		int startIndex = startNodes.indexOf(movesToFailure.get(0));
		startNodes.get(startIndex).setFailure(movesToFailure);
	}

	public PlayNode getPlayNodeFromMoves(List<Move> moves) {
		if (moves.size() == 0) {
			return startNodes.get(0);
		}
		Move firstMove = moves.get(0);
		int firstNodeIndex = startNodes.indexOf(firstMove);
		PlayNode firstNode = startNodes.get(firstNodeIndex);
		return firstNode.getPlayNodeFromMoves(moves);
	}
}

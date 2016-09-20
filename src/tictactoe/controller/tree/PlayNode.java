package tictactoe.controller.tree;

import java.util.ArrayList;
import java.util.List;

import tictactoe.model.Move;

public class PlayNode {
	List<Move> remainingMoves;
	List<PlayNode> connectedNodes;
	PlayNode parent;
	private Move move;
	private boolean losingMove;
	private boolean losingByChildren;
	
	public PlayNode(List<Move> remainingMoves, Move move, PlayNode parent, List<PlayNode> leaves) {
		// First remove this move from the list.
		this.setMove(move);
		this.remainingMoves = new ArrayList<Move>();
		this.remainingMoves.addAll(remainingMoves);
		this.remainingMoves.remove(move);
		connectedNodes = new ArrayList<PlayNode>();
		if (this.remainingMoves.isEmpty()) {
			leaves.add(this);
			losingByChildren = false;
		} else {
			losingByChildren = true;
		}
		for (Move remainingMove : this.remainingMoves) {
			connectedNodes.add(new PlayNode(this.remainingMoves, remainingMove, this, leaves));
		}
	}
	
	public void setFailure(List<Move> movesToFailure) {
		movesToFailure.remove(this);
		if (movesToFailure.isEmpty()) {
			setLosingMove(true);
			setAllChildrenLosingMoves();
		} else {
			int nextIndex = connectedNodes.indexOf(movesToFailure.get(0));
			connectedNodes.get(nextIndex).setFailure(movesToFailure);
		}
	}
	
	public void setAllChildrenLosingMoves() {
		for (PlayNode node : connectedNodes) {
			node.setLosingMove(true);
			node.setAllChildrenLosingMoves();
		}
	}
	
	public void setWinningByChildrenIfNotLosing() {
		if (!this.isLosingMove()) {
			losingByChildren = false;
			if (parent != null) {
				parent.setWinningByChildrenIfNotLosing();
			}
		}
	}
	
	public void setLosingByChildrenToLosing() {
		if (losingByChildren) {
			this.setLosingMove(true);
		}
	}
	
	public PlayNode getPlayNodeFromMoves(List<Move> moves) {
		moves.remove(0);
		if (moves.isEmpty()) {
			return this;
		} else {
			int nextIndex = connectedNodes.indexOf(moves.get(0));
			return connectedNodes.get(nextIndex).getPlayNodeFromMoves(moves);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o.getClass().equals(Move.class)) {
			return move.equals((Move)o);
		} else {
			return this == o;
		}
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public List<Move> getNonFailingMoves() {
		List<Move> nonFailingMoves = new ArrayList<Move>();
		for (PlayNode node : connectedNodes) {
			if (!node.isLosingMove()) {
				nonFailingMoves.add(node.getMove());
			}
		}
		return nonFailingMoves;
	}

	public boolean isLosingMove() {
		return losingMove;
	}

	public void setLosingMove(boolean losingMove) {
		this.losingMove = losingMove;
	}
}

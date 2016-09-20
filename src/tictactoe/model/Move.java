package tictactoe.model;

import tictactoe.controller.tree.PlayNode;

public class Move {
	private int x;
	private int y;
	
	public Move(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public Move getRotatedNinety() {
		int newY = getX();
		int newX = 2 - getY();
		return new Move(newX, newY);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public static Move buildFromString(String input) {
		input = input.substring(1, 4);
		String[] coordinates = input.split(",");
		int moveX = Integer.parseInt(coordinates[0]);
		int moveY = Integer.parseInt(coordinates[1]);
		Move move = new Move(moveX, moveY);
		return move;
	}
	
	@Override
	public String toString() {
		return ("(" + getX() + "," + getY() + ")");
	}
	
	@Override
	public boolean equals(Object o) {
		if (o.getClass().equals(Move.class)) {
			return x == ((Move)o).getX() && y == ((Move)o).getY();
		} else if (o.getClass().equals(PlayNode.class)) {
			return ((PlayNode)o).equals(this);
		} else {
			return this == o;
		}
	}
}

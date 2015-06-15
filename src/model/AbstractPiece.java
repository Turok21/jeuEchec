package model;

public abstract class AbstractPiece implements Pieces {
	private Couleur couleur;
	private Coord coord;

	@Override
	public int getX() {
		return coord.x;
	}

	@Override
	public int getY() {
		return coord.y;
	}

	@Override
	public boolean move(int xFinal, int yFinal) {
		return false;
	}
	
	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	abstract public String getName();

	@Override
	public Couleur getCouleur() {
		return couleur;
	}
}

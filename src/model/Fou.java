package model;

public class Fou extends AbstractPiece {

	public Fou(String name, Couleur couleur, Coord coord) {
		super(name, couleur, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		if(Math.abs(xFinal - yFinal) == Math.abs(getX() - getY())) {
			return true;
		}
		return false;
	}
}

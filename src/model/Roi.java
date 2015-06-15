package model;

public class Roi extends AbstractPiece {

	public Roi(String name, Couleur couleur, Coord coord) {
		super(name, couleur, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		if((xFinal <= getX()-1 || xFinal <= getX()+1) && (yFinal <= getY()-1 || yFinal <= getY()+1)) {
			return true;
		}
		return false;
	}
}
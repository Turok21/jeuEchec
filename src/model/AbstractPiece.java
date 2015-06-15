package model;

public abstract class AbstractPiece implements Pieces {
	private Couleur couleur;
	private Coord coord;
	private String name;
	
	public AbstractPiece(String name, Couleur couleur, Coord coord) {
		this.name = name;
		this.couleur = couleur;
		this.coord = coord;
	}

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
		if(Coord.coordonnees_valides(xFinal, yFinal)) {
			coord.x = xFinal;
			coord.y = yFinal;
			return true;
		}
		return false;		
	}
	
	@Override
	abstract public boolean isMoveOk(int xFinal, int yFinal);

	@Override
	public boolean capture() {
		return false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Couleur getCouleur() {
		return couleur;
	}

	@Override
	public String toString() {
		return "AbstractPiece [couleur=" + couleur + ", coord=" + coord
				+ ", name=" + name + "]";
	}
}

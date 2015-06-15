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
		return coord.coordonnees_valides(xFinal, yFinal);
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
}

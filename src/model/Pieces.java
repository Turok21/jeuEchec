package model;

public interface Pieces {
	int getX();
	int getY();
	boolean isMoveOk(int xFinal, int yFinal);
	boolean capture();
	boolean move(int xFinal, int yFinal);
	String getName();
	Couleur getCouleur();
}
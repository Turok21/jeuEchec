package model;
/**
 * Created by Arié on 15/06/2015.
 */
public class Echiquier {
	public Jeu[] tabJeu;
	private int courant;
	private String message;

	public Echiquier() {
		courant = 0;
		tabJeu = new Jeu[2];
		tabJeu[0] = new Jeu(Couleur.BLANC);
		tabJeu[1] = new Jeu(Couleur.NOIR);
	}

	public Couleur getColorCurrentPlayer() {
		return tabJeu[courant].getCouleur();
	}

	public String getMessage() {
		return this.message;
	}

	private void setMessage(String newMess) {
		this.message = "Joueur " + this.getColorCurrentPlayer() +" <==> "+ newMess;
	}

	public boolean isEchec() {
		return false;//TODO Arie : 
	}

	public boolean isEchecEtMat() {
		return false; // TODO Arie :
	}

	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		if (tabJeu[courant].isPieceHere(xInit, yInit)) {
			if ((Coord.coordonnees_valides(xFinal, yFinal)) && !(xInit == xFinal && yInit == yFinal)) {
				if (tabJeu[courant].isMoveOk(xInit, yInit, xFinal, yFinal)) {
					// todo rajouter condition piece intermédiaire
					if (!(tabJeu[courant].isPieceHere(xFinal, yFinal))) { // si par dessus une de notre piece
						if (tabJeu[(courant + 1) % 2].isPieceHere(xFinal, yFinal)) {
							tabJeu[(courant + 1) % 2].capture(xFinal, yFinal);
							this.setMessage("OK : Le joueur courant mange une piece adverse");
						}
						tabJeu[courant].Move(xInit, yInit, xFinal, yFinal);
						// this.switchJoueur(); // effectue dans observable controler
						this.setMessage("OK : Déplacement effectué correctement.");
						return true;
					} else {
						this.setMessage("KO : Le joueur courant a déja une piece à cet endroit");
					}
				} else {
					this.setMessage("KO : Cette piece ne peux pas bouger de cette facon");
				}
			} else {
				this.setMessage("KO : Coordonnées incorrect ou inchangées");
			}
		} else {
			this.setMessage("KO : Le joueur courant n'as pas de piece à cet endroit");
		}
		//System.out.println("Déplacement impossible");
		return false;
	}

	public void switchJoueur() {
		this.courant = (courant + 1) % 2;
	}

	public String toString() {
		String name, tab = "";
		tab += "\t 0\t 1\t 2\t 3\t 4\t 5\t 6\t 7\n";
		for (int row = 0; row <= 7; row++) {
			tab += row;
			for (int col = 0; col <= 7; col++) {
				name = tabJeu[1].getPieceName(col, row);
				if (name != null) {
					tab += "\t" + name;
				} else {
					name = tabJeu[0].getPieceName(col, row);
					if (name != null) {
						tab += "\t" + name;
					} else {
						tab += "\t____";
					}
				}
			}
			tab += "\n";
		}
		return tab;
	}

	public static void main(String[] args) {
		Echiquier e = new Echiquier();

		e.move(0, 6, 0, 5);
		System.out.println(e);

		e.move(3, 1, 3, 3);
		System.out.println(e);
	}
}
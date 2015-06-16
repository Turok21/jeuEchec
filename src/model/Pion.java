package model;

public class Pion extends AbstractPiece {

    public Pion(String name, Couleur couleur, Coord coordonnees) {
        super(name, couleur, coordonnees);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        if (this.getCouleur() == Couleur.NOIR) {
            if (this.getY() == 1) {
                if ((yFinal == 2 || yFinal == 3)) {
                    return true;
                }
            } else {
                if (this.getY() - yFinal == -1) {
                    return true;
                }
            }
        } else {
            if (this.getY() == 6) {
                if ((yFinal == 4 || yFinal == 5)) {
                    return true;
                }
            } else {
                if (this.getY() - yFinal == 1) {
                    return true;
                }
            }
        }
        return false;
    }

}

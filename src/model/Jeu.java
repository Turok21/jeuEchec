package model;
import tools.ChessPieceFactory;

import java.util.List;
import java.util.ListIterator;

public class Jeu {
    private List<Pieces> piecesList;
    private Couleur couleur;
    
    public Jeu(Couleur couleur) {
        this.couleur = couleur;
        this.piecesList = ChessPieceFactory.newPieces(couleur);
    }
    
    boolean capture(int xCatch, int yCatch) {
        return this.findPiece(xCatch, yCatch).capture();
    }
    
    public Couleur getCouleur() {
        return this.couleur;
    }
    
    private Pieces findPiece(int x, int y) {
        ListIterator<Pieces> listIterator = this.piecesList.listIterator();
        Pieces pieceFind = null;
        while (listIterator.hasNext() && pieceFind == null) {
            Pieces pcs = listIterator.next();
            if (pcs.getX() == x) {
                if (pcs.getY() == y) {
                    pieceFind = pcs;
                }
            }
        }
        return pieceFind;
    }
    
    public Couleur getPieceCouleur(int x, int y) {
        Pieces pieceFind = this.findPiece(x, y);
        if(pieceFind != null){
            return pieceFind.getCouleur();
        } else {
            return null; // piece find is null in this case
        }
    }
    
    public String getPieceName(int x, int y) {
        Pieces pieceFind = this.findPiece(x, y);
        if(pieceFind != null){
            return pieceFind.getName();
        } else {
            return null; // piece find is null in this case
        }
    }
    
    public String getPieceType(int x, int y) {
        Pieces pieceFind = this.findPiece(x, y);
        if(pieceFind != null){
            return pieceFind.getClass().getSimpleName();
        } else {
            return null; // piece find is null in this case
        }
    }
    
    boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
    	if(findPiece(xInit, yInit).isMoveOk(xFinal, yFinal)) {
    		return true;
    	}
    	return false;
    }
    /**
     * Vérifie si il y a une piece sur cette case sur tout l'échiquié
     */
    public boolean isPieceHere(int x, int y) {
        if (findPiece(x, y) == null) {
            return false;
        } else {
            return true;
        }
    }

//    public boolean isPieceToCatchHere(int x, int y) {
//    	
//
//    }

    //public boolean isPieceToMoveHere(int x, int y) {
    //	if(isPieceHere(x, y)) {
    //		return false;
    //	}
    //	return true;
    //}
    
    boolean Move(int xInit, int yInit, int xFinal, int yFinal) {
        Pieces pieceFind = this.findPiece(xInit, yInit);
        if (pieceFind != null) {
            return this.findPiece(xInit, yInit).move(xFinal, yFinal);
        } else {
            return false; // piece find is null in this case
        }
    }
    
    public String toString() {
        return this.piecesList.toString();
    }
    
    public static void main(String[] args) {
        Jeu jeu = new Jeu(Couleur.NOIR);
        //System.out.println(jeu.findPiece(0, 6));
        jeu.Move(4, 1, 4, 3);
        System.out.println(jeu.findPiece(4, 3));
        
        /*System.out.println(jeu.findPiece(3, 1));
        jeu.Move(3, 1, 3, 3);
        System.out.println(jeu.findPiece(3, 3));*/
    }
}
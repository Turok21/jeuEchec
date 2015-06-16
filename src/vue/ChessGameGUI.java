package vue;

import model.Coord;
import model.Couleur;
import model.observable.ChessGame;
import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;

import java.awt.Dimension;
import java.util.Observable;

import javax.swing.*;

import tools.ChessImageProvider;

import java.awt.*;
import java.awt.event.*;

public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener {
    private String titre;
    private ChessGameControlers controller;
    private Dimension dim;

    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;

    private double coefficientX = 1;
    private double coefficientY = 1;

    private Coord coordInitial, coordFinal;
    private JPanel componentDepart;

    public ChessGameGUI(String titre, ChessGameControlers chessGameControler, Dimension dim) {

        super(titre);
        this.initFields(chessGameControler, dim);
        this.setLayout();

    }

    private void initFields(ChessGameControlers chessGameControler, Dimension dim) {
        this.controller = chessGameControler;
        this.dim = dim;
        this.coefficientY = (double) this.dim.getHeight() / 8;
        this.coefficientX = (double) this.dim.getHeight() / 8;

        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(dim);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane

        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(dim);
        chessBoard.setBounds(0, 0, dim.width, dim.height);
    }

    private void setLayout() {
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.BLACK : Color.white);
            else
                square.setBackground(i % 2 == 0 ? Color.white : Color.BLACK);
        }


        int caseDamier = 0;
        JLabel piece;
        JPanel panel;
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                String pieceType = ChessImageProvider.getType(x, y);
                if (pieceType != null) { // si il y a une pièce à cette endroit
                    Couleur pieceCouleur = ChessImageProvider.getCouleur(x, y);
                    String imageUrl = ChessImageProvider.getImageFile(pieceType, pieceCouleur);

                    piece = new JLabel(new ImageIcon(imageUrl));
                    panel = (JPanel) chessBoard.getComponent(caseDamier);
                    panel.add(piece);
                }
                caseDamier++;
            }
        }
    }

    public void setListener() {

    }

    //Move the chess piece around
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    private Coord getCoordonneeFromPoint(MouseEvent e) {
        return new Coord((int) (e.getX() / coefficientX), (int) (e.getY() / coefficientY));
    }

    public void mousePressed(MouseEvent e) {
        this.coordInitial = getCoordonneeFromPoint(e);

        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel) return;


        this.componentDepart = (JPanel) c.getParent();
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();

        chessPiece = (JLabel) c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    //Drop the chess piece back onto the chess board
    public void mouseReleased(MouseEvent e) {
        this.coordFinal = getCoordonneeFromPoint(e);

        if (chessPiece == null) return;
        chessPiece.setVisible(false);

        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

//		System.out.println(coordInitial +" "+coordFinal);
        if (controller.move(coordInitial, coordFinal)) {
            if (c instanceof JLabel) {
                Container parent = c.getParent();
                parent.remove(0);
                parent.add(chessPiece);
            } else {
                Container parent = (Container) c;
                parent.add(chessPiece);
            }
        }
        /*****************************************/
        else {
            this.componentDepart.add(chessPiece);
        }

        chessPiece.setVisible(true);
    }

    /********************************************************/
    public void mouseClicked(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void kingDie() {
        JOptionPane.showMessageDialog(null, "Well Done, playeur " + controller.getColorCurrentPlayer() + " win this match");
    }

    /********************************************************/

    public void update(Observable arg0, Object arg1) {

    }

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        ChessGameControlers chessGameControler = new ChessGameControler(chessGame);
        Dimension dim = new Dimension(600, 600);
        JFrame frame = new ChessGameGUI("Jeu d'echec", chessGameControler, dim);

        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
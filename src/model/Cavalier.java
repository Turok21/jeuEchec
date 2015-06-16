package model;

public class Cavalier extends AbstractPiece{

	public Cavalier(String name, Couleur couleur, Coord coordonnees) {
		super(name,couleur,coordonnees);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		if (Math.abs(xFinal-this.getX())==2)
		{
			if (Math.abs(yFinal-this.getY())==1)
			{
				return true;
			}
		}
		if (Math.abs(yFinal-this.getY())==2)
		{
			if (Math.abs(xFinal-this.getX())==1)
			{
				return true;
			}
		}
		return false;
	}
}

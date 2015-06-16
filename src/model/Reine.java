package model;

public class Reine extends AbstractPiece{

	public Reine(String name, Couleur couleur, Coord coordonnees) {
		super(name,couleur,coordonnees);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		if ((this.getX()==xFinal)&&(this.getY()==yFinal))
		{
			return false;
		}
		if(Math.abs(xFinal-this.getX())==Math.abs(yFinal-this.getY()))
		{
			return true;
		}
		if(xFinal==this.getX())
		{
			if(yFinal!=this.getY())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if(yFinal==this.getY())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}


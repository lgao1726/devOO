package solnon;

import controleur.Controleur;

public class Main 
{
	private final static int dimX = 400;
	private final static int dimY = 400;
	
	/**
	 * Lanceur d'application
	 */
	public static void main(String[] args) 
	{
		// Creation du plan
		Plan plan = new Plan(dimX, dimY);
		
		new Controleur(plan);
	}

}


import modele.Plan;
import controleur.Controleur;

public class Main 
{
	/**
	 * Lanceur d'application
	 */
	public static void main(String[] args) 
	{
		// Creation du plan
		Plan plan = new Plan();
		
		new Controleur(plan);
	}

}

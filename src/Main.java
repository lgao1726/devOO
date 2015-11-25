import tsp.Graphe;
import tsp.GrapheLivraison;
import modele.Noeud;
import modele.Plan;
import modele.Tournee;
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
		
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Tournee tournee=new Tournee(plan);
		tournee.calculTournee(plan, plan.getDemandeLivraisons().getFenetres());
		
	}

}
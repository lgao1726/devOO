import tsp.Graphe;
import tsp.GrapheLivraison;
import vue.Fenetre;
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
		Plan plan = new Plan(dimX, dimY);
		
		Controleur controleur=new Controleur(plan);
		}

}
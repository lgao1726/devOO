package controleur;

import modele.Livraison;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

public interface Etat {
	
	public abstract void chargerPlan(Plan planDeVille, Fenetre fenetre);
	
	public abstract void chargerDemandes(Plan plan, Fenetre fenetre);
	
	public abstract void calculerTournee(Plan plan, Fenetre fenetre);
	
	public abstract void selectionnerLivraison(Livraison livraison);
	
	public abstract void supprimerLivraison(Plan plan, ListeCommandes listeDeCdes);
	
	// TODO Auto-generated method stub
		
	/**public abstract void genererFeuilleDeRoute(Tournee tournee);
	
	public abstract void supprimerLivraison(Tournee tournee, Livraison livraison);
	
	public abstract void deplacerLivraison(Tournee tournee, Livraison livraison, Livraison livraisonPrecedente);
	
	public abstract void ajouterLivraison(Tournee tournee, Plan plan, Noeud noeud, Livraison livraisonPrecedente, FenetreLivraison fenetreLivraison);
	
	public abstract void undo();
	
	public abstract void redo();**/

}

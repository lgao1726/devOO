package controleur;

import modele.DemandeLivraison;
import modele.Plan;
import vue.Fenetre;

public interface Etat {
	
	public void chargerPlan(Plan planDeVille, Fenetre fenetre);
	
	public void chargerDemandes(Plan plan, Fenetre fenetre);
	
	/*public abstract void calculerTournee(Plan plan);
	
	public abstract void genererFeuilleDeRoute(Tournee tournee);
	
	public abstract void supprimerLivraison(Tournee tournee, Livraison livraison);
	
	public abstract void deplacerLivraison(Tournee tournee, Livraison livraison, Livraison livraisonPrecedente);
	
	public abstract void ajouterLivraison(Tournee tournee, Plan plan, Noeud noeud, Livraison livraisonPrecedente, FenetreLivraison fenetreLivraison);
	
	public abstract void undo();
	
	public abstract void redo();**/

}

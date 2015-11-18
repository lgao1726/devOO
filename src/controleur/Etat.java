package controleur;

import model.Livraison;
import model.Plan;

public abstract class Etat {
	
	protected abstract void chargerPlan(planXML planDeVille);
	
	protected abstract void chargerDemandes(demandesXML demandesDeLivraison);
	
	protected abstract void calculerTournee(Plan plan);
	
	protected abstract void genererFeuilleDeRoute(Tournee tournee);
	
	protected abstract void supprimerLivraison(Tournee tournee, Livraison livraison);
	
	protected abstract void deplacerLivraison(Tournee tournee, Livraison livraison, Livraison livraisonPrecedente);
	
	protected abstract void ajouterLivraison(Tournee tournee, Plan plan, Noeud noeud, Livraison livraisonPrecedente, FenetreLivraison fenetreLivraison);
	
	protected abstract void undo();
	
	protected abstract void redo();

}

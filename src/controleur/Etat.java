package controleur;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import modele.Tournee;
import vue.Fenetre;

public interface Etat {
	
	public abstract void chargerPlan(Plan planDeVille, Fenetre fenetre);
	
	public abstract void chargerDemandes(Plan plan, Fenetre fenetre);
	
	public abstract void calculerTournee(Plan plan, Fenetre fenetre);
	
	public abstract void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre);
	
	public abstract void supprimerLivraison(Plan plan, ListeCommandes listeDeCdes, Fenetre fenetre);
	
	public abstract void echangerLivraison(Plan plan, ListeCommandes listeDeCdes, Fenetre fenetre);

	public abstract void undo(ListeCommandes listeDeCdes);
	
	public abstract void redo(ListeCommandes listeDeCdes);

	public abstract void selectionnerNoeud(Noeud noeud, Fenetre fenetre);

	public abstract void ajouterLivraison(Fenetre fenetre);
		
	public abstract void annuler(Fenetre fenetre);

	public abstract void valider(Fenetre fenetre);

	public abstract void deselectionner(Fenetre fenetre);
	
	//public abstract void genererFeuilleDeRoute(Tournee tournee);
		
	//public abstract void deplacerLivraison(Plan plan, Livraison livraison, Livraison livraisonPrecedente);
		
	
	

}

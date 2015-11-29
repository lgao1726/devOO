package controleur;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import vue.Fenetre;


public class EtatDefaut implements Etat {

	@Override
	public void chargerPlan(Plan planDeVille, Fenetre fenetre)
	{}

	@Override
	public void chargerDemandes(Plan plan, Fenetre fenetre)
	{}


	@Override
	public void calculerTournee(Plan plan, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void selectionnerLivraison(Plan plan,Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void supprimerLivraison(Plan plan, ListeCommandes listeDeCdes, Fenetre fenetre){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void echangerLivraison(Plan plan, ListeCommandes listeDeCdes, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void undo(ListeCommandes listeDeCdes){
		
	}

	public void redo(ListeCommandes listeDeCdes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectionnerNoeud(Plan plan, Noeud noeud, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterLivraison(Plan plan, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void annuler(Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valider(Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Noeud deselectionner(Fenetre fenetre) {
		return null;
		
	}

	@Override
	public void genererFeuilleDeRoute(Plan plan) {
		// TODO Auto-generated method stub
		
	}
	
/**	@Override
	protected void genererFeuilleDeRoute(Tournee tournee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void supprimerLivraison(Tournee tournee, Livraison livraison) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deplacerLivraison(Tournee tournee, Livraison livraison,
			Livraison livraisonPrecedente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void ajouterLivraison(Tournee tournee, Plan plan, Noeud noeud,
			Livraison livraisonPrecedente, FenetreLivraison fenetreLivraison) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void redo() {
		// TODO Auto-generated method stub
		
	}**/

}

package controleur;

import model.DemandeLivraison;
import model.FenetreLivraison;
import model.Livraison;
import model.Noeud;
import model.Plan;
import model.Tournee;

public class EtatDefaut extends Etat{

	@Override
	protected void chargerPlan(Plan planDeVille) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void chargerDemandes(DemandeLivraison demandesDeLivraison) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void calculerTournee(Plan plan) {
		// TODO Auto-generated method stub
		
	}

	@Override
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
		
	}

}

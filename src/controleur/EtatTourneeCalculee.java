package controleur;

import modele.Livraison;
import modele.Plan;
import vue.Fenetre;

public class EtatTourneeCalculee extends EtatDefaut{
	
	/**
	 * M�thode qui charger un plan
	 * @param Plan de ville
	 * @param Fenetre
	 * @throws ExceptionEtat
	 */
	@Override
	public void chargerPlan(Plan plan, Fenetre fenetre)
	{
		Controleur.etatInit.chargerPlan(plan, fenetre);
	}
	/**
	 * M�thode qui charge les demande des livraision et qui passe vers l'�tat LivraisonCharger
	 * @param Plan
	 * @param DemandeLivraison
	 */
	@Override
	public void chargerDemandes(Plan plan, Fenetre fenetre) 
	{
		Controleur.etatPlanCharge.chargerDemandes(plan, fenetre);
	}
	
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre) {
		// TODO Auto-generated method stub
		Controleur.etatLivraisonSelectionnee.setLivraison(livraison);
		Controleur.setEtatCourant(Controleur.etatLivraisonSelectionnee);
	}
	
	@Override
	public void undo(ListeCommandes listeDeCdes) {
		listeDeCdes.undo();
	}
	
	@Override
	public void redo(ListeCommandes listeDeCdes) {
		listeDeCdes.redo();
	}
	
	@Override
	public void ajouterLivraison(Fenetre fenetre) {
		Controleur.setEtatCourant(Controleur.etatModeAjout);
		fenetre.afficheMessage("Cliquer sur un noeud pour selectionner le lieu de votre livraison");
	}

}

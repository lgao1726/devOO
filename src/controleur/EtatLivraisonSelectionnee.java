package controleur;

import vue.Fenetre;
import modele.Livraison;
import modele.Plan;

public class EtatLivraisonSelectionnee extends EtatDefaut{
	
	Livraison livraison;
	
	public EtatLivraisonSelectionnee(){
		// TODO Auto-generated constructor stub
	}
	
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
	
	public void supprimerLivraison(Plan plan, ListeCommandes listeDeCdes, Fenetre fenetre){
		if(plan.getDemandeLivraisons().getTournee().getLivraisonPrecedente(livraison)!=null)
		{
			listeDeCdes.ajoute(new CommandeSupprimer(plan, livraison));
			Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		}
		else
			fenetre.afficheMessageBox("Vous essayez de supprimer l'entrepot");
	}
	
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes) {
		Controleur.etatLivraisonSelectionnee.setLivraison(livraison);
	}
	
	public void setLivraison(Livraison liv){
		this.livraison = liv;
	}	
	
	@Override
	public void undo(ListeCommandes listeDeCdes) {
		listeDeCdes.undo();
	}
	
	@Override
	public void redo(ListeCommandes listeDeCdes) {
		listeDeCdes.redo();
	}

}

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
	 * Mï¿½thode qui charger un plan
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
	 * Mï¿½thode qui charge les demande des livraision et qui passe vers l'ï¿½tat LivraisonCharger
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
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre) {
		Controleur.etatLivraisonSelectionnee.setLivraison(livraison);
		fenetre.afficheMessage("Vous avez selectionné la livraison ("+livraison.getId()+") à l'adresse ("+livraison.getAdresse().getId()+")");
	}
	
	@Override
	public void ajouterLivraison(Fenetre fenetre) {
		Controleur.setEtatCourant(Controleur.etatModeAjout);
		fenetre.afficheMessage("Cliquer sur un noeud pour selectionner le lieu de votre livraison");
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
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
	}
	
	public void setLivraison(Livraison liv){
			this.livraison = liv;
		}	
}

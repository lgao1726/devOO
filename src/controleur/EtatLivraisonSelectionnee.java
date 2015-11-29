package controleur;

import vue.Fenetre;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class EtatLivraisonSelectionnee extends EtatDefaut{
	
	Livraison livraison;
	
	public EtatLivraisonSelectionnee(){
	}
	
	/**
	 * Methode qui charger un plan
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
	 * Methode qui charge les demande des livraision et qui passe vers l'ï¿½tat LivraisonCharger
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
		}
		else
		{
			fenetre.afficheMessageBox("Vous essayez de supprimer l'entrepot");
		}
	}
	
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre) {
		Controleur.etatLivraisonSelectionnee.setLivraison(livraison);
		fenetre.afficheMessage("Vous avez selectionné la livraison ("+livraison.getId()+") à l'adresse ("+livraison.getAdresse().getId()+")");
	}
	
	@Override
	public void ajouterLivraison(Plan plan, Fenetre fenetre) {
		plan.updatePlan();
		Controleur.setEtatCourant(Controleur.etatModeAjout);
		fenetre.afficheMessage("Cliquer sur un noeud pour selectionner le lieu de votre livraison");
	}
	
	@Override
	public void echangerLivraison(Plan plan, ListeCommandes listeDeCdes, Fenetre fenetre) {
		plan.updatePlan();
		Controleur.setEtatCourant(Controleur.etatModeEchange);
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
	
	@Override
	public Noeud deselectionner(Fenetre fenetre){
		Noeud noeud=livraison.getAdresse();
		livraison=null;
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		return noeud;
	}

	@Override
	public void genererFeuilleDeRoute(Plan plan){
		plan.getDemandeLivraisons().genererFeuilleDeRoute(plan);
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
	}
	
	public void setLivraison(Livraison liv){
			this.livraison = liv;
		}	
}

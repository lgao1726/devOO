/**
 * 
 */
package controleur;

import modele.Plan;
import vue.Fenetre;

/**
 * Etat des les livraisons chargées
 * @author interCorp
 *
 */
public class EtatLivraisonCharge extends EtatDefaut 
{
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
	 * MÃ¯Â¿Â½thode qui charge les demande des livraision et qui passe vers l'Ã¯Â¿Â½tat LivraisonCharger
	 * @param Plan
	 * @param DemandeLivraison
	 */
	@Override
	public void chargerDemandes(Plan plan, Fenetre fenetre) 
	{
		Controleur.etatPlanCharge.chargerDemandes(plan, fenetre);
	}
	
	@Override
	public void calculerTournee(Plan plan, Fenetre fenetre)
	{
		plan.getDemandeLivraisons().calculerTournee(plan);
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		plan.updatePlan();
		fenetre.afficheMessage("TournÃ©e calculÃ©e avec succÃ¨s");
		fenetre.activerUndoRedoGenerer();
		fenetre.activerModification();
	}
}

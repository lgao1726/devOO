/**
 * 
 */
package controleur;

import modele.Plan;
import vue.Fenetre;

/**
 * Etat des les livraisons charg�es
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
	public void calculerTournee(Plan plan, Fenetre fenetre) {
		plan.getDemandeLivraisons().calculerTournee(plan);
		plan.updatePlan();
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("Tourn�e calculer avec succ�s");
	}
	
	
}

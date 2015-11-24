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
	 * M�thode qui charge les demande des livraision et qui passe vers l'�tat LivraisonCharger
	 * @param Plan
	 * @param DemandeLivraison
	 */
	@Override
	public void chargerDemandes(Plan plan, Fenetre fenetre) 
	{
		Controleur.etatPlanCharge.chargerDemandes(plan, fenetre);
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
}

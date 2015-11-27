/**
 * 
 */
package controleur;

import java.util.Calendar;
import java.util.List;

import modele.FenetreLivraison;
import modele.Livraison;
import modele.Plan;
import modele.Tournee;
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
		plan.updatePlan();
	}
	
	@Override
	public void calculerTournee(Plan plan, Fenetre fenetre) {
		Controleur.setTournee(plan.getDemandeLivraisons().calculerTournee(plan));
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		plan.updatePlan();
			
		
		
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

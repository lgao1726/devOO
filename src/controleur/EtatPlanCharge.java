package controleur;

import vue.Fenetre;
import xml.DeserialiseurXML;
import modele.Plan;

public class EtatPlanCharge extends EtatDefaut
{	
	/**
	 * Méthode qui charge les demande des livraision et qui passe vers l'état LivraisonCharger
	 * @param Plan
	 * @param DemandeLivraison
	 */
	@Override
	public void chargerDemandes(Plan plan, Fenetre fenetre) 
	{
		try 
		{
			if (DeserialiseurXML.chargerDemandeLivraison(plan))
			
				Controleur.setEtatCourant(Controleur.etatLivraisonCharge);
			
		} 
		catch (Exception e) 
		{
			fenetre.afficheMessageBox(e.getMessage());
			plan.setDemandeLivraisons(null);
		}
	}
	
	/**
	 * Méthode qui charger un plan
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

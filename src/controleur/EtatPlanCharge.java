package controleur;

import vue.Fenetre;
import xml.DeserialiseurXML;
import modele.Plan;

public class EtatPlanCharge extends EtatDefaut
{	
	/**
	 * M�thode qui charge les demande des livraision et qui passe vers l'�tat LivraisonCharger
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
			
			Controleur.setEtatCourant(Controleur.etatLivraisonCharge);
			//temporaire pour faciliter le developpement de la vue	
			plan.getDemandeLivraisons().calculerTournee(plan);
	        plan.notifyObservers();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fenetre.afficheMessageBox("Erreur au niveau de chargement du demande des livraisons");
			plan.setDemandeLivraisons(null);
		}
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

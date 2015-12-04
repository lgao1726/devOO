package controleur;

import vue.Fenetre;
import xml.DeserialiseurXML;
import modele.Plan;

public class EtatInit extends EtatDefaut
{
	/**
	 * Méthode qui charger un plan
	 * @param Plan de ville
	 * @param Fenetre
	 * @throws ExceptionEtat
	 */
	@Override
	public void chargerPlan(Plan plan, Fenetre fenetre)
	{
		try 
		{
			if(DeserialiseurXML.traiterPlan(plan))
			{
				plan.notifyObservers();
				Controleur.setEtatCourant(Controleur.etatPlanCharge);
				fenetre.activerChargementLivraison();
				fenetre.afficheMessage("Vous pouvez désormais charger une livraison");
			}
		} 
		catch (Exception e) 
		{
			fenetre.afficheMessageBox(e.getMessage());
			fenetre.afficheMessage("Ressayez de charger un plan");
			plan.reset();
		}
	}
	
}

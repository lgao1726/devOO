package controleur;

import vue.Fenetre;
import xml.ExceptionXML;
import xml.DeserialiseurXML;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
			if (DeserialiseurXML.traiterPlan(plan))
			
				Controleur.setEtatCourant(Controleur.etatPlanCharge);
			
		} 
		catch (Exception e) 
		{
			fenetre.afficheMessageBox(e.getMessage());
			// A changé jusqu'au on implémentra liste des commandes
			plan.reset(0);
		}
	}
	
}
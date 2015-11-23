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
	public void chargerPlan(Plan planDeVille, Fenetre fenetre)
	{
		try 
		{
			DeserialiseurXML.traiterPlan(planDeVille);
			
			Controleur.setEtatCourant(Controleur.etatPlanCharge);
			
		} 
		catch (Exception e) 
		{
			fenetre.afficheMessageBox("Erreur au niveau de chargement de plan");
			planDeVille.reset(0);
		}
	}
	
}

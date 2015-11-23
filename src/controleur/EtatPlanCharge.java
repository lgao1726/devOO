package controleur;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import vue.Fenetre;
import xml.DeserialiseurXML;
import xml.ExceptionXML;
import modele.DemandeLivraison;
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
			
		} 
		catch (Exception e) 
		{
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

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
	 * Méthode qui charge les demande des livraision et qui passe vers l'état LivraisonCharger
	 * @param Plan
	 * @param DemandeLivraison
	 */
	@Override
	public void chargerDemandes(Plan plan, Fenetre fenetre) 
	{
		try 
		{
			DeserialiseurXML.chargerDemandeLivraison(plan);
			
			Controleur.setEtatCourant(Controleur.etatLivraisonCharge);
			
		} 
		catch (Exception e) 
		{
			fenetre.afficheMessageBox("Erreur au niveau de chargement du demande des livraisons");
			plan.setDemandeLivraisons(null);
		}
	}
	
	
}

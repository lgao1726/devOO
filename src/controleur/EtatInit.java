package controleur;

import vue.Fenetre;
import xml.ExceptionXML;
import xml.DeserialiseurXML;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Plan;

public class EtatInit extends EtatDefaut{

	public  void chargerPlan(Plan planDeVille, Fenetre fenetre)
	{
		try {
			DeserialiseurXML.traiterPlan(planDeVille);
			Controleur.setEtatCourant(Controleur.etatPlanCharge);
		} catch (ParserConfigurationException 
				| SAXException | IOException 
				| ExceptionXML | NumberFormatException e) {
			//fenetre.afficheMessage(e.getMessage());
			//plan.reset(largeur, hauteur);
		}
	}
}
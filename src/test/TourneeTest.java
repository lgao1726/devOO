package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Itineraire;
import modele.Noeud;
import modele.Plan;
import modele.Tournee;
import modele.UsineNoeud;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.sun.javafx.css.CalculatedValue;

import xml.DeserialiseurXML;
import xml.ExceptionXML;

public class TourneeTest extends DeserialiseurXML{
	
	SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
	
	File xmlLivraison = null;
	File xmlPlan = null;

	@Test
	public void testSupprimerLivraison() {
		xmlPlan = new File("src/test/testXML/plan_tournee.xml");
		xmlLivraison = new File("src/test/testXML/livraison_tournee.xml");
		
		Plan plan = new Plan();
		try {
			traitementPlan(plan, xmlPlan);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			traitementDemandeLivraison(plan, xmlLivraison);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Tournee tournee = new Tournee();
		tournee.calculerTournee(plan, plan.getDemandeLivraisons().getFenetres());
	}

	@Test
	public void testEchangerLivraison() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjouterLivraison() {
		fail("Not yet implemented");
	}

	@Test
	public void testMiseAJourCout() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLivraisonPrecedente() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenererFeuille() {
		fail("Not yet implemented");
	}

}

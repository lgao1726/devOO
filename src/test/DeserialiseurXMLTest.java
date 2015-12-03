/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import modele.Troncon;
import modele.UsineNoeud;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xml.DeserialiseurXML;
import xml.ExceptionXML;
import xml.OuvreurDeFichierXML;

/**
 * @author Aiebobo
 *
 */
public class DeserialiseurXMLTest extends DeserialiseurXML{

	Plan plan;
	File xmlPlan;
	File xmlLivraison;
	SimpleDateFormat formater =  new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * Test method for {@link xml.DeserialiseurXML#traitementFichier(modele.Plan, java.io.File)}.
	 */
	@Before
	public void setUp(){
		
		plan = new Plan();
	}
	
	/**
	 * Test method for {@link xml.DeserialiseurXML#getEntrepot(org.w3c.dom.Element, modele.Plan)}.
	 */
	@Test
	public void testGetEntrepot() {
		xmlLivraison = new File("src/test/testXML/livraison10x10-1.xml");
		xmlPlan = new File("src/test/testXML/plan10x10.xml");
		Plan planManu = new Plan();
		
		try {
			DeserialiseurXML.traitementPlan(planManu, xmlPlan);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		planManu.setAdresseEntrepot(planManu.getIntersections().get(41));
		
		plan = planManu;
        DocumentBuilder docBuilder;
        Document document = null;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			document = docBuilder.parse(xmlLivraison);
	        Element racine = document.getDocumentElement();
			DeserialiseurXML.getEntrepot(racine, plan);
		} catch (SAXException | IOException | ParserConfigurationException | NumberFormatException | ExceptionXML e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(plan, planManu);
	}


	/**
	 * Test method for {@link xml.DeserialiseurXML#creerLivraison(org.w3c.dom.Element)}.
	 */
	@Test
	public void testCreerLivraison() {
		
		File xml = new File("src/test/testXML/livraison_1.xml");
		
        DocumentBuilder docBuilder;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document document = docBuilder.parse(xml);
	        Element racine = document.getDocumentElement();
	        NodeList elt = racine.getElementsByTagName("Livraison");
	        Calendar heureDebut = new GregorianCalendar();
	        Calendar heureFin = new GregorianCalendar();
	        
	        heureDebut.setTime(formater.parse("08:00:00"));
	        heureFin.setTime(formater.parse("10:00:00"));
	        
	        UsineNoeud.initPointFactory(1);
	        UsineNoeud.creeNoeud(0,10,10);
	        
	        Livraison autoLiv = creerLivraison((Element) elt.item(0), heureDebut, heureFin);
			Livraison manuLiv = new Livraison(1, UsineNoeud.getNoeud(0), 611, heureDebut, heureFin);
			assertEquals(autoLiv.getAdresse(), manuLiv.getAdresse());
			assertEquals(autoLiv.getId(), manuLiv.getId());
			assertEquals(autoLiv.getClient(), manuLiv.getClient());
			assertEquals(autoLiv.getHeureDebut(), manuLiv.getHeureDebut());
			assertEquals(autoLiv.getHeureFin(), manuLiv.getHeureFin());
			assertEquals(autoLiv.getHeurePassage(), manuLiv.getHeurePassage());
	        
		} catch (ParserConfigurationException | SAXException | IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * Test method for {@link xml.DeserialiseurXML#creerPlage(org.w3c.dom.Element)}.
	 */
	@Test
	public void testCreerPlage() {
		File xml = new File("src/test/testXML/plage_1.xml");
		
        DocumentBuilder docBuilder;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document document = docBuilder.parse(xml);
	        Element racine = document.getDocumentElement();
	        NodeList elt = racine.getElementsByTagName("Plage");
	        Calendar heureDebut = new GregorianCalendar();
	        Calendar heureFin = new GregorianCalendar();
	        
	        heureDebut.setTime(formater.parse("08:00:00"));
	        heureFin.setTime(formater.parse("10:00:00"));
	        
	        UsineNoeud.initPointFactory(1);
	        UsineNoeud.creeNoeud(0,10,10);
	        
	        FenetreLivraison autoLiv = creerPlage((Element) elt.item(0));
			FenetreLivraison manuLiv = new FenetreLivraison( heureDebut, heureFin);
			assertEquals(autoLiv.getHeureDebut(), manuLiv.getHeureDebut());
			assertEquals(autoLiv.getHeureFin(), manuLiv.getHeureFin());
	        
		} catch (ParserConfigurationException | SAXException | IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * Test method for {@link xml.DeserialiseurXML#creerNoeud(org.w3c.dom.Element)}.
	 */
	@Test
	public void testCreerNoeud() {
		
		File xml = new File("src/test/testXML/noeud_1.xml");
		
        DocumentBuilder docBuilder;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document document = docBuilder.parse(xml);
	        Element racine = document.getDocumentElement();
	        NodeList elt = racine.getElementsByTagName("Noeud");
	        
	        UsineNoeud.initPointFactory(1);
	        
	        Noeud autoLiv = creerNoeud((Element) elt.item(0));
			Noeud manuLiv = new Noeud(0, 63, 100);
			assertEquals(autoLiv.getId(), manuLiv.getId());
			assertEquals(autoLiv.getX(), manuLiv.getX());
			assertEquals(autoLiv.getY(), manuLiv.getY());
	        
		} catch (ParserConfigurationException | SAXException | IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	/**
	 * Test method for
	 * {@link xml.DeserialiseurXML#creerTroncon(org.w3c.dom.Element)}.
	 */
	@Test
	public void testCreerTroncon() {
		
		File xml = new File("src/test/testXML/troncon_1.xml");
		
        DocumentBuilder docBuilder;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document document = docBuilder.parse(xml);
	        Element racine = document.getDocumentElement();
	        NodeList elt = racine.getElementsByTagName("LeTronconSortant");
	        
	        Troncon autoLiv = creerTroncon((Element) elt.item(0));
			Troncon manuLiv = new Troncon((float) 3.9, (float) 601.1, "v0", 1);
			assertEquals(autoLiv.getIdNoeudDestination(), manuLiv.getIdNoeudDestination());
			assertEquals(autoLiv.getNomRue(), manuLiv.getNomRue());
			
			
		} catch (ParserConfigurationException | SAXException | IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}

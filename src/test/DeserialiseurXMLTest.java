/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modele.Noeud;
import modele.Plan;
import modele.Troncon;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
	
	/**
	 * Test method for {@link xml.DeserialiseurXML#traitementFichier(modele.Plan, java.io.File)}.
	 */
	@Before
	public void setUp(){
		
		Plan plan = new Plan();
	}
	
	/**
	 * Test method for {@link xml.DeserialiseurXML#traitementFichier(modele.Plan, java.io.File)}.
	 */
	@Test
	public void testTraitementFichier() {
		xml = new File("src/lib/plan_test.xml");
		
		/*
		Plan planManu = new Plan();
		
		Noeud noeud1 = new Noeud(0, 63, 100);
		Noeud noeud2 = new Noeud(1, 88, 171);
		
		noeud1.ajouterTroncon(new Troncon((float) 3.9, (float) 602.1, "v0", 1));
		noeud2.ajouterTroncon(new Troncon((float) 4.1, (float) 602.1, "v0", 0));
		
		planManu.ajouterNoeud(noeud1);
		planManu.ajouterNoeud(noeud2);
		
        planManu.setDimX(93);
        planManu.setDimY();
		
		Plan planAuto = new Plan();
		try {
			DeserialiseurXML.traitementFichier(planAuto, xml);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}

	/**
	 * Test method for {@link xml.DeserialiseurXML#chargerDemandeLivraison(modele.Plan)}.
	 */
	@Test
	public void testChargerDemandeLivraison() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link xml.DeserialiseurXML#getEntrepot(org.w3c.dom.Element, modele.Plan)}.
	 */
	@Test
	//TODO NETTOYER
	public void testGetEntrepot() {
		xmlLivraison = new File("src/testXML/livraison10x10-1.xml");
		xmlPlan = new File("src/testXML/plan10x10.xml");
		Plan planManu = new Plan();
		
		try {
			DeserialiseurXML.traitementFichier(planManu, xmlPlan);
		} catch (ParserConfigurationException | SAXException | IOException
				| ExceptionXML e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(planManu.getIntersections());
		
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
	 * Test method for {@link xml.DeserialiseurXML#contruireFenetresLivraison(org.w3c.dom.Element, modele.DemandeLivraison)}.
	 */
	@Test
	public void testContruireFenetresLivraison() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link xml.DeserialiseurXML#creerLivraison(org.w3c.dom.Element)}.
	 */
	@Test
	public void testCreerLivraison() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link xml.DeserialiseurXML#creerPlage(org.w3c.dom.Element)}.
	 */
	@Test
	public void testCreerPlage() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link xml.DeserialiseurXML#construireAPartirDeDOMXML(org.w3c.dom.Element, modele.Plan)}.
	 */
	@Test
	public void testConstruireAPartirDeDOMXML() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link xml.DeserialiseurXML#creerNoeud(org.w3c.dom.Element)}.
	 */
	@Test
	public void testCreerNoeud() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link xml.DeserialiseurXML#creerTroncon(org.w3c.dom.Element)}.
	 */
	@Test
	public void testCreerTroncon() {
		fail("Not yet implemented");
	}

}

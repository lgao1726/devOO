package xml;

import model.Noeud;
import model.Plan;
import model.Troncon;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DeserialiseurXML {
	/**
	 * Ouvre un fichier xml et cree plan a partir du contenu du fichier
	 * @param plan
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionXML
	 */
	public static void traiterPlan(Plan plan) throws ParserConfigurationException, SAXException, IOException, ExceptionXML{
		File xml = OuvreurDeFichierXML.getInstance().ouvre(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        if (racine.getNodeName().equals("Reseau")) {
           construireAPartirDeDOMXML(racine, plan);
           
        }
        else
        	throw new ExceptionXML("Document non conforme");
	}

    private static void construireAPartirDeDOMXML(Element noeudDOMRacine, Plan plan) throws ExceptionXML, NumberFormatException{
        
       	NodeList listeNoeuds = noeudDOMRacine.getElementsByTagName("Noeud");
       	int maxX = 0, maxY = 0;
       	for (int i = 0; i < listeNoeuds.getLength(); i++) {
       		Element noeudActuel = (Element) listeNoeuds.item(i);
       		Noeud noeud = creerNoeud(noeudActuel);
       		int x = Integer.parseInt(noeudActuel.getAttribute("x"));
       		int y = Integer.parseInt(noeudActuel.getAttribute("y"));
       		if(x > maxX) maxX = x;
       		if(y > maxY) maxY = y;
       		
           	NodeList listeTroncons = noeudActuel.getElementsByTagName("LeTronconSortant");
           	for (int j = 0; j < listeTroncons.getLength(); j++){
           		noeud.ajouterTroncon(creerTroncon((Element) listeTroncons.item(j)));
           	}
           	plan.ajouterNoeud(noeud);
       	}
        plan.setDimX(maxX);
        plan.setDimY(maxY);
    }
    
    private static Noeud creerNoeud(Element elt) throws ExceptionXML{
   		int x = Integer.parseInt(elt.getAttribute("x"));
   		int y = Integer.parseInt(elt.getAttribute("y"));
   		int id = Integer.parseInt(elt.getAttribute("id"));
   		return new Noeud(id, x, y);
    }
    
    private static Troncon creerTroncon(Element elt) throws ExceptionXML{
   		int idNoeudDestination = Integer.parseInt(elt.getAttribute("idNoeudDestination"));
   		String nomRue = elt.getAttribute("nomRue");
   		float vitesse = 0;
   		float longueur = 0;
   		
   		
   		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
   		symbols.setDecimalSeparator(',');
   		DecimalFormat format = new DecimalFormat("#,#");
   		format.setDecimalFormatSymbols(symbols);
   		try {
			vitesse = format.parse(elt.getAttribute("vitesse")).floatValue();
			longueur = format.parse(elt.getAttribute("longueur")).floatValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		
//   		float vitesse = Float.parseFloat(elt.getAttribute("vitesse"));
//   		float longueur = Float.parseFloat(elt.getAttribute("longueur"));
   		if (longueur <= 0)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Longueur négative");
   		if (vitesse <= 0)
   			throw new ExceptionXML("Erreur lors de la lecture du fichier : Vitesse négative");
   		return new Troncon(vitesse, longueur, nomRue, idNoeudDestination);
    }
 
}

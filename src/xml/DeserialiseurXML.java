package xml;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import modele.Troncon;
import modele.UsineNoeud;


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
        if (racine.getNodeName().equals("Reseau")) 
        {
           construireAPartirDeDOMXML(racine, plan);
           
        }
        else
        	throw new ExceptionXML("Document non conforme");
	}
	
	/**
	 * Ouvre un fichier xml et cree une demande de livraison a partir du contenu du fichier
	 * @param demandeLivraison
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws ExceptionXML
	 */
	public static void chargerDemandeLivraison(Plan plan) throws ParserConfigurationException, SAXException, 
																	   IOException, ExceptionXML
    {
		File xml = OuvreurDeFichierXML.getInstance().ouvre(true);
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(xml);
        Element racine = document.getDocumentElement();
        
        DemandeLivraison demande = new DemandeLivraison();
        
        if (racine.getNodeName().equals("JourneeType")) 
        {
           getEntrepot(racine, plan);
           contruireFenetresLivraison(racine, demande);
           
           plan.setDemandeLivraisons(demande);
           plan.notifyObservers();
        }
        else
        	throw new ExceptionXML("Document non conforme");
	}
	
	private static void getEntrepot(Element noeudDOMRacine, Plan plan) throws NumberFormatException, ExceptionXML
	{    
       	int adresseEntrepot = Integer.parseInt(((Element)noeudDOMRacine.getElementsByTagName("Entrepot").item(0)).getAttribute("adresse"));
       	
       	System.out.println(adresseEntrepot);
       	
       	Noeud entrepotNoeud = UsineNoeud.getNoeud(adresseEntrepot);
       	
       	if (entrepotNoeud != null)
       		
       		plan.setAdresseEntrepot(entrepotNoeud);
       	
       	else
       		
       		throw new ExceptionXML("L'adresse de l'entrepot est introuvable");
    }
	
	private static void contruireFenetresLivraison(Element noeudDOMRacine, DemandeLivraison demande) throws ExceptionXML, NumberFormatException{
        
       	NodeList listeNoeuds = noeudDOMRacine.getElementsByTagName("Plage");
       	
       	for (int i = 0; i < listeNoeuds.getLength(); i++) 
       	{
       		Element plageActuel = (Element) listeNoeuds.item(i);
       		
       		FenetreLivraison plage = creerPlage(plageActuel);
       		
           	NodeList listeLivraison = plageActuel.getElementsByTagName("Livraison");
           	
           	for (int j = 0; j < listeLivraison.getLength(); j++)
           	{
           		plage.ajouterLivraison(creerLivraison((Element) listeLivraison.item(j)));
           	}
           	
           	demande.ajouterFenetre(plage);
       	}
       	
    }
	
	private static Livraison creerLivraison(Element elt) throws ExceptionXML
	{
		int id = Integer.parseInt(elt.getAttribute("id"));
		int client = Integer.parseInt(elt.getAttribute("client"));
		int adresse = Integer.parseInt(elt.getAttribute("adresse"));
		
		Noeud livraisonNoeud = UsineNoeud.getNoeud(adresse);
		
		if (livraisonNoeud == null)
			
			throw new ExceptionXML("L'adresse du livraison n'existe pas sur le plan");
			
		
		return new Livraison(id, livraisonNoeud, client);
    }
	
	private static FenetreLivraison creerPlage(Element elt) throws ExceptionXML
	{
		SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
   		
   		Date debut;
   		Date fin;
   		
		try 
		{
			debut = formater.parse(elt.getAttribute("heureDebut"));
			fin = formater.parse(elt.getAttribute("heureFin"));
		} 
		catch (ParseException e) 
		{
			throw new ExceptionXML("Format de l'heure incorrecte");
		}
   		
   		return new FenetreLivraison(debut, fin);
    }

    private static void construireAPartirDeDOMXML(Element noeudDOMRacine, Plan plan) throws ExceptionXML, NumberFormatException{
        
       	NodeList listeNoeuds = noeudDOMRacine.getElementsByTagName("Noeud");
       	
       	int nbNoeuds = listeNoeuds.getLength();
       	int maxX = 0, maxY = 0;
       	
       	UsineNoeud.initPointFactory(nbNoeuds);
       	
       	for (int i = 0; i < nbNoeuds; i++) 
       	{
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
       	
        plan.setDimX(maxX+5);
        plan.setDimY(maxY+5);
        plan.notifyObservers();
    }
    
    private static Noeud creerNoeud(Element elt) throws ExceptionXML{
   		int x = Integer.parseInt(elt.getAttribute("x"));
   		int y = Integer.parseInt(elt.getAttribute("y"));
   		int id = Integer.parseInt(elt.getAttribute("id"));
   		return UsineNoeud.creeNoeud(id, x, y);
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

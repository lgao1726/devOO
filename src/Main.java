//package outils;


import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controleur.Controleur;
import modele.Plan;
import xml.DeserialiseurXML;
import xml.ExceptionXML;
import xml.ExceptionXML;
import xml.OuvreurDeFichierXML;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Plan plan = new Plan();
		Controleur controleur = new Controleur(plan,1);
		controleur.chargerPlan();
			System.out.println("X : " + plan.getDimX());
			System.out.println("Y : "+plan.getDimY());
			System.out.println("Noeuds :" + plan.getIntersections().size());
			System.out.println(plan.getIntersections().get(3).getListeTronconsSortants().get(0).getNomRue());
			
		}

}

<<<<<<< HEAD
//package outils;
=======
>>>>>>> 54a2fa4cabddc63be953bdf6b04d4eb151ec630e

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.Plan;
import xml.DeserialiseurXML;
import xml.ExceptionXML;

import xml.ExceptionXML;
import xml.OuvreurDeFichierXML;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
<<<<<<< HEAD
		OuvreurDeFichierXML ouvreurDeFichierXML = OuvreurDeFichierXML.getInstance();
=======
		DeserialiseurXML deserialiseurXML = new DeserialiseurXML();
		Plan plan = new Plan();
>>>>>>> 54a2fa4cabddc63be953bdf6b04d4eb151ec630e
		try {
			deserialiseurXML.traiterPlan(plan);
			System.out.println("X : " + plan.getDimX());
			System.out.println("Y : "+plan.getDimY());
			System.out.println("Noeuds :" + plan.getIntersections().size());
			System.out.println(plan.getIntersections().get(3).getListeTronconsSortants().get(0).getNomRue());
		} catch (ExceptionXML e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

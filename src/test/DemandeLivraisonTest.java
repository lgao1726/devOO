package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Tournee;

import org.junit.Before;
import org.junit.Test;

public class DemandeLivraisonTest {
	
	SimpleDateFormat formater;
	
	@Before
	public void setUp() {
		formater = new SimpleDateFormat("HH:mm:ss");
	}

	@Test
	public void testGetFenetre() {
		
		DemandeLivraison dLivraison = new DemandeLivraison();
		
		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();
		Calendar date3 = new GregorianCalendar();
		Calendar date4 = new GregorianCalendar();
		Calendar date5 = new GregorianCalendar();
		
		
		try {
			date1.setTime(formater.parse("13:00:00"));
			date2.setTime(formater.parse("15:00:00"));
			date3.setTime(formater.parse("08:00:00"));
			date4.setTime(formater.parse("12:00:00"));
			date5.setTime(formater.parse("17:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		FenetreLivraison fenetreTest = new FenetreLivraison(date1, date2);
		dLivraison.ajouterFenetre(new FenetreLivraison(date3, date4));
		dLivraison.ajouterFenetre(fenetreTest);
		dLivraison.ajouterFenetre(new FenetreLivraison(date2, date5));
		assertEquals(dLivraison.getFenetre(date1, date2), fenetreTest);
	}

	@Test
	public void testSupprimerLivraison(){
		fail("Not yet implemented");
	}

	@Test
	public void testCalculTournee() {
		fail("Not yet implemented");
	}
}

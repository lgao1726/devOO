package controleur;

import modele.Livraison;
import modele.Plan;
import vue.Fenetre;

public class EtatModeEchange extends EtatDefaut{
	
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre)
	{
		Controleur.etatLivraisonSelectionneeEchange.setLivraison(livraison);
		Controleur.setEtatCourant(Controleur.etatLivraisonSelectionneeEchange);
		System.out.print("etatModeEchange");
	}
}

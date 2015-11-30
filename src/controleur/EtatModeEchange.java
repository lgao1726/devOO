package controleur;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import vue.Fenetre;

public class EtatModeEchange extends EtatDefaut{
	
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre)
	{
		System.out.println("Vous avez selectionné:(mode echange) " + livraison.getAdresse().getId());
		Controleur.etatLivraisonSelectionneeEchange.setLivraison(livraison);
		Controleur.setEtatCourant(Controleur.etatLivraisonSelectionneeEchange);
		fenetre.afficheMessage("Adresse de la 1ère livraison: "+ livraison.getAdresse().getId()+ " ...");
	}
	
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("");
		fenetre.activerUndoRedoGenerer();
	}
	
	@Override
	public void selectionnerNoeud(Plan plan, Noeud noeud, Fenetre fenetre)
	{
		fenetre.afficheMessageBox("Il faut selectionner une livraison");
	}
}
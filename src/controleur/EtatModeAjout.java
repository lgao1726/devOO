package controleur;

import vue.Fenetre;
import modele.Noeud;

public class EtatModeAjout extends EtatDefaut{
	
	@Override
	public void selectionnerNoeud(Noeud noeud, Fenetre fenetre)
	{
		Controleur.etatNoeudSelectionne.setNoeud(noeud);
		Controleur.setEtatCourant(Controleur.etatNoeudSelectionne);
		fenetre.afficheMessage("Selectionnez la livraison apr�s laquelle vous voulez ins�rer la nouvelle");
	}
	
	@Override
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
	}
	
	

}

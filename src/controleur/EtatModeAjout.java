package controleur;

import vue.Fenetre;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class EtatModeAjout extends EtatDefaut{
	
	ListeCommandes listeDeCdes;
	
	@Override
	public void selectionnerNoeud(Plan plan, Noeud noeud, Fenetre fenetre)
	{	
		Controleur.etatNoeudSelectionne.setNoeud(noeud);
		Controleur.setEtatCourant(Controleur.etatNoeudSelectionne);
		fenetre.afficheMessage("Adresse de la livraison: " + noeud.getId() + ". Selectionnez la livraison apr�s laquelle vous voulez l'ins�rer");
	}
	
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre)
	{
		fenetre.afficheMessageBox("Vous devez selectionnez un noeud");
		plan.updatePlan();
	}
	
	
	@Override
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("");
		fenetre.activerUndoRedoGenerer();
	}
	
	@Override
	public void deselectionner(Fenetre fenetre)
	{
		fenetre.afficheMessage("Il faut selectionner un noeud");
	}
	
	

}

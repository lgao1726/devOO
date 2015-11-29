package controleur;

import vue.Fenetre;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class EtatModeAjout extends EtatDefaut{
	
	@Override
	public void selectionnerNoeud(Noeud noeud, Fenetre fenetre)
	{
		Controleur.etatNoeudSelectionne.setNoeud(noeud);
		Controleur.setEtatCourant(Controleur.etatNoeudSelectionne);
		fenetre.afficheMessage("Selectionnez la livraison après laquelle vous voulez insérer la nouvelle");
	}
	
	/**@Override
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre)
	{
		fenetre.afficheMessageBox("Vous devez selectionnez un noeud");
	}**/
	
	
	@Override
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("");
	}
	
	@Override
	public Noeud deselectionner(Fenetre fenetre)
	{
		fenetre.afficheMessage("Il faut selectionner un noeud");
		return null;
	}
	
	

}

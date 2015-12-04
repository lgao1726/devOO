package controleur;

import modele.Livraison;
import modele.Noeud;
import modele.Plan;
import vue.Fenetre;

/**
 * Etat mode changer une livriason
 * @author interCorp
 *
 */
public class EtatModeEchange extends EtatDefaut
{
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraison, ListeCommandes listeDeCdes, Fenetre fenetre)
	{
		Controleur.etatLivraisonSelectionneeEchange.setLivraison(livraison);
		Controleur.setEtatCourant(Controleur.etatLivraisonSelectionneeEchange);
		fenetre.afficheMessage("Adresse de la 1ère livraison: "+ livraison.getAdresse().getId()+ " ...");
		fenetre.selectionnerLivraisonTextuelle(livraison);
	}
	
	@Override
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

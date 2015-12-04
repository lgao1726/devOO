package controleur;

import vue.Fenetre;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;

/**
 * Classe Etat noeud sélectionner
 * @author H4101 International Corp
 *
 */
public class EtatNoeudSelectionne extends EtatDefaut
{	
	Noeud noeud;
	Livraison livraisonPrecedente;
	Plan plan;
	ListeCommandes listeDeCdes;

	/**
	 * Setteur de noeud courrent
	 * @param noeud
	 */
	public void setNoeud(Noeud noeud)
	{
		this.noeud=noeud;
	}
	
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraisonPrecedente, ListeCommandes listeDeCdes, Fenetre fenetre)
	{
		this.livraisonPrecedente=livraisonPrecedente;
		this.listeDeCdes=listeDeCdes;
		this.plan=plan;
		fenetre.afficheMessage("Elle sera livrÃ©e aprÃ¨s la livraison Ã  l'adresse: "+ livraisonPrecedente.getAdresse().getId()+". Validez!");
		fenetre.changerValider(true);
		fenetre.selectionnerLivraisonTextuelle(livraisonPrecedente);
	}
	
	@Override
	public void selectionnerNoeud(Plan plan, Noeud noeud, Fenetre fenetre)
	{
		Controleur.etatNoeudSelectionne.setNoeud(noeud);
		fenetre.afficheMessage("Adresse de la livraison: " + noeud.getId() + ". Selectionnez la livraison aprÃ¨s laquelle vous voulez l'insÃ©rer");

	}
	
	@Override
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("");
		fenetre.activerUndoRedoGenerer();
	}
	
	@Override
	public void valider(Fenetre fenetre)
	{
		Livraison livraison = new Livraison (0, noeud, 0 , null, null);
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);

		listeDeCdes.ajoute(new CommandeAjouter(plan, livraison, livraisonPrecedente));
		plan.getDemandeLivraisons().getTournee();
		fenetre.afficheMessage("Ajout terminÃ©");
		fenetre.activerUndoRedoGenerer();
		noeud=null;
		livraisonPrecedente=null;
	}
}

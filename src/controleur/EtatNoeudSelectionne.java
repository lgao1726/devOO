package controleur;

import vue.Fenetre;
import modele.DemandeLivraison;
import modele.FenetreLivraison;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class EtatNoeudSelectionne extends EtatDefaut{

	
	Noeud noeud;
	Livraison livraisonPrecedente;
	Plan plan;
	ListeCommandes listeDeCdes;
	
	public void setNoeud(Noeud noeud){
		System.out.println("idnoeud dans l'etat noeud selectionne: "+noeud.getId());
		this.noeud=noeud;
	}
	@Override
	public void selectionnerLivraison(Plan plan, Livraison livraisonPrecedente, ListeCommandes listeDeCdes, Fenetre fenetre){
		this.livraisonPrecedente=livraisonPrecedente;
		this.listeDeCdes=listeDeCdes;
	}
	
	@Override
	public void annuler(Fenetre fenetre)
	{
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("");
	}
	
	@Override
	public void valider(Fenetre fenetre)
	{
		Livraison livraison = new Livraison (0, noeud, 0 , null, null);
		listeDeCdes.ajoute(new CommandeAjouter(plan, livraison, livraisonPrecedente));
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.afficheMessage("Ajout terminé");
		noeud=null;
		livraisonPrecedente=null;
	}
	
}

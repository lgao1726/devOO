package controleur;

import vue.Fenetre;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class EtatLivraisonSelectionneeEchange extends EtatDefaut{
	
	Livraison livraison;
	Livraison livraison2;
	private ListeCommandes listeDeCdes;
	private Plan plan;
	public EtatLivraisonSelectionneeEchange(){
		// TODO Auto-generated constructor stub
	}
	
	public void setLivraison(Livraison liv){
		this.livraison = liv;
	}
	
	
	public void selectionnerLivraison(Plan plan, Livraison liv, ListeCommandes listeDeCdes, Fenetre fenetre){
		this.livraison2 = liv;
		this.listeDeCdes=listeDeCdes;
		this.plan=plan;
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
		if(livraison.getHeureDebut()!=null && livraison2.getHeureDebut()!=null)
		{
			listeDeCdes.ajoute(new CommandeEchanger(plan, livraison.getAdresse().getId(), livraison2.getAdresse().getId()));
		}
		else
		{
			fenetre.afficheMessageBox("Vous essayez de déplacer l'entrepot");
		}
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		livraison=null;
		livraison2=null;
	}
	
	@Override
	public void selectionnerNoeud(Noeud noeud, Fenetre fenetre)
	{
		fenetre.afficheMessageBox("Il faut selectionner une livraison");
	}

}
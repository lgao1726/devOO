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
		fenetre.afficheMessage(" ... Adresse de la deuxième livraison :" + livraison2.getAdresse().getId());
		System.out.println("Vous avez selectionné: (mode livraisonselectionneechange)" + livraison2.getAdresse().getId());
		fenetre.selectionnerLivraisonTextuelle(livraison2);

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
		
		System.out.println("echange :" + livraison.getAdresse().getId()+ " "+livraison2.getAdresse().getId());
		if(livraison.getHeureDebut()!=null && livraison2.getHeureDebut()!=null)
		{
			if(livraison!=livraison2)
			listeDeCdes.ajoute(new CommandeEchanger(plan, livraison.getAdresse().getId(), livraison2.getAdresse().getId()));
			else
			{
				fenetre.afficheMessageBox("Vous ne pouvez pas échanger une livraison avec elle-même");
				fenetre.afficheMessage("");
				Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
			}
		}
		else
		{
			fenetre.afficheMessageBox("Vous essayez de déplacer l'entrepot");
		}
		
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		fenetre.activerUndoRedoGenerer();
		livraison=null;
		livraison2=null;
	}
	
	@Override
	public void selectionnerNoeud(Plan plan, Noeud noeud, Fenetre fenetre)
	{
		fenetre.afficheMessageBox("Il faut selectionner une livraison");
	}
	
	@Override
	public void deselectionner(Fenetre fenetre){
		if(livraison2==null)
		{
			Controleur.setEtatCourant(Controleur.etatModeEchange);
		}
		else if(livraison!=null && livraison2!=null)
		{
			livraison2=null;
		}
	}

}
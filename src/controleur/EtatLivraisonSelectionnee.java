package controleur;

import vue.Fenetre;
import modele.Livraison;
import modele.Noeud;
import modele.Plan;

public class EtatLivraisonSelectionnee extends EtatDefaut{
	
	Livraison livraison;
	Livraison livraison2;
	private ListeCommandes listeDeCdes;
	private Plan plan;
	public EtatLivraisonSelectionnee(){
		// TODO Auto-generated constructor stub
	}
	
	public void setLivraison(Livraison liv){
		this.livraison = liv;
	}
	
	
	public void selectionnerLivraison(Plan plan, Livraison liv, ListeCommandes listeDeCdes, Fenetre fenetre){
		this.livraison2 = liv;
		this.listeDeCdes=listeDeCdes;
		this.plan=plan;
		fenetre.changerValider(true);
		fenetre.afficheMessage(" ... Adresse de la deuxi�me livraison :" + livraison2.getAdresse().getId()+" . Validez!");
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
		Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
		if(livraison.getHeureDebut()!=null && livraison2.getHeureDebut()!=null)
		{
			
			if(livraison!=livraison2){
			listeDeCdes.ajoute(new CommandeEchanger(plan, livraison.getAdresse().getId(), livraison2.getAdresse().getId()));
			plan.updatePlan();
			}
			else
			{
				fenetre.afficheMessageBox("Vous ne pouvez pas �changer une livraison avec elle-m�me");
				fenetre.afficheMessage("");
			}
		}
		else
		{
			fenetre.afficheMessageBox("On ne peut pas d�placer l'entrepot");
		}
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
			fenetre.afficheMessage("Cliquez sur la premi�re livraison");
		}
		else if(livraison!=null && livraison2!=null)
		{
			livraison2=null;
			fenetre.afficheMessage("Cliquez sur la deuxieme livraison");
			fenetre.changerValider(false);
		}
	}

}
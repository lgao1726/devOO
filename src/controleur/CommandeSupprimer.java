package controleur;

import java.sql.Date;

import modele.Livraison;
import modele.Plan;

public class CommandeSupprimer implements Commande {

	private Plan plan;
	private Livraison livraison;
	private Date heureDebut;
	private Date heureFin;
	
	public CommandeSupprimer(Plan plan,Livraison livraison,Date heureDebut, Date heureFin){
		this.plan = plan;
		this.livraison = livraison;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;n = heureFin;
	}
	
	@Override
	public void executer() {
		plan.getDemandeLivraisons().supprimerLivraison(livraison.getAdresse().getId());
		
	}

	@Override
	public void undo() {
		plan.getDemandeLivraisons().ajouterLivraison(livraison, heureDebut, heureFin);
		
	}

}

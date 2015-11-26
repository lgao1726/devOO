package controleur;

import modele.Plan;

public class CommandeSupprimer implements Commande {

	private Plan plan;
	private int idLivraison;
	public CommandeSupprimer(Plan plan,int idLivraison){
		this.plan = plan;
		this.idLivraison = idLivraison;
	}
	
	@Override
	public void executer() {
		plan.getDemandeLivraisons().getTournee().supprimerLivraison(idLivraison);
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}

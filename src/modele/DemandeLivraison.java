package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class DemandeLivraison
{
	private ArrayList<FenetreLivraison> listeFenetres;
	private Tournee tournee;

	public DemandeLivraison() {
		listeFenetres = new ArrayList<FenetreLivraison>();
	}
	
	public void ajouterFenetre(FenetreLivraison fenetre)
	{
		listeFenetres.add(fenetre);
	}

	public FenetreLivraison getFenetre(Date debut, Date fin) {
		Iterator<FenetreLivraison> it = listeFenetres.iterator();
		FenetreLivraison fenetre = null;

		while (it.hasNext()) {
			fenetre = (FenetreLivraison) it.next();

			if (fenetre.getHeureDebut().equals(debut) && fenetre.getHeureFin().equals(fin))

				break;
		}

		return fenetre;
	}

	public Iterator<FenetreLivraison> getFenetreIterator() {
		return listeFenetres.iterator();
	}

	public ArrayList<FenetreLivraison> getFenetres() {
		return listeFenetres;
	}
	
	public Tournee calculerTournee(Plan plan){
		tournee = new Tournee(plan);
		tournee.calculerTournee(plan, listeFenetres);
		plan.updatePlan();
		return tournee;
	}
	
	public Tournee getTournee(){
		return tournee;
	}
	
}

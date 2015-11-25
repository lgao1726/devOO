package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;

public class DemandeLivraison
{
	private ArrayList<FenetreLivraison> listeFenetres;
	private Tournee tournee;

	public DemandeLivraison() 
	{
		super();
		listeFenetres = new ArrayList<FenetreLivraison>();
	}
	
	public void ajouterFenetre(FenetreLivraison fenetre)
	{
		listeFenetres.add(fenetre);
	}
	
	public FenetreLivraison getFenetre(Date debut, Date fin)
	{
		Iterator<FenetreLivraison> it = listeFenetres.iterator();
		FenetreLivraison fenetre = null;
		
		while (it.hasNext())
		{
			fenetre = (FenetreLivraison)it.next();
			
			if (fenetre.getHeureDebut().equals(debut) && fenetre.getHeureFin().equals(fin))
				
				break;
		}
		
		return fenetre;
	}
	
	public Iterator<FenetreLivraison> getFenetreIterator()
	{
		return listeFenetres.iterator();
	}
	
	public ArrayList<FenetreLivraison> getFenetres(){
		return listeFenetres;
	}
	
	public Tournee calculerTournee(Plan plan){
		tournee = new Tournee(plan);
<<<<<<< HEAD
		tournee.calculTournee(plan, listeFenetres);
=======
		tournee.calculerTournee(plan, listeFenetres);
>>>>>>> branch 'development' of https://github.com/lgao1726/devOO.git
		plan.updatePlan();
		return tournee;
	}
	
	public Tournee getTournee(){
		return tournee;
	}
	
}

package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
		tournee.calculerTournee(plan, listeFenetres);
		plan.updatePlan();
		return tournee;
	}
	
	public Tournee getTournee(){
		return tournee;
	}
	
	public void ajouterLivraison(Livraison livraison,Date heureDebut,Date heureFin){
		FenetreLivraison fenetre = getFenetre(heureDebut,heureFin);
		fenetre.ajouterLivraison(livraison);
		getTournee().ajouterLivraison(livraison.getId(), livraison.getAdresse(),
				livraison.getClient(), fenetre.getLivraisons().size()-2);
	}
	
	public void supprimerLivraison(int adresseLivraison){
		getTournee().supprimerLivraison(adresseLivraison);
		for(FenetreLivraison fenetre:getFenetres()){
			List<Livraison> liste = fenetre.getLivraisons();
			for(Livraison liv:liste){
				if(liv.getAdresse().getId()==adresseLivraison){
					int pos = liste.indexOf(liv);
					liste.remove(pos);
				}
			}
		}
	}
	
	public Livraison getLivraison(int xPoint, int yPoint, int rayon)
	{
		for(FenetreLivraison fenetre:listeFenetres)
		{
			Iterator<Livraison>itLivraison=fenetre.getLivraisonIterator();
			while(itLivraison.hasNext())
			{
				Livraison livraison=(Livraison)itLivraison.next();
				Noeud noeud=livraison.getAdresse();
				if((xPoint>noeud.getX()-rayon) && (xPoint<noeud.getX()+rayon)  && (yPoint<noeud.getY()+rayon)  && (yPoint>noeud.getY()-rayon))
				{
					System.out.println(noeud.getId()+"|"+livraison.getId()+"|"+fenetre.getNbLivraisons());
					return livraison;
				}
			}
		}
		return null;
	}
	
}

package modele;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.TimeZone;
import java.util.Timer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class DemandeLivraison
{
	private ArrayList<FenetreLivraison> listeFenetres;
	private Tournee tournee;
	private ArrayList<Livraison> livraisonsRetard;

	public DemandeLivraison() 
	{
		super();
		tournee = new Tournee();
		listeFenetres = new ArrayList<FenetreLivraison>();
		livraisonsRetard = new ArrayList<Livraison>();
	}
	
	public void ajouterFenetre(FenetreLivraison fenetre)
	{
		listeFenetres.add(fenetre);
	}
	
	public FenetreLivraison getFenetre(Calendar debut, Calendar fin)
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
		tournee.calculerTournee(plan, listeFenetres);
		tournee.afficherListeItineraires();
		setHeuresPassage();
		plan.updatePlan();
		return tournee;
	}
	
	public Tournee getTournee(){
		return tournee;
	}
	
	public void ajouterLivraison(Livraison livraison, Livraison livraisonPrecedente){
		Calendar heureDebutPrecedente=livraisonPrecedente.getHeureDebut();
		Calendar heureFinPrecedente=livraisonPrecedente.getHeureFin();
		FenetreLivraison fenetre;
		if(heureDebutPrecedente==null && heureFinPrecedente==null)
		{
			fenetre=listeFenetres.get(1);
		}
		else
		{
			fenetre = getFenetre(heureDebutPrecedente,heureFinPrecedente);
		}
		if(livraison.getId()==0)
		{
			livraison.setId(fenetre.getNbLivraisons()+1);
			livraison.setIdClient(fenetre.getLivraisons().get(fenetre.getNbLivraisons()-1).getClient()+1);
			livraison.setHeureDebut(fenetre.getHeureDebut());
			livraison.setHeureFin(fenetre.getHeureFin());
			livraison.setHeurePassage(fenetre.getHeureDebut());
		}
		
		fenetre.ajouterLivraison(livraison);
		tournee.ajouterLivraison(livraison, livraisonPrecedente.getAdresse().getId());
		resetHeuresPassage();
		setHeuresPassage();
	}
	
	public void supprimerLivraison(int adresseLivraison){
		getTournee().supprimerLivraison(adresseLivraison);
		for(FenetreLivraison fenetre:getFenetres()){
			List<Livraison> liste = fenetre.getLivraisons();
			for(Livraison liv:liste){
				if(liv.getAdresse().getId()==adresseLivraison){
					int pos = liste.indexOf(liv);
					liste.remove(pos);
					break;
				}
			}
		}
		resetHeuresPassage();
		setHeuresPassage();
	}
	
	//echanger 2 livraisons, ils ont pas besoin d'etre un a cotre de l'autre
	public void echangerLivraisonSepares(int livraison1,int livraison2){
		List<Itineraire> itineraires = getTournee().getItineraires();
		//trouver qui est le livraison precedent entre les deux
		int posLiv1 = -1;
		int posLiv2 = -1;
		//positions des livraisons selectionnees
		int livAvant = -1;
		int livApres = -1;
		//position des livraisons precedent et suivant
		int posLivAvant = -1;
		int posLivApres = -1;
		//trouver les positions des livraisons donnees
		for(int i=0;i<itineraires.size();i++){
			Itineraire iti = itineraires.get(i);
			int courant = iti.getLivraisonOrigine().getAdresse().getId();
			if(courant==livraison1){
				posLiv1 = i;
			}else if(courant==livraison2){
				posLiv2 = i;
			}
		}
		//determiner quel livraison est precedent/suivant
		if(posLiv1<posLiv2){
			livAvant = livraison1;
			livApres = livraison2;
			posLivAvant = posLiv1;
			posLivApres = posLiv2;
		}else{
			livAvant = livraison2;
			livApres = livraison1;
			posLivAvant = posLiv2;
			posLivApres = posLiv1;
		}
		//on fait les echanges jusqu'a la position du livraison suivant
		for(int i=posLivAvant;i<posLivApres;i++){
			int avant = itineraires.get(i).getLivraisonOrigine().getAdresse().getId();
			int apres = itineraires.get(i+1).getLivraisonOrigine().getAdresse().getId();
			echangerLivraison(avant,apres);
		}
		//on fait les echanges jusqu'a la position du livraison precedent
		for(int i=posLivApres-1;i>posLivAvant;i--){
			int avant = itineraires.get(i).getLivraisonOrigine().getAdresse().getId();
			int apres = itineraires.get(i-1).getLivraisonOrigine().getAdresse().getId();
			echangerLivraison(avant,apres);
		}
	}
	
	
	//echanger deux livraison cotoyant
	//les deux n'ont pas besoin d'être ordonné
	public void echangerLivraison(int livraison1,int livraison2){
		List<Itineraire> itineraires = getTournee().getItineraires();
		//trouver qui est le livraison precedent entre les deux
		int livAvant = -1;
		int livApres = -1;
		for(Itineraire iti:itineraires){
			int courant = iti.getLivraisonOrigine().getAdresse().getId();
			if(courant==livraison1){
				livAvant = livraison1;
				livApres = livraison2;
				break;
			}else if(courant==livraison2){
				livAvant = livraison2;
				livApres = livraison1;
				break;
			}
		}
		
	    //echanger les livraisons
		tournee.echangerLivraison(livAvant, livApres);
		System.out.println(livAvant+" : "+livApres);
		
		//si les livraisons appartiennent aux différentes fenêtres
		//échanger les livraisons dans les fenêtres aussi
		List<FenetreLivraison> fenetres = getFenetres();
		FenetreLivraison fenetre1 = null;
		FenetreLivraison fenetre2 = null;
		Livraison liv1 = null;
		Livraison liv2 = null;
		for(FenetreLivraison fenetre:fenetres){
			for(Livraison liv:fenetre.getLivraisons() ){
				if(liv.getAdresse().getId() == livraison1){
					fenetre1 = fenetre;
					liv1 = liv;
				}else if(liv.getAdresse().getId() == livraison2){
					fenetre2 = fenetre;
					liv2 = liv;
				}
			}
		}
		if(!fenetre1.equals(fenetre2)){
			fenetre1.supprimerLivraison(liv1);
			fenetre2.supprimerLivraison(liv2);
			fenetre1.ajouterLivraison(liv2);
			fenetre2.ajouterLivraison(liv1);
			System.out.println("fenetres echanges");
		}
		
		resetHeuresPassage();
		setHeuresPassage();
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
	
	public FenetreLivraison getFenetre(Livraison liv){
		for(FenetreLivraison fenetre:listeFenetres){
			if(fenetre.getLivraisons().contains(liv)) return fenetre;
		}return null;
	}
	
	private void setHeuresPassage(){
		List<Itineraire> itineraires = tournee.getItineraires();
		for(Itineraire iti:itineraires){
			Livraison livOrigine = iti.getLivraisonOrigine();
			Livraison livDest = iti.getLivraisonDestination();
			FenetreLivraison fenetre = getFenetre(livDest);
			Calendar passage = (Calendar) livOrigine.getHeurePassage().clone();
			passage.add(Calendar.SECOND, (int) iti.getCout());
			livDest.setHeurePassage(passage);
			//on voit si l'heure de passage est avant ou apres la fenetre
			if(passage.before(fenetre.getHeureDebut())){
				long diff = fenetre.getHeureDebut().getTimeInMillis() - passage.getTimeInMillis();
				passage.add(Calendar.MILLISECOND, (int) diff);
			}else if(passage.after(fenetre.getHeureFin())){
				livraisonsRetard.add(livDest);
			}
		}
	}
	
	//reset les heures de passage de toutes les itin�raires avant une nouvelle calculation
	private void resetHeuresPassage(){
		List<FenetreLivraison> fenetres = getFenetres();
		for(int i=1;i<fenetres.size();i++){
			for(Livraison liv:fenetres.get(i).getLivraisons()){
				liv.getHeurePassage().setTimeInMillis(0x1808580);
			}
		}livraisonsRetard.clear();
	}
	
	public void genererFeuilleDeRoute(Plan plan)
	{
		List<String> strings=tournee.genererFeuille(plan);
		File file= new File("FeuilleDeRoute"+System.currentTimeMillis()+".txt");
		FileWriter fw;
	    try {
			fw = new FileWriter(file);
			for(String string:strings){
				fw.write(string);
			}
		      fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
	}
	
	public List<Livraison> getLivraisonsRetard(){
		return livraisonsRetard;
	}
	
}
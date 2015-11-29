package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Itineraire{

	private Livraison livraisonOrigine;
	private Livraison livraisonDestination;
	private ArrayList<Integer> listeNoeud;
	float cout;

	public Itineraire(ArrayList<Integer> listeNoeud) {
		this.listeNoeud = listeNoeud;
		//this.livraisonOrigine = livraisonOrigine;
		//this.livraisonDestination = livraisonDestination;
	}

	public Livraison getLivraisonOrigine() {
		return livraisonOrigine;
	}

	public void setLivraisonOrigine(Livraison livraisonOrigine) {
		this.livraisonOrigine = livraisonOrigine;
	}

	public Livraison getLivraisonDestination() {
		return livraisonDestination;
	}

	public void setLivraisonDestination(Livraison livraisonDestination) {
		this.livraisonDestination = livraisonDestination;
	}

	public ArrayList<Integer> getNoeuds() {
		return listeNoeud;
	}


	public void setListeNoeud(ArrayList<Integer> listeNoeud) {
		this.listeNoeud = listeNoeud;
	}

	public void ajouterNoeud(int id) {
		listeNoeud.add(id);
	}

	public void ajouterNoeud(int index, int id) {
		listeNoeud.add(index, id);
	}

	public void accepte(Visiteur v) {
		v.visite(this);
	}
	
	public void affichertItineraire()
	{
		System.out.print("Itin�raire entier de la tourn�e");
		for(Integer i:listeNoeud)
		{
			System.out.print(i+"|");
			
		}
		System.out.println("");

	}
	
	public void setCout(float cout){
		this.cout = cout + 10*60;
	}
	
	public float getCout(){
		return cout;
	}

	public String getFeuilleString(Plan plan){
		String itineraire= "";
		Calendar passage = livraisonDestination.getHeurePassage();
		itineraire += "Etape pour aller de: "+livraisonOrigine.getAdresse().getId()+
						" a "+livraisonDestination.getAdresse().getId()+"\n";
		itineraire += "Duree: "+(int) cout/60+" mins\n";
		itineraire += "Heure d'arrive: "+passage.get(Calendar.HOUR_OF_DAY)+"h"+passage.get(Calendar.MINUTE)+"\n";
		
		Troncon tronconPrecedent = null;
		float distance = 0;
		int nbInstruction = 1;
		for(int i=0;i<listeNoeud.size()-1;i++){
			//itineraire += (i+1) +") "+ listeNoeud.get(i)+" - "+listeNoeud.get(i+1)+"\n";
			Noeud noeudOrigine = plan.getNoeud(listeNoeud.get(i));
			Noeud noeudDestination = plan.getNoeud(listeNoeud.get(i+1));
			Troncon troncon = noeudOrigine.getToncon(noeudDestination.getId());
			//itineraire += (i+1) +") "+"Prendre "+troncon.getNomRue()+" et continuer "
			//				+troncon.getLongueur()+"m\n";
			if(tronconPrecedent==null){
				tronconPrecedent = troncon;						
			}
			
			boolean memeTroncon = tronconPrecedent.getNomRue().equals(troncon.getNomRue());
			
			if(memeTroncon){
				tronconPrecedent = troncon;
				distance += troncon.getLongueur();
			}else if(memeTroncon==false){
				itineraire += (nbInstruction) +") "+"Prendre "+tronconPrecedent.getNomRue()+" et continuer "
								+distance+"m\n";
				tronconPrecedent = troncon;
				distance = troncon.getLongueur();
				nbInstruction++;
			}
			
			if(i==listeNoeud.size()-2){
				itineraire += (nbInstruction) +") "+"Prendre "+troncon.getNomRue()+" et continuer "
						+distance+"m\n";
				nbInstruction++;
			}
			
		}
		return itineraire;
	}
	

}
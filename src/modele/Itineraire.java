package modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * La classe Itineraire contient le chemin prise entre deux livraisons
 * successifs dans la tournee sous forme d'une collection de'adresses
 * de noeuds. Les livraison d'origine et de destination se trouvent aussi 
 * dans cet objet 
 * @author H4101
 *
 */
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

	/**
	 * Obtenir la livraison d'origine
	 * @return
	 */
	public Livraison getLivraisonOrigine() {
		return livraisonOrigine;
	}

	/**
	 * Attribuer une livraison comme livraison origine
	 * @param livraisonOrigine
	 */
	public void setLivraisonOrigine(Livraison livraisonOrigine) {
		this.livraisonOrigine = livraisonOrigine;
	}

	/**
	 * Obtenir la livraison de destination
	 * @return
	 */
	public Livraison getLivraisonDestination() {
		return livraisonDestination;
	}

	/**
	 * Attribuer une livraison comme livraison de destination
	 * @param livraisonDestination
	 */
	public void setLivraisonDestination(Livraison livraisonDestination) {
		this.livraisonDestination = livraisonDestination;
	}

	/**
	 * Obtenir un List ordonne d'adresses de noeuds a suivre pour
	 * aller de l'origine a la destination 
	 * @return
	 */
	public ArrayList<Integer> getNoeuds() {
		return listeNoeud;
	}

	/**
	 * Ajouter un List ordonnee d'adresses de noeuds à suivre
	 * pour aller de l'origine a la destination
	 * @param listeNoeud
	 */
	public void setListeNoeud(ArrayList<Integer> listeNoeud) {
		this.listeNoeud = listeNoeud;
	}


	/**
	 * 
	 * @param v
	 */
	public void accepte(Visiteur v) {
		v.visite(this);
	}
	
	/**
	 * Attribuer un cout a cet itineraire en int (secondes)
	 * @param cout
	 */
	public void setCout(float cout){
		this.cout = cout + 10*60;
	}
	
	/**
	 * Obtenir le cout de cet itineraire en int (secondes)
	 * @return
	 */
	public float getCout(){
		return cout;
	}

	/**
	 * Generer la section de la feuille de route concernant cette Itineraire sous forme de String 
	 * @param plan
	 * @return String 
	 */
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
package controleur;

/**
 * La classe ListeCommandes contient toutes les commandes de l'utilisateur
 * Cette classe nous permet de annuler ou restaurer les commandes
 */
import java.util.LinkedList;

	public class ListeCommandes {
		
		private LinkedList<Commande> liste;
		private int indiceCrt;
		
		
		
		public ListeCommandes(){
		liste = new LinkedList<Commande>();
		indiceCrt = -1;
		System.out.println(indiceCrt) ;
		}
		
		/**
		 * Ajouter et executer une nouvelle commande
		 * @param c
		 */
		public void ajoute(Commande c){
			System.out.println(indiceCrt) ;
		for (int i=indiceCrt+1; i<liste.size(); i++)
			liste.remove(i);
		indiceCrt++;
		liste.add(indiceCrt, c);
		c.executer();

	}
		
	
		/**
		 * Annuler une commande 
		 */
		public void undo(){
			if(indiceCrt>=0){
				liste.get(indiceCrt--).undo();
			}
		}
		/**
		 * Restaurer une commande qui était annulée
		 */
		public void redo(){
			if(indiceCrt<liste.size()-1){
				liste.get(++indiceCrt).executer();
			}
		}
}

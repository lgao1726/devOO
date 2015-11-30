package controleur;



import java.util.LinkedList;

	public class ListeCommandes {
		
		private LinkedList<Commande> liste;
		private int indiceCrt;
		
		
		
		public ListeCommandes(){
		liste = new LinkedList<Commande>();
		indiceCrt = -1;
		System.out.println(indiceCrt) ;
		}
		
		
		public void ajoute(Commande c){
			System.out.println(indiceCrt) ;
		for (int i=indiceCrt+1; i<liste.size(); i++){
			liste.remove(i);
		}
		indiceCrt++;
		liste.add(indiceCrt, c);
		c.executer();
		System.out.println("indice a l'execution"+indiceCrt);

	}
		
		public void undo(){
			if(indiceCrt>=0){
				liste.get(indiceCrt--).undo();
				System.out.println("indice apres undo"+indiceCrt);
			}
		}
		
		public void redo(){
			if(indiceCrt<liste.size()-1){
				liste.get(++indiceCrt).executer();
			}
		}
}

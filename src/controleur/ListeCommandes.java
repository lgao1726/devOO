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
		for (int i=indiceCrt+1; i<liste.size(); i++)
			liste.remove(i);
		System.out.println(indiceCrt) ;
		indiceCrt++;
		System.out.println(indiceCrt) ;
		liste.add(indiceCrt, c);
		c.executer();
	}
		
	
		
		public void undo(){
			if(indiceCrt>=0){
				liste.get(indiceCrt--).undo();
				indiceCrt--;
			}
		}
		
		public void redo(){
			if(indiceCrt<liste.size()-1){
				indiceCrt++;
				liste.get(++indiceCrt).executer();
			}
		}
}

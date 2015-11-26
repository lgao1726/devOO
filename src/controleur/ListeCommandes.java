package controleur;

import java.util.LinkedList;

public class ListeCommandes {
	private LinkedList<Commande> liste;
	private int i;
	
	public ListeCommandes(){
		liste = new LinkedList<Commande>();
		i = 0;
	}
	
	public void undo(){
		if(i>=0)
			liste.get(i--).undo();
	}
	
	public void redo(){
		if(i<liste.size()-1)
			liste.get(++i).executer();
	}
}

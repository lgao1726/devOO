package controleur;

/**
 * Interface commande pour implémenter les deux fonction undo/redo
 * @author H4101 International Corp
 *
 */
public interface Commande 
{
	/**
	 * Méthode executer la commande
	 */
	void executer();
	
	/**
	 * Méthode annuler la commande
	 */
	void undo();
}

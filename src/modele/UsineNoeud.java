/**
 * 
 */
package modele;

public class UsineNoeud 
{
	private static Noeud noeuds[];
	private static int taille;

	/**
	 * Cree une fabrique de noeud capable de creer des noeuds dont les id sont noeuds[id]
	 * @param Taille
	 */
	public static void initPointFactory(int taille)
	{
		UsineNoeud.taille = taille;
		UsineNoeud.noeuds = new Noeud[taille+1];
	}
	
	/** 
	 * @param x
	 * @param y
	 * @return une instance p de noeud qui a comme indice id
	 */
	public static Noeud creeNoeud(int id, int x, int y)
	{
		return getNoeud(id) == null ? noeuds[id] = new Noeud(id, x, y) : noeuds[id];
	}
	
	/** 
	 * @param id
	 * @return une instance n de noeud qui a comme indice id
	 */
	public static Noeud getNoeud(int id)
	{
		if (id > taille || id < 0)
			return null;
		
		return noeuds[id];
	}
}


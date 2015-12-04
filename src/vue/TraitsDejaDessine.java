package vue;

/**
 * Classe qui indique les trais qui sont déjà dessinés
 * @author H4101 International Corp
 *
 */
public class TraitsDejaDessine 
{
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int occurences;
	
	/**
	 * Constructeur d'objet
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public TraitsDejaDessine(int x1,int y1,int x2,int y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.occurences = 0;
	}
	
	/**
	 * Méthode qui calcule le nombre d'occurence d'un trais
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return int nombre d'occurenses  
	 */
	public int dejaDessinee(int x1,int y1,int x2,int y2)
	{
		if(this.x1 == x1 && this.y1 == y1 && this.x2 == x2 && this.y2 == y2)
		{
			occurences++;
			return occurences;
		}
		else if (this.x1 == x2 && this.y1 == y2 && this.x2 == x1 && this.y2 == y1)
		{
			occurences++;
			return occurences;
		}
		else
			return -1;
	}
	
	/**
	 * Méthode qui mis à jour le nombre d'occurense d'un traits
	 */
	public void addTrait()
	{
		this.occurences++;
	}
}

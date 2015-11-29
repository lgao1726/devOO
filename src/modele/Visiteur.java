/**
 * 
 */
package modele;

/**
 * @author InterCorp
 *
 */
public interface Visiteur 
{
	public void visite(Livraison v);
	public void visite(DemandeLivraison v);
	public void visite(Itineraire v);
}

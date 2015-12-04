/**
 * 
 */
package modele;

/**
 * 
 * @author H4101
 *
 */
public interface Visiteur 
{
	public void visite(Livraison v);
	public void visite(Itineraire v);
	public void visite(DemandeLivraison v);
}

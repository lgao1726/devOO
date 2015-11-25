/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import modele.Noeud;
import modele.Plan;
import modele.UsineNoeud;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Aiebobo
 *
 */
public class PlanTest {

	Observer observer;
	boolean updateAppele;
	
	@Before
	public void setUp(){
		updateAppele = false;
		observer = new Observer(){public void update(Observable o, Object arg){updateAppele = true;}};
		UsineNoeud.initPointFactory(10);
	}
	
	/**
	 * Test method for {@link modele.Plan#ajouterNoeud(modele.Noeud)}.
	 */
	@Test
	public void testAjouterNoeud() {
		Plan plan = new Plan(10,10);
		plan.addObserver(observer);
		Noeud noeud = UsineNoeud.creeNoeud(0, 5, 8);
		plan.ajouterNoeud(noeud);
		assert(updateAppele);
	}

	/**
	 * Test method for {@link modele.Plan#getNoeud(int)}.
	 */
	@Test
	public void testGetNoeud() {
		Plan plan = new Plan(10,10);
		Noeud noeud = UsineNoeud.creeNoeud(0, 5, 8);
		plan.ajouterNoeud(noeud);
		assertEquals(noeud, plan.getNoeud(0));
	}

	/**
	 * Test method for {@link modele.Plan#setAdresseEntrepot(modele.Noeud)}.
	 */
	@Test
	public void testSetAdresseEntrepot() {
		Plan plan = new Plan(10,10);
		plan.addObserver(observer);
		Noeud noeud = UsineNoeud.creeNoeud(0, 3, 3);
		plan.ajouterNoeud(noeud);
		plan.setAdresseEntrepot(noeud);
		assert(updateAppele);
	}

	/**
	 * Test method for {@link modele.Plan#reset(int)}.
	 */
	@Test
	public void testReset() {
		Plan plan = new Plan(10,10);
		Plan planVide = new Plan(10,10);

		Noeud noeud1 = UsineNoeud.creeNoeud(0, 3, 3);
		Noeud noeud2 = UsineNoeud.creeNoeud(1, 2, 6);
		Noeud noeud3 = UsineNoeud.creeNoeud(2, 8, 6);
		
		plan.ajouterNoeud(noeud1);
		plan.ajouterNoeud(noeud2);
		plan.ajouterNoeud(noeud3);
		
		plan.reset();
		assertEquals(plan.getIntersections(),new ArrayList<Noeud>());
		assertEquals(plan.getAdresseEntrepot(), null);
		assertEquals(plan.getDemandeLivraisons(), null);
		assertEquals(plan.getNbIntersections(), 0);
	}

	/**
	 * Test method for {@link modele.Plan#updatePlan()}.
	 */
	@Test
	public void testSetTournee() {
		fail("Not yet implemented");
	}

}

/**
 * 
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import xml.OuvreurDeFichierXML;

/**
 * @author Aiebobo
 *
 */
public class OuvreurDeFichierXMLTest {

	private static OuvreurDeFichierXML instance;
	
	@Before
	public void setUp(){
		instance = OuvreurDeFichierXML.getInstance();
	}
	
	/**
	 * Test method for {@link xml.OuvreurDeFichierXML#getInstance()}.
	 */
	@Test
	public void testGetInstance1() {
		assertNotNull(instance);
	}
	
	/**
	 * Test method for {@link xml.OuvreurDeFichierXML#getInstance()}.
	 */
	@Test
	public void testGetInstance2() {
		OuvreurDeFichierXML instance2 = OuvreurDeFichierXML.getInstance();
		assertEquals(instance, instance2);
	}

	/**
	 * Test method for {@link xml.OuvreurDeFichierXML#accept(java.io.File)}.
	 */
	@Test
	public void testAccept() {
		File fichier = new File("src/testXML/livraison10x10-1.xml");
		assert(instance.accept(fichier));
	}

	/**
	 * Test method for {@link xml.OuvreurDeFichierXML#accept(java.io.File)}.
	 */
	@Test
	public void testAccept2() {
		File fichier = new File("src/testXML/fichier-existe-pas.xml");
		assert(!instance.accept(fichier));
	}
	
	/**
	 * Test method for {@link xml.OuvreurDeFichierXML#accept(java.io.File)}.
	 */
	@Test
	public void testAccept3() {
		File fichier = new File("src/testXML");
		assert(instance.accept(fichier));
	}

	/**
	 * Test method for {@link xml.OuvreurDeFichierXML#accept(java.io.File)}.
	 */
	@Test
	public void testAccept4() {
		File fichier = new File("src/testXML/livraison10x10-1.pdf");
		assert(!instance.accept(fichier));
	}
}

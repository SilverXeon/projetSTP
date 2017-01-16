package test.fr.univ_artois.stp.graph;

import fr.univ_artois.stp.graph.Noeud;
import junit.framework.TestCase;

/**
 * Created by Pierre on 12/01/2017.
 */
public class NoeudTest extends TestCase {
    public void testGetDescription() throws Exception {
        Noeud n1 = new Noeud("x0");
        assertTrue("Libelle non valide", n1.getDescription().equals("x0"));
    }

}
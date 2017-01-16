package test.fr.univ_artois.stp.graph;

import fr.univ_artois.stp.graph.Arete;
import junit.framework.TestCase;

/**
 * Created by Pierre on 12/01/2017.
 */
public class AreteTest extends TestCase {
    public void testGetPoids() throws Exception {
        Arete a = new Arete(0, 20);
        assertTrue("Poids non respecté", a.getPoids().equals("[0 , 20]"));

    }
}
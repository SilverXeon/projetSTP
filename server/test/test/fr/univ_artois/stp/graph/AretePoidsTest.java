package test.fr.univ_artois.stp.graph;

import fr.univ_artois.stp.graph.AretePoids;
import fr.univ_artois.stp.graph.Graph;
import fr.univ_artois.stp.graph.Noeud;
import junit.framework.TestCase;

/**
 * Created by Pierre on 12/01/2017.
 */
public class AretePoidsTest extends TestCase {
    public void testGetSrc() throws Exception {
        Graph g = new Graph();

        Noeud n1 = new Noeud("x0");
        Noeud n2 = new Noeud("x1");
        g.addNoeud(n1);
        g.addNoeud(n2);
        g.addArete(n1, n2, 0, 10);
        AretePoids a = g.getListeAreteReel().getLast();
        assertTrue("Source non correcte", a.getSrc() == n1);
    }

    public void testGetDest() throws Exception {
        Graph g = new Graph();

        Noeud n1 = new Noeud("x0");
        Noeud n2 = new Noeud("x1");
        g.addNoeud(n1);
        g.addNoeud(n2);
        g.addArete(n1, n2, 0, 10);
        AretePoids a = g.getListeAreteReel().getLast();
        assertTrue("Destination non correcte", a.getDest() == n2);
    }

    public void testGetPoids() throws Exception {
        Graph g = new Graph();

        Noeud n1 = new Noeud("x0");
        Noeud n2 = new Noeud("x1");
        g.addNoeud(n1);
        g.addNoeud(n2);
        g.addArete(n1, n2, 0, 10);
        AretePoids a = g.getListeAreteReel().getLast();

        assertTrue("Poids non correct", a.getPoids() == 10);
    }

}
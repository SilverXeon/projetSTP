package test.fr.univ_artois.stp.resolution;

import fr.univ_artois.stp.graph.Graph;
import fr.univ_artois.stp.graph.Noeud;
import fr.univ_artois.stp.resolution.ResolutionBellman;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Pierre on 12/01/2017.
 */
public class ResolutionBellmanTest extends TestCase {
    private Graph g;
    private ResolutionBellman r;

    public void setUp() throws Exception {
        super.setUp();
        g = new Graph();
        r = new ResolutionBellman();
        Noeud n1 = new Noeud("x0");
        Noeud n2 = new Noeud("x1");
        Noeud n3 = new Noeud("x2");
        Noeud n4 = new Noeud("x3");
        Noeud n5 = new Noeud("x4");
        g.addNoeud(n1);
        g.addNoeud(n2);
        g.addNoeud(n3);
        g.addNoeud(n4);
        g.addNoeud(n5);
        g.addArete(n1, n2, 10, 20);
        g.addArete(n1, n5, 50, 70);
        g.addArete(n2, n3, 30, 40);
        g.addArete(n4, n3, 0, 20);
        g.addArete(n4, n5, 40, 50);
    }

    public void testResolution() throws Exception {
        assertTrue("Mauvaise resolution", r.resolution(g));
    }

    public void testGetResultat() throws Exception {
        r.resolution(g);
        HashMap<String, LinkedList<Integer>> h = r.getResultat();
        if(h.get("distance").get(0) != 0)
            fail("Mauvais resultat");
        if(h.get("distance").get(1) != 10)
            fail("Mauvais resultat");
        if(h.get("distance").get(2) != 40)
            fail("Mauvais resultat");
        if(h.get("distance").get(3) != 20)
            fail("Mauvais resultat");
        if(h.get("distance").get(4) != 60)
            fail("Mauvais resultat");

    }

}
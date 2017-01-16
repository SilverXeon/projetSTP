package test.fr.univ_artois.stp.graph;

import fr.univ_artois.stp.graph.Graph;
import fr.univ_artois.stp.graph.Noeud;
import junit.framework.TestCase;

/**
 * Created by Pierre on 12/01/2017.
 */
public class GraphTest extends TestCase {
    public void testAddNoeud() throws Exception {
        Graph g = new Graph();
        Noeud n = new Noeud("x0");
        g.addNoeud(n);
        assertTrue("Noeud différend", g.getListeNoeud().getFirst() == n);
    }

    public void testRemoveNoeud() throws Exception {
        Graph g = new Graph();
        Noeud n1 = new Noeud("x0");
        Noeud n2 = new Noeud("x1");
        g.addNoeud(n1);
        g.addNoeud(n2);
        g.removeNoeud(n2);
        assertTrue("Noeud non supprimé", g.getListeNoeud().getLast() == n1);
    }

    public void testAddArete() throws Exception {
        Graph g = new Graph();
        Noeud n1 = new Noeud("x0");
        Noeud n2 = new Noeud("x1");
        g.addNoeud(n1);
        g.addNoeud(n2);
        g.addArete(n1, n2, 0, 10);
        assertTrue("Arete non inséré", g.getListeAreteReel().getLast().getSrc() == n1);
        assertTrue("Arete non inséré", g.getListeAreteReel().getLast().getDest() == n2);
    }

    public void testGetNbNoeud() throws Exception {
        Graph g = new Graph();
        Noeud n1 = new Noeud("x0");
        Noeud n2 = new Noeud("x1");
        g.addNoeud(n1);
        g.addNoeud(n2);
        assertTrue("Nombre de noeud différent", g.getNbNoeud() == 2);
    }

    public void testGetNbArete() throws Exception {
        Graph g = new Graph();
        Noeud n1 = new Noeud("x0");
        Noeud n2 = new Noeud("x1");
        g.addNoeud(n1);
        g.addNoeud(n2);
        g.addArete(n1, n2, 0, 10);
        assertTrue("Nombres d'aretes différent", g.getNbArete() == 1);
    }

}
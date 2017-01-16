package fr.univ_artois.stp.graph;

import org.jgrapht.DirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import java.util.LinkedList;

/**
 * Classe graphe, representant un graphe
 * Created by Pierre on 04/12/2016.
 */
public class Graph {
    /**
     * Graphe utilisé pour la resolution
     */
    private WeightedGraph<Noeud, AretePoids> graphReel;
    /**
     * Graphe utilisé pour l'affichage
     */
    private DirectedGraph<Noeud, Arete> graphAff;
    /**
     * Nombre de noeud
     */
    private int nbNoeud;
    /**
     * Nombre d'arete sur le graphe reel
     */
    private int nbArete;

    /**
     * Liste des aretes
     */
    private LinkedList<AretePoids> listeAreteReel;
    /**
     * Liste des noeud
     */
    private LinkedList<Noeud> listeNoeud;

    /**
     * Constructeur graphe
     * Initialise un graphe
     */
    public Graph(){
        graphReel = new DefaultDirectedWeightedGraph<Noeud, AretePoids>(AretePoids.class);
        graphAff = new DefaultDirectedGraph<Noeud, Arete>(Arete.class);
        nbNoeud = 0;
        nbArete = 0;
        listeAreteReel = new LinkedList<>();
        listeNoeud = new LinkedList<>();
    }

    /**
     * Ajoute un noeud
     * @param n noeud à ajouter
     */
    public void addNoeud(Noeud n){
        graphReel.addVertex(n);
        graphAff.addVertex(n);
        listeNoeud.add(n);
        nbNoeud++;
    }

    /**
     * Retire un noeud
     * @param n noeud à retirer
     */
    public void removeNoeud(Noeud n){
        graphReel.removeVertex(n);
        graphAff.removeVertex(n);
        nbNoeud--;
        listeNoeud.remove(n);
    }

    /**
     * Ajoute une arete
     * @param n1 Noeud de départ
     * @param n2 Noeud d'arrivée
     * @param min Temps minimum
     * @param max Temps maximum
     */
    public void addArete(Noeud n1, Noeud n2, int min, int max){ //exception arete non trouvé
        AretePoids tmp = graphReel.addEdge(n1, n2);
        graphReel.setEdgeWeight(tmp, max);
        listeAreteReel.add(tmp);
        nbArete++;
        if(min != 0) {
            tmp = graphReel.addEdge(n2, n1);
            graphReel.setEdgeWeight(tmp, -min);
            listeAreteReel.add(tmp);
            nbArete++;
        }

        Arete e = new Arete(min, max);
        graphAff.addEdge(n1, n2, e);
    }

    public String toString(){
        return graphAff.toString();
    }

    public WeightedGraph<Noeud, AretePoids> getGraphReel() {
        return graphReel;
    }

    public int getNbNoeud() {
        return nbNoeud;
    }

    public int getNbArete(){
        return nbArete;
    }

    public LinkedList<AretePoids> getListeAreteReel() {
        return listeAreteReel;
    }

    public LinkedList<Noeud> getListeNoeud() {
        return listeNoeud;
    }

    public DirectedGraph<Noeud, Arete> getGraphAff() {
        return graphAff;
    }
}

package fr.univ_artois.stp.graph;

import org.jgrapht.graph.DefaultEdge;

/**
 * Classe Arete
 * Represente une arete du graphe d'affichage
 * Created by Pierre on 06/11/2016.
 */
public class Arete extends DefaultEdge{
    /**
     * Poids de l'arete
     */
    private String poids;

    /**
     * Contructeur Arete
     * @param min temps minimum
     * @param max temps maximum
     */
    public Arete(int min, int max) {
       poids = "["+min+" , "+max+"]";
    }

    public String getPoids(){
        return poids;
    }

    public String toString(){
        return super.toString() + " " +poids;
    }
}

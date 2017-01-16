package fr.univ_artois.stp.resolution;

import fr.univ_artois.stp.graph.Graph;

/**
 * Interface de resolution
 * Created by Pierre on 06/11/2016.
 */
public interface Resolution {
    /**
     * Resoud le graphe
     * @param graph graphe à résoudre
     * @return resultat de la resolution
     */
    public boolean resolution(Graph graph);

    /**
     * @return resultat
     */
    public String resultat();
}

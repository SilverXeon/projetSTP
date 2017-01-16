package fr.univ_artois.stp.graph;

/**
 * Classe Noeud representant un noeud du graphe
 * Created by Pierre on 06/11/2016.
 */
public class Noeud {
    /**
     * Id du noeud
     */
    private int id;
    /**
     * Libelle du noeud
     */
    private String description;

    private static int nbNoeud = 0;

    public Noeud(String description) {
        this.id = nbNoeud;
        nbNoeud++;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){
        return description;
    }
}

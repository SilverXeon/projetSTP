package fr.univ_artois.stp.resolution;

import fr.univ_artois.stp.graph.Graph;
import fr.univ_artois.stp.graph.Noeud;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Classe Resolution Floyd utilisant l'algorihme de Floyd-Warshall
 * Created by Pierre on 14/01/2017.
 */
public class ResolutionFloyd  implements Resolution{
    /**
     * Matrice d'adjacence
     */
    private double[][] adjacence;
    /**
     * Taille des matrice
     */
    private int taille;
    /**
     * Table de correspondance des id des tableaux et des noeud
     */
    private HashMap<Noeud, Integer> correspondance;
    private boolean negativeCycle;


    public ResolutionFloyd(){
        correspondance = new HashMap<>();
    }

    /**
     * Définit la matrice d'adjacence pour le graph G
     * @param g Graphe
     */
    private void setMatriceAdjacence(Graph g){
        taille = g.getListeNoeud().size();
        this.adjacence = new double[g.getListeNoeud().size()][g.getListeNoeud().size()];
        for(int i = 0;i<g.getListeNoeud().size();i++){
            correspondance.put(g.getListeNoeud().get(i), i);
            for(int j = 0;j<g.getListeNoeud().size();j++){
                if(i == j){
                    adjacence[i][j] = 0;
                }
                else{
                    adjacence[i][j] = Double.MAX_VALUE;
                }
            }
        }

        for(int i = 0;i<g.getListeAreteReel().size();i++){
            adjacence[correspondance.get(g.getListeAreteReel().get(i).getSrc())][correspondance.get(g.getListeAreteReel().get(i).getDest())] = g.getListeAreteReel().get(i).getPoids();
        }
    }

    public boolean hasNegativeCycle() {
        return this.negativeCycle;
    }

    /**
     * Resoud le graph par un algorithme de floyd-warshall
     * @return matrice des distances
     */
    public double[][] floydWarshall() {
        double[][] distances;
        int n = this.adjacence.length;
        distances = Arrays.copyOf(this.adjacence, n); // On travaille sur une copie de la matrice

        for (int k = 0; k < n; k++) { // Prend la distance la plus courte
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }

            if (distances[k][k] < 0.0) { // Si la distance de k avec lui même est négative
                this.negativeCycle = true;
            }
        }

        return distances;
    }

    @Override
    public boolean resolution(Graph graph) {
        setMatriceAdjacence(graph);
        double tab[][] = floydWarshall();
        return true;
    }

    /**
     * @return Matrice de distance
     */
    public double[][] getResultat(){
       return floydWarshall();
    }

    @Override
    public String resultat() {
        return null;
    }
}

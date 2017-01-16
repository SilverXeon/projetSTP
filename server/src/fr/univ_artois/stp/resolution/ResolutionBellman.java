package fr.univ_artois.stp.resolution;

import fr.univ_artois.stp.graph.AretePoids;
import fr.univ_artois.stp.graph.Graph;
import fr.univ_artois.stp.graph.Noeud;
import org.jgrapht.WeightedGraph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Classe Resolution Bellman utilisant l'algorihme de Ford-Bellman
 * Created by Pierre on 06/11/2016.
 */
public class ResolutionBellman implements Resolution{
    /**
     * Tableau des distance
     */
    private int dist[];
    /**
     * Tableau des precedent
     */
    private Noeud prev[];
    /**
     * Liste des aretes
     */
    public static LinkedList<AretePoids> areteListe = new LinkedList<>();

    /**
     * @param listNoeud Liste des noeud
     * @param n Noeud à chercher
     * @return id du noeud
     */
    private int getIdInstance(LinkedList listNoeud, Noeud n){
        for(int i = 0;i<listNoeud.size();i++){
            if(n == listNoeud.get(i))
                return i;
        }
        return -1;
    }


    @Override
    public boolean resolution(Graph graph) {
        WeightedGraph cop = graph.getGraphReel();
        dist = new int[graph.getNbNoeud()];
        prev = new Noeud[graph.getNbNoeud()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, null);
        dist[0] = 0;

        for(int i = 0;i<graph.getListeAreteReel().size();i++) {
            if (dist[getIdInstance(graph.getListeNoeud(), graph.getListeAreteReel().get(i).getSrc())] > dist[getIdInstance(graph.getListeNoeud(), graph.getListeAreteReel().get(i).getDest())] + graph.getListeAreteReel().get(i).getPoids()) {
                dist[getIdInstance(graph.getListeNoeud(), graph.getListeAreteReel().get(i).getSrc())] = (int) (dist[getIdInstance(graph.getListeNoeud(), graph.getListeAreteReel().get(i).getDest())] + graph.getListeAreteReel().get(i).getPoids());
                prev[getIdInstance(graph.getListeNoeud(), graph.getListeAreteReel().get(i).getSrc())] = graph.getListeAreteReel().get(i).getSrc();
            }
        }

        if(dist[0] != 0)
            return false;

        for(int i = 0;i<dist.length;i++) {
            if(dist[i] == Integer.MAX_VALUE)
                return false;
            else
                dist[i]  = Math.abs(dist[i]);
        }
        return true;
    }

    @Override
    public String resultat() {
        return "";
    }

    /**
     * @return resultat de la resolution
     */
    public HashMap getResultat(){
        HashMap<String, LinkedList> map = new HashMap<>();
        LinkedList<Integer> distList = new LinkedList();

        for(int i = 0;i<dist.length;i++)
            distList.add(dist[i]);
        map.put("distance", distList);

        return map;
    }
}

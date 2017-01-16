package fr.univ_artois.stp.graph;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Classe AretePoids
 * Represente une arete du graphe réel
 * Created by Pierre on 06/11/2016.
 */
public class AretePoids extends DefaultWeightedEdge{

   public Noeud getSrc(){
       return (Noeud)(super.getSource());
   }

   public Noeud getDest(){
       return (Noeud)(super.getTarget());
   }

   public double getPoids(){
       return super.getWeight();
   }
}

package fr.univ_artois.stp.server;


import fr.univ_artois.stp.graph.Graph;
import fr.univ_artois.stp.graph.Noeud;
import fr.univ_artois.stp.resolution.ResolutionBellman;
import fr.univ_artois.stp.resolution.ResolutionFloyd;
import fr.univ_artois.stp.visualisation.GraphVisu;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Implementation des fonctions de l'interface STPInter
 * Created by Pierre on 28/12/2016.
 * @see STPInter
 */
@WebService(endpointInterface = "fr.univ_artois.stp.server.STPInter")
public class STPImpl implements STPInter{
    /**
     * Graphe de l'instance
     */
    private Graph graph;

    @Override
    public void init() {
        graph = new Graph();
    }

    @Override
    public void addNoeud(String libele) throws Exception {
        for(Noeud n : graph.getListeNoeud()){
            if(n.getDescription().hashCode() == libele.hashCode())
                throw new Exception("Noeud déja present");
        }

        Noeud n = new Noeud(libele);
        graph.addNoeud(n);
    }

    @Override
    public void removeNoeud(String libele) throws Exception {
        for(Noeud n : graph.getListeNoeud()){
            if(n.getDescription().hashCode() == libele.hashCode()) {
                graph.removeNoeud(n);
                return;
            }
        }
        throw new Exception("Noeud non present");
    }

    @Override
    public void addArete(String libeleDep, String libeleArr, int min, int max) throws Exception {
        if(min <0 || max <0)
            throw new Exception("Une durée ne peut pas etre inférieur");

        boolean trouveDep = false;
        boolean trouveFin = false;
        Noeud dep = null, arr = null;
        for(Noeud n : graph.getListeNoeud()){
            if(n.getDescription().hashCode() == libeleDep.hashCode()) {
                trouveDep = true;
                dep = n;
            }
            else if(n.getDescription().hashCode() == libeleArr.hashCode()){
                trouveFin = true;
                arr = n;
            }
        }

        if(trouveDep && trouveFin){
            graph.addArete(dep, arr, min, max);
        }
        else{
            throw new Exception("Un des noeud n'est pas present");
        }
    }

    @Override
    public String resolutionBellman() throws Exception{
        ResolutionBellman r = new ResolutionBellman();
        if(!r.resolution(graph))
            throw new Exception("Graph impossible");
        else{
            HashMap<String, LinkedList<Noeud>> m = r.getResultat();
            String s = "Noeud : \t";
            for (Noeud n : graph.getListeNoeud())
                s += n.getDescription() + "\t";
            s+= "\nDistance : \t";
            for(int i = 0;i<m.get("distance").size();i++)
                s+= m.get("distance").get(i) + "\t";
            s+= "\n";
            return s;
        }
    }

    public String resolutionFloyd(){
        ResolutionFloyd r = new ResolutionFloyd();
        r.resolution(graph);
        double[][] tab = r.floydWarshall();
        String s = "Noeud\t";
        for (Noeud n : graph.getListeNoeud())
            s += n.getDescription() + "\t\t\t";

        for(int i = 0;i<graph.getListeNoeud().size();i++){
            s+="\n"+graph.getListeNoeud().get(i).getDescription()+"\t";
            for(int j = 0;j<tab[i].length;j++){
                s+=Math.abs(tab[i][j])+"\t\t";
            }
        }
        return s;
    }

    @Override
    public String toString() {
        return graph.toString();
    }

    @Override
    public byte[] affGraph() {
        while(GraphVisu.onUse){
            try{
                Thread.sleep(20);
            }catch(InterruptedException e){
                System.err.println(e.getMessage());
                return null;
            }
        }
        GraphVisu g = new GraphVisu(graph);
        BufferedImage image = g.getImageOfGraph();
        byte[] tab = null;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", out);
            out.flush();
            tab = out.toByteArray();
            out.close();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }

        return tab;
    }
}

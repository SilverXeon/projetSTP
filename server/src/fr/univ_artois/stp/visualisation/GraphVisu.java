package fr.univ_artois.stp.visualisation;

/**
 * Created by Pierre on 28/12/2016.
 */

import fr.univ_artois.stp.graph.Arete;
import fr.univ_artois.stp.graph.Graph;
import fr.univ_artois.stp.graph.Noeud;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgrapht.ext.JGraphModelAdapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.undo.UndoableEdit;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Classe generant la visualisation du graphe
 */
public class GraphVisu extends JApplet{
    private JGraphModelAdapter<Noeud, Arete> jgAdapter;
    /**
     * Couleur de fond
     */
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    /**
     * Dimension de l'image
     */
    private static final Dimension DEFAULT_SIZE = new Dimension(1000, 1000);
    private static JFrame frame = new JFrame();
    /**
     * Classe en cours d'utilisation
     */
    public static boolean onUse = false;

    /**
     * Initialise la classe de visualisation
      * @param g graph à visualiser
     */
    public GraphVisu(Graph g){
        onUse = true;
        init(g);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(this);
        frame.setTitle("JGraphT Adapter to JGraph Demo");
        //frame.setDefaultCloseOperation(3);
        frame.pack();
        //frame.setLocation(-2000, -2000);
        frame.setVisible(true);
    }

    /**
     * @return image du graph
     */
    public BufferedImage getImageOfGraph(){
        try{
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            this.paint(graphics);
            graphics.dispose();
            frame.setVisible(false);
            onUse = false;
            return image;
        }
        catch(Exception exc){
            System.err.println(exc.getMessage());
        }
        onUse = false;
        return null;
    }

    /**
     * Initialisation de la JFrame
     * @param g
     */
    public void init(Graph g){
        this.jgAdapter = new JGraphModelAdapter<Noeud, Arete>(g.getGraphAff());
        JGraph jgraph = new JGraph(this.jgAdapter);
        this.adjustDisplaySettings(jgraph);
        this.getContentPane().add(jgraph);
        this.resize(DEFAULT_SIZE);
        int cptHoriz = 0;
        for(int i = 0;i<g.getListeNoeud().size();i++){
            positionVertexAt(g.getListeNoeud().get(i), (int)(300+(Math.sin(cptHoriz)*250)), (int)(300+(Math.cos(cptHoriz) * 250)));
            cptHoriz++;
        }
    }

    /**
     * Ajuste les parametres de la JFrame
     * @param jg
     */
    private void adjustDisplaySettings(JGraph jg) {
        jg.setPreferredSize(DEFAULT_SIZE);
        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = this.getParameter("bgcolor");
        } catch (Exception var5) {
        }

        if(colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }

    /**
     * Definit la position d'un noeud
     * @param vertex Noeud
     * @param x position x
     * @param y position y
     */
    private void positionVertexAt(Object vertex, int x, int y) {
        DefaultGraphCell cell = this.jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);
        //Double newBounds = new Double((double)x, (double)y, bounds.getWidth(), bounds.getHeight());
        bounds.setRect((double)x, (double)y, bounds.getWidth(), bounds.getHeight());
        GraphConstants.setBounds(attr, bounds);
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        this.jgAdapter.edit(cellAttr, (ConnectionSet)null, (ParentMap)null, (UndoableEdit[])null);
    }
}

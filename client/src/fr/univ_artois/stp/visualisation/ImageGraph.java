package fr.univ_artois.stp.visualisation;

import javax.swing.*;


/**
 * Classe affichant un graphe
 * Created by Pierre on 10/01/2017.
 */
public class ImageGraph {
    private JFrame frame;

    /**
     * Construit la fenetre a partir d'un tableau de byte
     * @param tab image sous forme de byte
     */
    public ImageGraph(byte[] tab) {
        frame = new JFrame();
        frame.setTitle("Visualisation");
        frame.getContentPane().add(new JLabel(new ImageIcon(tab)));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    /**
     * Affiche la fenetre
     */
    public void afficher(){
        frame.setVisible(true);
    }
}

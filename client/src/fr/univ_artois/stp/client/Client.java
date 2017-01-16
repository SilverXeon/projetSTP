package fr.univ_artois.stp.client;

import fr.univ_artois.stp.server.STPInter;
import fr.univ_artois.stp.visualisation.ImageGraph;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Classe Client, point d'entrée du programme client
 * Created by Pierre on 27/12/2016.
 */
public class Client {
    /**
     * Instance du serveur distant
     */
    private STPInter inst;

    /**
     * Constructeur Client
     * Initialise la connexion avec le serveur distant et initialise l'instance
     */
    public Client() {
        try{
            URL url = new URL("http://localhost:9999/stp?wsdl");
            QName qname = new QName("http://server.stp.univ_artois.fr/", "STPImplService");

            Service service = Service.create(url, qname);
            this.inst = service.getPort(STPInter.class);

            this.inst.init();
        }catch(MalformedURLException e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Main affichant un menu permettant de faire fonctionner le programme
     * @param args argument du programme
     */
    public static void main(String[] args){
        //Menu : Ajouter Noeud, retirer Noeud, Resoudre, Afficher, Quitter
        Client client = new Client();
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("STP Resolver\nCommandes disponibles : ajoutnoeud, retirernoeud, ajoutarete, resoudre, afficher, exit\nCommande> ");
            String str = sc.nextLine();
            switch (str.toLowerCase()) {
                case "ajoutnoeud":
                    System.out.print("Entrer le nom du noeud (pas d'espace, utilisez des _) > ");
                    String sAdd = sc.nextLine();
                    client.addNoeud(sAdd);
                    break;
                case "retirernoeud":
                    System.out.print("Entrer le nom du noeud (pas d'espace, utilisez des _) > ");
                    String sRemove = sc.nextLine();
                    client.removeNoeud(sRemove);
                    break;
                case "ajoutarete":
                    System.out.print("Entrer le nom du noeud de départ (pas d'espace, utilisez des _) > ");
                    String sDepart = sc.nextLine();
                    System.out.print("Entrer le nom du noeud d'arrivée (pas d'espace, utilisez des _) > ");
                    String sArrivee = sc.nextLine();
                    System.out.print("Entrer le temps minimum entre ces activités > ");
                    int min = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Entrer le temps maximum entre ces activités > ");
                    int max = sc.nextInt();
                    sc.nextLine();
                    client.addArete(sDepart, sArrivee, min, max);
                    break;
                case "resoudre":
                    System.out.print("Choisissez un algorithme dans la liste suivante : ");
                    System.out.print("Bellman, Floyd-Warshall");
                    System.out.print("> ");
                    String resol = sc.nextLine();
                    client.resolution(resol);
                    break;
                case "afficher":
                    client.afficher();
                    break;
                case "exit":

                    System.exit(0);
                    break;
                default:
                    System.err.println("Commande introuvable");
                    break;
            }
        }
    }

    /**
     * Ajoute un noeud au graphe
     * @param s nom du noeud
     */
    public void addNoeud(String s){
        try{
            inst.addNoeud(s);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Retire un noeud au graphe
     * @param s nom du noeud
     */
    public void removeNoeud(String s){
        try{
            inst.removeNoeud(s);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Ajoute une arete entre deux noeud du graphe
     * @param s1 nom du noeud de départ
     * @param s2 nom du noeud d'arrivée
     * @param min temps minimum
     * @param max temps maximum
     */
    public void addArete(String s1, String s2, int min, int max){
        try{
            inst.addArete(s1, s2, min, max);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Affiche le graphe dans une nouvelle fenetre
     */
    public void afficher(){
        ImageGraph g = new ImageGraph(inst.affGraph());
        g.afficher();
    }

    /**
     * Resoud le graphe
     * @param resolution nom de la methode de resolution
     */
    public void resolution(String resolution){
        switch (resolution.toLowerCase()){
            case "bellman":
                try {
                    System.out.println(inst.resolutionBellman());
                }
                catch (Exception e){
                    System.err.println(e.getMessage());
                }
                break;
            case "floyd-warshall":
                System.out.println(inst.resolutionFloyd());
                break;
            default:
                System.err.println("Algorithme introuvable");
                break;
        }
    }
}

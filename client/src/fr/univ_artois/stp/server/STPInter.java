package fr.univ_artois.stp.server;



import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/**
 * Interface STPInter
 * Définit les fonctions utilisables sur le serveur distant
 * Created by Pierre on 28/12/2016.
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface STPInter {
    //Initialisation

    /**
     * Initialise une instance
     */
    @WebMethod void init();

    //Graph

    /**
     * Ajoute un noeud au graphe
     * @param libele nom du noeud
     * @throws Exception noeud déja présent
     */
    @WebMethod void addNoeud(String libele) throws Exception;
    /**
     * Retire un noeud au graphe
     * @param libele nom du noeud
     * @throws Exception noeud non présent
     */
    @WebMethod void removeNoeud(String libele) throws Exception;

    /**
     * Ajoute une arete entre deux noeud du graphe
     * @param libeleDep nom du noeud de départ
     * @param libeleArr nom du noeud de départ
     * @param min temps minimum
     * @param max temps maximum
     * @throws Exception erreur de création
     */
    @WebMethod void addArete(String libeleDep, String libeleArr, int min, int max) throws Exception;

    /**
     * @return graphe au format texte
     */
    @WebMethod String toString();

    /**
     * @return tableau de byte correspondant à une instance de la classe BufferedImage
     */
    @WebMethod byte[] affGraph();


    //Resolution

    /**
     * Resoud le graphe par un algorithme de ford-bellman
     * @return resultat de la resolution
     * @throws Exception graphe impossible
     */
    @WebMethod String resolutionBellman() throws Exception;

    @WebMethod String resolutionFloyd();
}

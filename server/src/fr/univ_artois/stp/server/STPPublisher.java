package fr.univ_artois.stp.server;

/**

 */
import javax.xml.ws.Endpoint;

/**
 * Point d'entrée du programme serveur
 * Created by Pierre on 28/12/2016.
 */
public class STPPublisher {
    /**
     * Publie le service sur le web
     * @param args argument du programme
     */
    public static void main(String[] args) {
            Endpoint.publish("http://localhost:9999/stp", new STPImpl());
    }
}

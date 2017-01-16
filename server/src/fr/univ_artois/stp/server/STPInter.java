package fr.univ_artois.stp.server;

/**
 * Created by Pierre on 28/12/2016.
 */

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface STPInter {
    @WebMethod void init();

    //Graph
    @WebMethod void addNoeud(String libele) throws Exception;
    @WebMethod void removeNoeud(String libele) throws Exception;
    @WebMethod void addArete(String libeleDep, String libeleArr, int min, int max) throws Exception;
    @WebMethod String toString();
    @WebMethod byte[] affGraph();

    //Resolution
    @WebMethod
    String resolutionBellman() throws Exception;
    @WebMethod String resolutionFloyd();
}

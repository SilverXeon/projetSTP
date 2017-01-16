package test.fr.univ_artois.stp.server;

import fr.univ_artois.stp.server.STPImpl;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Pierre on 12/01/2017.
 */
public class STPImplTest extends TestCase {
    private STPImpl imp;
    public void setUp() throws Exception {
        super.setUp();
        imp = new STPImpl();
        imp.init();
        imp.addNoeud("x0");
        imp.addNoeud("x1");
        imp.addNoeud("x2");
        imp.addNoeud("x3");
        imp.addNoeud("x4");
        imp.addArete("x0", "x1", 10, 20);
        imp.addArete("x1", "x2", 30, 40);
        imp.addArete("x3", "x2", 0, 20);
        imp.addArete("x3", "x4", 40, 50);
        imp.addArete("x0", "x4", 50, 70);
    }

    public void testAddNoeud(){
        try {
            imp.addNoeud("x0");
            fail("Exception non lancée");
        }catch(Exception e){

        }
    }


    public void testRemoveNoeud(){
        try{
            imp.removeNoeud("x6");
            fail("Exception non lancée");
        }catch(Exception e){

        }
    }

    public void testAddArete(){
        try{
            imp.addArete("x0", "x1", -10, 20);
            fail("Exception non lancée");
        }catch (Exception e){

        }
        try{
            imp.addArete("x6", "x1", -10, 20);
            fail("Exception non lancée");
        }catch (Exception e){

        }
        try{
            imp.addArete("x0", "x6", -10, 20);
            fail("Exception non lancée");
        }catch (Exception e){

        }
    }

    public void testResolutionBellman() throws Exception {
        String s = imp.resolutionBellman();
        s = s.split("\n")[1];
        assertTrue("Resolution non exacte", s.equals("Distance : \t0\t10\t40\t20\t60\t"));
    }

}
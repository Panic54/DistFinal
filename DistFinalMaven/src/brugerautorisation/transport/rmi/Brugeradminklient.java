package brugerautorisation.transport.rmi;

import brugerautorisation.data.Diverse;
import brugerautorisation.transport.rmi.Brugeradmin;
import brugerautorisation.transport.rmi.Brugeradminklient;

import brugerautorisation.data.Bruger;
import brugerautorisation.data.DataTyper;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class Brugeradminklient {

    public Brugeradmin ba;

  //  public Brugeradminklient() {
    //}

    /*
	public static void main(String[] arg) throws Exception {
//		Brugeradmin ba =(Brugeradmin) Naming.lookup("rmi://localhost/brugeradmin");
		
                Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

                Scanner scan = new Scanner(System.in);
                      
                String userName = scan.nextLine();
                String passWord = scan.nextLine();
                
		Bruger b = ba.hentBruger(userName, passWord);
                System.out.println(b.adgangskode);
               
		System.out.println("Fik bruger = " + b);
		System.out.println("Data: " + Diverse.toString(b));
                
		 //ba.sendEmail("154102", "kodeomi6ag", "Hurra det virker!", "Jeg er så glad");
                 /*
		Object ekstraFelt = ba.getEkstraFelt("s123456", "kode1xyz", "hobby");
		System.out.println("Brugerens hobby er: " + ekstraFelt);
       
                   //ba.sendGlemtAdgangskodeEmail("s154102", "Dette er en test, husk at skifte kode");
		//ba.ændrAdgangskode("s154102", "abcd1234", "abc123");
		ba.setEkstraFelt("s154102", "kodeomi6ag", "Intet", "Fritid"); // Skriv noget andet her

		String webside = (String) ba.getEkstraFelt("s123456", "kode1xyz", "webside");
		System.out.println("Brugerens webside er: " + webside);
                 
	} */
    @Path("/test")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean login(DataTyper d) {
        Bruger b;
        //String username = "s154102", password = "abc123";
        System.out.println(d.getUsername() + " " + d.getPassword());
        try {
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        } catch (NotBoundException ex) {
            Logger.getLogger(Brugeradminklient.class.getName()).log(Level.SEVERE, null, ex);
            return false;//Response.status(200).build();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Brugeradminklient.class.getName()).log(Level.SEVERE, null, ex);
            return false;//Response.status(200).build();
        } catch (RemoteException ex) {
            Logger.getLogger(Brugeradminklient.class.getName()).log(Level.SEVERE, null, ex);
            return false;//Response.status(200).build();
        }

        try {
            b = ba.hentBruger(d.getUsername(), d.getPassword());
            System.out.println(b.adgangskode);
            System.out.println("Fik bruger = " + b);
            System.out.println("Data: " + Diverse.toString(b));
        } catch (RemoteException ex) {
            Logger.getLogger(Brugeradminklient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}

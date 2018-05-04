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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//
// JWT imports
//

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

@Path("/rest2")
public class Brugeradminklient {

    public Brugeradmin ba;
    Key key = MacProvider.generateKey();

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
    @Path("/javabog")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean login(DataTyper d) {
    	
        Bruger b;
        //String username = "s154102", password = "abc123";
        //System.out.println(d.getUsername() + " " + d.getPassword());
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
    
    @Path("/build")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String build(DataTyper d) {
    	
        //Laver JWT
        String compactJws = Jwts.builder()
        		.setSubject(d.getUsername())
        		.signWith(SignatureAlgorithm.HS512, key)
        		.compact();
        
        //Printer krypteret og ikke krypteret JWT ud...
        System.out.println("\nKrypteret JWT: " + compactJws);
        String decoded = Jwts.parser().setSigningKey(key).parse(compactJws).toString();
        System.out.println("Dekrypteret JWT: " + decoded);
        
        return compactJws;
    	
    }
    
    
    @Path("/buy")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean buy(DTO d) {
    	DTO dto = d;

    	System.out.println("Du k�bte: " + d.getName());

    	
    	return true;
    }
    
    @Path("/sell")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean sell(DTO d) {
    	//DTO dto = d;
    	
    	System.out.println("Du solgte: " + d.getName() + " til prisen: " + d.getPrice());

    	
    	return true;
    }
    
    @Path("/validate")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public boolean JWT(String s) {
    	
    	System.out.println("token: " + s);
    	
    	try {

    	    Jwts.parser().setSigningKey(key).parseClaimsJws(s);

    	    //OK, we can trust this JWT
    	    return true;

    	} catch (SignatureException e) {

    	    //don't trust the JWT!
    		return false;
    	}
    	
    }
    
    
}

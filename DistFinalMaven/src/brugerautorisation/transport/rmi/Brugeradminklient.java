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
import javax.ws.rs.core.Response.Status;

import io.jsonwebtoken.Jwt;

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
    static Key key = MacProvider.generateKey();

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
        	//	.setExpiration(time)
        		.signWith(SignatureAlgorithm.HS512, key)
        		.compact();
        
        System.out.println(key);
        
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
    public Response JWT(String token) {
    	
    	if (token.equals(null) || token.equals(""))
    		return Response.status(Status.FORBIDDEN).build();
    	
    	System.out.println(token);
    	System.out.println(key);
    	
    	//System.out.println("token: " + s);
    	String subject = "HACKER";
    	
    	try {

    		//Hvis token ikke kan parses, da bliver der throwet en SignatureException
    		subject = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    	  
    		System.out.println(subject);
    	    //OK, we can trust this JWT
    	    return Response.status(Status.ACCEPTED).build();

    	} catch (SignatureException e) {

    		System.out.println("exception: " + e.getMessage());
    		
    	    //don't trust the JWT!
    		return Response.status(Status.FORBIDDEN).build();
    	}
    	
    }
    
    
}

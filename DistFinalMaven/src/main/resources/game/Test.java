package game;

import brugerautorisation.transport.rmi.Brugeradmin;
import brugerautorisation.transport.rmi.BrugeradminImpl;
import brugerautorisation.transport.rmi.Brugeradminklient;
import java.rmi.Naming;
import java.util.Scanner;

public class Test {
 
    public static void main (String[] args) throws Exception {
        
                Scanner scan = new Scanner (System.in);
                
                java.rmi.registry.LocateRegistry.createRegistry(11114);
                
                Brugeradmin k = new BrugeradminImpl();
                
                System.setProperty("java.rmi.server.hostname", "http://javabog.dk");
                Naming.rebind("rmi://localhost:11114/galgeserver", k);
                
                System.out.println("Registeret");
                //Brugeradmin ba =(Brugeradmin) Naming.lookup("rmi://localhost:11112/brugeradmin");	
		//String name = scan.nextLine();
		//String password = scan.nextLine();

		Brugeradminklient test = new Brugeradminklient();
		//System.out.println(test.login("s154102", "abc123"));	 

    }
}

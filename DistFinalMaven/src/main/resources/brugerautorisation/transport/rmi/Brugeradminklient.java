package transport.rmi;
import data.Bruger;
import data.DataTyper;
import data.Diverse;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import transport.rmi.Brugeradmin;


public class Brugeradminklient {

    public Brugeradmin ba;
    DataTyper d = new DataTyper("hejs","heks"); 
    
   /* public Brugeradminklient() {   	
   
    } */

   
    
	/*public static void main(String[] arg) throws Exception {
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
                 
	} 
*/
    //@POST
   // @Consumes(MediaType.APPLICATION_JSON)
    public boolean login() {
        Bruger b;
       
        try {
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        } catch (NotBoundException ex) {
            Logger.getLogger(Brugeradminklient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Brugeradminklient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (RemoteException ex) {
            Logger.getLogger(Brugeradminklient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        try {
            b = ba.hentBruger("hej", "hej indsæt username og password her");
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

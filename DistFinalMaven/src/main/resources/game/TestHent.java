/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import brugerautorisation.transport.rmi.Brugeradmin;
import brugerautorisation.transport.rmi.Brugeradminklient;
import java.rmi.Naming;

/**
 *
 * @author madsfinnerup
 */
public class TestHent {
    public static void main (String[] args) throws Exception { 
      Brugeradminklient hej = new Brugeradminklient();
      
      System.out.println(hej.login("s154102", "abc123"));
        
    //Brugeradmin ba =(Brugeradmin) Naming.lookup("rmi://javabog.dk:11114/galgeserver");
    //ba.hentBruger("s154102", "abc123");   
    }
}

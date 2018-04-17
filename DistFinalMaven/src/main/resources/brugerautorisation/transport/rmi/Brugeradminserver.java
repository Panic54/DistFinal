package brugerautorisation.transport.rmi;
import brugerautorisation.server.Brugerdatabase;
import java.io.IOException;
import java.rmi.Naming;
public class Brugeradminserver
{
	public static void main(String[] arg) throws IOException
	{
		//Brugerdatabase db = Brugerdatabase.getInstans();
		//System.out.println("Publicerer Brugeradmin over RMI");
		Brugeradmin impl = new BrugeradminImpl();
		//impl.db = db;
		System.setProperty("java.rmi.server.hostname", "http://javabog.dk");
		java.rmi.registry.LocateRegistry.createRegistry(1999); // start rmiregistry i server-JVM
		Naming.rebind("rmi://localhost/brugeradmin", impl);
		System.out.println("Brugeradmin publiceret over RMI");
	}
}
/* Overfør til server med f.eks.:

cd /home/j/DistribueredeSystemer/DistribueredeSystemer/
ant -q
rsync -a dist/* deltagere.html gmail-adgangskode.txt  javabog.dk:DistribueredeSystemer/

// På serveren javabog.dk - start med
cd DistribueredeSystemer
java -jar DistribueredeSystemer.jar
*/

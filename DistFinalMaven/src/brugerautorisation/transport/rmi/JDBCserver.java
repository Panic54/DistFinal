package brugerautorisation.transport.rmi;

import javax.xml.ws.Endpoint;

public class JDBCserver {

	public static void main(String[] args) {
		System.out.println("publicerer kontotjeneste");
		JDBCI data = new JDBC();
		Endpoint.publish("http://ec2-18-188-46-76.us-east-2.compute.amazonaws.com:11112/JDBCxml?wsdl", data);
		System.out.println("JDBC server er startet.");

	}

}

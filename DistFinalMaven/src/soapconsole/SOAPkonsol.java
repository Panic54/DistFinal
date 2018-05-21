package soapconsole;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import brugerautorisation.transport.rmi.DTO;
import javax.xml.ws.Service;

import soapconsole.JDBCI;

import javax.xml.namespace.QName;

public class SOAPkonsol {

	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://ec2-18-188-46-76.us-east-2.compute.amazonaws.com:11112/JDBCxml?wsdl");
		QName qname = new QName("http://server/", "JDBCService");
		Service service = Service.create(url, qname);
		JDBCI data = service.getPort(JDBCI.class);

		Scanner Keyboard = new Scanner(System.in);
		int Value = 1;
		while (Value != 0) {
			System.out.println("--------------Database Admin Program--------------");
			System.out.println("Write 1 to show current table");
			System.out.println("Write 2 to delete all records from a user");
			System.out.println("Write 3 to add a row to the database");
			System.out.println("Write 4 to delete a specific item");
			System.out.println("Write 0 to exit console program and cleanup");
			System.out.println("--------------------------------------------------");
			System.out.println();
			Value = Keyboard.nextInt();
			Keyboard.nextLine();

			switch (Value) {
			case 1:
				for (DTO i : data.getTable()) {
					System.out.println("Sælger  : " + i.getName());
					System.out.println("Tilsalg : " + i.getItem());
					System.out.println("Pris    : " + i.getPrice() + "\n");
				}
				break;
			case 2:
				String name2;
				System.out.println("Enter name of owner");
				name2 = Keyboard.nextLine();
				data.delete(name2);
				break;
			case 3:
				String name1, item;
				int price;
				System.out.println("Write name");
				name1 = Keyboard.nextLine();
				System.out.println("Write item");
				item = Keyboard.nextLine();
				System.out.println("Write price");
				price = Keyboard.nextInt();
				System.out.println("Adding row");
				data.addRow(name1, item, price);
				break;
			case 4:
				String name3, item2;
				System.out.println("Write name");
				name3 = Keyboard.nextLine();
				System.out.println("Write item");
				item2 = Keyboard.nextLine();
				data.deleteItem(name3, item2);
				break;
			case 0:
				System.out.println("Cleaing up");
				Keyboard.close();
				// data.close();
				System.out.println("Closing console");
				break;
			default:
				System.out.println("This is not an approved value");
				break;

			}
		}
	}
}

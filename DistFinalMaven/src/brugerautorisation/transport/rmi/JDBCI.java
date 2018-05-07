package brugerautorisation.transport.rmi;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface JDBCI {

	@WebMethod
	ArrayList<DTO> getTable();

	@WebMethod
	void addRow(String name, String item, int price);
	
	@WebMethod
	void close();
	
	@WebMethod
	public void delete(String name);
	
	@WebMethod
	public void deleteItem(String name, String item);

}

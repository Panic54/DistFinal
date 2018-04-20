package transport.rmi;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {

	Connection myConn;
	Statement statement;
	ResultSet res;

	JDBC(){

		try{

			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gruppe50", "root", "");
			statement = myConn.createStatement();

			//res = statement.executeQuery("Some Query");
			//while(res.next()){
			//	System.out.println(res.getString("user") + ", " + res.getString("item") + ", " + res.getString("price"));
			//}	

		}
		catch(SQLException e){
			e.printStackTrace();
		}

	}

	public ArrayList<DTO> getTable(){

		ArrayList<DTO> dto = new ArrayList<DTO>();

		try {
			res = statement.executeQuery("SELECT * FROM items");

			while(res.next()){
				dto.add(new DTO(res.getString("user"), res.getString("item"), res.getInt("price")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dto;
	}

	//Virker måske ikke?
	public void addRow(String name, String item, int price){

		try {
			statement.execute("INSERT INTO items VALUES(" + name + ", " + item + ", " + price);
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}


}

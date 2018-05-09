package server;

import java.sql.*;
import java.util.ArrayList;

import javax.jws.WebService;

import server.DTO;
import server.JDBCI;

@WebService(endpointInterface = "server.JDBCI")
public class JDBC implements JDBCI {

	Connection myConn;
	Statement statement;
	ResultSet res;

	JDBC() {

		try {

			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gruppe50", "root", "");
			statement = myConn.createStatement();

			// res = statement.executeQuery("Some Query");
			// while(res.next()){
			// System.out.println(res.getString("user") + ", " + res.getString("item") + ",
			// " + res.getString("price"));
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	public ArrayList<DTO> getTable() {

		ArrayList<DTO> list = new ArrayList<DTO>();

		try {
			res = statement.executeQuery("SELECT * FROM items ORDER BY user");

			while (res.next()) {
				DTO dto = new DTO();
				dto.setName(res.getString("user"));
				dto.setItem(res.getString("item"));
				dto.setPrice(res.getInt("price"));
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public void addRow(String name, String item, int price) {

		try {
			statement.execute("INSERT INTO items VALUES('" + name + "', '" + item + "', " + price + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	@Override
	public void close() {
		try {
			res.close();
			statement.close();
			if (myConn != null) {
				myConn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(String name) {
		try {
			statement.execute("DELETE FROM items WHERE user = " + "'" + name + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteItem(String name, String item) {
		try {
			statement.execute("DELETE FROM items WHERE user = " + "'" + name + "' AND item =" + "'" + item + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

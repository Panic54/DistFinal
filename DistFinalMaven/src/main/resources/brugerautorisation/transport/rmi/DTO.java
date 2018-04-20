package transport.rmi;

import java.util.ArrayList;

public class DTO {
	
	String names;
	String items;
	int prices;
	
	DTO(String names, String items, int prices){
		
		this.names = names;
		this.items = items;
		this.prices = prices;
		
	}

	public String getNames(){
		return names;
	}
	
	public void setNames(String names){
		this.names = names;
	}

	
	public String getItems(){
		return items;
	}
	
	public void setItems(String items){
		this.items = items;
	}
	
	public int getPrices(){
		return prices;
	}
	
	public void setPrices(int prices){
		this.prices = prices;
	}
	
}

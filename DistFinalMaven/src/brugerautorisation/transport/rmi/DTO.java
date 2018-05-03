package brugerautorisation.transport.rmi;

import java.util.ArrayList;

public class DTO {

	private String name;
	private String item;
	private int price;

	DTO(String name, String item, int price){

		this.name = name;
		this.item = item;
		this.price = price;

	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}


	public String getItem(){
		return item;
	}

	public void setItem(String item){
		this.item = item;
	}

	public int getPrice(){
		return price;
	}

	public void setPrice(int price){
		this.price = price;
	}

}
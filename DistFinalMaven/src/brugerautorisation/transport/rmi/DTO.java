package brugerautorisation.transport.rmi;

public class DTO {

	private String name;
	private String item;
	private int price;

	DTO(){
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
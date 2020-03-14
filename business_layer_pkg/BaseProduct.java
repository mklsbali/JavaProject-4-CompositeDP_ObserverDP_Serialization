package ro.utcn.tp.assig4.business_layer_pkg;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseProduct extends MenuItem implements Serializable{



	private int id;
	private String name;
	private float price;
	public BaseProduct(int id, String name, float price)
	{
		super(id,name,price);
		this.id=id;
		this.name=name;
		this.price=price;
	}
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public float computePrice() {
		return price;
	}
	
}

package ro.utcn.tp.assig4.business_layer_pkg;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements Serializable{
	private int id;
	private String name;
	private float price;
	private ArrayList<MenuItem> products=new ArrayList<MenuItem>();
	public float computePrice() {
		for (MenuItem m:products)
			price+=m.computePrice();
		return price;
	}
	public CompositeProduct(int id, String name, float price){
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

	public float getPrice() {
		return price;
	}
	public ArrayList<MenuItem> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<MenuItem> products) {
		this.products = products;
	}
	
	
	

}

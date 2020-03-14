package ro.utcn.tp.assig4.data_layer_pkg;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ro.utcn.tp.assig4.business_layer_pkg.MenuItem;
import ro.utcn.tp.assig4.business_layer_pkg.Order;
import ro.utcn.tp.assig4.business_layer_pkg.Restaurant;



public class FileWriter {
	private Restaurant restaurant;
	public FileWriter(Restaurant r)
	{
		restaurant=r;
	}
	public  void writeBill() {
		
		
		
		try {
			PrintWriter writer = new PrintWriter("Bill.txt", "UTF-8");
			for (Order o:restaurant.getOrder().keySet())
			{
				writer.println("Order ID: "+o.getOrderID());
				writer.println("Table nr"+o.getTable());
				writer.println("ORder date:"+o.getDate());
				String ProductIDs="";
				String ProductName="";
				float prprice=0;
				for (MenuItem m:restaurant.getOrder().get(o)) {
					ProductIDs+=m.getId()+" ";
					ProductName+=m.getName()+" ";
					prprice+=m.computePrice();
				}
				writer.println("Product IDs: "+ProductIDs);
				writer.println("Product Name: "+ProductName);
				writer.println("Toal price: "+prprice);
				writer.println("");
			}
			writer.close();
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		
	}
}

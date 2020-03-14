package ro.utcn.tp.assig4.business_layer_pkg;

import java.util.ArrayList;

public interface RestaurantProcessing {

	public void createNewMenuItem(MenuItem m);
	public void deleteMenuItem(int ID);
	public void editMenuItem(int ID, MenuItem newItem);
	public void createNewOrder(Order o, ArrayList<MenuItem> m, int[] menuID);
	public float computePriceForAnOrder(Order o);
	public String generateBill(Order order);
}

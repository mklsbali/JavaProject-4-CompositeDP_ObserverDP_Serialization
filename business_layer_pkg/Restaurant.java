package ro.utcn.tp.assig4.business_layer_pkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


public class Restaurant extends Observable implements RestaurantProcessing{
	private Map<Order,ArrayList<MenuItem>> orders=new HashMap<Order, ArrayList<MenuItem>>();
	private ArrayList<MenuItem> menu=new ArrayList<MenuItem>();
	private ArrayList<MenuItem>baseProducts=new ArrayList<MenuItem>();
	private ArrayList<MenuItem>compositeProducts=new ArrayList<MenuItem>();
	public static String newlyAdded;
	public ArrayList<MenuItem> getBaseProducts() {
		return baseProducts;
	}
	public void setBaseProducts(ArrayList<MenuItem> baseProducts) {
		this.baseProducts = baseProducts;
	}
	public ArrayList<MenuItem> getCompositeProducts() {
		return compositeProducts;
	}
	public void setCompositeProducts(ArrayList<MenuItem> compositeProducts) {
		this.compositeProducts = compositeProducts;
	}
	public ArrayList<MenuItem> getMenu() {
		return menu;
	}
	
	public void createNewMenuItem(MenuItem m) {
		assert m!=null:"Nu este acceptat un obiect neinitializat";
		if (m.getClass().getSimpleName().equals("BaseProduct"))
			baseProducts.add(m);
		if (m.getClass().getSimpleName().equals("CompositeProduct"))
			compositeProducts.add(m);
		menu.add(m);
	}
	public void deleteMenuItem(int ID) {
		int succes=0;
		for (MenuItem m:menu) {
			if(m.getId()==ID)
			{
				menu.remove(m);
				succes=1;
				return;
			}
				
		}
		assert succes!=0:"Nu a fost gasit produsul";
		
	}
	
	public void editMenuItem(int ID, MenuItem newMenu) {
		int succes=0;
		for (MenuItem m:menu) {
			if (m.getId()==ID)
			{
				menu.set(--ID, newMenu);
				succes=1;
				break;
			}
				
		}
		assert succes!=0:"Nu a fost gasit produsul!";
	}
	public void createNewOrder(Order order, ArrayList <MenuItem>item, int[] menuID) {
		int succes=0;
		for (int i=0;i<menuID.length;i++) {
			for (MenuItem m:menu) {
				if (m.getId()==menuID[i])
				{
					item.add(m);
					newlyAdded=m.getName();
					setChanged();
					notifyObservers();
					succes=1;
					break;
				}
			}
		}
		if (item.size()!=0)
			orders.put(order, item);
		assert succes!=0:"Nu a fost gasit produsul!";
		
	}
	public float computePriceForAnOrder(Order order) {
		ArrayList<MenuItem> currentOrder=orders.get(order);
		float price=0;
		for (MenuItem item:currentOrder) {
			price+=item.getPrice();
		}
		return price;
	}
	public String generateBill(Order order) {
		String s="";
		for (Order o:orders.keySet()) {
			if (o.equals(order)){
				s+=("Order ID: "+o.getOrderID()+System.lineSeparator());
				s+=("Table number: "+o.getTable()+System.lineSeparator());
				s+=("Order date: "+o.getDate()+System.lineSeparator());
				String orderName="";
				for (MenuItem m:orders.get(o)) {
					orderName+=(m.getName()+" ");
				}
				s+=("Ordered Items: "+orderName+System.lineSeparator());
				s+=("Total price: "+computePriceForAnOrder(o)+System.lineSeparator());
				s+=System.lineSeparator();
				break;
			}
		}
		return s;
	}
	public Map<Order,ArrayList<MenuItem>> getOrder() {
		return orders;
	}
	public void setOrder(Map<Order,ArrayList<MenuItem>> order) {
		this.orders = order;
	}
	public void setMenu(ArrayList<MenuItem> menu) {
		this.menu=menu;
	}

}

package ro.utcn.tp.assig4.business_layer_pkg;

public class Order {
	private int orderID;
	private String date;
	private int table;
	public Order(int id, String date, int table)
	{
		orderID=id;
		this.date=date;
		this.table=table;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTable() {
		return table;
	}
	public void setTable(int table) {
		this.table = table;
	}
}

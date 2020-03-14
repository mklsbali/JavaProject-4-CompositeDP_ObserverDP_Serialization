package ro.utcn.tp.assig4.presentation_layer_pkg;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow;

import javax.swing.*;

import ro.utcn.tp.assig4.business_layer_pkg.MenuItem;
import ro.utcn.tp.assig4.business_layer_pkg.Order;
import ro.utcn.tp.assig4.business_layer_pkg.Restaurant;
import ro.utcn.tp.assig4.data_layer_pkg.FileWriter;

public class WaiterGUI extends JFrame{
	private Restaurant restaurant;
	private FileWriter fw;
	private JPanel mainPanel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JTextField addNewOrdertable;
	private JTextField addNewOrderdate;
	private JTextField addNewOrderproductID;
	private JTextArea billTextArea;
	private JTextField computeBillForAnOrderID;
	
	private JButton addNewOrderB;
	private JButton viewOrdersB;
	private JButton computeBill;
	
	private JTable orderTable;
	private JScrollPane sp;
	
	private int orderID=0;
	
	public WaiterGUI(Restaurant restaurant) {
		this.restaurant=restaurant;
		fw=new FileWriter(restaurant);
		
		
		
		new JFrame("Waiter_GUI");
		setSize(750, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createMainPanel();
		createPanel1();
		createPanel2();
		createPanel3();
		createPanel4();
		createPanel5();
		setContentPane(mainPanel);
		setVisible(true);
		addActonListeners();
		
	}
	public void createMainPanel() {
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	}
	public void createPanel1() {
		createJTable();
		panel1=new JPanel();
		sp=new JScrollPane(orderTable);
		panel1.add(sp);
		mainPanel.add(panel1);
	}
	public void createPanel3() {
		addNewOrdertable=new JTextField("Table Number");
		addNewOrdertable.setPreferredSize(new Dimension(100,30));
		addNewOrdertable.setFont(new Font("Arial", Font.PLAIN,15));
		addNewOrderdate=new JTextField("Order date");
		addNewOrderdate.setPreferredSize(new Dimension(80,30));
		addNewOrderdate.setFont(new Font("Arial", Font.PLAIN,15));
		addNewOrderproductID=new JTextField("Product ID");
		addNewOrderproductID.setPreferredSize(new Dimension(80,30));
		addNewOrderproductID.setFont(new Font("Arial", Font.PLAIN,15));
		addNewOrderB=new JButton("Add new order");
		panel3=new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.add(addNewOrdertable);
		panel3.add(addNewOrderdate);
		panel3.add(addNewOrderproductID);
		panel3.add(addNewOrderB);
		mainPanel.add(panel3);
		}
	public void createPanel2() {
		panel2=new JPanel();
		viewOrdersB =new JButton("View all orders");
		panel2.add(viewOrdersB);
		mainPanel.add(panel2);
	}
	public void createPanel4() {
		computeBillForAnOrderID=new JTextField("Order ID");
		computeBillForAnOrderID.setPreferredSize(new Dimension(80,30));
		computeBillForAnOrderID.setFont(new Font("Arial",Font.PLAIN,15));
		computeBill=new JButton("Compute Bill");
		panel4=new JPanel();
		panel4.add(computeBillForAnOrderID);
		panel4.add(computeBill);
		mainPanel.add(panel4);
		
	}
	public void createPanel5() {
		panel5=new JPanel();
		billTextArea=new JTextArea();
		billTextArea.setPreferredSize(new Dimension(600,100));
		billTextArea.setEditable(false);
		panel5.add(billTextArea);
		mainPanel.add(panel5);
	}
	public JTable createJTable() {
		String[] header= {"Order ID","OrderTable","Order date","Product ID","Product Name"};
		String[][]datas=new String[restaurant.getOrder().size()][];
		for (int i=0;i<restaurant.getOrder().size();i++) {
			datas[i]=new String[5];
		}
		int i=0;
		for (Order o:restaurant.getOrder().keySet()) {
			datas[i][0]=""+o.getOrderID();
			datas[i][1]=""+o.getTable();
			datas[i][2]=""+o.getDate();
			String ProductIDs="";
			String ProductName="";
			for (MenuItem m:restaurant.getOrder().get(o)) {
				ProductIDs+=m.getId()+" ";
				ProductName+=m.getName()+" ";
			}
			datas[i][3]=ProductIDs;
			datas[i][4]=ProductName;
			i++;
		}
		orderTable=new JTable(datas,header);
		orderTable.setPreferredScrollableViewportSize(new Dimension(640,50));
		orderTable.setFillsViewportHeight(true);
		return orderTable;
	}
	public void addActonListeners() {
		addShowOrdersListener();
		addCreateOrderListener();
		addCreateBillListener();
	}
	public void addShowOrdersListener() {
		viewOrdersB.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				orderTable=createJTable();
				panel1.remove(sp);
				sp=new JScrollPane(orderTable);
				panel1.add(sp);
				panel1.revalidate();
				
			}
		});
	}
	public void addCreateOrderListener() {
		addNewOrderB.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String tableNums=addNewOrdertable.getText().replaceAll(" ", "");
				int tableNum=Integer.parseInt(tableNums);
				String orderDates=addNewOrderdate.getText();
				String ordersIDs=addNewOrderproductID.getText();
				String ordersIDss[]=ordersIDs.split(" ");
				
				int[] ids=new int[ordersIDss.length];
				for (int i=0;i<ids.length;i++)
				{
					ids[i]= Integer.parseInt(ordersIDss[i]);
				}
				
				Order o=new Order(++orderID,orderDates,tableNum);
				ArrayList<MenuItem> items=new ArrayList<MenuItem>();
				restaurant.createNewOrder(o, items, ids);
				fw.writeBill();
				
			}
		});
	}
	public void addCreateBillListener() {
		computeBill.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String orderids=computeBillForAnOrderID.getText().replaceAll(" ", "");
				int orderID=Integer.parseInt(orderids);
				Order order=null;
				for (Order o:restaurant.getOrder().keySet()) {
					if ((o.getOrderID()==orderID))
					{
						order=o;
						billTextArea.setText(restaurant.generateBill(order));
						break;
					}
						
				}
				
			}
		});
	}
	
	
}

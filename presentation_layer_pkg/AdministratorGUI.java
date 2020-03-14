package ro.utcn.tp.assig4.presentation_layer_pkg;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;

import javax.swing.*;

import ro.utcn.tp.assig4.business_layer_pkg.BaseProduct;
import ro.utcn.tp.assig4.business_layer_pkg.CompositeProduct;
import ro.utcn.tp.assig4.business_layer_pkg.MenuItem;
import ro.utcn.tp.assig4.business_layer_pkg.Restaurant;
import ro.utcn.tp.assig4.data_layer_pkg.RestaurantSerializator;


@SuppressWarnings("serial")
public class AdministratorGUI extends JFrame {
	
	private Restaurant restaurant=new Restaurant();
	private JPanel mainPanel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel6;
	
	private JTable menuTable;
	private JScrollPane sp;
	
	private Choice menuItemTypes;
	
	private JButton showMenuButton;
	private JButton addNewMenuItemB;
	private JButton deleteMenuItemB;
	private JButton editMenuItemB;
	private JButton addAdditionalProductB;
	
	private JTextField addItemName;
	private JTextField addItemPrice;
	
	private JTextField addAdditionalProductName;
	private JTextField addAdditionalProductPrice;
	
	private JTextField deleteItemID;
	
	private JTextField editMenuItemID;
	private JTextField editMenuItemName;
	private JTextField editMenuItemPrice;
	
	
	public static int ID=0; 

	public AdministratorGUI(Restaurant restaurant) {
		this.restaurant=restaurant;
	/*	restaurant.createNewMenuItem(new BaseProduct(++ID, "Cartofi prajiti", 4));
		restaurant.createNewMenuItem(new CompositeProduct(++ID, "Cascaval pane cu salata de varza", 12));
		CompositeProduct p=new CompositeProduct(5, "Suc de 0.5 litri cu mustar", 7);
		CompositeProduct m=new CompositeProduct(++ID, "Pizza menu", 20);
		
		m.getProducts().add(p);
		restaurant.createNewMenuItem(m);
		*/
		
		new JFrame();
		setTitle("Admin_GUI");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(670, 480);
		createMainPanel();
		createPanel1();
		createPanel2();
		createPanel3();
		//createPanel6();
		createPanel4();
		createPanel5();

		setContentPane(mainPanel);
		
		addActionListeners();
		//restaurant.createNewMenuItem(null);//Assert error
		setVisible(true);

		
	}
	public void createMainPanel() {
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	}
	public void createPanel1() {
		try {
			restaurant.setMenu(RestaurantSerializator.readFile());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel1=new JPanel();
		panel1.setLayout(new FlowLayout());
		sp=new JScrollPane(menuTable);
		panel1.add(sp);
		mainPanel.add(panel1);
	}
	public void createPanel2() {
		panel2=new JPanel();
		panel2.setLayout(new FlowLayout());
		showMenuButton=new JButton("Show Menu items");
		menuItemTypes=new Choice();
		menuItemTypes.setFont(new Font("Arial", Font.PLAIN, 17));
		menuItemTypes.add("Base Product");
		menuItemTypes.add("Composite Product");
		panel2.add(showMenuButton);
		panel2.add(menuItemTypes);
		mainPanel.add(panel2);
	}
	public void createPanel3() {
		panel3=new JPanel();
		panel3.setLayout(new FlowLayout());
		addItemName=new JTextField("Product name");
		addItemName.setPreferredSize(new Dimension(100,30));
		addItemName.setFont(new Font("Arial", Font.PLAIN, 15));
		addItemPrice=new JTextField("Product Price");
		addItemPrice.setPreferredSize(new Dimension(100,30));
		addItemPrice.setFont(new Font("Arial", Font.PLAIN, 15));
		addNewMenuItemB=new JButton("Add item");
		panel3.add(addItemName);
		panel3.add(addItemPrice);
		panel3.add(addNewMenuItemB);
		mainPanel.add(panel3);
	}
	public void createPanel4() {
		panel4=new JPanel();
		panel4.setLayout(new FlowLayout());
		deleteItemID=new JTextField("Product ID");
		deleteItemID.setPreferredSize(new Dimension(80,30));
		deleteItemID.setFont(new Font("Arial", Font.PLAIN, 15));
		deleteMenuItemB=new JButton("Delete Item");
		panel4.add(deleteItemID);
		panel4.add(deleteMenuItemB);
		mainPanel.add(panel4);
	}
	public void createPanel5() {
		panel5=new JPanel();
		panel5.setLayout(new FlowLayout());
		editMenuItemID=new JTextField("Product ID");
		editMenuItemID.setPreferredSize(new Dimension(80,30));
		editMenuItemID.setFont(new Font("Arial", Font.PLAIN, 15));
		editMenuItemName=new JTextField("New name");
		editMenuItemName.setPreferredSize(new Dimension(80,30));
		editMenuItemName.setFont(new Font("Arial", Font.PLAIN, 15));
		editMenuItemPrice=new JTextField("New price");
		editMenuItemPrice.setPreferredSize(new Dimension(80,30));
		editMenuItemPrice.setFont(new Font("Arial", Font.PLAIN, 15));
		editMenuItemB=new JButton("Edit Item");
		panel5.add(editMenuItemID);
		panel5.add(editMenuItemName);
		panel5.add(editMenuItemPrice);
		panel5.add(editMenuItemB);
		mainPanel.add(panel5);
	}
	public void createPanel6() {
		panel6=new JPanel();
		panel6.setLayout(new FlowLayout());
		addAdditionalProductName=new JTextField("Product Name");
		addAdditionalProductName.setPreferredSize(new Dimension(120,30));
		addAdditionalProductName.setFont(new Font("Arial", Font.PLAIN, 15));
		addAdditionalProductPrice=new JTextField("Product Price");
		addAdditionalProductPrice.setPreferredSize(new Dimension(120,30));
		addAdditionalProductPrice.setFont(new Font("Arial", Font.PLAIN, 15));
		addAdditionalProductB=new JButton("Add aditional product");
		panel6.add(addAdditionalProductName);
		panel6.add(addAdditionalProductPrice);
		panel6.add(addAdditionalProductB);
		mainPanel.add(panel6);
	
	}
	public void addActionListeners() {

		addShowMenuButtonListener();
		addNewMenuItemBListener();
		addDeleteMenuItemBListener();
		addEditMenuItemBListener();
	}
	public JTable createJTable() {
		String[] header= {"Product ID","Product name","Product price","Product type"};
		String[][] data=new String[restaurant.getMenu().size()][];
		
		for (int i=0;i<restaurant.getMenu().size();i++) {
			data[i]=new String[4];
		}
		
		for (int i=0;i<restaurant.getMenu().size();i++) {
			MenuItem m=restaurant.getMenu().get(i);
			data[i][0]=""+m.getId();
			data[i][1]=""+m.getName();
			data[i][2]=""+m.computePrice();
			data[i][3]=""+m.getClass().getSimpleName();
		}
		menuTable=new JTable(data,header);
		menuTable.setPreferredScrollableViewportSize(new Dimension(640,50));
		menuTable.setFillsViewportHeight(true);
		return menuTable;
	}
	public void addShowMenuButtonListener() {
		showMenuButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				menuTable=createJTable();
				panel1.remove(sp);
				sp=new JScrollPane(menuTable);
				panel1.add(sp);
				panel1.revalidate();
								
			}
		});
	}
	public void addNewMenuItemBListener() {
		
		addNewMenuItemB.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ID=restaurant.getMenu().size();	
				String productName=addItemName.getText();
				String priceS=addItemPrice.getText();
				priceS=priceS.replaceAll(" ", "");
				float price=Float.parseFloat(priceS);
				MenuItem m=null;
				if (menuItemTypes.getSelectedItem().equals("Base Product"))
					m=new BaseProduct(++ID, productName, price);
				else if (menuItemTypes.getSelectedItem().equals("Composite Product")) 
					m=new CompositeProduct(++ID, productName, price);
				else 
					m=new CompositeProduct(++ID, productName, price);
				if (m!=null)	
					restaurant.createNewMenuItem(m);
		//		createJTable();
				try {
					RestaurantSerializator.writeFile(restaurant.getMenu());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	public void addDeleteMenuItemBListener() {
		deleteMenuItemB.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String IDs=deleteItemID.getText();
				IDs=IDs.replaceAll(" ", "");
				int ID=Integer.parseInt(IDs);
				restaurant.deleteMenuItem(ID);
				//--AdministratorGUI.ID;
				//createJTable();
				try {
					RestaurantSerializator.writeFile(restaurant.getMenu());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	public void addEditMenuItemBListener() {
		editMenuItemB.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String IDs=editMenuItemID.getText();
				int ID=Integer.parseInt(IDs);
				String name=editMenuItemName.getText();
				String prices=editMenuItemPrice.getText();
				float price=Float.parseFloat(prices);
				if (menuItemTypes.getSelectedIndex()==0) {
					MenuItem m=new BaseProduct(ID, name, price);
					restaurant.editMenuItem(ID, m);
				}
				else{
					MenuItem m=new CompositeProduct(ID, name, price);
					restaurant.editMenuItem(ID, m);
				}
				try {
					RestaurantSerializator.writeFile(restaurant.getMenu());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	
}

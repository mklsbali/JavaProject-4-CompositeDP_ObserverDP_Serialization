package ro.utcn.tp.assig4.presentation_layer_pkg;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ro.utcn.tp.assig4.business_layer_pkg.Restaurant;

public class ChefGUI implements Observer{
	private JTextArea ta;
	private JPanel mainPanel;
	private Restaurant restaurant;
	public ChefGUI(Restaurant restaurant) {
		this.restaurant=restaurant;
		JFrame f=new JFrame("Chef_GUI");
		f.setSize(400,320);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel=new JPanel();
		ta=new JTextArea();
		ta.setPreferredSize(new Dimension(380,300));
		ta.setEditable(false);
		mainPanel.add(ta);
		
		f.setContentPane(mainPanel);
		f.setVisible(true);
	}
	public void update(Observable o, Object arg) {
		ta.setText(ta.getText()+"A new order was added to the list:"+Restaurant.newlyAdded+"\n");
		
	}
}

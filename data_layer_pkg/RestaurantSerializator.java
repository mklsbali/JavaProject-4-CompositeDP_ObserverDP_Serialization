package ro.utcn.tp.assig4.data_layer_pkg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import ro.utcn.tp.assig4.business_layer_pkg.MenuItem;

public class RestaurantSerializator {
	public static void writeFile(ArrayList<MenuItem> writableMenu) throws IOException{
		ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream("MenuItems.bin"));
		outputStream.writeObject(writableMenu);
	}
	public static ArrayList<MenuItem>readFile() throws IOException,ClassNotFoundException{
		ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("MenuItems.bin"));
		ArrayList<MenuItem> newlyReadedMenuItems=(ArrayList<MenuItem>) inputStream.readObject();
		return newlyReadedMenuItems;
	}
	
}

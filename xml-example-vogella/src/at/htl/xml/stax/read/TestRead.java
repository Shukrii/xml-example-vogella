package at.htl.xml.stax.read;

import java.util.List;

import at.htl.xml.stax.model.Item;

public class TestRead {

	public static void main(String[] args) {
		StaXParser read = new StaXParser();
	    List<Item> readConfig = read.readConfig("config.xml");
	    for (Item item : readConfig) {
	      System.out.println(item);
	    }


	}

}

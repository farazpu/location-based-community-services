package net.fast.lbcs.data.entities.user;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;

@Default
public class ProductResultSet {
	private List<Product> products = new ArrayList<Product>() ;
	private Location location = new Location(0,0);
	
	public ProductResultSet() {}

	public ProductResultSet(List<Product> products, Location location) {
		super();
		this.products = products;
		this.location = location;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	


}
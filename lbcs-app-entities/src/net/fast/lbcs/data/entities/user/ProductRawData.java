package net.fast.lbcs.data.entities.user;

import java.util.List;

import org.simpleframework.xml.Default;

@Default
public class ProductRawData {
	private int price;
	private int priceDeci;
	
	private Location location;
	
	private List<ProductAttribute> attrs;
}

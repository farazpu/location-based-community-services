package net.fast.lbcs.user.controller;

import java.util.List;

import net.fast.lbcs.admin.item.ServiceItem;

public class Product {
	
	private ProductID id;
	
	private ServiceItem serviceItem;
	
	private int price;
	private int priceDeci;
	
	private int distance;
	
	private Location location;
	
	private List<ProductAttribute> attrs;
	
	private int averageRatting;
	
	private List<ProductReview> reviews;
	
	public ProductReview getReviewForUser(String userId) {
		return null;
	}
}

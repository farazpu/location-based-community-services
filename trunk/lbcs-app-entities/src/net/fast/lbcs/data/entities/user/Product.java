package net.fast.lbcs.data.entities.user;

import java.util.List;

import org.simpleframework.xml.Default;

import net.fast.lbcs.data.entities.admin.item.ServiceItem;


@Default
public class Product {
	
	private ProductID id;
	
	private ServiceItem serviceItem;
	private String name;
	private int price;
	private int priceDeci;
	
	private int distance;
	
	private Location location;
	
	private List<ProductAttribute> attrs;
	
	private int averageRatting;
	
	private List<ProductReview> reviews;
	
	public Product() {}
	
	public Product(ProductID id, String name, ServiceItem serviceItem, int price,
			int priceDeci, int distance, Location location,
			List<ProductAttribute> attrs, int averageRatting,
			List<ProductReview> reviews) {
		super();
		this.id = id;
		this.name = name;
		this.serviceItem = serviceItem;
		this.price = price;
		this.priceDeci = priceDeci;
		this.distance = distance;
		this.location = location;
		this.attrs = attrs;
		this.averageRatting = averageRatting;
		this.reviews = reviews;
	}

	public ProductReview getReviewForUser(String userId) {
		return null;
	}

	public ProductID getId() {
		return id;
	}

	public void setId(ProductID id) {
		this.id = id;
	}

	public ServiceItem getServiceItem() {
		return serviceItem;
	}

	public void setServiceItem(ServiceItem serviceItem) {
		this.serviceItem = serviceItem;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPriceDeci() {
		return priceDeci;
	}

	public void setPriceDeci(int priceDeci) {
		this.priceDeci = priceDeci;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<ProductAttribute> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<ProductAttribute> attrs) {
		this.attrs = attrs;
	}

	public int getAverageRatting() {
		return averageRatting;
	}

	public void setAverageRatting(int averageRatting) {
		this.averageRatting = averageRatting;
	}

	public List<ProductReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<ProductReview> reviews) {
		this.reviews = reviews;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

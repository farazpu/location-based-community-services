package net.fast.lbcs.data.entities.user;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;

import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute;


@Default
public class Product {
	
	private ProductID id;
	
	private ServiceItem serviceItem;

	private String name = " ";
	
	private int distance;
	
	private Location location=new Location(1,1);
	
	private List<ProductAttribute> attrs = new ArrayList<ProductAttribute>();
	
	private String Rating;
	
	private List<ProductReview> reviews = new ArrayList<ProductReview>();
	
	private List<ProductComment> comments = new ArrayList<ProductComment>();
	
	private List<ProductValueReview> valueReviews = new ArrayList<ProductValueReview>();
	
	public Product() {
	}
	
	public Product(ProductID id, String name, ServiceItem serviceItem, int distance, Location location,
			List<ProductAttribute> attrs, String Rating, List<ProductReview> reviews,
			List<ProductComment> comments, List<ProductValueReview> valueReviews) {
		this.id = id;
		this.name = name;
		this.serviceItem = serviceItem;
		this.distance = distance;
		this.location = location;
		this.attrs = attrs;
		this.Rating = Rating;
		this.reviews = reviews;
		this.comments = comments;
		this.valueReviews = valueReviews;
		initAttributes();
	}
	
	public void initAttributes(){
		attrs = new ArrayList<ProductAttribute>();
		List<ServiceItemAttribute> attributes = serviceItem.getAttrs().getAttrs();
		for(ServiceItemAttribute attribute : attributes) {
			ProductAttribute pa = new ProductAttribute(attribute.getName()," ");
			attrs.add(pa);
		}
	}

	public void initAttributesWithDefaultValues(){
		attrs = new ArrayList<ProductAttribute>();
		int a=0;
		List<ServiceItemAttribute> attributes = serviceItem.getAttrs().getAttrs();
		for(ServiceItemAttribute attribute : attributes) {
			ProductAttribute pa = new ProductAttribute(attribute.getName(),a + "");
			a++;
			attrs.add(pa);
		}
	}

	public ProductReview getReviewForUser(String userId) {
		for(ProductReview pr : reviews){
			if(pr.getUsername().equals(userId)){
				return pr;
			}
		}
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

	public String getRating() {
		return Rating;
	}

	public String getPublicRating() {
		float pRating=0;
		if(reviews.size()!=0){
			for(ProductReview review : reviews){
				pRating+=Integer.parseInt(review.getReviewRating());
			}
			pRating /=reviews.size();
		}
		else{
			return "NA";
		}

		return ""+pRating;
	}

	public void setRating(String averageRatting) {
		this.Rating = averageRatting;
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
	
	
	
	public List<ProductComment> getComments() {
		return comments;
	}

	public void setComments(List<ProductComment> comments) {
		this.comments = comments;
	}

	public List<ProductValueReview> getValueReviews() {
		return valueReviews;
	}

	public void setValueReviews(List<ProductValueReview> valueReviews) {
		this.valueReviews = valueReviews;
	}

	public boolean setAttributeValue(String key, String value)
	{
		for(int i = 0 ; i < attrs.size() ; i++){
			if(attrs.get(i).getKey().equals(key)) {
				ProductAttribute pa = attrs.get(i);
				attrs.remove(i);
				pa.setValue(value);
				attrs.add(pa);
				return true;
			}
		}
		return false;
	}

	public String getAttributeValue(String key)
	{
		for(ProductAttribute pa : attrs){
			if(pa.getKey().equals(key)) {
				return pa.getValue();
			}
		}
		return "";
	}

	public String getAttributePublicValue(String key)
	{
		ServiceItemAttribute attr = new ServiceItemAttribute();
		List<ServiceItemAttribute> attrs = getServiceItem().getAttrs().getAttrs();
		System.out.println("-------------------");
		for(ServiceItemAttribute a : attrs)
			if(a.getId().equals(key))
				attr=a;
		if(attr.getType().equals("number")){
				float pRating=0;
				int i=0;
				for(ProductValueReview review : valueReviews){
					if(review.getAttributeId().equals(key)){
	 					pRating+=Integer.parseInt(review.getValue());
					}
					i++;
 				}
				if(i>0)
					return ""+(pRating /i);
				return "NA";
		}
		else{
			String result="NA";
			int maxcount=0;
			for(ProductValueReview review : valueReviews){
				if(review.getAttributeId().equals(key)){
					String thisValue = review.getValue();
					int thisCount=0;
					for(ProductValueReview check : valueReviews){
						if(check.getValue().equals(thisValue) && check.getAttributeId().equals(key)){
							thisCount++;
						}
					}
					if(thisCount>maxcount){
						maxcount=thisCount;
						result=thisValue;
					}	
				}
			}
			return result;
		}
	}

}

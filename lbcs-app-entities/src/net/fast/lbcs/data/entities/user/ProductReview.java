package net.fast.lbcs.data.entities.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fast.lbcs.data.entities.MyDate;

import org.simpleframework.xml.Default;

@Default
public class ProductReview {
	private Product product = new Product();
	private List<ProductAttribute> reviewValues = new ArrayList<ProductAttribute>();
	private int reviewRating;
	private String reviewText = " ";
	private MyDate date = new MyDate(new Date());
	private String username = " ";
	
	
	public ProductReview() {
	}

	public ProductReview(Product product, List<ProductAttribute> reviewValues,
			int reviewRating, String reviewText, MyDate date, String username) {
		this.product = product;
		this.reviewValues = reviewValues;
		this.reviewRating = reviewRating;
		this.reviewText = reviewText;
		this.date = date;
		this.username = username;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ProductAttribute> getReviewValues() {
		return reviewValues;
	}

	public void setReviewValues(List<ProductAttribute> reviewValues) {
		this.reviewValues = reviewValues;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "ProductReview [product=" + product + ", reviewValues="
				+ reviewValues + ", reviewRating=" + reviewRating
				+ ", reviewText=" + reviewText + ", date=" + date
				+ ", username=" + username + "]";
	}
	
	
}

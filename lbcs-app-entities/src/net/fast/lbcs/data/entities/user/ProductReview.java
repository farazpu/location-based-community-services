package net.fast.lbcs.data.entities.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fast.lbcs.data.entities.MyDate;

import org.simpleframework.xml.Default;

@Default
public class ProductReview {
	private String productId = " ";
	private String reviewRating = " ";
	private MyDate date = new MyDate(new Date());
	private String username = " ";
	private String status = " ";
	
	public ProductReview() {
	}

	public ProductReview(String product, String reviewRating, MyDate date, String username, String status) {
		this.productId = product;
		this.reviewRating = reviewRating;
		this.date = date;
		this.username = username;
		this.status = status;
	}

	public String getProduct() {
		return productId;
	}

	public void setProduct(String product) {
		this.productId = product;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductReview [productId=" + productId + ", reviewRating="
				+ reviewRating + ", date=" + date + ", username=" + username
				+ ", status=" + status + "]";
	}

	
	
}

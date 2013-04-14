package net.fast.lbcs.data.entities.user;

import java.util.Date;

import net.fast.lbcs.data.entities.MyDate;

public class ProductComment {
	private String productId = " ";
	private String comment = " ";
	private MyDate date = new MyDate(new Date());
	private String username = " ";
	

	
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public ProductComment() {
		super();
	}

	public ProductComment(String productId, String comment, MyDate date,
			String username) {
		super();
		this.productId = productId;
		this.comment = comment;
		this.date = date;
		this.username = username;
	}



	@Override
	public String toString() {
		return "ProductComment [productId=" + productId + ", comment="
				+ comment + ", date=" + date + ", username=" + username + "]";
	}

	
}

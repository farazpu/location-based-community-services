package net.fast.lbcs.data.entities.user;

import org.simpleframework.xml.Default;

@Default
public class ProductValueReview {
	private String productId = " ";
	private String attributeId = " ";
	private String value = " ";
	private String status = " ";
	private String username = " ";
	
	
	public ProductValueReview(String productId, String attributeId,
			String value, String status, String username) {
		this.productId = productId;
		this.attributeId = attributeId;
		this.value = value;
		this.status = status;
		this.username = username;
	}


	public ProductValueReview() {
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getAttributeId() {
		return attributeId;
	}


	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}



	@Override
	public String toString() {
		return "ProductValueReview [productId=" + productId + ", attributeId="
				+ attributeId + ", value=" + value + ", status=" + status
				+ ", username=" + username + "]";
	}
	
	
	
	
}
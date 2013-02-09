package net.fast.lbcs.data.entities.user;

import org.simpleframework.xml.Default;

@Default
public class ProductAttribute {
	private String key = " ";
	private String value = " ";
		
	public ProductAttribute() {
	}

	public ProductAttribute(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ProductAttribute [key=" + key + ", value=" + value + "]";
	}
	
	
	
	

}

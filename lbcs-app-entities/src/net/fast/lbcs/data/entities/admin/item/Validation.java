package net.fast.lbcs.data.entities.admin.item;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Default;

@Default
public class Validation {
	String id = " ";
	String name = " ";
	String type = " ";
	String description = " ";
	List<String> params = new ArrayList<String>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public Validation(String id, String name, String type, String description,
			List<String> params) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.params = params;
	}
	
	public Validation() {
	}

	@Override
	public String toString() {
		return "Validation [id=" + id + ", name=" + name + ", type=" + type
				+ ", description=" + description + ", params=" + params + "]";
	}

}

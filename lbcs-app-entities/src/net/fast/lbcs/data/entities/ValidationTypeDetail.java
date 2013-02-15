package net.fast.lbcs.data.entities;

import java.util.List;

public class ValidationTypeDetail {

	String type;
	int paramsRequired;
	String paramInputHTML;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getParamsRequired() {
		return paramsRequired;
	}

	public void setParamsRequired(int paramsRequired) {
		this.paramsRequired = paramsRequired;
	}

	public String getParamInputHTML() {
		return paramInputHTML;
	}

	public void setParamInputHTML(String paramInputHTML) {
		this.paramInputHTML = paramInputHTML;
	}

	public ValidationTypeDetail() {
	}

	public ValidationTypeDetail(String type, int paramsRequired,
			String paramInputHTML) {
		this.type = type;
		this.paramsRequired = paramsRequired;
		this.paramInputHTML = paramInputHTML;
	}

	@Override
	public String toString() {
		return "ValidationTypeDetail [type=" + type + ", paramsRequired="
				+ paramsRequired + ", paramInputHTML=" + paramInputHTML + "]";
	}
	
	
}

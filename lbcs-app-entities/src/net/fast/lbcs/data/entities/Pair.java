package net.fast.lbcs.data.entities;

import org.simpleframework.xml.Default;

@Default
public class Pair {
	private String first;
	private String second;
	public Pair(String first, String second) {
		super();
		this.first = first;
		this.second = second;
	}
	public Pair() {}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}

	
	
}

package net.fast.lbcs.data.entities.user;

import org.simpleframework.xml.Default;

@Default
public class Location {
	
	private double lon;
	private double lat;
	public Location() {}
	
	public Location(double lon, double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	
}

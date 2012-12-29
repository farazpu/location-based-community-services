package net.fast.lbcs.data.entities.user;

import java.util.List;

import org.simpleframework.xml.Default;


@Default
public class User {
	private String id;
	private String password;

	private UserSettings settings;
	
	private List<Product> favourites;
	
	public User () {}

	public User(String id, String password, UserSettings settings,
			List<Product> favourites) {
		super();
		this.id = id;
		this.password = password;
		this.settings = settings;
		this.favourites = favourites;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserSettings getSettings() {
		return settings;
	}

	public void setSettings(UserSettings settings) {
		this.settings = settings;
	}

	public List<Product> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<Product> favourites) {
		this.favourites = favourites;
	}
	
	
	
}

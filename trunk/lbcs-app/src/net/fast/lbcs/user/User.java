package net.fast.lbcs.user;

import java.util.List;

import net.fast.lbcs.user.controller.Product;
import net.fast.lbcs.user.controller.UserSettings;

public class User {
	private String id;
	private String password;

	private UserSettings settings;
	
	private List<Product> favourites;
}

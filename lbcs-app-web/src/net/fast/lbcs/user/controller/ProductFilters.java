package net.fast.lbcs.user.controller;

import java.util.ArrayList;
import java.util.List;

import net.fast.lbcs.data.entities.user.Location;
import net.fast.lbcs.data.entities.user.Product;
import java.lang.Math;

public class ProductFilters {
	
	private static double ToRadians(double degrees) 
	{
		double radians = degrees * Math.PI  / 180;
		return radians;
	}
	public static List<Product> DistanceFilter(List<Product> products, Location location, int radius){
		for(int i=0;i<products.size();i++){
			Product p = products.get(i);
			double lat1 = location.getLat();
			double lng1 = location.getLon();
			double lat2 = p.getLocation().getLat();
			double lng2 = p.getLocation().getLon();
			double earthRadius = 3958.75;
			double dLat = ToRadians(lat2-lat1);
			double dLng = ToRadians(lng2-lng1);
			double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(ToRadians(lat1)) * Math.cos(ToRadians(lat2)) * Math.sin(dLng/2) * Math.sin(dLng/2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			double dist = earthRadius * c;
			double meterConversion = 1609.00;
			dist = dist * meterConversion;
			if(dist>radius){
				products.remove(i);
				i--;
			}
		}
		return products;
	}
	
	
	public static List<Product> GroupFilter(List<Product> products, String groupId){
		for(int i=0;i<products.size();i++){
			Product p = products.get(i);
			if(!(p.getServiceItem().getGroup().getId().getId().equals(groupId))){
				products.remove(i);
				i--;
			}
		}
		return products;			
	}


	public static List<Product> ItemFilter(List<Product> products, String itemId,String groupId, String username){
		for(int i=0;i<products.size();i++){
			Product p = products.get(i);
			if(!(p.getServiceItem().getGroup().getId().getId().equals(groupId))){
				products.remove(i);
				i--;
			}else{
				if(!(itemId.equals(p.getServiceItem().getId().getId()))){
					
					if(!(p.getReviewForUser(username).getReviewRating().equals("4")) && !(p.getReviewForUser(username).getReviewRating().equals("5")) 
							&& Double.parseDouble(p.getRating()) <= 4 && Double.parseDouble(p.getPublicRating()) <= 4 ){
						products.remove(i);
						i--;
					}
				}
			}
		}
		return products;			
	}
	
	
	public static List<Product> NameFilter(List<Product> products, String name){
		List<Product> plist = new ArrayList<Product>();
		for(int i=0;i<products.size();i++){
			if(products.get(i).getName().contains(name)){
				plist.add(products.get(i));
			}
		}
		return plist;
	
	}


	public static List<Product> RatingFilter(List<Product> products, String username, int threshold){
		
		
		
		while(products.size()>threshold){
			int index=0;
			double minvalue=0;
			if(products.get(0).getReviewForUser(username)==null){				
				minvalue =( Double.parseDouble(products.get(0).getRating()) + Double.parseDouble(products.get(0).getPublicRating()))/ 2;
			}
			else{
				minvalue =( Double.parseDouble(products.get(0).getRating()) + Double.parseDouble(products.get(0).getPublicRating())
						+ Double.parseDouble(products.get(0).getReviewForUser(username).getReviewRating()) )/ 3;
			}
			for(int i=0;i<products.size();i++){
				Product p=products.get(i);
				double value=0;
				if(p.getReviewForUser(username)==null){				
					value =( Double.parseDouble(p.getRating()) + Double.parseDouble(p.getPublicRating()))/ 2;
				}
				else{
					value =( Double.parseDouble(products.get(0).getRating()) + Double.parseDouble(products.get(0).getPublicRating())
							+ Double.parseDouble(p.getReviewForUser(username).getReviewRating()) )/ 3;
				}
				
				if(value<minvalue){
					index = i;
					minvalue = value;
				}
		
			}
			products.remove(index);
		}
		return products;			
	}




}

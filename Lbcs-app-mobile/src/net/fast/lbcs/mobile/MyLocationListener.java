package net.fast.lbcs.mobile;


import android.content.Context;
import android.location.LocationManager;

public class MyLocationListener {
	public static GPSTracker listener;
	
	public static void activate(Context context)
	{
		listener=new GPSTracker(context);
        LocationManager locationManager;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0 , MyLocationListener.listener);

	}

}

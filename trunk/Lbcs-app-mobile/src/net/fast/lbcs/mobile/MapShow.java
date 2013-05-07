package net.fast.lbcs.mobile;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductAttribute;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MapShow extends MapActivity {

    List<Product> seletedItems = new ArrayList<Product>();

    Context context = this;
    MapView mapView;
    MapController mapController;
    String clickedText;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_show);
        mapView = (MapView) findViewById(R.id.map);
        clickedText = getIntent().getExtras().getString("clickedText");
        Product product = CurrentServiceInfo.getProduct(clickedText);
//        GeoPoint point = new GeoPoint( (int)(product.getLocation().getLat() * 1000000 ) , (int)(product.getLocation().getLon() *1000000));
        GeoPoint point = new GeoPoint((int)(MyLocationListener.listener.getLatitude()*1000000), (int)(MyLocationListener.listener.getLongitude()*1000000));
              mapView.setBuiltInZoomControls(true);
        
        mapController = mapView.getController();
        mapController.setZoom(18); 
        mapController.setCenter(point);
        List< Overlay > mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
         
        MyItemizedOverlay itemizedoverlay = new MyItemizedOverlay(drawable, this);
        mapView.getController().setCenter(point);
        OverlayItem overlayitem = new OverlayItem(point, product.getName() + " (" + product.getId().getId() + ")" , "Your Position");
        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);
        
        Drawable pdrawable = this.getResources().getDrawable(R.drawable.ic_action_search);
         
        MyItemizedOverlay pitemizedoverlay = new MyItemizedOverlay(pdrawable, this);
        
        List<Product> plist = CurrentServiceInfo.getProductList();
        for(Product product1 : plist){
        	
	        String detail = "Rating = "+ product1.getRating() + "\n";
	        List<ProductAttribute> attributes = product1.getAttrs();
	        for(ProductAttribute pa : attributes) {
	        	List<ServiceItemAttribute> itemAttrList = product1.getServiceItem().getAttrs().getAttrs();
	        	for(ServiceItemAttribute sia : itemAttrList){
	        		if(sia.getId().equals(pa.getKey()) && !pa.getValue().equals("-")){
	        			detail += sia.getName()+ " = " + pa.getValue() + "\n";
	        		}
	        	}
	        }
	        GeoPoint point1 = new GeoPoint((int)(product.getLocation().getLat()*1000000), (int)(product.getLocation().getLon()*1000000));
	        OverlayItem poverlayitem = new OverlayItem(point1, product.getName() + " (" + product.getId().getId() + ")" , detail);
	        pitemizedoverlay.addOverlay(poverlayitem);
        } 
        mapOverlays.add(pitemizedoverlay);
       //this will show you the map at the exact location you want (if you not set this you will see the map somewhere in America)
 
 
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_map_show, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	      
	      // add marker
	      


/*	class MapOverlay extends Overlay
	{
	  @Override
	  public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
	    super.draw(canvas, mapView, shadow);           

	    // convert point to pixels
	    Point screenPts = new Point();
	    mapView.getProjection().toPixels(pointToDraw, screenPts);

	    // add marker
	    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.red);
	    canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 24, null);    
	    return true;
	  }
	}

*/
	
}

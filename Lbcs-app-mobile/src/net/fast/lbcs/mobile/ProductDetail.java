package net.fast.lbcs.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fast.lbcs.data.entities.MyDate;
import net.fast.lbcs.data.entities.user.Product;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetail extends Activity {
	
	Product product;

	Spinner spinner;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        
        String clickedText = getIntent().getExtras().getString("clickedText");
        product = CurrentServiceInfo.getProduct(clickedText);
        
        TextView tv = (TextView) findViewById(R.id.pdName);
        tv.setText("Product Name : "+product.getName());
        
        tv = (TextView) findViewById(R.id.pdType);
        tv.setText("Product Kind : "+product.getServiceItem().getName());
        
        tv = (TextView) findViewById(R.id.pdNumberOfRatings);
        tv.setText("Total Ratings Recieved : "+product.getReviews().size());
        
        tv = (TextView) findViewById(R.id.pdAdminRating);
        tv.setText("Admin Rating : "+product.getRating());
        
        tv = (TextView) findViewById(R.id.pdPublicRating);
        tv.setText("Public Rating : "+product.getPublicRating());
        
        tv = (TextView) findViewById(R.id.pdUserRating);
        tv.setText("You Rated : "+product.getReviewForUser(CurrentServiceInfo.currentUser));
        
        
        
        spinner = (Spinner) findViewById(R.id.pdRatingSpinner);
		List<String> ids=new ArrayList<String>();
       	ids.add("1");
       	ids.add("2");
       	ids.add("3");
       	ids.add("4");
       	ids.add("5");
       	       	
       	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner!=null)
        	spinner.setAdapter(adapter);
        

        Button submit = (Button) findViewById(R.id.pdSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				MyDate date = new MyDate(new Date());
				String request = "http://"  + TempIP.ip + ":8888/user/add_product_review.jsp";
				request+= "?serviceId=" + CurrentServiceInfo.getLocationService().getId().getId(); 
				request+= "&itemId=" + product.getServiceItem().getId().getId();
				request+= "&productId=" + product.getId().getId();
				request+= "&username=" + CurrentServiceInfo.currentUser;
				request+= "&date=" + date;
				request+= "&rating=" + spinner.getSelectedItem();
    	        UrlTextLoader urlTextLoader = new UrlTextLoader() {
    				@Override
    				public void responseComplete(String result) {
						CurrentServiceInfo.addProductRating(product, spinner.getSelectedItem().toString());
    			        TextView tv = (TextView) findViewById(R.id.pdPublicRating);
    			        tv.setText("Public Rating : "+product.getPublicRating());
    			        
    			        tv = (TextView) findViewById(R.id.pdUserRating);
    			        tv.setText("You Rated : "+product.getReviewForUser(CurrentServiceInfo.currentUser));
    				}
				};
				
				urlTextLoader.execute(request);
				
			}
		});
        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_product_detail, menu);
        return true;
    }
}

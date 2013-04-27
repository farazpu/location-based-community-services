package net.fast.lbcs.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fast.lbcs.data.entities.MyDate;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute;
import net.fast.lbcs.data.entities.user.Location;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductAttribute;
import net.fast.lbcs.data.entities.user.ProductID;
import net.fast.lbcs.data.entities.user.ProductReview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewItem extends Activity {

	Context context = this;
	ProductReview review;
	Product product;
	Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_item);        
        spinner = (Spinner) findViewById(R.id.rating_spinner);
		List<String> ids=new ArrayList<String>();
       	ids.add("1");
       	ids.add("2");
       	ids.add("3");
       	ids.add("4");
       	ids.add("5");
       	       	
       	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
       			android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner!=null)
        spinner.setAdapter(adapter);
        
        product = CurrentServiceInfo.getProduct(getIntent().getExtras().getString("Product"));
        
        setTitle(product.getName() + " (" + product.getId().getId() + ")");
        
        TextView adminRating= (TextView) findViewById(R.id.admin_rating);
        adminRating.setText("Admin Rating = "+product.getRating());
        TextView publicRating= (TextView) findViewById(R.id.public_rating);
        adminRating.setText("Public Rating("+product.getReviews().size()+") = "+product.getPublicRating());
        
/*        List<KeyValuePair> resource = new ArrayList<KeyValuePair>();
        List<ProductAttribute> attributeList = product.getAttrs();
        for(ProductAttribute pa : attributeList) {
        	List<ServiceItemAttribute> itemAttrList = product.getServiceItem().getAttrs().getAttrs();
        	for(ServiceItemAttribute sia : itemAttrList){
        		if(sia.getId().equals(pa.getKey()) && sia.getFlag().equals("Yes")){
                	resource.add( new KeyValuePair(sia.getName(),pa.getValue()));
        		}
        	}
        }
        resource.add(new KeyValuePair("Comment", ""));
        ReviewAttriburesAdapter attrAdapter = new ReviewAttriburesAdapter(this, 0, resource);
        ListView lw = (ListView) findViewById(R.id.review_attributes);
        lw.setAdapter(attrAdapter);
*/        
        
        Button sendReview = (Button) findViewById(R.id.review_post);
        sendReview.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
    	        ListView lw = (ListView) findViewById(R.id.review_attributes);
//				review = new ProductReview();
//				List<ProductAttribute> attrList = new ArrayList<ProductAttribute>();
//				review.setDate(new MyDate());
//				review.setProduct(product.getId().getId());
//				review.se

	/*			for(int i=0;i<lw.getCount();i++)
    	        {
    	        	View view = lw.getChildAt(i);
    	        	EditText et = (EditText) view.findViewById(R.id.review_value);
    	        	String value = et.getText().toString();
    	        	TextView tv = (TextView) view.findViewById(R.id.review_key);
    	        	String key = tv.getText().toString();
    	        	if( "Comment".equals(key)){
    	        		review.setReviewText(value);
    	        		if(value.length()==0){
    	        			review.setReviewText("-");
    	        		}
    	        	}
    	        	else{
    	        		attrList.add(new ProductAttribute(key, value));
    	        	}
    	        }
				Date now = new Date();
*/
//    	        review.setReviewValues(attrList);
//    	        review.setDate(new MyDate(now));
//    	        review.setReviewRating(spinner.getSelectedItem().toString());
//    	        review.setId("" + product.getName() + review.getDate());
//    	        if(doReviewAttrValidation(review)){
    	        	MyDate currentDate = new MyDate();
    	        String url = "http://"  + TempIP.ip + ":8888/user/add_product_review.jsp"
    	        		+ "?serviceId=" + CurrentServiceInfo.getLocationService().getId().getId()
    	        		+ "&itemId=" + product.getServiceItem().getId().getId() 
    	        		+ "&productId=" + product.getId().getId() 
    	        		+ "&rating=" +spinner.getSelectedItem()
    	        		+ "&date=" +currentDate;
	    	        		
/*	    	        for(ProductAttribute pa : attrList ) {
	    	        	if(pa.getValue().equals(""))
	    	       		pa.setValue("-");
	    	        	url = url + "&" + pa.getKey() + "=" + pa.getValue();
	    	        }
*/
    	        ProductReview pr = new ProductReview(product.getId().getId(), spinner.getSelectedItem().toString()
    	        		, currentDate, CurrentServiceInfo.currentUser, "unhandled");
    	        List<ProductReview> reviews =product.getReviews();
    	        reviews.add(pr);
    	        product.setReviews(reviews);
    	        UrlTextLoader urlTextLoader = new UrlTextLoader() {
    				@Override
    				public void responseComplete(String result) {
   						Toast.makeText(context, result, Toast.LENGTH_LONG).show();
   						if (result.equals("Review Added Successfully.")){
   							CurrentServiceInfo.addProduct(product);
   						}
    				}
				};
    			urlTextLoader.execute(url);
    	        // send req with productXML and service id
			}
		});    
	}

	private List<ProductAttribute> computeCommunityValues() {
		List<ProductAttribute> communityValues = new ArrayList<ProductAttribute>();
		List<ServiceItemAttribute> attrList = product.getServiceItem().getAttrs().getAttrs();
		for(ServiceItemAttribute attr : attrList) {
			if(attr.getFlag().equals("true")){
				List<ProductReview> reviews = product.getReviews();
				List<ProductAttribute> attrVals= new ArrayList<ProductAttribute>();
				for(ProductReview rev : reviews){
					List<ProductAttribute> revAttrList = rev.getReviewValues();
					for(ProductAttribute revattrval : revAttrList){
						if(revattrval.getKey().equals(attr.getId())){
							attrVals.add(revattrval);
						}
					}
				}
				
				if(attr.getType().equals("number")){
					float val=0;
					int count=0;
					for(ProductAttribute pa : attrVals){
						if("".equals(pa.getValue()) || " ".equals(pa.getValue()) || "-".equals(pa.getValue())){}
						else{
							val=val + Integer.parseInt(pa.getValue());
							count++;
						}
					}
					val=val/count;
					ProductAttribute resultVal = new ProductAttribute(attr.getId(),val+"");
					communityValues.add(resultVal);
				}
				else if(attr.getType().equals("string")){
					
				}
			}
		}
		return communityValues;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.activity_review_item, menu);
	    return true;
	}
	
	public boolean doReviewAttrValidation(ProductReview review){
		
		List<ServiceItemAttribute> siaList =  CurrentServiceInfo.getProductById(review.getProduct()).getServiceItem().getAttrs().getAttrs();
		List<ProductAttribute> paList = review.getReviewValues();
		boolean retValue = true;
		for(ServiceItemAttribute sia : siaList){
			for(ProductAttribute pa : paList){
				if(pa.getKey().equals(sia.getName())&& !pa.getValue().equals("")){
					String result = Validator.validate(sia.getValidation(), pa.getValue(), sia.getName());
					if(!(result.equals("true"))){
						Toast.makeText(context, result, Toast.LENGTH_LONG).show();
						retValue=false;
					}
				}
			}
    	}
    	return retValue;
	}
    
}

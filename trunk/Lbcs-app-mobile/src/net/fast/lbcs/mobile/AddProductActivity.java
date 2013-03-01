package net.fast.lbcs.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import net.fast.lbcs.data.entities.MyDate;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.item.ServiceItemAttribute;
import net.fast.lbcs.data.entities.admin.service.ServiceInfo;
import net.fast.lbcs.data.entities.user.Location;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductAttribute;
import net.fast.lbcs.data.entities.user.ProductID;
import net.fast.lbcs.data.entities.user.ProductReview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class AddProductActivity extends Activity {

	Context context = this;
    List<ServiceItem> itemList = CurrentServiceInfo.getObjectList();
    ServiceItem selectedItem=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        
        Spinner itemSpinner = (Spinner) findViewById(R.id.selectItemSpinner);
        Button submit = (Button) findViewById(R.id.addProductSubmit);
        List<String> itemNames = new ArrayList<String>();
        
        
        for(ServiceItem si : itemList){
        	itemNames.add(si.getName());
        }
       	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
       			android.R.layout.simple_spinner_item, itemNames);
       	
       	itemSpinner.setAdapter(adapter);
       	
       	for(ServiceItem si : itemList) {
    		if(si.getName().equals(itemSpinner.getSelectedItem().toString())){
    			selectedItem = si;
    			break;
    		}
       	}itemSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String itemName = parent.getItemAtPosition(position).toString();
				setAttributeList(itemName);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
       	
       	
        submit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
    	        ListView lw = (ListView) findViewById(R.id.attributeListView);
				Product product = new Product();
				List<ProductAttribute> attrList = new ArrayList<ProductAttribute>();
    	        
    	        for(int i=0;i<lw.getCount();i++)
    	        {
    	        	View view = lw.getChildAt(i);
    	        	EditText et = (EditText) view.findViewById(R.id.review_value);
    	        	String value = et.getText().toString();
    	        	TextView tv = (TextView) view.findViewById(R.id.review_key);
    	        	String key = tv.getText().toString();
    	        	if( "ProductName".equals(key)){
    	        		product.setName(value);
    	        		product.setId(new ProductID(value + new Date()));
    	        	}
    	        	else{
    	        		attrList.add(new ProductAttribute(key, value));
    	        	}
    	        }
    	        product.setAttrs(attrList);
    	        product.setAverageRatting(3);
    	        product.setLocation(new Location(0,0));
    	        product.setReviews(new ArrayList<ProductReview>());
    	        product.setServiceItem(selectedItem);
    	        
    	        product.setId(new ProductID( product.getName() + new MyDate(new Date())));
    	        if(doAttrValidation(product)){
	    	        String url = "http://"  + TempIP.ip + ":8888/user/add_product.jsp?serviceId=" + CurrentServiceInfo.getLocationService().getId().getId()
	    	        		+ "&itemId=" + selectedItem.getId().getId() + "&name=" + product.getName()
	    	        		+ "&productId=" + product.getId().getId();
	    	        for(ProductAttribute pa : attrList ) {
	    	        	url = url + "&" + pa.getKey() + "=" + pa.getValue();
	    	        }
	    	           	UrlTextLoader urlTextLoader = new UrlTextLoader() {
	    				@Override
	    				public void responseComplete(String result) {
	   						Toast.makeText(context, result, Toast.LENGTH_LONG).show();
	    				}
	    			};
	    			urlTextLoader.execute(url);
	    	        
	    	        // send req with productXML and service id
    	        }    	        
			}
		});
    }

    public boolean doAttrValidation(Product product) {
    	List<ServiceItemAttribute> siaList = selectedItem.getAttrs().getAttrs();
    	boolean retValue = true;
    	for(ServiceItemAttribute sia : siaList){
    		String result = Validator.validate(sia.getValidation(), product.getAttributeValue(sia.getName()), sia.getName());
    		if(!(result.equals("true"))){
    			Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    			retValue=false;
    		}
    	}
		return retValue;
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_product, menu);
        return true;
    }
    
    public void setAttributeList(String itemName){
    	for(ServiceItem si : itemList) {
    		if(si.getName().equals(itemName)){
    			selectedItem = si;
    	        List<KeyValuePair> resource = new ArrayList<KeyValuePair>();
    	        List<ServiceItemAttribute> attributeList = si.getAttrs().getAttrs();
    	        for(ServiceItemAttribute pa : attributeList) {
    	        	resource.add( new KeyValuePair(pa.getName(),"a"));
    	        }
    	        resource.add(new KeyValuePair("ProductName", "a"));
    	        ReviewAttriburesAdapter attrAdapter = new ReviewAttriburesAdapter(this, 0, resource);
    	        ListView lw = (ListView) findViewById(R.id.attributeListView);
    	        lw.setAdapter(attrAdapter);
    		}
    	}
    }
    

    
}

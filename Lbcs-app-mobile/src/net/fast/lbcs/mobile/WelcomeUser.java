package net.fast.lbcs.mobile;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.ServiceInfo;
import net.fast.lbcs.data.entities.user.Product;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;



public class WelcomeUser extends Activity {

	Context context=this;
	LocationService locationService;
	List<ServiceItem> objectList = CurrentServiceInfo.getObjectList();
	List<Product> itemList = CurrentServiceInfo.getProductList();
	List<ServiceItemGroup> groupList = CurrentServiceInfo.getGroupList();
	String dataType;
	String XMLServiceInfo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        Spinner spinner = (Spinner) findViewById(R.id.SpinnerValue);
        
        Button addProduct = (Button) findViewById(R.id.addproduct);
        
		List<String> ids=new ArrayList<String>();
       	ids.add("Item");
       	ids.add("Object");
       	ids.add("Group");

       	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
       			android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				resetList(parent.getItemAtPosition(position).toString());
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
		resetList(dataType);
			
		ListView lv = (ListView) findViewById(R.id.objectList);
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		TextView sName = (TextView) view.findViewById(R.id.text);
    			String Name=sName.getText().toString();
        		if(dataType.equals("Item")) {
	        		Intent intent=new Intent(context, MapShow.class);
//	        		String strs [] = Name.split("\\:\\:");
//	        		String str = strs[0].trim();
	        		String pid = itemList.get(position).getId().getId();
	        		intent.putExtra("clickedText" , pid);
	        		startActivity(intent);
        		}
        		else {
        			makeList(Name);
        		}
        	}
		});
        
        
        addProduct.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, AddProductActivity.class);
				startActivity(intent);
				
			}
		});
        
        Button sendNotification = (Button) findViewById(R.id.sendNotification);
        sendNotification.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(context, SendNotificationActivity.class);
				startActivity(intent);
			}
		});
		

    
    }
    
    protected void makeList(String name) {
    	List<Product> products = new ArrayList<Product>();
    	List<String> ids = new ArrayList<String>();
    	if(dataType.equals("Group")) {
    		products = CurrentServiceInfo.getGroupProducts(name);
    	}
    	else if(dataType.equals("Object")) {
    		products = CurrentServiceInfo.getObjectProducts(name);
    	}
    	
    	Spinner spinner = (Spinner) findViewById(R.id.SpinnerValue);
        spinner.setSelection(0, true);
    	
    	for(int i=0;i<products.size();i++) {
        	ids.add(products.get(i).getName() + " :: " + products.get(i).getRating() + " :: " + products.get(i).getPublicRating());
    	}

		ListView lv = (ListView) findViewById(R.id.objectList);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.simple_listview_row,R.id.text,ids);
        lv.setAdapter(adapter);
    	
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_welcome_user, menu);
        return true;
    }
    
    protected void resetList(String type) {
    	dataType = type;
    	if(type!=null) {
    		ListView lv = (ListView) findViewById(R.id.objectList);
        	ArrayList<String> ids=new ArrayList<String>();
    		if (type.equals("Item")) {
    	        for(int i=0;i<itemList.size();i++)
    	        	ids.add(itemList.get(i).getName() + " :: " + itemList.get(i).getRating() + " :: " + itemList.get(i).getPublicRating());			
    		}
    		if (type.equals("Object")) {
    	        for(int i=0;i<objectList.size();i++)
    	        	ids.add(objectList.get(i).getName());			
    		}
    		if (type.equals("Group")) {
    	        for(int i=0;i<groupList.size();i++)
    	        	ids.add(groupList.get(i).getName());			
    		}
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.simple_listview_row,R.id.text,ids);
            lv.setAdapter(adapter);

    	}
    	
    
    }

    
}

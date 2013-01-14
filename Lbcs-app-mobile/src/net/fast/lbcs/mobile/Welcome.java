package net.fast.lbcs.mobile;

import java.util.ArrayList;
import java.util.List;

import net.fast.lbcs.data.entities.admin.group.ServiceItemGroup;
import net.fast.lbcs.data.entities.admin.item.ServiceItem;
import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.LocationServices;
import net.fast.lbcs.data.entities.admin.service.ServiceInfo;
import net.fast.lbcs.data.entities.user.Product;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Welcome extends Activity {

	Context context=this;
	LocationService locationService;
	List<ServiceItem> objectList = new ArrayList<ServiceItem>();
	List<Product> itemList = new ArrayList<Product>();
	List<ServiceItemGroup> groupList = new ArrayList<ServiceItemGroup>();
	
	
	String dataType;
	String XMLServiceInfo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Spinner spinner = (Spinner) findViewById(R.id.SpinnerValue);
        
		List<String> ids=new ArrayList<String>();
       	ids.add("Item");
       	ids.add("Object");
       	ids.add("Group");

       	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
       			android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner!=null)
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
        
        
    	Serializer serializer = new Persister();
    	XMLServiceInfo = getIntent().getExtras().getString("ServiceInfo");
        try {
        	XMLToServiceInfo xmlToServiceInfo = new XMLToServiceInfo() {
				
				@Override
				public void responseComplete(ServiceInfo serviceInfo) {
					// TODO Auto-generated method stub
					Log.d("Async", "khatam hoo gya");
					locationService = serviceInfo.getLocationService();
					itemList = serviceInfo.getProductResultSet().getProducts();
					objectList = locationService.getItems();
					groupList = locationService.getGroups();
					resetList(dataType);
				}
			};
			
			ListView lv = (ListView) findViewById(R.id.objectList);
	        
	        lv.setOnItemClickListener(new OnItemClickListener() {
	        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        		TextView sName = (TextView) view.findViewById(R.id.text);
	        		String Name=sName.getText().toString();
	        		Intent intent=new Intent(context, MapShow.class);
	        		intent.putExtra("dataType", dataType);
	        		intent.putExtra("clickedText" , Name);
	        		intent.putExtra("serviceInfo", XMLServiceInfo);
	        		startActivity(intent);
	        	}
			});
			
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
    }

    
    
    
    protected void resetList(String type) {
    	dataType = type;
		ListView lv = (ListView) findViewById(R.id.objectList);
    	ArrayList<String> ids=new ArrayList<String>();
		if (type.equals("Item")) {
	        for(int i=0;i<itemList.size();i++)
	        	ids.add(itemList.get(i).getName());			
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

    
    
    
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_service_list, menu);
        return true;
    }

}

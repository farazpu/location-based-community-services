package net.fast.lbcs.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.fast.lbcs.data.entities.admin.service.LocationService;
import net.fast.lbcs.data.entities.admin.service.LocationServices;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceListActivity extends Activity {

	List<LocationService> serviceList;
	Context context=this;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        
    	Serializer serializer = new Persister();

        try {
			LocationServices locationServices = serializer.read(LocationServices.class,  getIntent().getExtras().getString("locationServices"));
			serviceList = locationServices.getLocationServices();
			ArrayList<String> ids=new ArrayList<String>();
	        for(int i=0;i<serviceList.size();i++)
	        	ids.add(serviceList.get(i).getName());
	        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.simple_listview_row,R.id.text,ids);
			ListView lv = (ListView) findViewById(R.id.lv);
	        lv.setAdapter(adapter);
	        
	        lv.setOnItemClickListener(new OnItemClickListener() {
	        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        		TextView sName = (TextView) view.findViewById(R.id.text);
	        		String serviceName=sName.getText().toString();
	        		for(LocationService ls : serviceList) {
	        			if(serviceName.equals(ls.getName())) { 
	        		    	UrlTextLoader urlTextLoader = new UrlTextLoader() {
	        					@Override
	        					public void responseComplete(String result) {
	        						updateResult(result);
	        					}
	        				};
	        				urlTextLoader.execute("http://"  + TempIP.ip + ":8888/user/login.jsp?serviceID=" + ls.getId().getId());
	        			}
	        		}
	        	}
			});
			
			
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_service_list, menu);
        return true;
    }
    
    public void updateResult(String result) {

    	Serializer serializer = new Persister();

    	try {
			LocationServices lss = serializer.read(LocationServices.class, result);
			Intent intent=new Intent(this,ServiceListActivity.class);
			intent.putExtra("locationServices", result);
			startActivity(intent);
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}

    }

    
}
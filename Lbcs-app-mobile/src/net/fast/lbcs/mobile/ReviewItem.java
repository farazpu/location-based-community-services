package net.fast.lbcs.mobile;

import java.util.ArrayList;
import java.util.List;

import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductAttribute;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class ReviewItem extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_item);        
        Spinner spinner = (Spinner) findViewById(R.id.rating_spinner);
		List<String> ids=new ArrayList<String>();
       	ids.add("Very Low");
       	ids.add("Low");
       	ids.add("Average");
       	ids.add("High");
       	ids.add("Very High");

       	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
       			android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner!=null)
        spinner.setAdapter(adapter);
        
        Product product = CurrentServiceInfo.getItem(getIntent().getExtras().getString("Item")).get(0);
        
        setTitle(product.getName() + " (" + product.getId().getId() + ")");
        
        List<KeyValuePair> resource = new ArrayList<KeyValuePair>();
        List<ProductAttribute> attributeList = product.getAttrs();
        for(ProductAttribute pa : attributeList) {
        	resource.add( new KeyValuePair(pa.getKey(),pa.getValue()));
        }
        resource.add(new KeyValuePair("Comment", ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_review_item, menu);
        return true;
    }
}

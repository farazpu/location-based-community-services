package net.fast.lbcs.mobile;

import java.util.List;

import net.fast.lbcs.data.entities.admin.item.ServiceItem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class AddProductActivity extends Activity {

	Context context = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        
        Spinner itemSpinner = (Spinner) findViewById(R.id.selectItemSpinner);
        Button submit = (Button) findViewById(R.id.addProductSubmit);
        ListView listView = (ListView) findViewById(R.id.itemSelection);
        
        List<ServiceItem> itemList = CurrentServiceInfo.getObjectList();
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_product, menu);
        return true;
    }
}

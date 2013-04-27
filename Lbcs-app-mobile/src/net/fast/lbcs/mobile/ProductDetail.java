package net.fast.lbcs.mobile;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ProductDetail extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_product_detail, menu);
        return true;
    }
}

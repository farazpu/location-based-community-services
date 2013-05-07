package net.fast.lbcs.mobile;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ProductDetailTabbedActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_tabbed);
        
        
        TabHost tabHost = getTabHost();
        
        String clickedText = getIntent().getExtras().getString("clickedText");

        // Tab for Photos
        TabSpec map = tabHost.newTabSpec("Map");
        map.setIndicator("Map", getResources().getDrawable(R.drawable.ic_launcher));
        Intent mapIntent = new Intent(this, MapShow.class);
        mapIntent.putExtra("clickedText", clickedText);
        map.setContent(mapIntent);
        
        TabSpec detail = tabHost.newTabSpec("Detail");
        detail.setIndicator("Detail", getResources().getDrawable(R.drawable.ic_launcher));
        Intent detailIntent = new Intent(this, ProductDetail.class);
        detailIntent.putExtra("clickedText", clickedText);
        detail.setContent(detailIntent);
        

        TabSpec rating = tabHost.newTabSpec("Value Reviews");
        rating.setIndicator("Value Reviews", getResources().getDrawable(R.drawable.ic_launcher));
        Intent ratingIntent = new Intent(this, ReviewItem.class);
        ratingIntent.putExtra("clickedText", clickedText);
        rating.setContent(ratingIntent);

        TabSpec comments = tabHost.newTabSpec("Comments");
        comments.setIndicator("Comments", getResources().getDrawable(R.drawable.ic_launcher));
        Intent commentsIntent = new Intent(this, Comments.class);
        commentsIntent.putExtra("clickedText", clickedText);
        comments.setContent(commentsIntent);
        
        
        tabHost.addTab(map);
        tabHost.addTab(detail);
        tabHost.addTab(rating);
        tabHost.addTab(comments);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_product_detail_tabbed, menu);
        return true;
    }
}

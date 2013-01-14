package net.fast.lbcs.mobile;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.*;

public class MyItemizedOverlay extends ItemizedOverlay{

    private ArrayList< OverlayItem > mOverlays = new ArrayList< OverlayItem >();
    Context mContext;
 
 
 
    public MyItemizedOverlay(Drawable marker) {
        super(boundCenterBottom(marker));
    }
 
    public MyItemizedOverlay(Drawable marker, Context context) {
        super(boundCenterBottom(marker));
        mContext = context;
    }
 
    public void addOverlay(OverlayItem overlay) {
        mOverlays.add(overlay);
        populate();
    }
 
    @Override
    protected OverlayItem createItem(int i) {
        return mOverlays.get(i);
    }
 
    @Override
    public int size() {
        return mOverlays.size();
    }
 
    @Override
    protected boolean onTap(int i) {
        //when you tap on the marker this will show the informations provided by you when you create in the 
        //main class the OverlayItem
        OverlayItem item = mOverlays.get(i);
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle(item.getTitle());
        dialog.setMessage(item.getSnippet());
        dialog.show();
        return true;
    }
}

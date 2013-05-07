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
import net.fast.lbcs.data.entities.user.ProductValueReview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
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
        ExpandableListView list = (ExpandableListView) findViewById(R.id.expandableList);
        String clickedText = getIntent().getExtras().getString("clickedText");
        product = CurrentServiceInfo.getProduct(clickedText);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.activity_review_item, menu);
	    return true;
	}
	
	private class AttributeAdapter extends BaseExpandableListAdapter{

		public Object getChild(int groupPosition, int childPosition) {
			return null;
		}

		public long getChildId(int groupPosition, int childPosition) {
			return 0;
		}

		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			if(product.getServiceItem().getAttrs().getAttrs().get(groupPosition).getFlag().equals("true")){
			    LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    convertView = inflater.inflate(R.layout.child_row, null);
			    EditText et = (EditText) convertView.findViewById(R.id.rvAttributeValue);
			    List<ProductValueReview> vals=product.getValueReviews();
			    int count=0;
			    for(ProductValueReview rev :  vals){
			    	if(rev.getAttributeId().equals(product.getServiceItem().getAttrs().getAttrs().get(groupPosition).getId())){
			    		count++;
			    		if(rev.getUsername().equals(CurrentServiceInfo.currentUser))
			    			et.setText(rev.getValue());
			    	}
			    }
			    TextView tv = (TextView) convertView.findViewById(R.id.rvAttributeRevs);
			    tv.setText("Review Count = " + count + "   ");
			    
			    Button button = (Button) findViewById(R.id.rvSend);
			    button.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
//						if(Validator.validate(product.getServiceItem().getAttrs().getAttrs().get(groupPosition).getValidation(), value, attr))
						
					}
				});
			    
			}
			else{
			    LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    convertView = inflater.inflate(R.layout.unavailable_child_row, null);				
			}
			return convertView;
		}

		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return 0;
		}

		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return null;
		}

		public int getGroupCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			if(convertView==null){
			    LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    convertView = inflater.inflate(R.layout.parent_row, null);								
			}
			TextView tv = (TextView) findViewById(R.id.attrAdminValue);
			tv.setText(product.getAttributeValue(product.getServiceItem().getAttrs().getAttrs().get(groupPosition).getId()));
				tv = (TextView) findViewById(R.id.attrPublicValue);
			if(product.getServiceItem().getAttrs().getAttrs().get(groupPosition).getFlag().equals("true"))
				tv.setText(product.getAttributePublicValue(product.getServiceItem().getAttrs().getAttrs().get(groupPosition).getId()));
			else
				tv.setText(" ");
			return convertView;
		}

		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
    
}

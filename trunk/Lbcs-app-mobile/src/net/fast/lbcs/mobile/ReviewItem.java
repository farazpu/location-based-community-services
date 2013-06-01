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

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
	
	Intent thisintent;
	Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_item);        
        thisintent = getIntent();
        ExpandableListView list = (ExpandableListView) findViewById(R.id.expandableList);
        String clickedText = getIntent().getExtras().getString("clickedText");
        List<List<String>> arr=new ArrayList<List<String>>();
        List<String> arr1=new ArrayList<String>();
        arr1.add("a");
        product = CurrentServiceInfo.getProduct(clickedText);
        for(int i=0;i<product.getServiceItem().getAttrs().getAttrs().size();i++)
        {
        	arr.add(arr1);
        }
        AttributeAdapter adapter= new AttributeAdapter(arr);
        list.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.activity_review_item, menu);
	    return true;
	}
	
	private class AttributeAdapter extends BaseExpandableListAdapter{

		private List<List<String>> arr;

		public AttributeAdapter(List<List<String>> arr2) {
			this.arr = arr2;
			// TODO Auto-generated constructor stub
		}

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
			    button.setTag(1, product.getServiceItem().getAttrs().getAttrs().get(groupPosition));
			    button.setTag(2, et);
			    button.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						ServiceItemAttribute attribute = (ServiceItemAttribute)v.getTag(1);
						EditText et = (EditText)v.getTag(2);
//						if(Validator.validate(product.getServiceItem().getAttrs().getAttrs().get(groupPosition).getValidation(), value, attr))
						String validation = Validator.validate(attribute.getValidation(),et.getText().toString(),attribute.getId());
						if(validation.equals("true")){
							MyDate date = new MyDate(new Date());
							String request = "http://"  + TempIP.ip + ":8888/user/add_product_value_review.jsp";
							request+= "?serviceId=" + CurrentServiceInfo.getLocationService().getId().getId(); 
							request+= "&itemId=" + product.getServiceItem().getId().getId();
							request+= "&productId=" + product.getId().getId();
							request+= "&attributeId=" + attribute.getId();
							request+= "&username=" + CurrentServiceInfo.currentUser;
							request+= "&date=" + date;
							request+= "&value=" + et.getText().toString();
			    	        UrlTextLoader urlTextLoader = new UrlTextLoader() {
			    				@Override
			    				public void responseComplete(String result) {
			    					Toast.makeText(context, result, Toast.LENGTH_LONG).show();
			    					if(result.equals("Success.")){
			    						finish();
			    						startActivity(thisintent);
			    					}
			    				}
							};
							
							urlTextLoader.execute(request);

						}
						else
						{
							Toast.makeText(context, validation, Toast.LENGTH_LONG).show();
						}
						
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

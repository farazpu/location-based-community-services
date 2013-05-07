package net.fast.lbcs.mobile;

import java.util.Date;
import java.util.List;

import net.fast.lbcs.data.entities.MyDate;
import net.fast.lbcs.data.entities.user.Product;
import net.fast.lbcs.data.entities.user.ProductComment;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Comments extends Activity {

	Product product;
	Context context;
	Button send;
	EditText commentbox;
	ListView commentListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

       
        String clickedText = getIntent().getExtras().getString("clickedText");
        product = CurrentServiceInfo.getProduct(clickedText);
        
        List<ProductComment> comments= product.getComments();
        CommentAdapter ca = new CommentAdapter(context, R.layout.comment_row, comments);
        ListView lv = (ListView) findViewById(R.id.commentList);
        lv.setAdapter(ca);
        
        commentbox = (EditText) findViewById(R.id.commentEditText);
        send=(Button) findViewById(R.id.commentSubmit);
        commentListView = (ListView) findViewById(R.id.commentList);
        
        send.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				MyDate date = new MyDate(new Date());
				String request="http://"  + TempIP.ip + ":8888/user/add_comment.jsp";
				request+= "?serviceId=" + CurrentServiceInfo.getLocationService().getId().getId(); 
				request+= "&itemId=" + product.getServiceItem().getId().getId();
				request+= "&productId=" + product.getId().getId();
				request+= "&username=" + CurrentServiceInfo.currentUser;
				request+= "&date=" + date;
				request+= "&text=" + commentbox.getText().toString();
    	        UrlTextLoader urlTextLoader = new UrlTextLoader() {
    				@Override
    				public void responseComplete(String result) {
    						if(result.equals("Comment Added Successfully.")){
    					        List<ProductComment> comments= product.getComments();
    					        comments.add(new ProductComment(product.getId().getId(), commentbox.getText().toString(),
    					        		new MyDate(new Date()), CurrentServiceInfo.currentUser));
    					        CommentAdapter ca = new CommentAdapter(context, R.layout.comment_row, comments);
    					        ListView lv = (ListView) findViewById(R.id.commentList);
    					        lv.setAdapter(ca);
    							
    						}
   						}
    				};
    			urlTextLoader.execute(request);

			}
		});
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_comments, menu);
        return true;
    }
    
    
    
    private class CommentAdapter extends ArrayAdapter<ProductComment>{

    	Context context;
    	int resId;
    	List<ProductComment> data;
    	
    	
		public CommentAdapter(Context context, int textViewResourceId,
				List<ProductComment> objects) {
			super(context, textViewResourceId, objects);
			this.context=context;
			resId = textViewResourceId;
			data = objects;
		}
		
		@Override
		public View getView(int position,View ConvertView,ViewGroup parent)
		{
			View row=ConvertView;
			if(row==null)
			{
				LayoutInflater inflater=((Activity)context).getLayoutInflater();
				row = inflater.inflate(R.layout.attribute_row, parent, false);
				
			}
			
			TextView tw = (TextView)row.findViewById(R.id.crUsername);
			tw.setText(data.get(position).getUsername());
			tw = (TextView)row.findViewById(R.id.crTime);
			tw.setText(data.get(position).getDate()+"");
			tw = (TextView)row.findViewById(R.id.crText);
			tw.setText(data.get(position).getComment());
	        return row;
			
		}

		
    }
    
    
    
    
}

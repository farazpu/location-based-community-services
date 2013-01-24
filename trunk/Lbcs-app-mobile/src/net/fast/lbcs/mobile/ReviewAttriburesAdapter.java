package net.fast.lbcs.mobile;

import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ReviewAttriburesAdapter extends ArrayAdapter<KeyValuePair>{

	Context context;
	int textViewId;
	List<KeyValuePair> objects;
	public ReviewAttriburesAdapter(Context context, int textViewResourceId,
			List<KeyValuePair> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.textViewId = textViewResourceId;
		this.objects = objects;
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
		else
		{
		}
		
		TextView tw = (TextView)row.findViewById(R.id.review_key);
		tw.setText(objects.get(position).getKey());
		EditText et = (EditText)row.findViewById(R.id.review_value);
		et.setText(objects.get(position).getValue());
        return row;
		
	}

}

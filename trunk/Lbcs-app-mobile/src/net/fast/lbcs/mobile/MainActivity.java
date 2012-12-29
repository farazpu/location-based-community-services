package net.fast.lbcs.mobile;

import java.io.IOException;

import net.fast.lbcs.data.entities.Pair;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void readPoint(View view) {
    	EditText editText1 = (EditText) findViewById(R.id.editText1);
    	TextView textView1 = (TextView) findViewById(R.id.textView1);
    	
    	textView1.setText("Fetching from " + editText1.getText().toString() + "...");
    	
    	UrlTextLoader urlTextLoader = new UrlTextLoader() {
			@Override
			public void responseComplete(String result) {
				updateResult(result);
			}
		};
    	
		urlTextLoader.execute(editText1.getText().toString());
    }
    
    public void updateResult(String result) {
    	TextView textView1 = (TextView) findViewById(R.id.textView1);
    	textView1.setText(result);
    	
    	Serializer serializer = new Persister();

    	try {
			Pair p = serializer.read(Pair.class, result);
			String t = p.getFirst() + ", " + p.getSecond();
			textView1.setText(t);
		} catch (Exception e) {
			textView1.setText(e.getMessage());
		}

    }
    
    /*private class UrlTextLoader extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
	        HttpClient client = new DefaultHttpClient();
	        String url = params[0];
			HttpGet request = new HttpGet(url);
	        ResponseHandler<String> responseHandler = new BasicResponseHandler();
	        try {
				String response = client.execute(request, responseHandler);
				return response;
			} catch (ClientProtocolException e) {
				return e.getMessage();
			} catch (IOException e) {
				return e.getMessage();
			}
		}
		
		@Override
		protected void onPostExecute(String result) {
	    	TextView textView1 = (TextView) findViewById(R.id.textView1);
	    	textView1.setText(result);
	    	
	    	Serializer serializer = new Persister();

	    	try {
				Pair p = serializer.read(Pair.class, result);
				String t = p.getFirst() + ", " + p.getSecond();
				textView1.setText(t);
			} catch (Exception e) {
				textView1.setText(e.getMessage());
			}
	    	
		}
    	
    }*/
}

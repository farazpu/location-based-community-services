package net.fast.lbcs.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import net.fast.lbcs.data.entities.Pair;
import net.fast.lbcs.data.entities.UserLoginData;
import net.fast.lbcs.data.entities.user.ProductResultSet;

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
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	EditText usernameEditText = (EditText) findViewById(R.id.username);
    	EditText passwordEditText = (EditText) findViewById(R.id.password);
    	usernameEditText.setText("user1");
    	passwordEditText.setText("pass1");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void readPoint(View view) {
    	EditText usernameEditText = (EditText) findViewById(R.id.username);
    	EditText passwordEditText = (EditText) findViewById(R.id.password);
		Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();

    	
    	UrlTextLoader urlTextLoader = new UrlTextLoader() {
			@Override
			public void responseComplete(String result) {
				updateResult(result);
			}
		};
		urlTextLoader.execute("http://"  + TempIP.ip + ":8888/user/login.jsp?username=" + usernameEditText.getText().toString() + "&password=" + passwordEditText.getText().toString());
    }
    
    public void updateResult(String result) {
    	TextView textView1 = (TextView) findViewById(R.id.error);
    	
    	Serializer serializer = new Persister();

    	try {
			UserLoginData lss = serializer.read(UserLoginData.class, result);
			if(lss.isError()) {
				textView1.setText("Login Failed");
			}
			else {
				CurrentServiceInfo.validations = lss.getValidatios();
				Toast.makeText(this, "going", Toast.LENGTH_LONG).show();
				Intent intent=new Intent(this,ServiceListActivity.class);
				intent.putExtra("locationServices", result);
				startActivity(intent);
			}
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}

    }
    
}

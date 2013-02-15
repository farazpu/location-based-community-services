package net.fast.lbcs.mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.fast.lbcs.data.entities.admin.service.ServiceInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.os.AsyncTask;
import android.util.Log;


public abstract class XMLToServiceInfo  extends AsyncTask<String, Void, ServiceInfo> {

	@Override
	protected ServiceInfo doInBackground(String... params) {
		// TODO Auto-generated method stub
        String XMLServiceInfo = params[0];
    	Serializer serializer = new Persister();
    	ServiceInfo serviceInfo = null;
		try {
			serviceInfo = serializer.read(ServiceInfo.class,  XMLServiceInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("111exception", e.getMessage());
		}
		return serviceInfo;
	}
	
	@Override
	protected void onPostExecute(ServiceInfo result) {
    	responseComplete(result);
	}

	public abstract void responseComplete(ServiceInfo result);

	

}

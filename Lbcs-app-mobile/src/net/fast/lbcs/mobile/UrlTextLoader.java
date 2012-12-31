package net.fast.lbcs.mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public abstract class UrlTextLoader extends AsyncTask<String, Void, String> {

	
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
    	responseComplete(result);
	}
	
	public abstract void responseComplete(String result);
	
}
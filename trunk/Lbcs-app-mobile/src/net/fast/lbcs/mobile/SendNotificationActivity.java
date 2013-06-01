package net.fast.lbcs.mobile;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendNotificationActivity extends Activity {

	Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);
        Button button = (Button) findViewById(R.id.sendNotificationButton);
        button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				EditText et = (EditText) findViewById(R.id.notificationText);
				String notification = et.getText().toString();
		    	UrlTextLoader urlTextLoader = new UrlTextLoader() {
					@Override
					public void responseComplete(String result) {
						Toast.makeText(context, result, Toast.LENGTH_LONG).show();
					}
				};
				notification.replaceAll(" ", "_").toLowerCase();
				urlTextLoader.execute("http://"  + TempIP.ip + ":8888/user/notify.jsp?username=" + CurrentServiceInfo.currentUser + "&serviceId=" + CurrentServiceInfo.currentServiceInfo.getLocationService().getId().getId()
						+ "&notification=" + notification);

			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_send_notification, menu);
        return true;
    }
}

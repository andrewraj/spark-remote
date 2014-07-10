package com.android.sparkremote;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.Switch;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnDragListener, OnClickListener {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			Switch a = (Switch)rootView.findViewById(R.id.sparkSwitch);
			a.setOnDragListener(this);
			a.setOnClickListener(this);
			//a.setOnCheckedChangeListener(listener);
			return rootView;
		}

		@Override
		public boolean onDrag(View v, DragEvent event) {
			makeCall();
			return false;
		}

		@Override
		public void onClick(View v) {
			makeCall();
		}
		
		public void makeCall(){
			// TODO Auto-generated method stub
			String serverURL = "https://api.spark.io/v1";
			String deviceID = "/devices/51ff6f065067545734370187";
			final String accessToken = "ddc13c3a19c8f135af2a4427f5101cfcce1adfe0";
			
			
			
			// Tag used to cancel the request
			String tag_json_obj = "json_obj_req";
			 
			String url = serverURL + deviceID + "/relay";
			         
   
			         
			        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
			                url, new JSONObject(),
			                new Response.Listener<JSONObject>() {
			 
			                    @Override
			                    public void onResponse(JSONObject response) {
			                        Log.d(TAG, response.toString());
			                    }
			                }, new Response.ErrorListener() {
			 
			                    @Override
			                    public void onErrorResponse(VolleyError error) {
			                        VolleyLog.d(TAG, "Error: " + error.getMessage());
			                        // hide the progress dialog
			                    }
			                }) {
			            @Override
			            protected Map<String, String> getParams() {
			                Map<String, String> params = new HashMap<String, String>();
			                params.put("access_token", "ddc13c3a19c8f135af2a4427f5101cfcce1adfe0");
			                params.put("params", "r1,LOW");
			 
			                return params;
			            }
			        };
			 
			// Adding request to request queue
			HTTPConnection.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
		}
		
		
		
		
	}
	
}

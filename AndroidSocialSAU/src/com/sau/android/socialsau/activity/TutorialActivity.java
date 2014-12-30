package com.sau.android.socialsau.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.ImageReader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sau.android.socialsau.adapter.AdapterListViewTutorial;
import com.sau.android.socialsau.dto.Tutorial;
import com.sau.android.socialsau.util.IP;

public class TutorialActivity extends Activity {

	private ProgressDialog progressDialog;
	private ListView listViewTutorial;
	private ImageView imageRefresh;
	
	private List<Tutorial> tutorials;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tutorial);

		listViewTutorial = (ListView) findViewById(R.id.listview_tutorial);
		imageRefresh = (ImageView) findViewById(R.id.image_refresh);
		
		new LoadTutorialsTask().execute();
			
		imageRefresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new LoadTutorialsTask().execute();
			}
		});
		
	}
	
	private class LoadTutorialsTask extends AsyncTask<String, String, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(TutorialActivity.this);
			progressDialog.setMessage("Loading ...");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(IP.currentIP + "tutorial/getAllTutorialJSON");
				
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity entity = httpResponse.getEntity();
				
				if (entity != null) {
					InputStream is = entity.getContent();
					StringBuffer sb = new StringBuffer();
					String line = null;

					BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
					if ((line = reader.readLine()) != null) {
						sb.append(line);
					}
					reader.close();

					tutorials = new ArrayList<Tutorial>();
					JSONArray jArrays = new JSONArray(sb.toString());
					
					for (int i = 0; i < jArrays.length(); i++) {
						JSONObject jObjs = jArrays.getJSONObject(i);
						Tutorial tutorial = new Tutorial();
						tutorial.setTutorialId(jObjs.getInt("tutorialId"));
						tutorial.setUserId(jObjs.getInt("userId"));
						tutorial.setGroupId(jObjs.getInt("groupId"));
						tutorial.setTutorialName(jObjs.getString("tutorialName"));
						tutorial.setTutorialDetailReport(jObjs.getString("tutorialDetailReport"));
						tutorial.setUserUpdate(jObjs.getString("userUpdate"));
						tutorial.setUserCreate(jObjs.getString("userCreate"));
						tutorial.setTutorialUpdateStr(jObjs.getString("tutorialUpdateStr"));
						tutorial.setTutorialCreateStr(jObjs.getString("tutorialCreateStr"));
						tutorials.add(tutorial);
					}
				}else {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(TutorialActivity.this, "โหลดข้อมูลผิดพลาด", Toast.LENGTH_SHORT).show();
						}
					});
				}
			
				ListAdapter adapter = new AdapterListViewTutorial(TutorialActivity.this, tutorials);
				Log.d("adapter", adapter.getItem(0).toString());
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			ListAdapter adapter = new AdapterListViewTutorial(TutorialActivity.this, tutorials);
			listViewTutorial.setAdapter(adapter);
			
			listViewTutorial.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
					// inner class - insert final int position
					Tutorial tutorial = (Tutorial) listViewTutorial.getItemAtPosition(position);
					Intent intent = new Intent(TutorialActivity.this, TutorialDetailActivity.class);
					intent.putExtra("tutorial", tutorial);
					startActivity(intent);
				}
			});
		}
	}
	
	// back 
	@Override
	public void onBackPressed() {
		final Dialog dialog = new Dialog(TutorialActivity.this);
		dialog.setContentView(R.layout.dialog_exit);
		dialog.setTitle("Exit Application.");

		Button btnYes = (Button) dialog.findViewById(R.id.btn_yes);
		Button btnNo = (Button) dialog.findViewById(R.id.btn_no);

		btnYes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btnNo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
}
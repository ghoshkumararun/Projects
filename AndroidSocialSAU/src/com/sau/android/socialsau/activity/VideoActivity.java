package com.sau.android.socialsau.activity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sau.android.socialsau.adapter.AdapterListViewVideo;
import com.sau.android.socialsau.dto.Video;
import com.sau.android.socialsau.util.IP;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class VideoActivity extends Activity{

	private ProgressDialog progressDialog;
	private ListView listVideo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_video);
		
		listVideo = (ListView) findViewById(R.id.listview_video);
		Integer tutorialId = getIntent().getExtras().getInt("tutorialId");
		// Log.d("///// tutorialId /////" , tutorialId.toString());
		
		new LoadVideoTask().execute(tutorialId.toString());
	}
	
	private class LoadVideoTask extends AsyncTask<String, String, String> {

		private List<Video> videos;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(VideoActivity.this);
			progressDialog.setMessage("Loading ...");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("tutorialId", params[0]));
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(IP.currentIP + "video/findAllVideoJSON");
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
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
					
					videos = new ArrayList<Video>();
					JSONArray jsonArray = new JSONArray(sb.toString());
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObj = jsonArray.getJSONObject(i);
						Video video = new Video();
						video.setVideoId(jsonObj.getInt("videoId"));
						video.setTutorialId(jsonObj.getInt("tutorialId"));
						video.setVideoName(jsonObj.getString("videoName"));
						video.setVideoUrl(jsonObj.getString("videoUrl"));
						video.setVideoCode(jsonObj.getString("videoCode"));
						video.setVideoCreateStr(jsonObj.getString("videoCreateStr"));
						video.setStauts(jsonObj.getString("status"));
						videos.add(video);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (videos != null && !videos.isEmpty()) {
				ListAdapter adapter = new AdapterListViewVideo(VideoActivity.this, videos);
				listVideo.setAdapter(adapter);
				
				listVideo.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Video video = (Video) listVideo.getItemAtPosition(position);
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + video.getVideoCode()));
						//Log.d("/// video code ///", video.toString());
						startActivity(intent);
					}
				});
			}
			progressDialog.dismiss();
		}
		
		
	}
	
}

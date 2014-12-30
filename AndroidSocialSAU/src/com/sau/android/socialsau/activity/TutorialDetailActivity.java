package com.sau.android.socialsau.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.sau.android.socialsau.adapter.AdapterListViewChapter;
import com.sau.android.socialsau.dto.Chapter;
import com.sau.android.socialsau.dto.Tutorial;
import com.sau.android.socialsau.dto.Video;
import com.sau.android.socialsau.util.IP;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TutorialDetailActivity extends Activity {

	private ProgressDialog progressDialog;
	private TextView lbTutorialHeader, lbTutorialUpdate, lbTutorialUserUpdate, lbVideo;
	private ImageView imgVideo;
	private LinearLayout bgVideo;
	
	private ListView listChapter;
	
	private Tutorial tutorial;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tutorial_detail);
		
		lbTutorialHeader = (TextView) findViewById(R.id.lb_tutorial_header);
		lbTutorialUpdate = (TextView) findViewById(R.id.lb_tutorial_update);
		lbTutorialUserUpdate = (TextView) findViewById(R.id.lb_tutorial_user_update);
		listChapter = (ListView) findViewById(R.id.list_chapter);
		
		// Video
		lbVideo = (TextView) findViewById(R.id.lb_video);
		imgVideo = (ImageView) findViewById(R.id.img_video);
		bgVideo = (LinearLayout) findViewById(R.id.bg_video);
		
		// User user = (User) getIntent().getSerializableExtra("user");
		//Integer tutorialId = getIntent().getExtras().getInt("tutorialId");
		tutorial = (Tutorial)getIntent().getSerializableExtra("tutorial");
		
		new LoadTutorialDetailTask().execute(tutorial.getTutorialId().toString());
		new LoadChapterTask().execute(tutorial.getTutorialId().toString());
		new LoadVideoTask().execute(tutorial.getTutorialId().toString());
		
	}
	
	private class LoadTutorialDetailTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(TutorialDetailActivity.this);
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
				HttpPost httpPost = new HttpPost(IP.currentIP + "tutorial/searchTutorialJSON");
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
					
					tutorial = new Tutorial();
					
					JSONObject jObj = new JSONObject(sb.toString());
					tutorial.setTutorialId(jObj.getInt("tutorialId"));
					tutorial.setUserId(jObj.getInt("userId"));
					tutorial.setGroupId(jObj.getInt("groupId"));
					tutorial.setTutorialName(jObj.getString("tutorialName"));
					tutorial.setTutorialDetailReport(jObj.getString("tutorialDetailReport"));
					tutorial.setUserUpdate(jObj.getString("userUpdate"));
					tutorial.setUserCreate(jObj.getString("userCreate"));
					tutorial.setTutorialUpdateStr(jObj.getString("tutorialUpdateStr"));
					tutorial.setTutorialCreateStr(jObj.getString("tutorialCreateStr"));
				}
				/*else {
					runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(TutorialDetailActivity.this, "โหลดข้อมูลผิดพลาด", Toast.LENGTH_SHORT).show();
					}
					});
				}*/
			}catch(IOException e)  {
				e.printStackTrace();
				this.cancel(true);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (tutorial != null) {
				lbTutorialHeader.setText(tutorial.getTutorialName());
				lbTutorialUpdate.setText(tutorial.getTutorialUpdateStr());
				lbTutorialUserUpdate.setText(tutorial.getUserUpdate());
			}
		}
		
	}
	
	private class LoadChapterTask extends AsyncTask<String, String, String> {

		List<Chapter> chapters;
		
		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("tutorialId", params[0]));
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(IP.currentIP + "chapter/findAllChaptersJSON");
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
					
					JSONArray jArrays = new JSONArray(sb.toString());
						
					chapters = new ArrayList<Chapter>();
					for (int i = 0; i < jArrays.length(); i++) {
						JSONObject jObj = jArrays.getJSONObject(i);
						Chapter chapter = new Chapter();
						chapter.setChapterId(jObj.getInt("chapterId"));
						chapter.setTutorialId(jObj.getInt("tutorialId"));
						chapter.setChapterNo(jObj.getInt("chapterNo"));
						chapter.setChapterName(jObj.getString("chapterName"));
						chapter.setChapterDetailReport(jObj.getString("chapterDetailReport"));
						chapter.setUserUpdate(jObj.getString("chapterUpdateStr"));
						chapter.setUserCreate(jObj.getString("chapterCreateStr"));
						chapters.add(chapter);
					}
				}
			}catch(IOException e)  {
				e.printStackTrace();
				this.cancel(true);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (chapters != null) {
				ListAdapter adapter = new AdapterListViewChapter(TutorialDetailActivity.this, chapters);
				listChapter.setAdapter(adapter);
				listChapter.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Intent intent = new Intent(TutorialDetailActivity.this, ChapterDetailActivity.class);
						intent.putExtra("chapterId", chapters.get(position).getChapterId());
						startActivity(intent);
					}
				});
			}else {
				// แสดงข้อความ  No Chapter.
				List<Chapter> dummyData = new ArrayList<Chapter>();
				dummyData.add(new Chapter("No Chapter."));
				ListAdapter adapter = new AdapterListViewChapter(TutorialDetailActivity.this, dummyData);
				listChapter.setAdapter(adapter);
				listChapter.setBackgroundColor(Color.parseColor("#E2E2E2"));
			}
		}
	}
	
	private class LoadVideoTask extends AsyncTask<String, String, String> {

		List<Video> videos;
		
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
					
					JSONArray jArrays = new JSONArray(sb.toString());
					
					videos = new ArrayList<Video>();
					for (int i = 0; i < jArrays.length(); i++) {
						JSONObject jObj = jArrays.getJSONObject(i);
						
						Video video = new Video();
						video.setVideoId(jObj.getInt("videoId"));
						video.setTutorialId(jObj.getInt("tutorialId"));
						video.setVideoName(jObj.getString("videoName"));
						video.setVideoUrl(jObj.getString("videoUrl"));
						video.setVideoCode(jObj.getString("videoCode"));
						video.setVideoCreateStr(jObj.getString("videoCreateStr"));
						video.setStauts(jObj.getString("status"));
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
				lbVideo.setText(videos.size() + "  Videos on youtube.");
				bgVideo.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(TutorialDetailActivity.this, VideoActivity.class);
						intent.putExtra("tutorialId", tutorial.getTutorialId());
						Log.d("///// tutorialId /////" , tutorial.getTutorialId().toString());
						startActivity(intent);
					}
				});
			}
			// TEXT เป็น Video not found. ตั้งแต่แรก
			progressDialog.dismiss();
		}
		
	}
	
}
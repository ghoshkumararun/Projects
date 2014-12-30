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
import org.json.JSONObject;

import com.sau.android.socialsau.dto.Chapter;
import com.sau.android.socialsau.util.IP;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class ChapterDetailActivity extends Activity {

	private ProgressDialog progressDialog;
	private TextView lbChapter, lbChapterDetail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chapter_detail);
		
		lbChapter = (TextView) findViewById(R.id.lb_chapter);
		lbChapterDetail = (TextView) findViewById(R.id.lb_chapter_detail);
		
		Integer chapterId = getIntent().getExtras().getInt("chapterId");
		// Log.d("///// Chapter ID /////", chapterId.toString());
		new LoadChapterTask().execute(chapterId);
	}
	
	private class LoadChapterTask extends AsyncTask<Integer, String, String> {

		private Chapter chapter;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(ChapterDetailActivity.this);
			progressDialog.setMessage("Loading ...");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(Integer... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("chapterId", params[0].toString()));
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(IP.currentIP + "chapter/findChapterJSON");
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
					
					JSONObject jsonObj = new JSONObject(sb.toString());
					chapter = new Chapter();
					
					chapter.setChapterId(jsonObj.getInt("chapterId"));
					chapter.setTutorialId(jsonObj.getInt("tutorialId"));
					chapter.setChapterNo(jsonObj.getInt("chapterNo"));
					chapter.setChapterName(jsonObj.getString("chapterName"));
					chapter.setChapterDetail(jsonObj.getString("chapterDetail"));
					chapter.setChapterDetailReport(jsonObj.getString("chapterDetailReport"));
					chapter.setUserUpdate(jsonObj.getString("userUpdate"));
					chapter.setUserCreate(jsonObj.getString("userCreate"));
					chapter.setChapterUpdateStr(jsonObj.getString("chapterUpdateStr"));
					chapter.setChapterCreateStr(jsonObj.getString("chapterCreateStr"));
					
					lbChapter.setText(chapter.getChapterName());
					lbChapterDetail.setText(chapter.getChapterDetailReport());
				}else {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(ChapterDetailActivity.this, "โหลดข้อมูลผิดพลาด", Toast.LENGTH_SHORT).show();
						}
					});
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
		}
		
	}
	
}

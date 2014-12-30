package com.sau.android.socialsau.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.sau.android.socialsau.dto.User;
import com.sau.android.socialsau.util.IP;
import com.sau.android.socialsau.activity.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
 
	private ProgressDialog progressDialog;
	
	private EditText edtEmail, edtPassword;
	private Button btnLogin;
	
	private String email, password;
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		edtEmail = (EditText) findViewById(R.id.edt_email);
		edtPassword = (EditText) findViewById(R.id.edt_password);
		btnLogin = (Button) findViewById(R.id.btn_login);
		
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				email = edtEmail.getText().toString();
				password = edtPassword.getText().toString();
				if (email.trim().equals("")) {
					Toast.makeText(MainActivity.this, "กรุณากรอก 'อีเมล์'", Toast.LENGTH_SHORT).show();
				}else if (password.trim().equals("")) {
					Toast.makeText(MainActivity.this, "กรุณากรอก 'รหัสผ่าน'", Toast.LENGTH_SHORT).show();
				}else {
					new LoginTast().execute(email, password);
				}
				
				/*ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo network = connectivity.getActiveNetworkInfo();
				if (network != null) {
					email = edtEmail.getText().toString();
					password = edtPassword.getText().toString();
					//Log.e("email : ", email);
					//Log.e("password : ", password);
					if (email.trim().equals("")) {
						Toast.makeText(MainActivity.this, "กรุณากรอก 'อีเมล์'", Toast.LENGTH_SHORT).show();
					}else if (password.trim().equals("")) {
						Toast.makeText(MainActivity.this, "กรุณากรอก 'รหัสผ่าน'", Toast.LENGTH_SHORT).show();
					}else {
						new LoginTast().execute(email, password);
					}
				}else {
					Toast.makeText(MainActivity.this, "กรุณาเชื่อมต่อ 'อินเตอร์เน็ต'", Toast.LENGTH_SHORT).show();
				}*/
			}
		});
	}
	
	// Params, Progress, Result
	private class LoginTast extends AsyncTask<String, String, String>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setMessage("Loading ...");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("email", params[0]));
			nameValuePairs.add(new BasicNameValuePair("password", params[1]));
			// Log.d("email : ", params[0]);
			// Log.d("password : ", params[1]);
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(IP.currentIP + "user/getUserJSON");
				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
				
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity entity = httpResponse.getEntity();
				
				if (entity != null) {
					Intent intent = new Intent(MainActivity.this, TutorialActivity.class);
					intent.putExtra("user", user);
					startActivity(intent);
					finish();
				}else {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "กรุณากรอก 'อีเมล์/รหัสผ่าน ให้ถูกต้อง'", Toast.LENGTH_SHORT).show();
						}
					});
				}
			}catch(IOException e)  {
				e.printStackTrace();
				//this.cancel(true);
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
	
	// back 
	@Override
	public void onBackPressed() {
		final Dialog dialog = new Dialog(MainActivity.this);
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

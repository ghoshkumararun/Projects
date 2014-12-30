package com.sau.android.socialsau.adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sau.android.socialsau.activity.R;
import com.sau.android.socialsau.dto.Chapter;
import com.sau.android.socialsau.dto.Tutorial;
import com.sau.android.socialsau.dto.Video;

public class AdapterListViewVideo extends BaseAdapter {

	private Context context;
	private List<Video> videos;
	
	public AdapterListViewVideo(Context context, List<Video> videos) {
		this.context = context;
		this.videos = videos;
	}
	
	@Override
	public int getCount() {
		return videos.size();
	}
	@Override
	public Object getItem(int position) {
		return videos.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public class ViewHolder {
		TextView lbVideo;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (view == null) {
			view = inflater.inflate(R.layout.list_video, null);
			holder = new ViewHolder();
			holder.lbVideo = (TextView) view.findViewById(R.id.lb_video_name);
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		
		Video video = (Video) getItem(position);
		holder.lbVideo.setText(video.getVideoName());
		
		return view;
	}
	
}

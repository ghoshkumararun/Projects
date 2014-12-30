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

public class AdapterListViewChapter extends BaseAdapter {

	private Context context;
	private List<Chapter> chapters;
	
	public AdapterListViewChapter(Context context, List<Chapter> chapters) {
		this.context = context;
		this.chapters = chapters;
	}
	
	@Override
	public int getCount() {
		return chapters.size();
	}
	@Override
	public Object getItem(int position) {
		return chapters.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public class ViewHolder {
		TextView lbChapter;
		ImageView imgNext;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (view == null) {
			view = inflater.inflate(R.layout.list_chapter, null);
			holder = new ViewHolder();
			holder.lbChapter = (TextView) view.findViewById(R.id.lb_chapter);
			holder.imgNext = (ImageView) view.findViewById(R.id.img_next);
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		
		Chapter chapter = (Chapter)getItem(position);
		holder.lbChapter.setText(chapter.getChapterName());
		
		
		//กำหนด กรณี No Chapter.
		if (chapter.getChapterName().trim().equals("No Chapter.")) {
			holder.imgNext.setImageDrawable(null);
		}
		
		return view;
	}
	
}

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
import android.widget.TextView;

import com.sau.android.socialsau.activity.R;
import com.sau.android.socialsau.dto.Tutorial;

public class AdapterListViewTutorial extends BaseAdapter {

	private Context context;
	private List<Tutorial> tutorials;
	
	public AdapterListViewTutorial(Context context, List<Tutorial> tutorials) {
		this.context = context;
		this.tutorials = tutorials;
	}
	
	@Override
	public int getCount() {
		return tutorials.size();
	}
	@Override
	public Object getItem(int position) {
		return tutorials.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public class ViewHolder {
		TextView lbTutorial;
		TextView lbUpdateTutorial;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (view == null) {
			view = inflater.inflate(R.layout.list_tutorial, null);
			
			holder = new ViewHolder();
			holder.lbTutorial = (TextView) view.findViewById(R.id.lb_tutorial);
			holder.lbUpdateTutorial = (TextView) view.findViewById(R.id.lb_updateTutorial);
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		
		Tutorial tutorial = (Tutorial)getItem(position);
		
		holder.lbTutorial.setText(tutorial.getTutorialName());
		holder.lbUpdateTutorial.setText(tutorial.getTutorialUpdateStr());
		
		if (position % 2 == 0) {
			view.setBackgroundColor(Color.parseColor("#E2E2E2"));
		}else {
			view.setBackgroundColor(Color.parseColor("#FFFFFF"));
		}
		
		return view;
	}
	
}

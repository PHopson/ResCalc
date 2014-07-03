package us.platyp.rescalc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AllColorAdapter extends ArrayAdapter<String> {
	private Activity activity;
	private int resource;
	String[] data;
	LayoutInflater inflater;

	public AllColorAdapter(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		this.activity = (Activity) context;
		this.resource = resource;
		this.data = objects;
		inflater = activity.getLayoutInflater();
	}
	
	//Map strings to their proper colors
	public int StringToColor(String color) {
		int resColor;
		switch (color.toUpperCase()) {
			case "BLACK":
				resColor = R.color.BLACK;
				break;
			case "BROWN":
				resColor = R.color.BROWN;
				break;
			case "RED":
				resColor = R.color.RED;
				break;
			case "ORANGE":
				resColor = R.color.ORANGE;
				break;
			case "YELLOW":
				resColor = R.color.YELLOW;
				break;
			case "GREEN":
				resColor = R.color.GREEN;
				break;
			case "BLUE":
				resColor = R.color.BLUE;
				break;
			case "VIOLET":
				resColor = R.color.VIOLET;
				break;
			case "GREY":
				resColor = R.color.GREY;
				break;
			case "GOLD":
				resColor = R.color.GOLD;
				break;
			case "SILVER":
				resColor = R.color.SILVER;
				break;
			default:
				resColor = R.color.WHITE;
		}
		
		return resColor;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		super.getView(position, convertView, parent);
		
		
		TextView tView = (TextView) inflater.inflate(resource, parent, false);
		
		int bColor = StringToColor(data[position]);
		if (bColor == R.color.BLACK) {
			tView.setTextColor(activity.getResources().getColor(R.color.WHITE));
		}
		tView.setBackgroundResource(bColor);
		tView.setText(data[position]);
		
		return tView;
		
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		super.getDropDownView(position, convertView, parent);
		
		return getView(position, convertView, parent);
	}

}

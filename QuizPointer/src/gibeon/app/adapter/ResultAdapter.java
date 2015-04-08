package gibeon.app.adapter;
import gibeon.app.DataAccess.DataItem;
import gibeon.app.entities.Item;
import gibeon.app.entities.SectionItem;
import gibeon.app.quizpointer.R;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ResultAdapter extends ArrayAdapter<Item> {
	 
	 private Context context;
	 private ArrayList<Item> items;
	 private LayoutInflater vi;
	 
	 public ResultAdapter(Context context,ArrayList<Item> items) {
	  super(context,0, items);
	  this.context = context;
	  this.items = items;
	  vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 }
	 
	 
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
	  View v = convertView;
	 
	  final Item i = items.get(position);
	  if (i != null) {
	   if(i.isSection()){
	    SectionItem si = (SectionItem)i;
	    v = vi.inflate(R.layout.userresult_cellheader, null);
	 
	    v.setOnClickListener(null);
	    v.setOnLongClickListener(null);
	    v.setLongClickable(false);
	     
	    final TextView sectionView = (TextView) v.findViewById(R.id.user_list);
	    sectionView.setText(si.getTitle());
	     
	   }else{
	    DataItem ei = (DataItem)i;
	    v = vi.inflate(R.layout.userresult_celllist, null);
	    final TextView dates = (TextView)v.findViewById(R.id.tv_date);
	    final TextView packages = (TextView)v.findViewById(R.id.tv_package);
	    final TextView counts = (TextView)v.findViewById(R.id.tv_count);
	    
	     dates.setText(ei.dates);
	     packages.setText(ei.packages);
	     counts.setText(ei.counts);

	   }
	  }
	  return v;
	 }
	 
	}


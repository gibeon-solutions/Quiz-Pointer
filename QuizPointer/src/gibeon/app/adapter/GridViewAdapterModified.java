package gibeon.app.adapter;

import gibeon.app.entities.Packages;
import gibeon.app.quizpointer.R;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

/**
 * 
 * @author javatechig {@link http://javatechig.com}
 * 
 */
public class GridViewAdapterModified extends ArrayAdapter<Packages> {
	private Context context;
	private int layoutResourceId;
	private ArrayList<Packages> data = new ArrayList<Packages>();

	public GridViewAdapterModified(Context context, int layoutResourceId,
			ArrayList<Packages> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.packageTitle = (Button) row.findViewById(R.id.btnpackage);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}
		
		Packages rowData = data.get(position);
		holder.packageTitle.setText(rowData.getName());
		holder.packageTitle.setTag(rowData.getStatus());
		holder.packageTitle.setBackgroundResource(rowData.iconBg);
		holder.packageTitle.setTextColor(rowData.colorTc);
		return row;
	}

	static class ViewHolder {
		Button packageTitle;
	}
}
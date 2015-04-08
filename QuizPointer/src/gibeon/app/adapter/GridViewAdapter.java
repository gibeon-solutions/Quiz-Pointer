package gibeon.app.adapter;

import gibeon.app.entities.Packages;
import gibeon.app.entities.packageUser;
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
public class GridViewAdapter extends ArrayAdapter<packageUser> {
	private Context context;
	private int layoutResourceId;
	private ArrayList<packageUser> data = new ArrayList<packageUser>();

	public GridViewAdapter(Context context, int layoutResourceId,
			ArrayList<packageUser> data) {
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
		packageUser rowData = data.get(position);
		holder.packageTitle.setText(rowData.getPackageName());
		holder.packageTitle.setTag(rowData.getPackageId());

		if (holder.packageTitle.getText().toString().equals("start")) {
			holder.packageTitle.setBackgroundResource(R.drawable.bstartoff);
			holder.packageTitle.setText("");
		}

		return row;
	}

	static class ViewHolder {
		Button packageTitle;
	}
}
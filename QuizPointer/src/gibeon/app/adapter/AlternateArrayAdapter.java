package gibeon.app.adapter;

import gibeon.app.entities.User;
import gibeon.app.quizpointer.R;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AlternateArrayAdapter extends ArrayAdapter<User> {
	private int[] colors = new int[] { (Color.parseColor("#EAEAEA")),
			Color.WHITE };
	List<User> listUser = new ArrayList<User>();
	int resourceId;
	private Context context;

	public AlternateArrayAdapter(Context context, int resource,
			List<User> objects) {
		super(context, resource, objects);
		this.listUser = objects;
		this.resourceId = resource;
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		ViewHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(resourceId, parent, false);
			holder = new ViewHolder();
			holder.username = (TextView) row.findViewById(R.id.user_list);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}
		User rowData = listUser.get(position);
		holder.username.setText(rowData.getUsername());
		holder.username.setTag(rowData.getId());
		int colorPos = position % colors.length;
		row.setBackgroundColor(colors[colorPos]);
		return row;
	}

	static class ViewHolder {
		TextView username;
	}
}

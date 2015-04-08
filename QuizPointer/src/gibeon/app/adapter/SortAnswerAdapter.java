package gibeon.app.adapter;

import gibeon.app.entities.Answer;
import gibeon.app.quizpointer.R;

import java.util.ArrayList;
import java.util.Collections;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
//import android.widget.LinearLayout;

public class SortAnswerAdapter extends BaseAdapter implements DropListener, RemoveListener {
	private int _mIds;
    private int _mLayouts;
    private LayoutInflater _mInflater;
	private ArrayList<Answer> _values;
	ArrayList<Integer> answerMark = new ArrayList<Integer>();
		
	public SortAnswerAdapter(Context context, ArrayList<Answer> answers) {
		init(context, R.layout.sorted_cell, R.id.sorted_answer, answers);
		answerMark.add(1);
		answerMark.add(2);
		answerMark.add(3);
		answerMark.add(4);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null)
		{
			convertView = _mInflater.inflate(_mLayouts, null);
			
			holder = new ViewHolder();
			holder.text = (TextView)convertView.findViewById(_mIds);
			
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		Answer rowData = _values.get(position);
		
		holder.text.setText(rowData.AnswerText);
		holder.text.setBackgroundResource(rowData.iconBg);
		holder.text.setTag(answerMark.get(position));
		return convertView;
	}
	
	private void init(Context context, int layout, int id, ArrayList<Answer> content) {
    	// Cache the LayoutInflate to avoid asking for a new one each time.
    	_mInflater = LayoutInflater.from(context);
    	_mIds = id;
    	_mLayouts = layout;
    	_values = content;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _values.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _values.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public void onRemove(int which) {
		if (which < 0 || which > _values.size()) return;		
		_values.remove(which);
	}

	@Override
	public void onDrop(int from, int to) {
		Collections.swap(_values, from, to);
		Collections.swap(answerMark, from, to);
	}
	
	public ArrayList<Answer> get_values() {
		return _values;
	}
	
	static class ViewHolder {
        TextView text;
    }
	
}

package gibeon.app.adapter;

import gibeon.app.entities.Answer;
import gibeon.app.quizpointer.R;

import java.util.ArrayList;
import java.util.Collections;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MatchAnswerAdapter extends BaseAdapter implements DropListener, RemoveListener {
    private int _mLayouts;
    private LayoutInflater _mInflater;
	private ArrayList<Answer> _values;
	ArrayList<Integer> answerMark = new ArrayList<Integer>();
	
	public MatchAnswerAdapter(Context context, ArrayList<Answer> answers,int a,int b, int c) {
		init(context, R.layout.pairmatching_cell, R.id.pairmatchingCell, answers);
		answerMark.add(a);
		answerMark.add(b);
		answerMark.add(c);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		
		if(convertView == null)
		{
			convertView = _mInflater.inflate(_mLayouts, null);
			
			holder = new Holder();
			holder.text = (TextView)convertView.findViewById(R.id.pairmatching_answer);
			
			convertView.setTag(holder);
		}
		else
		{
			holder = (Holder) convertView.getTag();
		}
		
		Answer rowData = _values.get(position);
		holder.text.setText(rowData.AnswerText);
		holder.text.setBackgroundResource(rowData.iconBg);
		holder.text.setTag(answerMark.get(position));
		
		return convertView;
	}
	
	private void init(Context context, int layout, int id, ArrayList<Answer> content) {
    	_mInflater = LayoutInflater.from(context);
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
	
	static class Holder {
        TextView text;
    }
	
	

}

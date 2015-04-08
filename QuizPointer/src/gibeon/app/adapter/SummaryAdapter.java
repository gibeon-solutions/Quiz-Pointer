package gibeon.app.adapter;

import gibeon.app.DataAccess.DataItem;
import gibeon.app.entities.Item;
import gibeon.app.entities.SectionItem;
import gibeon.app.entities.SummaryResult;
import gibeon.app.quizpointer.R;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SummaryAdapter extends ArrayAdapter<SummaryResult> {

	private Context context;
	private ArrayList<SummaryResult> items;
	private LayoutInflater vi;
	private int[] colors = new int[] { Color.WHITE, Color.parseColor("#BEC5CB") };

	public SummaryAdapter(Context context, ArrayList<SummaryResult> items) {
		super(context, 0, items);
		this.context = context;
		this.items = items;
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		int colorPos = position % colors.length;
		final SummaryResult i = items.get(position);
		if (i != null) {
			v = vi.inflate(R.layout.summary_cell, null);
			v.setOnClickListener(null);
			v.setOnLongClickListener(null);
			v.setLongClickable(false);

			final LinearLayout linearQuestion = (LinearLayout) v
					.findViewById(R.id.linearquestion);

			final TextView sectionView = (TextView) v
					.findViewById(R.id.tv_summaryquestion);
			final TextView answerUser = (TextView) v
					.findViewById(R.id.tv_summaryuserquestion);
			final TextView correctAnswer = (TextView) v
					.findViewById(R.id.tv_summarycorrectquestion);

			final ImageView icon = (ImageView) v.findViewById(R.id.iv_sign);

			if (colorPos == 0) {
				linearQuestion.setVisibility(View.VISIBLE);
				icon.setBackgroundResource(i.iconBg);
				answerUser.setVisibility(View.GONE);
				correctAnswer.setVisibility(View.GONE);
				sectionView.setText("Question : "+ i.getQuestion());
			} else {
				linearQuestion.setVisibility(View.GONE);
				answerUser.setVisibility(View.VISIBLE);
				answerUser.setText("Answer : " + i.getUserAnswer());

				if (!i.isSection) {
					correctAnswer.setVisibility(View.VISIBLE);
					correctAnswer.setText("Correct : " + i.getCorrectAnswer());
					answerUser.setTextColor(Color.RED);
				} else {
					correctAnswer.setVisibility(View.GONE);
				}

			}
		}

		v.setBackgroundColor(colors[colorPos]);
		return v;
	}

}

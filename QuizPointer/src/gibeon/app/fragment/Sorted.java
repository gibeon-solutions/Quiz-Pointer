package gibeon.app.fragment;

import gibeon.app.adapter.DragListener;
import gibeon.app.adapter.DragNDropListView;
import gibeon.app.adapter.DropListener;
import gibeon.app.adapter.SortAnswerAdapter;
import gibeon.app.entities.Answer;
import gibeon.app.entities.Question;
import gibeon.app.entities.QuestionsBL;
import gibeon.app.entities.ToolTips;
import gibeon.app.quizpointer.QuizFragment;
import gibeon.app.quizpointer.R;

import java.util.ArrayList;
import java.util.Collections;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

public class Sorted extends Fragment {
	ArrayList<Answer> answer_data, answerTemp;

	public View questionView;
	ArrayList<Question> question = QuizFragment.question;
	private static ArrayList<Integer> bgSortAnswers;
	SortAnswerAdapter adapter;
	String choice;
	int drawableImage = 0, drawableImage2;
	String correctAnswer = "", userAnswer = "ABCD";
	String[] alfaChoice = { "A", "B", "C", "D" };
	String[] textChoicee = new String[4];
	String result[] = new String[4];
	String splitCorrect[];
	ArrayList<String> answerMark = new ArrayList<String>();
	ArrayList<String> answerText = new ArrayList<String>();
	ToolTips tooltip;
	QuestionsBL qbl = new QuestionsBL(getActivity(), "1");

	public static ArrayList<String> textChoice = new ArrayList<String>();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		createData();
		answerMark.add("A");
		answerMark.add("B");
		answerMark.add("C");
		answerMark.add("D");

		if (questionView != null) {
			ViewGroup parent = (ViewGroup) questionView.getParent();
			if (parent != null)
				parent.removeView(questionView);
		}

		questionView = inflater.inflate(R.layout.page_sorted, container, false);

		QuizFragment.tvMessage.setText("Sort into correct order");
		QuizFragment.tvMessage.setBackgroundColor(Color.parseColor("#DFE2E5"));
		QuizFragment.tvMessage.setTextColor(Color.parseColor("#9EA7B2"));

		DragNDropListView answersListView = (DragNDropListView) questionView
				.findViewById(R.id.lv_sortedanswer);

		answer_data = qbl.getSortAnswers(question.get(QuizFragment.counter));
		answerTemp = answer_data;

		splitCorrect = question.get(QuizFragment.counter).getAnswer()
				.split(",");

		Answer.answerTextUser = "\n";
		for (int j = 0; j < bgSortAnswers.size(); j++) {
			answer_data.get(j).iconBg = bgSortAnswers.get(j);
			answerText.add(answer_data.get(j).AnswerText);
			Answer.answerUser += answer_data.get(j).AnswerMark;
			Answer.answerTextUser += answer_data.get(j).AnswerText + "\n";
			if (answer_data.get(j).AnswerText.length() > Integer
					.parseInt(getString(R.string.maxcharactersorted))) {
				answer_data.get(j).AnswerText = answer_data.get(j).AnswerText
						.substring(
								0,
								Integer.parseInt(getString(R.string.maxcharactersorted)) - 4)
						+ "....";
			} else {
				answer_data.get(j).AnswerText = answer_data.get(j).AnswerText
						.substring(0, answer_data.get(j).AnswerText.length());
			}
		}
		// userAnswer=Answer.answerUser;
		for (int y = 0; y < alfaChoice.length; y++) {
			for (int u = 0; u < alfaChoice.length; u++) {
				if (answer_data.get(y).AnswerMark.equals(alfaChoice[u])) {
					for (int k = 0; k < 4; k++) {
						if (splitCorrect[k].equals(alfaChoice[u])) {
							result[k] = alfaChoice[y];
						}
					}
				}
			}
		}

		for (int a = 0; a < result.length; a++) {
			correctAnswer += result[a];
			if (a < result.length - 1) {
				correctAnswer += ",";
			}
		}

		Answer.correctAnswer = question.get(QuizFragment.counter).getAnswer();

		adapter = new SortAnswerAdapter(getActivity(), answer_data);

		answersListView.setAdapter(adapter);
		answersListView.setDropListener(mDropListener);
		answersListView.setDragListener(mDragListener);

		TextView questionTV = (TextView) questionView
				.findViewById(R.id.sorted_questiontext);
		questionTV
				.setText(question.get(QuizFragment.counter).getQuestionText());

		tooltip = new ToolTips(getActivity(), questionView);
		questionView.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));

		return questionView;
	}

	private DropListener mDropListener = new DropListener() {
		public void onDrop(int from, int to) {
			if (QuizFragment.isEnableChoice) {
				DragNDropListView listView = (DragNDropListView) questionView
						.findViewById(R.id.lv_sortedanswer);

				SortAnswerAdapter adapter = (SortAnswerAdapter) listView
						.getAdapter();
				adapter.onDrop(from, to);
				((AbsListView) listView).invalidateViews();

				Collections.swap(answerMark, from, to);

				Answer.answerUser = "";
				Answer.answerTextUser = "\n";
				for (int h = 0; h < answer_data.size(); h++) {
					Answer.answerUser += answer_data.get(h).AnswerMark;
					Answer.answerTextUser += answer_data.get(h).AnswerText
							+ "\n";
				}

				userAnswer = "";
				for (int h = 0; h < answerMark.size(); h++) {
					userAnswer += answerMark.get(h);
				}

				for (int a = 0; a < answerMark.size(); a++) {
					String data = answerMark.get(a);
					if (data.equals(choice)) {
						answer_data.get(a).iconBg = drawableImage;
						break;
					}
					adapter.notifyDataSetChanged();
				}

			}
		}
	};

	private DragListener mDragListener = new DragListener() {
		TextView textToolTip;

		public void onDrag(int x, int y, ListView listView) {
		}

		public void onStartDrag(View itemView) {

			if (QuizFragment.isEnableChoice) {
				// QuizFragment.continueButton
				// .setBackgroundResource(R.drawable.bnexton);
				// QuizFragment.isActiveNext = true;

				itemView.setVisibility(View.INVISIBLE);
				textToolTip = (TextView) itemView
						.findViewById(R.id.sorted_answer);
				String toolTip = "";
				int getTag = Integer.parseInt(textToolTip.getTag().toString());

				if (getTag == 1) {
					toolTip = answerText.get(0);
					drawableImage = R.drawable.quizchoiceselecta;
					drawableImage2 = R.drawable.quizchoicea;
					choice = "A";
				} else if (getTag == 2) {
					toolTip = answerText.get(1);
					drawableImage = R.drawable.quizchoiceselectb;
					drawableImage2 = R.drawable.quizchoiceb;
					choice = "B";
				} else if (getTag == 3) {
					toolTip = answerText.get(2);
					drawableImage = R.drawable.quizchoiceselectc;
					drawableImage2 = R.drawable.quizchoicec;
					choice = "C";
				} else if (getTag == 4) {
					toolTip = answerText.get(3);
					drawableImage = R.drawable.quizchoiceselectd;
					drawableImage2 = R.drawable.quizchoiced;
					choice = "D";
				}
				textToolTip.setBackgroundResource(drawableImage);
				tooltip.showToolTip(textToolTip, toolTip);
			}
		}

		public void onStopDrag(View itemView) {
			textToolTip.setBackgroundResource(drawableImage2);
			if (QuizFragment.isEnableChoice) {
				itemView.setVisibility(View.VISIBLE);
				tooltip.removeToolTip();
			}
		}

	};

	void createData() {
		bgSortAnswers = new ArrayList<Integer>();
		bgSortAnswers.add(R.drawable.quizchoicea);
		bgSortAnswers.add(R.drawable.quizchoiceb);
		bgSortAnswers.add(R.drawable.quizchoicec);
		bgSortAnswers.add(R.drawable.quizchoiced);
	}

	public void changeColorAnswer() {
		String corrects[] = Answer.correctAnswer.split(",");
		Answer.answerTextCorrect = "\n";
		for (int i = 0; i < corrects.length; i++) {
			for (int j = 0; j < alfaChoice.length; j++) {
				if (corrects[i].equals(alfaChoice[j])) {
					Answer.answerTextCorrect += textChoice.get(j) + "\n";
				}
			}
		}

		if (!Answer.answerUser.equals(Answer.correctAnswer)) {

			String replaceAnswer = userAnswer.replaceAll(",", "");
			String replaceCorrect = correctAnswer.replaceAll(",", "");

			for (int i = 0; i < replaceAnswer.length(); i++) {
				if (replaceAnswer.charAt(i) == 'A') {
					answer_data.get(i).iconBg = R.drawable.quizchoicewronga;
				} else if (replaceAnswer.charAt(i) == 'B') {
					answer_data.get(i).iconBg = R.drawable.quizchoicewrongb;
				} else if (replaceAnswer.charAt(i) == 'C') {
					answer_data.get(i).iconBg = R.drawable.quizchoicewrongc;
				} else if (replaceAnswer.charAt(i) == 'D') {
					answer_data.get(i).iconBg = R.drawable.quizchoicewrongd;
				}

				if (replaceAnswer.charAt(i) == replaceCorrect.charAt(i)) {
					if (replaceAnswer.charAt(i) == 'A') {
						answer_data.get(i).iconBg = R.drawable.quizchoicecorrecta;
					} else if (replaceAnswer.charAt(i) == 'B') {
						answer_data.get(i).iconBg = R.drawable.quizchoicecorrectb;
					} else if (replaceAnswer.charAt(i) == 'C') {
						answer_data.get(i).iconBg = R.drawable.quizchoicecorrectc;
					} else if (replaceAnswer.charAt(i) == 'D') {
						answer_data.get(i).iconBg = R.drawable.quizchoicecorrectd;
					}
				}
			}

			adapter.notifyDataSetChanged();
			TextView textCorrect = (TextView) questionView
					.findViewById(R.id.sorted_righttext);
			textCorrect.setVisibility(View.VISIBLE);
			textCorrect.setText("Right Answer : "
					+ correctAnswer.replaceAll(",", " "));
		}

		if (Answer.answerUser.equals(Answer.correctAnswer)) {
			String replaceAnswer = correctAnswer.replaceAll(",", "");
			for (int i = 0; i < replaceAnswer.length(); i++) {
				if (replaceAnswer.charAt(i) == 'A') {
					answer_data.get(i).iconBg = R.drawable.quizchoicecorrecta;
				} else if (replaceAnswer.charAt(i) == 'B') {
					answer_data.get(i).iconBg = R.drawable.quizchoicecorrectb;
				} else if (replaceAnswer.charAt(i) == 'C') {
					answer_data.get(i).iconBg = R.drawable.quizchoicecorrectc;
				} else if (replaceAnswer.charAt(i) == 'D') {
					answer_data.get(i).iconBg = R.drawable.quizchoicecorrectd;
				}
			}
			adapter.notifyDataSetChanged();
		}

	}

}

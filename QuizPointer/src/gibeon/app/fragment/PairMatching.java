package gibeon.app.fragment;

import gibeon.app.adapter.DragListener;
import gibeon.app.adapter.DragNDropListView;
import gibeon.app.adapter.DragNDropListView2;
import gibeon.app.adapter.DropListener;
import gibeon.app.adapter.MatchAnswerAdapter;
import gibeon.app.adapter.TextViewCustom;
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

public class PairMatching extends Fragment {

	public View questionView;
	String answerUser = "", answerUser1 = "ABC", answerUser2 = "DEF",
			correctAnswer1 = "", correctAnswer2 = "", correctAnswer = "";
	ArrayList<Question> question = QuizFragment.question;
	private static ArrayList<Integer> bgMatchAnswers;

	QuestionsBL qbl = new QuestionsBL(getActivity(), "1");
	ArrayList<Answer> answer_data;
	ArrayList<Answer> answer_data1, answer_data2;
	MatchAnswerAdapter adapter1, adapter2;
	String choice;
	int drawableImage = 0, drawableImage2;
	TextView textToolTip1, textToolTip2;
	ArrayList<String> answerMark1 = new ArrayList<String>();
	ArrayList<String> answerMark2 = new ArrayList<String>();
	public static ArrayList<String> textChoice1 = new ArrayList<String>();
	public static ArrayList<String> textChoice2 = new ArrayList<String>();

	String[] alfaChoice1 = { "A", "B", "C" };
	String[] alfaChoice2 = { "D", "E", "F" };

	String result1[] = new String[3];
	String result2[] = new String[3];

	String splitCorrect1[];
	String splitCorrect2[];
	
	ArrayList<String> answerText1 = new ArrayList<String>();
	ArrayList<String> answerText2 = new ArrayList<String>();
	ToolTips tooltip;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		createData();
		answerMark1.add("A");
		answerMark1.add("B");
		answerMark1.add("C");
		answerMark2.add("D");
		answerMark2.add("E");
		answerMark2.add("F");

		if (questionView != null) {
			ViewGroup parent = (ViewGroup) questionView.getParent();
			if (parent != null)
				parent.removeView(questionView);
		}

		questionView = inflater.inflate(R.layout.page_pairmatching, container,
				false);
		DragNDropListView answersListView1 = (DragNDropListView) questionView
				.findViewById(R.id.lv_pairmatchinganswer1);
		DragNDropListView2 answersListView2 = (DragNDropListView2) questionView
				.findViewById(R.id.lv_pairmatchinganswer2);

		QuizFragment.tvMessage.setText("Select the matching pair");
		QuizFragment.tvMessage.setBackgroundColor(Color.parseColor("#DFE2E5"));
		QuizFragment.tvMessage.setTextColor(Color.parseColor("#9EA7B2"));

		answer_data = qbl.getPairAnswers(question.get(QuizFragment.counter));
		Answer.correctAnswer = question.get(QuizFragment.counter)
				.getAnswer();

		answer_data1 = new ArrayList<Answer>();
		answer_data2 = new ArrayList<Answer>();

		Answer.answerTextUser="\n";
		Answer.answerTextUser2="\n";
		for (int j = 0; j < answer_data.size(); j++) {
			answerText1.add(answer_data.get(j).AnswerText);
			if (j < 3) {
				answer_data1.add(answer_data.get(j));
				answer_data1.get(j).iconBg = bgMatchAnswers.get(j);

				if (answer_data1.get(j).AnswerText.length() > Integer
						.parseInt(getString(R.string.maxcharacterpair))) {
					answer_data1.get(j).AnswerText = answer_data1.get(j).AnswerText
							.substring(
									0,
									Integer.parseInt(getString(R.string.maxcharacterpair)) - 4)
							+ "....";
				} else {
					answer_data1.get(j).AnswerText = answer_data1.get(j).AnswerText
							.substring(0,
									answer_data1.get(j).AnswerText.length());
				}
				
				Answer.answerUser +=answer_data1.get(j).AnswerMark;
				Answer.answerTextUser +=answer_data1.get(j).AnswerText+"\n";
			} else {
				answer_data2.add(answer_data.get(j));
				answer_data2.get(j - 3).iconBg = bgMatchAnswers.get(j);

				if (answer_data2.get(j - 3).AnswerText.length() > Integer
						.parseInt(getString(R.string.maxcharacterpair))) {
					answer_data2.get(j - 3).AnswerText = answer_data2
							.get(j - 3).AnswerText
							.substring(
									0,
									Integer.parseInt(getString(R.string.maxcharacterpair)) - 4)
							+ "....";
				} else {
					answer_data2.get(j - 3).AnswerText = answer_data2
							.get(j - 3).AnswerText.substring(0,
							answer_data2.get(j - 3).AnswerText.length());
				}
				Answer.answerUser2 +=answer_data2.get(j-3).AnswerMark;
				Answer.answerTextUser2 +=answer_data2.get(j-3).AnswerText+"\n";
			}

		}
		Answer.answerTemp=answerUser;
		String replaceCorrect = question.get(QuizFragment.counter)
				.getAnswer().replaceAll("-|,", "");
		String correct1 = "", correct2 = "";

		for (int h = 0; h < replaceCorrect.length(); h++) {
			if (h % 2 == 0) {
				correct1 += replaceCorrect.charAt(h);
			} else {
				correct2 += replaceCorrect.charAt(h);
			}
		}

		Answer.answerTextCorrect="\n";
		Answer.answerTextCorrect2="\n";
		for (int y = 0; y < alfaChoice1.length; y++) {
			for (int u = 0; u < alfaChoice1.length; u++) {
				if (answer_data1.get(y).AnswerMark.equals(alfaChoice1[u])) {
					for (int k = 0; k < 3; k++) {
						if (correct1.split("")[k + 1].equals(alfaChoice1[u])) {
							result1[k] = alfaChoice1[y];
							Answer.answerTextCorrect +=answer_data1.get(y).AnswerText+"\n";
						}
					}
				}
			}
		}

		for (int a = 0; a < result1.length; a++) {
			correctAnswer1 += result1[a];
		}

		for (int y = 0; y < alfaChoice2.length; y++) {
			for (int u = 0; u < alfaChoice2.length; u++) {
				if (answer_data2.get(y).AnswerMark.equals(alfaChoice2[u])) {
					for (int k = 0; k < 3; k++) {
						if (correct2.split("")[k + 1].equals(alfaChoice2[u])) {
							result2[k] = alfaChoice2[y];
							Answer.answerTextCorrect2 +=answer_data2.get(y).AnswerText+"\n";
						}
					}
				}
			}
		}

		for (int a = 0; a < result2.length; a++) {
			correctAnswer2 += result2[a];
		}

			adapter1 = new MatchAnswerAdapter(getActivity(), answer_data1, 1, 2, 3);

		adapter2 = new MatchAnswerAdapter(getActivity(), answer_data2, 4, 5, 6);

		answersListView1.setAdapter(adapter1);
		answersListView2.setAdapter(adapter2);

		answersListView1.setDropListener(pair1DropListener);
		answersListView2.setDropListener(pair2DropListener);
		answersListView1.setDragListener(dragListener1);
		answersListView2.setDragListener(dragListener2);

		TextView questionTV = (TextView) questionView
				.findViewById(R.id.pairmatching_questiontext);

		TextViewCustom.customFonts(getActivity(), "font/ExoRegular.otf",
				questionTV, question.get(QuizFragment.counter)
						.getQuestionText());
		
		tooltip=new ToolTips(getActivity(), questionView);
//		QuizFragment.sr.add(new SummaryResult(question.get(QuizFragment.counter)
//				.getQuestionText()));
		questionView.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));

		return questionView;
	}

	private DragListener dragListener1 = new DragListener() {
		public void onDrag(int x, int y, ListView listView) {
		}

		public void onStartDrag(View itemView) {
			if (QuizFragment.isEnableChoice) {
				QuizFragment.continueButton
						.setBackgroundResource(R.drawable.bnexton);
//				QuizFragment.isActiveNext = true;
				itemView.setVisibility(View.INVISIBLE);
				textToolTip1 = (TextView) itemView
						.findViewById(R.id.pairmatching_answer);
				String toolTip = "";
				if (Integer.parseInt(textToolTip1.getTag().toString()) == 1) {
					toolTip = answerText1.get(0);
					drawableImage = R.drawable.pairmatchingchoiceselecta;
					drawableImage2 = R.drawable.pairmatchingchoicea;
					choice = "A";
				} else if (Integer.parseInt(textToolTip1.getTag().toString()) == 2) {
					toolTip = answerText1.get(1);
					drawableImage = R.drawable.pairmatchingchoiceselectb;
					drawableImage2 = R.drawable.pairmatchingchoiceb;
					choice = "B";
				} else if (Integer.parseInt(textToolTip1.getTag().toString()) == 3) {
					toolTip = answerText1.get(2);
					drawableImage = R.drawable.pairmatchingchoiceselectc;
					drawableImage2 = R.drawable.pairmatchingchoicec;
					choice = "C";
				}
				textToolTip1.setBackgroundResource(drawableImage);
				tooltip.showToolTip(textToolTip1, toolTip);
			}
		}

		public void onStopDrag(View itemView) {
			if (QuizFragment.isEnableChoice) {
				textToolTip1.setBackgroundResource(drawableImage2);
				itemView.setVisibility(View.VISIBLE);
				tooltip.removeToolTip();
			}
		}
	};

	private DragListener dragListener2 = new DragListener() {
		public void onDrag(int x, int y, ListView listView) {
		}

		public void onStartDrag(View itemView) {
			if (QuizFragment.isEnableChoice) {
				QuizFragment.continueButton
						.setBackgroundResource(R.drawable.bnexton);
//				QuizFragment.isActiveNext = true;
				itemView.setVisibility(View.INVISIBLE);
				textToolTip2 = (TextView) itemView
						.findViewById(R.id.pairmatching_answer);
				String toolTip = "";
				if (Integer.parseInt(textToolTip2.getTag().toString()) == 4) {
					toolTip = answerText1.get(3);
					drawableImage = R.drawable.pairmatchingchoiceselectd;
					drawableImage2 = R.drawable.pairmatchingchoiced;
					choice = "D";
				} else if (Integer.parseInt(textToolTip2.getTag().toString()) == 5) {
					toolTip = answerText1.get(4);
					drawableImage = R.drawable.pairmatchingchoiceselecte;
					drawableImage2 = R.drawable.pairmatchingchoicee;
					choice = "E";
				} else if (Integer.parseInt(textToolTip2.getTag().toString()) == 6) {
					toolTip = answerText1.get(5);
					drawableImage = R.drawable.pairmatchingchoiceselectf;
					drawableImage2 = R.drawable.pairmatchingchoicef;
					choice = "F";
				}
				textToolTip2.setBackgroundResource(drawableImage);
				tooltip.showToolTip(textToolTip2, toolTip);
			}
		}

		public void onStopDrag(View itemView) {
			if (QuizFragment.isEnableChoice) {
				textToolTip2.setBackgroundResource(drawableImage2);
				itemView.setVisibility(View.VISIBLE);
				tooltip.removeToolTip();
			}
		}
	};

	private DropListener pair1DropListener = new DropListener() {
		public void onDrop(int from, int to) {
			if (QuizFragment.isEnableChoice) {
				DragNDropListView listView = (DragNDropListView) questionView
						.findViewById(R.id.lv_pairmatchinganswer1);

				MatchAnswerAdapter adapter = (MatchAnswerAdapter) listView
						.getAdapter();

				adapter.onDrop(from, to);
				((AbsListView) listView).invalidateViews();

				Collections.swap(answerMark1, from, to);

				Answer.answerUser = "";
				Answer.answerTemp = "";
				Answer.answerTextUser="\n";

				for (int h = 0; h < adapter.get_values().size(); h++) {
					Answer.answerUser += adapter.get_values().get(h).AnswerMark;
					Answer.answerTextUser +=adapter.get_values().get(h).AnswerText+"\n";
				}
				Answer.answerTemp = Answer.answerUser;

				answerUser1 = "";
				for (int h = 0; h < answerMark1.size(); h++) {
					answerUser1 += answerMark1.get(h);
				}

				for (int a = 0; a < answerMark1.size(); a++) {
					String data = answerMark1.get(a);
					if (data.equals(choice)) {
						answer_data1.get(a).iconBg = drawableImage;
						break;
					}
					adapter.notifyDataSetChanged();
				}
			}
		}
	};

	private DropListener pair2DropListener = new DropListener() {
		public void onDrop(int from, int to) {
			if (QuizFragment.isEnableChoice) {
				DragNDropListView2 listView = (DragNDropListView2) questionView
						.findViewById(R.id.lv_pairmatchinganswer2);

				MatchAnswerAdapter adapter = (MatchAnswerAdapter) listView
						.getAdapter();

				adapter.onDrop(from, to);
				((AbsListView) listView).invalidateViews();
				Collections.swap(answerMark2, from, to);

				Answer.answerUser2 = "";
				Answer.answerTextUser2="\n";
				for (int h = 0; h < adapter.get_values().size(); h++) {
					Answer.answerUser2 += adapter.get_values().get(h).AnswerMark;
					Answer.answerTextUser2 +=adapter.get_values().get(h).AnswerText+"\n";
				}

				answerUser2 = "";
				for (int h = 0; h < answerMark2.size(); h++) {
					answerUser2 += answerMark2.get(h);
				}

				for (int a = 0; a < answerMark2.size(); a++) {
					String data = answerMark2.get(a);
					if (data.equals(choice)) {
						answer_data2.get(a).iconBg = drawableImage;
						break;
					}
					adapter.notifyDataSetChanged();
				}

			}
		}
	};

	public void createData() {
		bgMatchAnswers = new ArrayList<Integer>();
		bgMatchAnswers.add(R.drawable.pairmatchingchoicea);
		bgMatchAnswers.add(R.drawable.pairmatchingchoiceb);
		bgMatchAnswers.add(R.drawable.pairmatchingchoicec);
		bgMatchAnswers.add(R.drawable.pairmatchingchoiced);
		bgMatchAnswers.add(R.drawable.pairmatchingchoicee);
		bgMatchAnswers.add(R.drawable.pairmatchingchoicef);
	}

	public void changeColorAnswer() {
		char[] alfaChoice1 = { 'A', 'B', 'C' };
		char[] alfaChoice2 = { 'D', 'E', 'F' };
		String temp1 = "", temp2 = "";
		String corrects[] = Answer.correctAnswer.split(",|-");
		for (int i = 0; i < corrects.length; i++) {
			if (i % 2 == 0) {
				temp1 += corrects[i];
			} else {
				temp2 += corrects[i];
			}
		}
		
//		textCorrect(Answer.answerTextCorrect, temp1, alfaChoice1, textChoice1);
//		textCorrect(Answer.answerTextCorrect2, temp2, alfaChoice2, textChoice2);

		String splitSummary1[] = Answer.answerTextUser.split("\n");
		String splitSummary2[] = Answer.answerTextUser2.split("\n");
		
		String splitSummary3[] = textCorrect(Answer.answerTextCorrect, temp1, alfaChoice1, textChoice1).split("\n");
		String splitSummary4[] = textCorrect(Answer.answerTextCorrect2, temp2, alfaChoice2, textChoice2).split("\n");

		Answer.answerTextUser="\n";
		for (int a = 1; a < splitSummary1.length; a++) {
			Answer.answerTextUser +=splitSummary1[a]+"<--->"+splitSummary2[a]+"\n";
		}

		Answer.answerTextCorrect="\n";
		for (int a = 1; a < splitSummary3.length; a++) {
			Answer.answerTextCorrect +=splitSummary3[a]+"<--->"+splitSummary4[a]+"\n";
		}
		
		
//		QuizFragment.sr.add(new SummaryResult(Answer.answerTextUser,Answer.answerTextCorrect,false));

		String splitAlfaCheck1[] = Answer.answerUser.split(",");
		String splitAlfaCheck2[] = Answer.correctAnswer.split(",");
		int counter=0;
		
		for (int a = 0; a < splitAlfaCheck1.length; a++) {
			for(int b=0;b<splitAlfaCheck2.length;b++){
				if(splitAlfaCheck1[a].equals(splitAlfaCheck2[b])){
					counter++;
				}
			}
		}
		
		String splitAlfa1[] = answerUser1.split("");
		String splitAlfa2[] = answerUser2.split("");
		answerUser = "";
		for (int a = 1; a < splitAlfa1.length; a++) {
			answerUser += splitAlfa1[a] + "-" + splitAlfa2[a];
			if (a < splitAlfa1.length - 1) {
				answerUser += ",";
			}
		}

		String splitAlfa3[] = correctAnswer1.split("");
		String splitAlfa4[] = correctAnswer2.split("");
		correctAnswer = "";
		for (int a = 1; a < splitAlfa3.length; a++) {
			correctAnswer += splitAlfa3[a] + "-" + splitAlfa4[a];
			if (a < splitAlfa3.length - 1) {
				correctAnswer += ",";
			}
		}

		if (counter<3) {
			String replaceUser = answerUser.replaceAll("-|,", "");

			String leftCorrect = "", rightCorrect = "";
			for (int h = 0; h < replaceUser.length(); h++) {
				if (h % 2 == 0) {
					leftCorrect += replaceUser.charAt(h);
				} else {
					rightCorrect += replaceUser.charAt(h);
				}
			}

			int drawable1 = 0, drawable2 = 0;

			for (int i = 0; i < answer_data1.size(); i++) {
				if (leftCorrect.charAt(i) == 'A') {
					answer_data1.get(i).iconBg = R.drawable.pairmatchingchoicewronga;
					drawable1 = R.drawable.pairmatchingchoicecorrecta;
				} else if (leftCorrect.charAt(i) == 'B') {
					answer_data1.get(i).iconBg = R.drawable.pairmatchingchoicewrongb;
					drawable1 = R.drawable.pairmatchingchoicecorrectb;
				} else if (leftCorrect.charAt(i) == 'C') {
					answer_data1.get(i).iconBg = R.drawable.pairmatchingchoicewrongc;
					drawable1 = R.drawable.pairmatchingchoicecorrectc;
				}

				if (rightCorrect.charAt(i) == 'D') {
					answer_data2.get(i).iconBg = R.drawable.pairmatchingchoicewrongd;
					drawable2 = R.drawable.pairmatchingchoicecorrectd;
				} else if (rightCorrect.charAt(i) == 'E') {
					answer_data2.get(i).iconBg = R.drawable.pairmatchingchoicewronge;
					drawable2 = R.drawable.pairmatchingchoicecorrecte;
				} else if (rightCorrect.charAt(i) == 'F') {
					answer_data2.get(i).iconBg = R.drawable.pairmatchingchoicewrongf;
					drawable2 = R.drawable.pairmatchingchoicecorrectf;
				}

				for(int y=0;y<answer_data1.size();y++){
				if (leftCorrect.charAt(i) == correctAnswer1.charAt(y)
						&& rightCorrect.charAt(i) == correctAnswer2.charAt(y)) {
					answer_data1.get(i).iconBg = drawable1;
					answer_data2.get(i).iconBg = drawable2;
				}}
			}

			adapter1.notifyDataSetChanged();
			adapter2.notifyDataSetChanged();

			QuizFragment.wrongAnswer++;
			QuizFragment.answered=0;
			Answer.isCorrect=false;
			QuizFragment.tvMessage.setText("Wrong Answer");
			QuizFragment.tvMessage.setBackgroundColor(Color.parseColor("#E74C3C"));
			
			TextView textCorrect = (TextView) questionView
					.findViewById(R.id.pairmatching_righttext1);
			textCorrect.setVisibility(View.VISIBLE);
			textCorrect.setText("Right Answer: "
					+ correctAnswer.replaceAll(",", "\n\t\t"));
			
			
		}
		
		if (counter==3) {
			for (int i = 0; i < answer_data1.size(); i++) {
				if (answerUser1.charAt(i) == 'A') {
					answer_data1.get(i).iconBg = R.drawable.pairmatchingchoicecorrecta;
				} else if (answerUser1.charAt(i) == 'B') {
					answer_data1.get(i).iconBg = R.drawable.pairmatchingchoicecorrectb;
				} else if (answerUser1.charAt(i) == 'C') {
					answer_data1.get(i).iconBg = R.drawable.pairmatchingchoicecorrectc;
				}
				if (answerUser2.charAt(i) == 'D') {
					answer_data2.get(i).iconBg = R.drawable.pairmatchingchoicecorrectd;
				} else if (answerUser2.charAt(i) == 'E') {
					answer_data2.get(i).iconBg = R.drawable.pairmatchingchoicecorrecte;
				} else if (answerUser2.charAt(i) == 'F') {
					answer_data2.get(i).iconBg = R.drawable.pairmatchingchoicecorrectf;
				}
			}
			
			QuizFragment.tvMessage.setText("Right Answer");
			QuizFragment.tvMessage.setBackgroundColor(Color.parseColor("#1ABC9C"));
			QuizFragment.rightAnswer++;
			QuizFragment.answered=1;
			Answer.isCorrect=true;
			QuizFragment.pointValue += QuizFragment.question.get(QuizFragment.counter)
					.getValue();

			adapter1.notifyDataSetChanged();
			adapter2.notifyDataSetChanged();
		}
	}
	
	public String textCorrect(String textCorrect,String temp, char[] alfaChoice, ArrayList<String> arrayCorrect) {
		textCorrect = "\n";
		for (int i = 0; i < temp.length(); i++) {
			for (int j = 0; j < alfaChoice.length; j++) {
				if (temp.charAt(i) == alfaChoice[j]) {
					textCorrect += arrayCorrect.get(j) + "\n";
				}
			}
		}
		return textCorrect;
	}
}

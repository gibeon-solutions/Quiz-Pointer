package gibeon.app.fragment;

import gibeon.app.adapter.TextViewCustom;
import gibeon.app.entities.Answer;
import gibeon.app.entities.Question;
import gibeon.app.entities.QuestionsBL;
import gibeon.app.entities.SummaryResult;
import gibeon.app.entities.ToolTips;
import gibeon.app.quizpointer.QuizFragment;
import gibeon.app.quizpointer.R;

import java.util.ArrayList;
import java.util.Collections;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MultipleChoice extends Fragment {
	public View questionView;
	private int selected;
	ArrayList<Question> question = QuizFragment.question;
	public static TextView a, b, c, d;

	ArrayList<Answer> answers;

	ToolTips tooltip;
	QuestionsBL qbl = new QuestionsBL(getActivity(), "1");
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (questionView != null) {
			ViewGroup parent = (ViewGroup) questionView.getParent();
			if (parent != null)
				parent.removeView(questionView);
		}

		questionView = inflater.inflate(R.layout.page_multiplechoice,
				container, false);
		
		a = (TextView) questionView.findViewById(R.id.tv_answerA);
		b = (TextView) questionView.findViewById(R.id.tv_answerB);
		c = (TextView) questionView.findViewById(R.id.tv_answerC);
		d = (TextView) questionView.findViewById(R.id.tv_answerD);
		
		TextViewCustom.customFontRegular(getActivity(), a);
		TextViewCustom.customFontRegular(getActivity(), b);
		TextViewCustom.customFontRegular(getActivity(), c);
		TextViewCustom.customFontRegular(getActivity(), d);
		
		a.setBackgroundResource(R.drawable.quizchoicea);
		b.setBackgroundResource(R.drawable.quizchoiceb);
		c.setBackgroundResource(R.drawable.quizchoicec);
		d.setBackgroundResource(R.drawable.quizchoiced);
		
		TextView[] tvs= { a, b, c, d };
		int lengthAnswer=tvs.length;
		
		if (QuizFragment.question.get(QuizFragment.counter).getTypeQuestionId() == 2) {
		lengthAnswer=tvs.length-1;
		d.setVisibility(View.INVISIBLE);
		((View) questionView.findViewById(R.id.viewline))
				.setVisibility(View.INVISIBLE);
		}
		
		QuizFragment.tvMessage.setText("Select one answer");
		QuizFragment.tvMessage.setBackgroundColor(Color.parseColor("#DFE2E5"));
		QuizFragment.tvMessage.setTextColor(Color.parseColor("#9EA7B2"));


		answers = qbl.getMultiAnswer(question
				.get(QuizFragment.counter));

		for (int j = 0; j < lengthAnswer; j++) {
			tvs[j].setText(answers.get(j).AnswerText);
			showAnswer(tvs[j], answers.get(j).AnswerText);
			if (answers.get(j).AnswerMark.equals(question.get(
					QuizFragment.counter).getAnswer())) {
				Answer.correctAnswer = getCorrect(j);
				Answer.answerTextCorrect = answers.get(j).AnswerText;
			}
		}

		a.setOnClickListener(choose1Listener);
		b.setOnClickListener(choose1Listener);
		c.setOnClickListener(choose1Listener);
		d.setOnClickListener(choose1Listener);

		TextView questionTV = (TextView) questionView
				.findViewById(R.id.question_text);

		TextViewCustom.customFonts(getActivity(), "font/ExoRegular.otf",
				questionTV, question.get(QuizFragment.counter)
						.getQuestionText());

		tooltip=new ToolTips(getActivity(), questionView);
		
		questionView.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
		return questionView;
	}

	private OnClickListener choose1Listener = new OnClickListener() {
		private int prevSelected;

		@Override
		public void onClick(View v) {

			if (QuizFragment.isEnableChoice) {
				QuizFragment.continueButton
						.setBackgroundResource(R.drawable.bnexton);
				QuizFragment.isActiveNext = true;
				Answer.answerTextUser="";
				if (v.equals(a)) {
					tooltip.showToolTip(a, answers.get(0).AnswerText);
					v.setBackgroundResource(R.drawable.quizchoiceselecta);
					selected = 1;
					Answer.answerUser = "A";
					Answer.answerTextUser=answers.get(0).AnswerText;
				} else if (v.equals(b)) {
					tooltip.showToolTip(b, answers.get(1).AnswerText);
					v.setBackgroundResource(R.drawable.quizchoiceselectb);
					selected = 2;
					Answer.answerUser = "B";
					Answer.answerTextUser=answers.get(1).AnswerText;
				} else if (v.equals(c)) {
					tooltip.showToolTip(c, answers.get(2).AnswerText);
					v.setBackgroundResource(R.drawable.quizchoiceselectc);
					selected = 3;
					Answer.answerUser = "C";
					Answer.answerTextUser=answers.get(2).AnswerText;
				} else if (v.equals(d)) {
					tooltip.showToolTip(d, answers.get(3).AnswerText);
					v.setBackgroundResource(R.drawable.quizchoiceselectd);
					selected = 4;
					Answer.answerUser = "D";
					Answer.answerTextUser=answers.get(3).AnswerText;
				}
				if (selected != prevSelected) {
					switch (prevSelected) {
					case 1:
						a.setBackgroundResource(R.drawable.quizchoicea);
						break;
					case 2:
						b.setBackgroundResource(R.drawable.quizchoiceb);
						break;
					case 3:
						c.setBackgroundResource(R.drawable.quizchoicec);
						break;
					case 4:
						d.setBackgroundResource(R.drawable.quizchoiced);
						break;
					default:
						break;
					}
				}
				prevSelected = selected;
			}
		}
	};

	private void showAnswer(TextView tView, String value) {
		if (tView.getText().length() > Integer
				.parseInt(getString(R.string.maxcharactermultiple))) {
			tView.setText(value.substring(0,
					Integer.parseInt(getString(R.string.maxcharactermultiple)) - 4)
					+ "....");
		} else {
			tView.setText(value.substring(0, value.length()));
		}
	}

public void changeColorAnswer() {
		boolean wrong = false;
		if (!Answer.answerUser.equals(Answer.correctAnswer)) {
			if (Answer.answerUser.equals("A")) {
				a.setBackgroundResource(R.drawable.quizchoicewronga);
			} else if (Answer.answerUser.equals("B")) {
				b.setBackgroundResource(R.drawable.quizchoicewrongb);
			} else if (Answer.answerUser.equals("C")) {
				c.setBackgroundResource(R.drawable.quizchoicewrongc);
			} else if (Answer.answerUser.equals("D")) {
				d.setBackgroundResource(R.drawable.quizchoicewrongd);
			}
			wrong = true;
		}

		if (Answer.correctAnswer.equals("A")) {
			if (wrong) {
				a.setBackgroundResource(R.drawable.quiztypecorrecta);
			} else {
				a.setBackgroundResource(R.drawable.quizchoicecorrecta);
			}
		} else if (Answer.correctAnswer.equals("B")) {
			if (wrong) {
				b.setBackgroundResource(R.drawable.quiztypecorrectb);
			} else {
				b.setBackgroundResource(R.drawable.quizchoicecorrectb);
			}
		} else if (Answer.correctAnswer.equals("C")) {
			if (wrong) {
				c.setBackgroundResource(R.drawable.quiztypecorrectc);
			} else {
				c.setBackgroundResource(R.drawable.quizchoicecorrectc);
			}
		} else if (Answer.correctAnswer.equals("D")) {
			if (wrong) {
				d.setBackgroundResource(R.drawable.quiztypecorrectd);
			} else {
				d.setBackgroundResource(R.drawable.quizchoicecorrectd);
			}
		}

	}

	

	public String getCorrect(int i) {
		String correctAnswer="";
		if (i == 0) {
			correctAnswer = "A";
		} else if (i == 1) {
			correctAnswer = "B";
		} else if (i == 2) {
			correctAnswer = "C";
		} else if (i == 3) {
			correctAnswer = "D";
		}
		return correctAnswer;
	}
}

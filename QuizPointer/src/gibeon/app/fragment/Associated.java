package gibeon.app.fragment;

import java.util.ArrayList;
import java.util.LinkedList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import gibeon.app.adapter.TextViewCustom;
import gibeon.app.entities.Answer;
import gibeon.app.entities.Question;
import gibeon.app.entities.QuestionsBL;
import gibeon.app.entities.ToolTips;
import gibeon.app.quizpointer.QuizFragment;
import gibeon.app.quizpointer.R;

public class Associated extends Fragment {
	public View questionView;
	ArrayList<Question> question = QuizFragment.question;
	public static int counterAssociated;
	TextView a, b, c, d;

	int counter;
	String correctAnswer = "";
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

		questionView = inflater.inflate(R.layout.page_associated, container,
				false);

		a = (TextView) questionView.findViewById(R.id.associated_answerA);
		b = (TextView) questionView.findViewById(R.id.associated_answerB);
		c = (TextView) questionView.findViewById(R.id.associated_answerC);
		d = (TextView) questionView.findViewById(R.id.associated_answerD);

		QuizFragment.tvMessage.setText("Three answer are correct");
		QuizFragment.tvMessage.setBackgroundColor(Color.parseColor("#DFE2E5"));
		QuizFragment.tvMessage.setTextColor(Color.parseColor("#9EA7B2"));

		a.setBackgroundResource(R.drawable.quizchoicea);
		b.setBackgroundResource(R.drawable.quizchoiceb);
		c.setBackgroundResource(R.drawable.quizchoicec);
		d.setBackgroundResource(R.drawable.quizchoiced);

		answers = qbl.getAssociatedAnswers(question.get(QuizFragment.counter));

		TextView[] tvs = { a, b, c, d };

		String splitCorrect[] = question.get(QuizFragment.counter).getAnswer()
				.split(",");

		Answer.answerTextCorrect ="";
		for (int j = 0; j < tvs.length; j++) {
			tvs[j].setText(answers.get(j).AnswerText);
			showAnswer(tvs[j], answers.get(j).AnswerText);
			for (int k = 0; k < splitCorrect.length; k++) {
				if (answers.get(j).AnswerMark.equals(splitCorrect[k])) {
					Answer.correctAnswer = getCorrect(j);
					Answer.answerTextCorrect +=answers.get(j).AnswerText+"-";
				}
			}
		}

		a.setOnClickListener(choose3Listener);
		b.setOnClickListener(choose3Listener);
		c.setOnClickListener(choose3Listener);
		d.setOnClickListener(choose3Listener);

		TextView questionTV = (TextView) questionView
				.findViewById(R.id.tv_questiontext);

		TextViewCustom.customFonts(getActivity(), "font/ExoRegular.otf",
				questionTV, question.get(QuizFragment.counter)
						.getQuestionText());

		tooltip = new ToolTips(getActivity(), questionView);
		
//		QuizFragment.sr.add(new SummaryResult(question.get(QuizFragment.counter)
//				.getQuestionText()));
		questionView.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));

		return questionView;
	}

	private OnClickListener choose3Listener = new OnClickListener() {

		private LinkedList<Integer> selectedAsscociated = new LinkedList<Integer>();
		private LinkedList<String> selectedAsscociatedtText = new LinkedList<String>();

		@Override
		public void onClick(View v) {

			if (QuizFragment.isEnableChoice) {
				// if (selectedAsscociated.size() < 3) {
				if (v.equals(a)) {
					tooltip.showToolTip(a, answers.get(0).AnswerText);
					if (!selectedAsscociated.contains(1)
							&& selectedAsscociated.size() < 3) {
						v.setBackgroundResource(R.drawable.quizchoiceselecta);
						selectedAsscociated.add(1);
						counterAssociated++;
						selectedAsscociatedtText.add(a.getText().toString());
					} else if (selectedAsscociated.contains(1)) {
						v.setBackgroundResource(R.drawable.quizchoicea);
						selectedAsscociated.remove((Integer) 1);
						counterAssociated--;
						selectedAsscociatedtText.remove(a.getText().toString());
					}

				} else if (v.equals(b)) {
					tooltip.showToolTip(b, answers.get(1).AnswerText);
					if (!selectedAsscociated.contains(2)
							&& selectedAsscociated.size() < 3) {
						v.setBackgroundResource(R.drawable.quizchoiceselectb);
						selectedAsscociated.add(2);
						selectedAsscociatedtText.add(b.getText().toString());
						counterAssociated++;
					} else if (selectedAsscociated.contains(2)) {
						v.setBackgroundResource(R.drawable.quizchoiceb);
						selectedAsscociated.remove((Integer) 2);
						selectedAsscociatedtText.remove(b.getText().toString());
						counterAssociated--;
					}
				} else if (v.equals(c)) {
					tooltip.showToolTip(c, answers.get(2).AnswerText);
					if (!selectedAsscociated.contains(3)
							&& selectedAsscociated.size() < 3) {
						v.setBackgroundResource(R.drawable.quizchoiceselectc);
						selectedAsscociated.add(3);
						selectedAsscociatedtText.add(c.getText().toString());
						counterAssociated++;
					} else if (selectedAsscociated.contains(3)) {
						v.setBackgroundResource(R.drawable.quizchoicec);
						selectedAsscociated.remove((Integer) 3);
						selectedAsscociatedtText.remove(c.getText().toString());
						counterAssociated--;
					}
				} else if (v.equals(d)) {
					tooltip.showToolTip(d, answers.get(3).AnswerText);
					if (!selectedAsscociated.contains(4)
							&& selectedAsscociated.size() < 3) {
						v.setBackgroundResource(R.drawable.quizchoiceselectd);
						selectedAsscociated.add(4);
						selectedAsscociatedtText.add(d.getText().toString());
						counterAssociated++;
					} else if (selectedAsscociated.contains(4)) {
						v.setBackgroundResource(R.drawable.quizchoiced);
						selectedAsscociated.remove((Integer) 4);
						selectedAsscociatedtText.remove(d.getText().toString());
						counterAssociated--;
					}
				}
				// }
				// else if(selectedAsscociated.size()==3){
				//
				// }

				// else {
				// for (int selected : this.selectedAsscociated) {
				// switch (selected) {
				// case 1:
				// a.setBackgroundResource(R.drawable.quizchoicea);
				// selectedAsscociated.remove((Integer) 1);
				// counterAssociated--;
				// break;
				// case 2:
				// b.setBackgroundResource(R.drawable.quizchoiceb);
				// selectedAsscociated.remove((Integer) 2);
				// counterAssociated--;
				// break;
				// case 3:
				// c.setBackgroundResource(R.drawable.quizchoicec);
				// selectedAsscociated.remove((Integer) 3);
				// counterAssociated--;
				// break;
				// case 4:
				// d.setBackgroundResource(R.drawable.quizchoiced);
				// selectedAsscociated.remove((Integer) 4);
				// counterAssociated--;
				// break;
				// default:
				// break;
				// }
				// }
				// selectedAsscociated.clear();
				// counterAssociated = 0;
				// Answer.answerUser = "";
				// QuizFragment.continueButton
				// .setBackgroundResource(R.drawable.bnextoff);
				// QuizFragment.isActiveNext = false;
				// }

				if (counterAssociated == 3) {
					Answer.answerUser = "";
					Answer.answerTextUser ="";
					QuizFragment.continueButton
							.setBackgroundResource(R.drawable.bnexton);
					QuizFragment.isActiveNext = true;
					for (int i = 0; i < selectedAsscociated.size(); i++) {
						int answer = selectedAsscociated.get(i);
						if (answer == 1) {
							Answer.answerUser += "A";
						} else if (answer == 2) {
							Answer.answerUser += "B";
						} else if (answer == 3) {
							Answer.answerUser += "C";
						} else if (answer == 4) {
							Answer.answerUser += "D";
						}
//						if(i<selectedAsscociated.size()-1){
						Answer.answerTextUser +=selectedAsscociatedtText.get(i)+"-";
//						}else{
//						Answer.answerTextUser +="-";
//						}
					}
				} else {
					QuizFragment.continueButton
							.setBackgroundResource(R.drawable.bnextoff);
					QuizFragment.isActiveNext = false;
				}
			}
		}
	};

	private void showAnswer(TextView tView, String value) {
		if (tView.getText().length() > Integer
				.parseInt(getString(R.string.maxcharactermultiple)) - 6) {
			tView.setText(value.substring(
					0,
					Integer.parseInt(getString(R.string.maxcharactermultiple)) - 10)
					+ "....");
		} else {
			tView.setText(value.substring(0, value.length()));
		}
	}

	public void changeColorAnswer() {
//QuizFragment.sr.add(new SummaryResult(Answer.answerTextUser,Answer.answerTextCorrect,false));
		
		if (!Answer.answerUser.equals(Answer.correctAnswer)) {
			for (int i = 0; i < Answer.answerUser.length(); i++) {

				if (Answer.correctAnswer.charAt(i) == 'A') {
					a.setBackgroundResource(R.drawable.quiztypecorrecta);
				} else if (Answer.correctAnswer.charAt(i) == 'B') {
					b.setBackgroundResource(R.drawable.quiztypecorrectb);
				} else if (Answer.correctAnswer.charAt(i) == 'C') {
					c.setBackgroundResource(R.drawable.quiztypecorrectc);
				} else if (Answer.correctAnswer.charAt(i) == 'D') {
					d.setBackgroundResource(R.drawable.quiztypecorrectd);
				}

				if (Answer.answerUser.charAt(i) == 'A') {
					a.setBackgroundResource(R.drawable.quizchoicewronga);
				} else if (Answer.answerUser.charAt(i) == 'B') {
					b.setBackgroundResource(R.drawable.quizchoicewrongb);
				} else if (Answer.answerUser.charAt(i) == 'C') {
					c.setBackgroundResource(R.drawable.quizchoicewrongc);
				} else if (Answer.answerUser.charAt(i) == 'D') {
					d.setBackgroundResource(R.drawable.quizchoicewrongd);
				}

				for (int j = 0; j < Answer.correctAnswer.length(); j++) {
					if (Answer.answerUser.charAt(i) == Answer.correctAnswer
							.charAt(j)) {
						if (Answer.answerUser.charAt(i) == 'A') {
							a.setBackgroundResource(R.drawable.quizchoicecorrecta);
						} else if (Answer.answerUser.charAt(i) == 'B') {
							b.setBackgroundResource(R.drawable.quizchoicecorrectb);
						} else if (Answer.answerUser.charAt(i) == 'C') {
							c.setBackgroundResource(R.drawable.quizchoicecorrectc);
						} else if (Answer.answerUser.charAt(i) == 'D') {
							d.setBackgroundResource(R.drawable.quizchoicecorrectd);
						}
					}
				}

			}

		}

		if (Answer.answerUser.equals(Answer.correctAnswer)) {
			for (int i = 0; i < Answer.answerUser.length(); i++) {
				if (Answer.answerUser.charAt(i) == 'A') {
					a.setBackgroundResource(R.drawable.quizchoicecorrecta);
				} else if (Answer.answerUser.charAt(i) == 'B') {
					b.setBackgroundResource(R.drawable.quizchoicecorrectb);
				} else if (Answer.answerUser.charAt(i) == 'C') {
					c.setBackgroundResource(R.drawable.quizchoicecorrectc);
				} else if (Answer.answerUser.charAt(i) == 'D') {
					d.setBackgroundResource(R.drawable.quizchoicecorrectd);
				}
			}
		}
	}

	public String getCorrect(int i) {
		if (i == 0) {
			correctAnswer += "A";
			counter++;
		}
		if (i == 1) {
			correctAnswer += "B";
			counter++;
		}
		if (i == 2) {
			correctAnswer += "C";
			counter++;
		}
		if (i == 3) {
			correctAnswer += "D";
			counter++;
		}
		if (counter < 3) {
			correctAnswer += ",";
		}

		return correctAnswer;
	}
}

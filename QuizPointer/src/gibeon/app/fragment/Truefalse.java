package gibeon.app.fragment;

import gibeon.app.entities.Answer;
import gibeon.app.entities.Question;
import gibeon.app.entities.QuestionsBL;
import gibeon.app.entities.SummaryResult;
import gibeon.app.quizpointer.QuizFragment;
import gibeon.app.quizpointer.R;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Truefalse extends Fragment {
	public View questionView;
	ArrayList<Question> question = QuizFragment.question;
	TextView tA,tB;
	QuestionsBL qbl = new QuestionsBL(getActivity(), "1");
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (questionView != null) {
			ViewGroup parent = (ViewGroup) questionView.getParent();
			if (parent != null)
				parent.removeView(questionView);
		}

		questionView = inflater.inflate(R.layout.page_truefalse, container,
				false);

		tA = (TextView) questionView.findViewById(R.id.tv_true);
		tB = (TextView) questionView.findViewById(R.id.tv_false);

		QuizFragment.tvMessage.setText("It is true or false");
		QuizFragment.tvMessage.setBackgroundColor(Color.parseColor("#DFE2E5"));
		QuizFragment.tvMessage.setTextColor(Color.parseColor("#9EA7B2"));
		
		ArrayList<Answer> answers = qbl.getTrueFalseAnswer(question
				.get(QuizFragment.counter));
		
		TextView tvs[]={tA,tB};
		
		for (int j = 0; j < tvs.length; j++) {
			tvs[j].setText(answers.get(j).AnswerText);
			if (answers.get(j).AnswerMark.equals(question.get(
					QuizFragment.counter).getAnswer())) {
				Answer.correctAnswer = getCorrect(j);
				Answer.answerTextCorrect=answers.get(j).AnswerText;
			}
		}
		
		
		tA.setOnClickListener(choose1Listener);
		tB.setOnClickListener(choose1Listener);

		TextView questionTV = (TextView) questionView
				.findViewById(R.id.question_text);
		questionTV.setText(question.get(QuizFragment.counter).getQuestionText());
//		QuizFragment.sr.add(new SummaryResult(question.get(QuizFragment.counter)
//				.getQuestionText()));
		questionView.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
		
		return questionView;
	}
	
	public void changeColorAnswer(){
		boolean wrong=false;
		if (!Answer.answerUser.equals(Answer.correctAnswer)) {
			if(Answer.answerUser.equals("A")){
				tA.setBackgroundResource(R.drawable.truefalsewrong);
			}else if(Answer.answerUser.equals("B")){
				tB.setBackgroundResource(R.drawable.truefalsewrong);
			}
			wrong=true;
			}
		
		if(Answer.correctAnswer.equals("A")){
			if(wrong){
				tA.setBackgroundResource(R.drawable.truefalsetypecorrect);}
				else{
					tA.setBackgroundResource(R.drawable.truefalsecorrect);}
		}else if(Answer.correctAnswer.equals("B")){
			if(wrong){
				tB.setBackgroundResource(R.drawable.truefalsetypecorrect);}
				else{
					tB.setBackgroundResource(R.drawable.truefalsecorrect);}
		}
	}

	
	private OnClickListener choose1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(QuizFragment.isEnableChoice){
				QuizFragment.continueButton.setBackgroundResource(R.drawable.bnexton);
				QuizFragment.isActiveNext=true;
				Answer.answerTextUser="";
			if (v.equals(tA)) {
				Answer.answerUser = "A";
				Answer.answerTextUser=tA.getText().toString();
				v.setBackgroundResource(R.drawable.truefalseselect);
				tB.setBackgroundResource(R.drawable.truefalsedefault);
			} else if (v.equals(tB)) {
				Answer.answerUser = "B";
				Answer.answerTextUser=tB.getText().toString();
				v.setBackgroundResource(R.drawable.truefalseselect);
				tA.setBackgroundResource(R.drawable.truefalsedefault);
			}}
			
//			QuizFragment.sr.add(new SummaryResult(Answer.answerTextUser,Answer.answerTextCorrect,false));
			
		}
	};
	


	public String getCorrect(int i) {
		String correctAnswer="";
		if (i == 0) {
			correctAnswer = "A";
		} else if (i == 1) {
			correctAnswer = "B";
		}
		return correctAnswer;
	}
	}

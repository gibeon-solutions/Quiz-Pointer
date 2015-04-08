package gibeon.app.adapter;

import gibeon.app.fragment.Associated;
import gibeon.app.fragment.MultipleChoice;
import gibeon.app.fragment.PairMatching;
import gibeon.app.fragment.Sorted;
import gibeon.app.fragment.Truefalse;
import gibeon.app.quizpointer.QuizFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ReadAdapter extends FragmentStatePagerAdapter {
	public MultipleChoice typeQuestion1;
	public Associated typeQuestion3;
	public Sorted typeQuestion4;
	public PairMatching typeQuestion5;
	public Truefalse typeQuestion6; 

	private int typePosition;
	

	public int getTypePosition() {
		return typePosition;
	}

	public ReadAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
		typePosition = 0;
		
	}

	@Override
	public Fragment getItem(int position) {
		if(QuizFragment.question.get(QuizFragment.counter).getTypeQuestionId()==2||
				QuizFragment.question.get(QuizFragment.counter).getTypeQuestionId()==3){
			this.typeQuestion1 = new MultipleChoice();				
			return typeQuestion1;
		}
		else if(QuizFragment.question.get(QuizFragment.counter).getTypeQuestionId()==4){
			this.typeQuestion3 = new Associated();
			return typeQuestion3;
		}else if(QuizFragment.question.get(QuizFragment.counter).getTypeQuestionId()==5){
			this.typeQuestion4 = new Sorted();
			return typeQuestion4;
		}else if(QuizFragment.question.get(QuizFragment.counter).getTypeQuestionId()==6){
			this.typeQuestion5 = new PairMatching();
			return typeQuestion5;
		}else if(QuizFragment.question.get(QuizFragment.counter).getTypeQuestionId()==1){
			this.typeQuestion6 = new Truefalse();
			return typeQuestion6;
		}
		return typeQuestion1;
	}

	@Override
	public void destroyItem(View collection, int position, Object o) {
		 View view = (View) o;
		 ((ViewPager) collection).removeView(view);
		 view = null;
	}

	@Override
	public int getCount() {
		return 1;
	}
}

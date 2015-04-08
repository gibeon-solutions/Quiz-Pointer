package gibeon.app.entities;

import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;

import gibeon.app.adapter.TimerToolTip;
import gibeon.app.quizpointer.R;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;


public class ToolTips {
	Activity activity;
	ToolTipView mToolTipView;
	ToolTipRelativeLayout mToolTipFrameLayout;
	
	public ToolTips(Activity activity, View questionView){
		this.activity=activity;
//		this.mToolTipView=mToolTipView;
//		this.mToolTipFrameLayout=mToolTipFrameLayout;

		mToolTipFrameLayout = (ToolTipRelativeLayout) questionView
				.findViewById(R.id.activity_main_tooltipframelayout);

	}
	
	public void showToolTip(TextView name, String value) {
		TimerToolTip.initTime();
		if (mToolTipView != null) {
			mToolTipView.remove();
			mToolTipView = null;
		}
		if (name.getText().toString().contains("....")) {
			if (mToolTipView == null) {
				createTooltip(name, value);
			} else {
				mToolTipView.remove();
				mToolTipView = null;
				createTooltip(name, value);
			}
			TimerToolTip.showTime(mToolTipView, activity);
		}
	}

	private void createTooltip(TextView name, String value) {
		ToolTip toolTip = new ToolTip().withText(value)
				.withColor(Color.parseColor("#ffbb33"))
				.withAnimationType(ToolTip.AnimationType.FROM_TOP);
		mToolTipView = mToolTipFrameLayout.showToolTipForView(toolTip, name);
	}
	
	public void removeToolTip(){
		if (mToolTipView != null) {
			mToolTipView.remove();
			mToolTipView = null;
		}
	}
}

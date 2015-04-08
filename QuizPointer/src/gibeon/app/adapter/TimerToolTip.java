package gibeon.app.adapter;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.util.Log;

import com.nhaarman.supertooltips.ToolTipView;

public class TimerToolTip {
	
	static Timer time;
	static int countDownTime;

	
	
	public static void showTime(final ToolTipView mToolTipView,final Activity activity){
		time = new Timer();
		time.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				countDownTime--;
				if (mToolTipView != null && countDownTime == 0) {
					activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mToolTipView.remove();
						}
					});
					time.cancel();
				}
				 Log.d("timew", ""+countDownTime);
			}
		}, 1000, 1000);
	}
	
	
	public static void initTime(){
	countDownTime = 10;
	if (time != null) {
		time.cancel();
	}
	}
}

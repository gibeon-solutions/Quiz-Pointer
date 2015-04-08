package gibeon.app.quizpointer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);

		new Handler().postDelayed(new Thread() {
			@Override
			public void run() {
				Intent mainMenu = new Intent(SplashActivity.this,
						LoginActivity.class);
				startActivity(mainMenu);
				SplashActivity.this.finish();
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		}, 500);
	}
}

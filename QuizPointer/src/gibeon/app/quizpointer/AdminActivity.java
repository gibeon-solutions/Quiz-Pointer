package gibeon.app.quizpointer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AdminActivity extends Activity {
	TextView textWelcome;
	ImageButton btnPackages,btnResult;
	Button btnLogout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.page_admin);
		
		textWelcome=(TextView)findViewById(R.id.welcome_text);
		btnPackages=(ImageButton)findViewById(R.id.button_setpackages);
		btnResult=(ImageButton)findViewById(R.id.button_result);
		btnLogout=(Button)findViewById(R.id.buttonlogout);

		textWelcome.setText("Welcome, "+LoginActivity.user);
		
		
		btnPackages.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent listuser=new Intent(AdminActivity.this, ListUserActivity.class);
				AdminActivity.this.finish();
				startActivity(listuser);
			}
		});
		
		btnResult.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent listresult=new Intent(AdminActivity.this, UserResultActivity.class);
				AdminActivity.this.finish();
				startActivity(listresult);
			}
		});
		
		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent login=new Intent(AdminActivity.this, LoginActivity.class);
				AdminActivity.this.finish();
				startActivity(login);

//				AnswerBL abl=new AnswerBL(getApplicationContext());
//				ArrayList<Answer> ab=abl.getAllAnswered(3);
//				for(int a=0;a<abl.getAllAnswered(3).size();a++){
//					Toast.makeText(getApplicationContext(), 
//				"jumlah="+ab.size()+"\n"+
//				"id="+ab.get(a).get_id()+"\n"+
//				"userid="+ab.get(a).getUserId()+"\n"+
//				"packageid="+ab.get(a).getPackageId()+"\n"+
//				"questionid="+ab.get(a).getQuestionId()+"\n"+
//				"rightansweredid="+ab.get(a).getRightAnsweredId()+"\n"
//				, Toast.LENGTH_SHORT).show();
//				}
			}
		});
	}
	
	public void onBackPressed(){
		
	}
	
	
}

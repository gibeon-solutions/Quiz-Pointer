package gibeon.app.quizpointer;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gibeon.app.DataAccess.WebService;
import gibeon.app.adapter.ResultAdapter;
import gibeon.app.adapter.SummaryAdapter;
import gibeon.app.adapter.TextViewCustom;
import gibeon.app.entities.Item;
import gibeon.app.entities.SummaryResult;
import android.R.integer;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ResultActivity extends Activity {
	Button textRight, textWrong;
	TextView textPoint, textGrade, textResult, textLogin;
	Button btnSummary;

	final Context context = this;
	private ProgressDialog pDialog;
	ArrayList<SummaryResult> items;
	ListView listview;
	SummaryAdapter adapter;
	private ViewFlipper flipper_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.vlipperresult);

		pDialog = new ProgressDialog(context, R.style.CustomLoading);
		pDialog.setCancelable(false);
		pDialog.show();

		flipper_content = (ViewFlipper) findViewById(R.id.viewFlipper1);

		textResult = (TextView) findViewById(R.id.tv_result);
		TextViewCustom.customFontRegular(this, textResult);

		textRight = (Button) findViewById(R.id.button_right);
		textWrong = (Button) findViewById(R.id.button_wrong);

		TextViewCustom.customFontBold(this, textRight);
		TextViewCustom.customFontBold(this, textWrong);

		textPoint = (TextView) findViewById(R.id.tv_totalpoint);
		TextViewCustom.customFontBold(this, textPoint);

		textGrade = (TextView) findViewById(R.id.tv_grade);
		TextViewCustom.customFontRegular(this, textGrade);

		textLogin = (TextView) findViewById(R.id.tv_userlogin);
		TextViewCustom.customFontRegular(this, textLogin);
		textLogin.setText("Login as " + LoginActivity.user);

		btnSummary = (Button) findViewById(R.id.buttonsummary);

		// UserBL ubl=new UserBL(context);
		// User user=new User();
		// user.setId(1);
		// user.setUsername(ubl.getUsername());
		// user.setDateTest(day+"-"+month+"-"+year);
		// user.setPackageName("Action Movie");
		// user.setCorrectAnswer(""+QuizFragment.rightAnswer);
		// user.setTotalQuestion(""+(QuizFragment.rightAnswer+QuizFragment.wrongAnswer));
		// ubl.addResult(user);

		new ServerTask().execute();
		items = new ArrayList<SummaryResult>();
		items = QuizFragment.sr;
		for (int a = 0; a < items.size(); a++) {
			if (a % 2 == 0 && !items.get(a).isSection) {
				items.get(a + 1).iconBg = R.drawable.summarywronganswer;
			}
		}

		ArrayList<Integer> temp1 = new ArrayList<Integer>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		for (int a = 0; a < items.size(); a++) {
			if (a % 2 == 0) {
				temp1.add(a);
			} else {
				temp2.add(a);
			}
		}
		for (int a = 0; a < temp1.size(); a++) {
			Collections.swap(items, temp1.get(a), temp2.get(a));
		}

		btnSummary.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showSummary();
				showFlipperContentIndex(1);

				// final Dialog d = new Dialog(context);
				// d.requestWindowFeature(Window.FEATURE_NO_TITLE);
				// d.setContentView(R.layout.popup_summary);
				// WindowManager.LayoutParams wmlp =
				// d.getWindow().getAttributes();
				// wmlp.gravity = Gravity.CENTER;
				//
				// DisplayMetrics displayMetrics = context.getResources()
				// .getDisplayMetrics();
				// int width = displayMetrics.widthPixels;
				// int height = displayMetrics.heightPixels;
				//
				// wmlp.width = width;
				// wmlp.height = height;
				//
				// d.setCancelable(false);
				// ((Button) d.findViewById(R.id.btnclose))
				// .setOnClickListener(new OnClickListener() {
				// @Override
				// public void onClick(View arg0) {
				// d.cancel();
				// }
				// });
				//
				// listview = (ListView) d.findViewById(R.id.lv_listsummary);
				//
				// adapter = new SummaryAdapter(context, items);
				// listview.setAdapter(adapter);
				// // adapter.notifyDataSetChanged();
				//
				// ((Button) d.findViewById(R.id.buttonmainpage))
				// .setOnClickListener(new View.OnClickListener() {
				// @Override
				// public void onClick(View arg0) {
				// QuizFragment.isActiveNext = false;
				// QuizFragment.isEnableChoice = true;
				// QuizFragment.counter = 0;
				// QuizFragment.pointValue = 0;
				// QuizFragment.rightAnswer = 0;
				// QuizFragment.wrongAnswer = 0;
				// QuizFragment.question
				// .removeAll(QuizFragment.question);
				// QuizFragment.sr.removeAll(QuizFragment.sr);
				// ResultActivity.this.finish();
				// startActivity(new Intent(
				// getApplicationContext(),
				// PackageActivity.class));
				// }
				// });
				// d.show();

			}
		});
	}

	private class ServerTask extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... arg) {
			String response = "";
			try {
				JSONObject parameter = new JSONObject();
				parameter.put("userid", LoginActivity.idUser);
				parameter.put("packageid", QuizFragment.packageId);
				parameter.put("sessionid", QuizFragment.sId);
				response = WebService
						.post("http://quizpointer.azurewebsites.net/Quizapps/GetQuestionResult",
								parameter);
			} catch (Exception e) {
			}
			return response;
		}

		@Override
		protected void onProgressUpdate(Integer... item) {
		}

		@Override
		protected void onPostExecute(String result) {
			int rightAnswer = 0, wrongAnswer = 0;
			try {
				JSONObject jObject = new JSONObject(result);
				JSONArray jArray = jObject.getJSONArray("Resultlist");
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject menuObject = jArray.getJSONObject(i);
					if (menuObject.getString("RightAnswered").equals("1")) {
						rightAnswer++;
					} else {
						wrongAnswer++;
					}
				}

				int current = QuizFragment.question.size()
						- (rightAnswer + wrongAnswer);

				wrongAnswer += current;

				textRight.setText("Right\n"
						+ Html.fromHtml("<b>" + rightAnswer + "</b>"));
				textWrong.setText("Wrong\n"
						+ Html.fromHtml("<b>" + wrongAnswer + "</b>"));
				textPoint.setText("Total Point : " + QuizFragment.pointValue);
				textPoint.setBackgroundColor(Color.parseColor("#DFE2E5"));

				double value = (double) rightAnswer
						/ (rightAnswer + wrongAnswer) * 100;
				String grade = "";
				if (value == 100) {
					grade = "Perfect!";
				} else if (value < 100 && value >= 60) {
					grade = "Not bad!";
				} else {
					grade = "Next Time better!";
				}
				textGrade.setText(grade);

			} catch (JSONException e) {
			}
			pDialog.dismiss();
		}
	}

	public void onBackPressed() {
//		showFlipperContentIndex(0);
	}

	public void showFlipperContentIndex(int target) {
		int current = flipper_content.getDisplayedChild();
		int steps = Math.abs(target - current);
		for (int i = 0; i < steps; i++) {
			if (current < target) {
				flipper_content.showNext();
				flipper_content.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_up_in));
			} else if (current > target) {
				flipper_content.showPrevious();
				flipper_content.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_down_in));
			}
		}
	}

	private void showSummary() {
		((Button) findViewById(R.id.buttonbackpage))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						showFlipperContentIndex(0);
					}
				});

		listview = (ListView) findViewById(R.id.lv_listsummary);
		adapter = new SummaryAdapter(context, items);
		listview.setAdapter(adapter);

		((Button) findViewById(R.id.buttonmainpage))
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						QuizFragment.isActiveNext = false;
						QuizFragment.isEnableChoice = true;
						QuizFragment.counter = 0;
						QuizFragment.pointValue = 0;
						QuizFragment.rightAnswer = 0;
						QuizFragment.wrongAnswer = 0;
						QuizFragment.question.removeAll(QuizFragment.question);
						QuizFragment.sr.removeAll(QuizFragment.sr);
						ResultActivity.this.finish();
						startActivity(new Intent(getApplicationContext(),
								PackageActivity.class));
					}
				});
	}
}

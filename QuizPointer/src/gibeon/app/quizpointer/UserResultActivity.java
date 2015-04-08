package gibeon.app.quizpointer;

import gibeon.app.DataAccess.DataItem;
import gibeon.app.DataAccess.WebService;
import gibeon.app.adapter.GridViewAdapter;
import gibeon.app.adapter.ResultAdapter;
import gibeon.app.entities.Answer;
import gibeon.app.entities.AnswerBL;
import gibeon.app.entities.Item;
import gibeon.app.entities.SectionItem;
import gibeon.app.entities.UserBL;
import gibeon.app.entities.packageUser;

import java.util.ArrayList;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class UserResultActivity extends Activity {
	ArrayList<Item> items = new ArrayList<Item>();
	ListView listview = null;
	private LinkedList<Integer> selectedPackaged = new LinkedList<Integer>();
	private LinkedList<String> selectedPackageds = new LinkedList<String>();

	Button btnSave, btnBack;

	final Context context = this;
	private ProgressDialog pDialog;
	int rightAnswer, wrongAnswer;
	String packageName, username, dateTest;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_userresult);
		listview = (ListView) findViewById(R.id.lv_listuser);

		// UserBL ubl=new UserBL(context);
		// Toast.makeText(getApplicationContext(),
		// ubl.getResult().get(1).getCorrectAnswer(),Toast.LENGTH_SHORT).show();
		pDialog = new ProgressDialog(context, R.style.CustomLoading);
		pDialog.setCancelable(false);
		// pDialog.show();
		// new ServerTask().execute("UserResultList");

		// items.add(new SectionItem("Sharon"));
		// items.add(new DataItem(1,"Sharon","12 Februari 2015",
		// "package A","4/10"));
		// items.add(new DataItem(2,"Sharon","15 Februari 2015",
		// "package C","7/10"));
		//
		// items.add(new SectionItem("John"));
		// items.add(new DataItem(1,"John","12 Februari 2015",
		// "package A","6/10"));
		// items.add(new DataItem(2,"John","15 Februari 2015",
		// "package D","8/10"));

		try {
			AnswerBL abl = new AnswerBL(getApplicationContext());
			ArrayList<Answer> ab = abl.getAllAnswered(3);
			for (int a = 0; a < abl.getAllAnswered(3).size(); a++) {
				if (ab.get(a).getRightAnsweredId() == 1) {
					rightAnswer++;
				} else {
					wrongAnswer++;
				}
			}

			username = ab.get(0).getUsername();
			packageName = ab.get(0).getPackageName();
			dateTest = ab.get(0).getDateTest();

			items.add(new SectionItem(username));
			items.add(new DataItem(1, username, dateTest, packageName,
					rightAnswer + "/" + (rightAnswer + wrongAnswer)));
			ResultAdapter adapter = new ResultAdapter(context, items);
			listview.setAdapter(adapter);
		} catch (Exception e) {

		}

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				ImageView imgcek = (ImageView) arg1.findViewById(R.id.checkbox);

				if (!selectedPackaged.contains((arg2 + 1))) {
					imgcek.setBackgroundResource(R.drawable.checkboxselect);
					selectedPackaged.add((arg2 + 1));
					selectedPackageds.add(items.get(arg2).data().toString());
				} else if (selectedPackaged.contains((arg2 + 1))) {
					imgcek.setBackgroundResource(R.drawable.checkboxdefault);
					selectedPackaged.remove((Integer) (arg2 + 1));
					selectedPackageds.remove(items.get(arg2).data().toString());
				}
			}
		});

		btnSave = (Button) findViewById(R.id.buttonsave);
		btnSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String saveResult = "";
				for (int o = 0; o < selectedPackageds.size(); o++) {
					saveResult += selectedPackageds.get(o).toString() + "\n";
				}
				Toast.makeText(getApplicationContext(), saveResult,
						Toast.LENGTH_SHORT).show();
			}
		});

		btnBack = (Button) findViewById(R.id.buttonback);
		btnBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(UserResultActivity.this,
						AdminActivity.class));
			}
		});
	}

	private class ServerTask extends AsyncTask<String, Integer, String> {
		private String task;

		@Override
		protected String doInBackground(String... arg) {
			String response = "";
			task = arg[0];
			if (task.equals("UserResultList")) {
				try {
					JSONObject parameter = new JSONObject();
					parameter.put("userid", 3);
					response = WebService
							.post("http://quizpointer.azurewebsites.net/Quizapps/GetUserResult",
									parameter);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return response;
		}

		@Override
		protected void onProgressUpdate(Integer... item) {
		}

		@Override
		protected void onPostExecute(String result) {
			if (task.equals("UserResultList")) {
				try {
					JSONObject jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("UserResultList");
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject menuObject = jArray.getJSONObject(i);
						if (menuObject.getString("RightAnswered").equals("1")) {
							rightAnswer++;
						} else {
							wrongAnswer++;
						}
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			if (result.equals("")) {
				showChoices("Do you want to try again?");
			}
			pDialog.dismiss();
		}
	}

	private void showChoices(String msg) {
		AlertDialog.Builder ab = new AlertDialog.Builder(context);
		ab.setMessage(msg);
		ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				pDialog.show();
				new ServerTask().execute("UserResultList");
			}
		});
		ab.show();
	}

}

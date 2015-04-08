package gibeon.app.quizpointer;

import gibeon.app.DataAccess.WebService;
import gibeon.app.adapter.GridViewAdapter;
import gibeon.app.adapter.GridViewAdapterModified2;
import gibeon.app.adapter.TextViewCustom;
import gibeon.app.entities.PackageBL;
import gibeon.app.entities.Question;
import gibeon.app.entities.UserBL;
import gibeon.app.entities.packageUser;
import gibeon.app.entities.selectedPackage;

import java.util.ArrayList;
import java.util.Date;
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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PackageActivity extends Activity {

	Button btnLogout;
	private LinkedList<Integer> selectedPackaged = new LinkedList<Integer>();

	private boolean isStart;
	private GridView gridView;
	private GridViewAdapter customGridAdapter;
	ArrayList<packageUser> packageQuiz = new ArrayList<packageUser>();
	final Context context = this;
	private ProgressDialog pDialog;
	public static String packageId, packageName = "";
	public static int time;
	PackageBL pbl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.page_package);
		pDialog = new ProgressDialog(context, R.style.CustomLoading);
		pDialog.setCancelable(false);
		// pDialog.show();

		pbl = new PackageBL(context);
		gridView = (GridView) findViewById(R.id.gl_package);

		TextView textLogin = (TextView) findViewById(R.id.tv_userlogin);
		TextViewCustom.customFontRegular(this, textLogin);
		textLogin.setText("Login as " + LoginActivity.user);

		checkPackage();

		btnLogout = (Button) findViewById(R.id.buttonlogout);

		// btnStart.setOnClickListener(new View.OnClickListener() {
		// String choice="";
		// @Override
		// public void onClick(View arg0i) {
		// if(isStart){
		// choice="Your package choice is : ";
		// for(int a=0;a<selectedPackaged.size();a++){
		// choice += "\npackage "+selectedPackaged.get(a).toString()+"\n";
		// }
		//
		// Toast.makeText(getApplicationContext(), choice,
		// Toast.LENGTH_SHORT).show();
		// startActivity(new Intent(PackageActivity.this,QuizFragment.class));
		// }
		// }
		// });

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			String choice = "";

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				Button btnPackage = (Button) v.findViewById(R.id.btnpackage);

				for (int a = 0; a < arg0.getCount(); a++) {
					if (!btnPackage.getText().toString().equals("")) {
						if (arg2 == a) {
							if (!selectedPackaged.contains(Integer
									.parseInt(btnPackage.getTag().toString()))) {
								btnPackage
										.setBackgroundResource(R.drawable.custompackagechoiced);
								btnPackage.setTextColor(Color.WHITE);
								selectedPackaged.add(Integer
										.parseInt(btnPackage.getTag()
												.toString()));
							} else if (selectedPackaged.contains(Integer
									.parseInt(btnPackage.getTag().toString()))) {
								btnPackage
										.setBackgroundResource(R.drawable.custompackage);
								btnPackage.setTextColor(Color
										.parseColor("#9EA7B2"));
								selectedPackaged.remove((Object) Integer
										.parseInt(btnPackage.getTag()
												.toString()));
							}
							break;
						}
					}
				}

				int index = 5;
				if (packageQuiz.size() == 9) {
					index = 8;
				}
//				else if (packageQuiz.size() == 12) {
//					index = 11;
//				}
//				Toast.makeText(context, arg0.getChildCount()+"", Toast.LENGTH_SHORT).show();
				
				View btnStart = arg0.getChildAt(index);
				Button bStart = (Button) btnStart.findViewById(R.id.btnpackage);

				if (selectedPackaged.size() > 0) {
					isStart = true;
					bStart.setBackgroundResource(R.drawable.bstarton);
				} else {
					isStart = false;
					bStart.setBackgroundResource(R.drawable.bstartoff);
				}

				if (packageQuiz.size() == 6 && isStart) {
					if (arg2 == 5 && selectedPackaged.size() > 0) {
						for (int a = 0; a < selectedPackaged.size(); a++) {
							choice += selectedPackaged.get(a).toString();
							if (a < selectedPackaged.size() - 1) {
								choice += ",";
							}
						}

						Intent intent = new Intent(PackageActivity.this,
								QuizFragment.class);
						Bundle bundle = new Bundle();
						bundle.putString("packageid", choice);
						intent.putExtras(bundle);
						PackageActivity.this.finish();
						startActivity(intent);
					}
				}

				else if (packageQuiz.size() == 9 && isStart) {
					if (arg2 == 8 && selectedPackaged.size() > 0) {
						for (int a = 0; a < selectedPackaged.size(); a++) {
							choice += selectedPackaged.get(a).toString();
							if (a < selectedPackaged.size() - 1) {
								choice += ",";
							}

						}

						Intent intent = new Intent(PackageActivity.this,
								QuizFragment.class);
						Bundle bundle = new Bundle();
						bundle.putString("packageid", choice);
						intent.putExtras(bundle);
						PackageActivity.this.finish();
						startActivity(intent);
					}
				}

				else if (packageQuiz.size() == 16 && isStart) {
					if (arg2 == 15 && selectedPackaged.size() > 0) {
						for (int a = 0; a < selectedPackaged.size(); a++) {
							choice += selectedPackaged.get(a).toString();
							if (a < selectedPackaged.size() - 1) {
								choice += ",";
							}

						}

						Intent intent = new Intent(PackageActivity.this,
								QuizFragment.class);
						Bundle bundle = new Bundle();
						bundle.putString("packageid", choice);
						intent.putExtras(bundle);
						PackageActivity.this.finish();
						startActivity(intent);
					}
				}

			}

		});

		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PackageActivity.this.finish();
				startActivity(new Intent(PackageActivity.this,
						LoginActivity.class));
			}
		});
	}

	// private ArrayList<String> getData() {
	// // column 2
	// packageQuiz.add(getString(R.string.package1));
	// packageQuiz.add("");
	// packageQuiz.add("");
	// packageQuiz.add("");
	// packageQuiz.add("");
	// packageQuiz.add("start");
	// return packageQuiz;
	//
	// }

	private void setColumn() {
		if (packageQuiz.size() < 6) {
			gridView.setNumColumns(2);
		} else if (packageQuiz.size() > 6) {
			gridView.setNumColumns(3);
		} 
//		else if (packageQuiz.size() > 8) {
//			gridView.setNumColumns(4);
//		}
	}

	private class ServerTask extends AsyncTask<String, Integer, String> {
		private String task;

		@Override
		protected String doInBackground(String... arg) {
			String response = "";
			task = arg[0];
			if (task.equals("PackageList")) {
				try {
					JSONObject parameter = new JSONObject();
					// parameter.put("roleid", LoginActivity.data.getRoleId());
					parameter.put("roleid", LoginActivity.idRole);

					response = WebService
							.post("http://quizpointer.azurewebsites.net/Quizapps/GetPackage",
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
			if (task.equals("PackageList")) {
				try {
					JSONObject jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("PackageList");
					int lengthPackage=0;
					if (jArray.length() < 6) {
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						lengthPackage=5;
					} else if (jArray.length() > 6) {
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						packageQuiz.add(new packageUser(1, ""));
						lengthPackage = 8;
					}

//					else if (jArray.length() > 8) {
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//						packageQuiz.add(new packageUser(1, ""));
//					}

					for (int i = 0; i < lengthPackage; i++) {
						JSONObject menuObject = jArray.getJSONObject(i);

						String convertStartDate[] = menuObject.getString(
								"startdate").split("/Date\\(|\\)/");
						String convertEndDate[] = menuObject.getString(
								"enddate").split("/Date\\(|\\)/");

						Long lStartDate = Long.parseLong(convertStartDate[1]);
						Date startDate = new Date(lStartDate);

						Long lEndDate = Long.parseLong(convertEndDate[1]);
						Date endDate = new Date(lEndDate);

						Date nowDate = new Date();
						if (nowDate.getTime() >= startDate.getTime()
								&& nowDate.getTime() <= endDate.getTime()) {
							packageQuiz.set(
									i,
									new packageUser(menuObject
											.getInt("packageid"), menuObject
											.getString("name")));
						}
						time = Integer.parseInt(menuObject.getString("timer"));
					}

					packageQuiz.add(new packageUser(1, "start"));
					customGridAdapter = new GridViewAdapter(context,
							R.layout.package_cell, packageQuiz);
					setColumn();
					gridView.setAdapter(customGridAdapter);
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
				new ServerTask().execute("PackageList");
			}
		});
		ab.setNegativeButton("Maybe later",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

		ab.show();
	}

	private void checkPackage() {
		// pbl.getSavedPackage(7);
		// Toast.makeText(context, pbl.getSavedPackage(7).getIdPackage(),
		// Toast.LENGTH_SHORT).show();
		// if (LoginActivity.online) {
		pDialog.show();
		new ServerTask().execute("PackageList");
		// } else {
		// offlineMode();
		// }
	}

	// private void offlineMode() {
	// // pbl.getSavedPackage(1);
	// // UserBL ubl = new UserBL(context);
	//
	// // selectedPackage sp=new selectedPackage();
	//
	// try {
	// String data[] = pbl.getSavedPackage(LoginActivity.idRole)
	// .getIdPackage().split(",");
	// String data2[] = pbl.getSavedPackage(LoginActivity.idRole)
	// .getPackageName().split(",");
	// if (!data[0].equals("")) {
	// if (data.length < 6) {
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// } else if (data.length > 6) {
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// packageQuiz.add(new packageUser(1, ""));
	// }
	//
	// for (int i = 0; i < data.length; i++) {
	// packageQuiz
	// .set(i, new packageUser(Integer.parseInt(data[i]),
	// data2[i]));
	//
	// packageName += data2[i];
	// }
	// }
	//
	// packageQuiz.add(new packageUser(1, "start"));
	// customGridAdapter = new GridViewAdapter(context,
	// R.layout.package_cell, packageQuiz);
	// setColumn();
	// gridView.setAdapter(customGridAdapter);
	// } catch (Exception e) {
	// Toast.makeText(context,
	// "nothing package, please contact Admin" + e.getMessage(),
	// Toast.LENGTH_SHORT).show();
	//
	// // packageQuiz.add(new packageUser(1, "Action Movie"));
	// // packageQuiz.add(new packageUser(1, ""));
	// // packageQuiz.add(new packageUser(1, ""));
	// // packageQuiz.add(new packageUser(1, ""));
	// // packageQuiz.add(new packageUser(1, ""));
	// // packageQuiz.add(new packageUser(1, "start"));
	// //
	// // customGridAdapter = new GridViewAdapter(context,
	// // R.layout.package_cell, packageQuiz);
	// // setColumn();
	// // gridView.setAdapter(customGridAdapter);
	//
	// }
	// }
}

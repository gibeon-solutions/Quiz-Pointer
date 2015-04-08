package gibeon.app.quizpointer;

import java.util.ArrayList;

import gibeon.app.DataAccess.WebService;
import gibeon.app.entities.Answer;
import gibeon.app.entities.User;
import gibeon.app.entities.UserBL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	TextView textUsername, textPassword;
	ImageButton btnLogin;
	ImageView imgLogo;

	private boolean isActiveButton;
//	public static boolean online = false;
	public static String user, idRole;
	public static int idUser;
	String username, password;
	public static User data = new User();
	private ProgressDialog pDialog;
	final Context context = this;
	UserBL ubl;
	ArrayList<Answer> resultUser = new ArrayList<Answer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.page_login);

		ubl = new UserBL(context);

		pDialog = new ProgressDialog(context, R.style.CustomLoading);
		pDialog.setCancelable(false);

		imgLogo = (ImageView) findViewById(R.id.img_logo);
		textUsername = (EditText) findViewById(R.id.et_username);
		textPassword = (EditText) findViewById(R.id.et_password);
		btnLogin = (ImageButton) findViewById(R.id.buttonlogin);

		buttonLoginActivated(textUsername);
		buttonLoginActivated(textPassword);

		// QuestionsBL qbl=new QuestionsBL(context);
		// ArrayList<Question> question=new ArrayList<Question>();
		// question=qbl.getQuestion(3);
		//
		// for(int a=0;a<question.size();a++){
		// Toast.makeText(context, question.get(a).getQuestionText()+
		// "\n"+question.get(a).getAnswer(), Toast.LENGTH_LONG).show();
		// }

		// User user=new User();
		// user.setId(3);
		// user.setUsername("nsiem");
		// user.setPassword("nsiem");
		// ubl.addUser(user);

		login();
		// ubl.getUser();
	}

	/*********************************************************
	 ******************** LOGIN ********************
	 ********************************************************/
	private boolean checklogin() {
		username = textUsername.getText().toString();
		password = textPassword.getText().toString();
		if (username.equals(data.getUsername())
				&& password.equals(data.getPassword())) {
			user = data.getUsername();
			ubl.addUser(data.getId(), data.getUsername());
			if (data.getIsAdmin() == 1) {
				this.finish();
				startActivity(new Intent(LoginActivity.this,
						AdminActivity.class));
			} else {
				idUser = data.getId();
				idRole=data.getRoleId();
//				online = true;
				this.finish();
				startActivity(new Intent(LoginActivity.this,
						PackageActivity.class));
			}
		} else {
			showWarning("User account or password is incorrect");
			return false;
		}
		return true;
	}

	private void login() {
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (isActiveButton) {
//					if (isOnline()) {
						pDialog.show();
						new ServerTask().execute("UsersList");
//					}
//					else {
//						// check offline
//						User user = ubl.getUserLogin(textUsername.getText()
//								.toString(), textPassword.getText().toString());
//						if (user.getCountLogin() > 0) {
//							idUser = user.getId();
//							idRole = user.getRoleId();
//							startActivity(new Intent(LoginActivity.this,
//									PackageActivity.class));
//						} else {
//							showWarning("User account or password is incorrect");
//						}
//					}
				}
			}
		});
	}

	private void buttonLoginActivated(final TextView name) {
		name.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				if (!textUsername.getText().toString().equals("")
						&& !textPassword.getText().toString().equals("")) {
					imgLogo.setBackgroundResource(R.drawable.logologinon);
					btnLogin.setBackgroundResource(R.drawable.bloginon);
					isActiveButton = true;
				} else {
					imgLogo.setBackgroundResource(R.drawable.logologinoff);
					btnLogin.setBackgroundResource(R.drawable.bloginoff);
					isActiveButton = false;
				}

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});
	}

	/*********************************************************
	 ******************** END LOGIN ********************
	 ********************************************************/

	public void showWarning(String s) {
		Toast t = Toast
				.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
		t.setGravity(Gravity.CENTER, 0, 0);
		t.show();
	}

	private class ServerTask extends AsyncTask<String, Integer, String> {
		// private String task;

		@Override
		protected String doInBackground(String... arg) {
			String response = "";

			// Answer user = new Answer();
			// user.setUserId(4);
			// user.setPackageId(10);
			// user.setQuestionId(13);
			// user.setRightAnsweredId(1);
			//
			// // user.setUserId(4);
			// // user.setPackageId(10);
			// // user.setQuestionId(14);
			// // user.setRightAnsweredId(0);
			// resultUser.add(user);

			try {
				JSONObject parameter = new JSONObject();
				parameter.put("username", textUsername.getText().toString());
				response = WebService.post("http://quizpointer.azurewebsites.net/Quizapps/getUser",parameter);
//				
//				List<JSONObject> list = new ArrayList<JSONObject>();
//				parameter.put("UserId", 5);
//				parameter.put("PackageId",7);
//				parameter.put("QuestionId", 148);
//				parameter.put("RightAnswered", 0);
//				list.add(parameter);
//				JSONArray array = new JSONArray(list);

//				JSONObject parameter2 = new JSONObject();
//				parameter2.put("Resultlist", array);
				
//				parameter.put("userid", 2);
//				parameter.put("packageid",14);
//				parameter.put("sessionid",201503184);
//				response = WebService.post("http://quizpointer.azurewebsites.net/Quizapps/GetQuestionResult",parameter);
					

			} catch (Exception e) {
			}

			return response;
		}

		@Override
		protected void onProgressUpdate(Integer... item) {
		}

		@Override
		protected void onPostExecute(String result) {
			try {
				JSONObject jObject = new JSONObject(result);
				JSONArray jArray = jObject.getJSONArray("UsersList");
				if (jArray.length() > 0) {
					JSONObject menuObject = jArray.getJSONObject(0);
					data.setId(menuObject.getInt("Id"));
					data.setGroupId(menuObject.getInt("groupid"));
					data.setRoleId(menuObject.getString("roleid"));
					data.setIsAdmin(menuObject.getInt("isadmin"));
					data.setGroupName(menuObject.getString("groupname"));
					data.setRoleName(menuObject.getString("rolename"));
					data.setUsername(menuObject.getString("username"));
					data.setPassword(menuObject.getString("password"));
					data.setFirstname(menuObject.getString("firstname"));
					data.setLastname(menuObject.getString("lastname"));
					checklogin();
				} else {
					showWarning("User account or password is incorrect");
				}

			} catch (JSONException e) {
			}
			
			
			
//getResult
//			int rightAnswer=0,wrongAnswer=0;
//			try {
//			JSONObject jObject = new JSONObject(result);
//			JSONArray jArray = jObject.getJSONArray("Resultlist");
//			for (int i = 0; i < jArray.length(); i++) {
//				JSONObject menuObject = jArray.getJSONObject(i);
//				if(menuObject.getString("RightAnswered").equals("1")){
//					rightAnswer++;
//				}else{
//					wrongAnswer++;
//				}
//			}
//		} catch (JSONException e) {
//		}
			pDialog.dismiss();
//			Toast.makeText(context, "right="+rightAnswer+"\nwrong="+
//			wrongAnswer, Toast.LENGTH_SHORT).show();
		}
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

}

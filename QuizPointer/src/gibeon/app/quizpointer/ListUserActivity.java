package gibeon.app.quizpointer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gibeon.app.DataAccess.WebService;
import gibeon.app.adapter.AlternateArrayAdapter;
import gibeon.app.entities.User;
import gibeon.app.entities.UserBL;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListUserActivity extends Activity {
	TextView textWelcome;
	Button btnLogout;
	ListView listviewUser;
	private List<User> userList = new ArrayList<User>();
	public static String user;
	public static int id;

	private ProgressDialog pDialog;
	final Context context = this;
	
	UserBL ubl=new UserBL(context);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.page_listuser);

		textWelcome = (TextView) findViewById(R.id.welcome_text);
		btnLogout = (Button) findViewById(R.id.buttonlogout);
		textWelcome.setText("Welcome," + LoginActivity.user);
		listviewUser = (ListView) findViewById(R.id.lv_listuser);
		pDialog = new ProgressDialog(context, R.style.CustomLoading);
		pDialog.setCancelable(false);
		pDialog.show();

		new ServerTask().execute("UsersList");

		listviewUser.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextView tv = (TextView) arg1.findViewById(R.id.user_list);
				arg1.setBackgroundColor(Color.parseColor("#FBF0C1"));
				user = tv.getText().toString();
				id = Integer.parseInt(tv.getTag().toString());
				startActivity(new Intent(ListUserActivity.this,
						PackageUserActivity.class));
			}
		});

		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent back=new Intent(ListUserActivity.this, AdminActivity.class);
				ListUserActivity.this.finish();
				startActivity(back);
			}
		});

	}

	private class ServerTask extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... arg) {
			String response = "";
			try {
				JSONObject parameter = new JSONObject();
				parameter.put("", "");
				response = WebService
						.post("http://quizpointer.azurewebsites.net/Quizapps/GetUserList",
								parameter);
			} catch (Exception e) {
				e.printStackTrace();
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
				ubl.deleteAllUser();
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject menuObject = jArray.getJSONObject(i);
					if (!menuObject.getString("isadmin").equals("1")) {
						
						userList.add(new User(menuObject.getInt("roleid"),
								menuObject.getString("rolename")));
						
						User user=new User();
						user.setId(Integer.parseInt(menuObject.getString("Id")));
						user.setGroupId(Integer.parseInt(menuObject.getString("groupid")));
						user.setRoleId(menuObject.getString("roleid"));
						user.setIsAdmin(Integer.parseInt(menuObject.getString("isadmin")));
						user.setGroupName(menuObject.getString("groupname"));
						user.setRoleName(menuObject.getString("rolename"));
						user.setUsername(menuObject.getString("username"));
						user.setPassword(menuObject.getString("password"));
						user.setFirstname(menuObject.getString("firstname"));
						user.setLastname(menuObject.getString("lastname"));
						ubl.addUser(user);
					}
				}
				
				
				for(int j=0;j<userList.size();j++){
					for(int k=0;k<userList.size();k++){
						if(userList.get(j).equals(userList.get(k))){
							userList.remove(j);
						}
					}					
				}
				
				ArrayAdapter<User> aa = new AlternateArrayAdapter(context,
						R.layout.listuser_cell, userList);
				listviewUser.setAdapter(aa);

			} catch (JSONException e) {
				e.printStackTrace();

			}
			pDialog.dismiss();
		}
	}
	
	public void onBackPressed(){
		Intent back=new Intent(ListUserActivity.this, AdminActivity.class);
		ListUserActivity.this.finish();
		startActivity(back);
	}
}

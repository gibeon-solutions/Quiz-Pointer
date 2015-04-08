package gibeon.app.quizpointer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gibeon.app.DataAccess.WebService;
import gibeon.app.adapter.GridViewAdapter;
import gibeon.app.adapter.GridViewAdapterModified;
import gibeon.app.adapter.GridViewAdapterModified2;
import gibeon.app.entities.PackageBL;
import gibeon.app.entities.Packages;
import gibeon.app.entities.Question;
import gibeon.app.entities.QuestionsBL;
import gibeon.app.entities.User;
import gibeon.app.entities.UserBL;
import gibeon.app.entities.packageUser;
import gibeon.app.entities.selectedPackage;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class PackageUserActivity extends Activity {
	TextView textWelcome,textUserName;
	Button btnSave,btnRemove,btnBack;
//	private LinkedList<Integer> selectedPackaged = new LinkedList<Integer>();	
	private LinkedList<Integer> removedPackaged = new LinkedList<Integer>();	
	
	private LinkedList<selectedPackage> selectedPackaged = new LinkedList<selectedPackage>();	
	
	
	private GridView gridView;
	private GridViewAdapterModified2 customGridAdapter;
	PackageBL pbl;
	QuestionsBL qbl;
	UserBL ubl;
	ArrayList<packageUser> packageQuiz = new ArrayList<packageUser>();
	int drawable;
	boolean nothing=false;
	final Context context=this;
	String idPackage;
	private ProgressDialog pDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.page_packageuser);
		pDialog = new ProgressDialog(context,R.style.CustomLoading);
		pDialog.setCancelable(false);
		pDialog.show();
		
		new ServerTask().execute("getpackage");
		
		pbl=new PackageBL(getApplicationContext());
		qbl=new QuestionsBL(getApplicationContext());
		ubl=new UserBL(getApplicationContext());
		
		textWelcome=(TextView)findViewById(R.id.welcome_text);
		textUserName=(TextView)findViewById(R.id.user_text);
		btnBack=(Button)findViewById(R.id.buttonback);
		btnSave=(Button)findViewById(R.id.buttonsave);
		btnRemove=(Button)findViewById(R.id.buttonremove);
		
		textWelcome.setText("Welcome,"+LoginActivity.user);
		textUserName.setText(ListUserActivity.user);
		
		gridView = (GridView) findViewById(R.id.gl_packageuser);

		
		
//		for(int a=0;a<ubl.getUser().size();a++){
//			Toast.makeText(getApplicationContext(), "saved\n"+
//					pbl.getAllPackage().get(a).getStatus()+"\n" +
//					pbl.getAllPackage().get(a).getUser()+"\n" +
//					pbl.getAllPackage().get(a).getSavedPackage()+"\n", Toast.LENGTH_SHORT).show();
//		}

	
//		for(int a=0;a<pbl.getAllPackage().size();a++){
//			Toast.makeText(getApplicationContext(), "saved\n"+
//					pbl.getAllPackage().get(a).getStatus()+"\n" +
//					pbl.getAllPackage().get(a).getUser()+"\n" +
//					pbl.getAllPackage().get(a).getSavedPackage()+"\n", Toast.LENGTH_SHORT).show();
//		}
		
//		check();
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				Button btnPackage=(Button) v.findViewById(R.id.btnpackage);
				
				for(int a=0;a<arg0.getCount();a++){
					if (arg2==a) {
						if(!btnPackage.getText().toString().equals("")){
						if (!selectedPackaged.contains((a+1))) {
							btnPackage.setBackgroundResource(R.drawable.custompackagechoiced);
							btnPackage.setTextColor(Color.WHITE);
							selectedPackaged.add(new selectedPackage(btnPackage.getTag().toString(),btnPackage.getText().toString()));
						} else if (selectedPackaged.contains((a+1))) {
							btnPackage.setBackgroundResource(R.drawable.custompackage);
							btnPackage.setTextColor(Color.parseColor("#9EA7B2"));
							selectedPackaged.remove(new selectedPackage(btnPackage.getTag().toString(),btnPackage.getText().toString()));
						}}
						break;
					}
				}
			}
			
		});
		
		btnSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				idPackage = "";
				String namePackage="";
				
				for(int a=0;a<selectedPackaged.size();a++){
					idPackage +=selectedPackaged.get(a).getIdPackage();
					namePackage +=selectedPackaged.get(a).getPackageName();
					
					if (a < selectedPackaged.size() - 1) {
						idPackage +=",";
						namePackage +=",";
					}
					
					
				}
				
				if(!idPackage.equals("")){
					pDialog.show();
					pbl.addSavedPackage(ListUserActivity.id,ListUserActivity.id, ListUserActivity.user, idPackage,namePackage);
//					User user=new User();
//					user.setId(ListUserActivity.id);
//					user.setUsername(ListUserActivity.user);
//					ubl.addUser(user);
					new ServerTask().execute("getquestion");
//					Toast.makeText(getApplicationContext(), idPackage+"\nsaved success", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "please choice the package", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btnRemove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				for(int a=0;a<removedPackaged.size();a++){
//					selectedPackaged.remove((Integer) (a+1));
//				}
//				Toast.makeText(getApplicationContext(), "remove Success", Toast.LENGTH_SHORT).show();
//				
//				
				Toast.makeText(getApplicationContext(), "size="+qbl.getQuestion(String.valueOf(ListUserActivity.id)).size(), Toast.LENGTH_SHORT).show();
				
			}
		});
		
		btnBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				startActivity(new Intent(PackageUserActivity.this, ListUserActivity.class));
				PackageUserActivity.this.finish();
			}
		});
		
	}
	
//	private void check(){
//		String data[]=pbl.getSavedPackage(ListUserActivity.id).split(",");
//		if(!data[0].equals("")){
//		for(int i=0;i<data.length;i++){
//			packageQuiz.get(Integer.parseInt(data[i])-1).iconBg=R.drawable.custompackagechoiced;
//			packageQuiz.get(Integer.parseInt(data[i])-1).colorTc=Color.WHITE;
//			selectedPackaged.add(Integer.parseInt(data[i]));
//		}
//		customGridAdapter.notifyDataSetChanged();
//		nothing=false;
//		}
//		else{
//			nothing=true;
//		}
//	}
	
	
	
private class ServerTask extends AsyncTask<String, Integer, String> {
	private String task;
		@Override
		protected String doInBackground(String... arg) {
			String response = "";
			task = arg[0];
			if(task.equals("getpackage")){
			try {
				JSONObject parameter = new JSONObject();
				parameter.put("roleid", ListUserActivity.id);
					response = WebService.post("http://quizpointer.azurewebsites.net/Quizapps/GetPackage",parameter);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			else if(task.equals("getquestion")){
				try {
					JSONObject parameter = new JSONObject();
//					parameter.put("packageid",bundle.getString("packageid"));
					parameter.put("packageid",idPackage);
						response = WebService.post("http://quizpointer.azurewebsites.net/Quizapps/GetPackageQuestion",parameter);
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
			if(task.equals("getpackage")){
				try {
					JSONObject jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("PackageList");
						packageQuiz.add(new packageUser(1,""));
						packageQuiz.add(new packageUser(1,""));
						packageQuiz.add(new packageUser(1,""));
						packageQuiz.add(new packageUser(1,""));
						packageQuiz.add(new packageUser(1,""));
						packageQuiz.add(new packageUser(1,""));
						packageQuiz.add(new packageUser(1,""));
						packageQuiz.add(new packageUser(1,""));
						packageQuiz.add(new packageUser(1,""));

						for (int i = 0; i < jArray.length(); i++) {
						JSONObject menuObject = jArray.getJSONObject(i);
						packageQuiz.set(i,new packageUser(menuObject.getInt("packageid"),menuObject.getString("name")));
						}
						
						customGridAdapter = new GridViewAdapterModified2(context, R.layout.package_cell, packageQuiz);
						gridView.setAdapter(customGridAdapter);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				pDialog.dismiss();
			}
			else if(task.equals("getquestion")){
				try {
					JSONObject jObject = new JSONObject(result);
					JSONArray jArray = jObject.getJSONArray("PackageQuestionList");
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject menuObject = jArray.getJSONObject(i);
						
						qbl.addQuestion(new Question(menuObject.getInt("id"), 
								menuObject.getInt("typequestionid"), 
										menuObject.getInt("levelid"), 
												menuObject.getInt("value"), 
												menuObject.getInt("packageid"), 
												menuObject.getString("questiontext"), 
												menuObject.getString("answer"), 
												menuObject.getString("optionone"), 
												menuObject.getString("optiontwo"), 
												menuObject.getString("optionthree"), 
												menuObject.getString("optionfour"), 
												menuObject.getString("optionfive"), 
												menuObject.getString("optionsix"), 
												menuObject.getString("explanation"),
												menuObject.getString("reference")),ListUserActivity.id);
					}
					
					Toast.makeText(getApplicationContext(), "saved success", Toast.LENGTH_LONG).show();
				} catch (JSONException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				}
				pDialog.dismiss();
			}
				
			}
	}

public void onBackPressed(){
	PackageUserActivity.this.finish();
}
}

package gibeon.app.DataAccess;

import gibeon.app.entities.Answer;
import gibeon.app.entities.Packages;
import gibeon.app.entities.Question;
import gibeon.app.entities.User;
import gibeon.app.entities.selectedPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;

@SuppressLint("SimpleDateFormat") public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "QuizUsers";

//	private static final String TABLE_USER = "user";
//	private static final String COL_ID = "id";
//	private static final String COL_USERNAME = "username";
//	private static final String COL_PASSWORD = "password";
//	private static final String COL_LOGON = "logon";

	// private static final String TABLE_CORRECTANSWERED= "questions";
	// private static final String COL_QUESTIONID = "questionId";
	//
	// private static final String TABLE_DBUSED= "dbUsed";
	// private static final String COL_DBNAME = "dbName";
	// private static final String COL_DBVERSION = "dbVersion";

	// packagess
	private static final String TABLE_PACKAGE = "savedpackage";
	private static final String COL_IDPACKAGE = "id";
	private static final String COL_IDROLE = "idrole";
	private static final String COL_NAMEUSER = "nameuser";
	private static final String COL_PACKAGE = "package";
	private static final String COL_PACKAGENAME = "packagename";
	
	// Question
		private static final String TABLE_QUESTION = "question";
		private static final String COL_IDQUESTION = "id";
		private static final String COL_TYPEQUESTION = "typequestionid";
		private static final String COL_LEVELID = "levelid";
		private static final String COL_VALUE = "value";
		private static final String COL_PACKAGEID = "packageid";
		private static final String COL_QUESTION = "questiontext";
		private static final String COL_ANSWER = "answer";
		private static final String COL_OPTIONONE = "optionone";
		private static final String COL_OPTIONTWO = "optiontwo";
		private static final String COL_OPTIONTHREE = "optionthree";
		private static final String COL_OPTIONFOUR = "optionfour";
		private static final String COL_OPTIONFIVE = "optionfive";
		private static final String COL_OPTIONSIX = "optionsix";
		private static final String COL_EXPLANATION = "explanation";
		private static final String COL_REFERENCE = "reference";
		private static final String COL_IDROLEQ = "idrole";
		
		// login
		private static final String TABLE_LOGIN = "userlogin";
		private static final String COL_IDUSERL = "iduser";
		private static final String COL_USERNAMEL = "username";
		
		
		// Question
		private static final String TABLE_RESULT = "userresult";
		private static final String COL_IDR = "idresult";
		private static final String COL_USERNAMER = "username";
		private static final String COL_DATETEST = "datetest";
		private static final String COL_PACKAGENAMER = "packagename";
		private static final String COL_CORRECTANSWER = "correctanswer";
		private static final String COL_TOTALQUESTION = "totalquestion";
		
		
		//User
		private static final String TABLE_USER = "user";
		private static final String COL_IDU = "id";
		private static final String COL_GROUPID = "groupid";
		private static final String COL_ROLEID = "roleid";
		private static final String COL_ISADMIN = "isadmin";
		private static final String COL_GROUPNAME = "groupname";
		private static final String COL_ROLENAME = "rolename";
		private static final String COL_USERNAME = "username";
		private static final String COL_PASSWORD = "password";
		private static final String COL_FIRSTNAME = "firstname";
		private static final String COL_LASTNAME = "lastname";
		
		//answer User
		private static final String TABLE_ANSWER = "answer";
		private static final String COL_IDA = "id";
		private static final String COL_USERIDA = "userid";
		private static final String COL_PACKAGEIDA = "packageid";
		private static final String COL_QUESTIONIDA = "questionid";
		private static final String COL_RIGHTANSWEREDA = "rightanswered";
		private static final String COL_USERNAMEA = "username";
		private static final String COL_PACKAGENAMEA = "packagename";
		private static final String COL_TESTDATE = "testdate";
		private static final String COL_SESSIONID = "sessionid";
	
//		
//		
//		this.id = id;
//		this.groupId = groupId;
//		this.roleId = roleId;
//		this.isAdmin = isAdmin;
//		this.groupName = groupName;
//		this.roleName = roleName;
//		this.username = username;
//		this.password = password;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		
		
		
	Context context;

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context=context;
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		// users
//		String CREATE_TABLE_USERPROFILES = "CREATE TABLE " + TABLE_USER + "("
//				+ COL_ID + " INTEGER," + COL_USERNAME + " VARCHAR,"
//				+ COL_PASSWORD + " VARCHAR," + COL_LOGON + " INTEGER" + ")";
//		db.execSQL(CREATE_TABLE_USERPROFILES);
		String CREATE_TABLE_USER="CREATE TABLE " + TABLE_USER
				+ "(" + 
				COL_IDU + " INTEGER," + 
				COL_GROUPID+ " INTEGER," + 
				COL_ROLEID+ " VARCHAR," + 
				COL_ISADMIN+ " INTEGER," + 
				COL_GROUPNAME+ " VARCHAR," + 
				COL_ROLENAME+ " VARCHAR," + 
				COL_USERNAME+ " VARCHAR," + 
				COL_PASSWORD+ " VARCHAR," + 
				COL_FIRSTNAME+ " VARCHAR," + 
				COL_LASTNAME + " VARCHAR" + ")";
		db.execSQL(CREATE_TABLE_USER);

		String CREATE_TABLE_SAVEDPACKAGE = "CREATE TABLE " + TABLE_PACKAGE
				+ "(" + COL_IDPACKAGE + " INTEGER," + 
				COL_IDROLE+ " INTEGER," + 
				COL_NAMEUSER+ " VARCHAR," + 
				COL_PACKAGE+ " VARCHAR," + 
				COL_PACKAGENAME + " VARCHAR" + ")";
		db.execSQL(CREATE_TABLE_SAVEDPACKAGE);
		
		String CREATE_TABLE_QUESTION = "CREATE TABLE " + TABLE_QUESTION
				+ "(" + 
				COL_IDQUESTION + " INTEGER," + 
				COL_TYPEQUESTION+ " INTEGER," + 
				COL_LEVELID+ " INTEGER," + 
				COL_VALUE+ " INTEGER," + 
				COL_PACKAGEID+ " INTEGER," + 
				COL_QUESTION+ " VARCHAR," + 
				COL_ANSWER+ " VARCHAR," + 
				COL_OPTIONONE+ " VARCHAR," + 
				COL_OPTIONTWO+ " VARCHAR," + 
				COL_OPTIONTHREE+ " VARCHAR," + 
				COL_OPTIONFOUR+ " VARCHAR," + 
				COL_OPTIONFIVE+ " VARCHAR," + 
				COL_OPTIONSIX+ " VARCHAR," + 
				COL_EXPLANATION+ " TEXT," + 
				COL_REFERENCE+ " TEXT," + 
				COL_IDROLEQ + " INTEGER" + ")";
		db.execSQL(CREATE_TABLE_QUESTION);
		
		
		

		
		String CREATE_TABLE_LOGIN = "CREATE TABLE " + TABLE_LOGIN + "("
				+ COL_IDUSERL + " INTEGER," + COL_USERNAMEL + " VARCHAR" + ")";
		db.execSQL(CREATE_TABLE_LOGIN);
		
		
		String CREATE_TABLE_RESULT = "CREATE TABLE " + TABLE_RESULT
				+ "(" + 
				COL_IDR + " INTEGER," + 
				COL_USERNAME+ " VARCHAR," + 
				COL_DATETEST+ " VARCHAR," + 
				COL_PACKAGENAMER+ " VARCHAR," + 
				COL_CORRECTANSWER+ " INTEGER," + 
				COL_TOTALQUESTION + " INTEGER" + ")";
		db.execSQL(CREATE_TABLE_RESULT);


		
		String CREATE_TABLE_ANSWER = "CREATE TABLE " + TABLE_ANSWER
				+ "(" + COL_IDA + " INTEGER," + 
				COL_USERIDA+ " INTEGER," + 
				COL_PACKAGEIDA+ " VARCHAR," + 
				COL_QUESTIONIDA+ " INTEGER," + 
				COL_RIGHTANSWEREDA+ " INTEGER," + 
				COL_USERNAMEA+ " VARCHAR," + 
				COL_PACKAGENAMEA+ " VARCHAR," + 
				COL_TESTDATE + " VARCHAR," +
				COL_SESSIONID+" INTEGER)";
		db.execSQL(CREATE_TABLE_ANSWER);
		
		
		
		// //correct answered
		// String CREATE_TABLE_USERCORRECTANSWERED = "CREATE TABLE " +
		// TABLE_CORRECTANSWERED + "("
		// + COL_USERNAMEID + " TEXT,"
		// + COL_QUESTIONID + " INTEGER" + ")";
		// db.execSQL(CREATE_TABLE_USERCORRECTANSWERED);
		//
		// //db version used
		// String CREATE_TABLE_DBUSED = "CREATE TABLE " + TABLE_DBUSED + "("
		// + COL_DBNAME + " TEXT,"
		// + COL_DBVERSION + " INTEGER" + ")";
		// db.execSQL(CREATE_TABLE_DBUSED);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PACKAGE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWER);

		// db.execSQL("DROP TABLE IF EXISTS " + TABLE_CORRECTANSWERED);
		// db.execSQL("DROP TABLE IF EXISTS " + TABLE_DBUSED);

		// Create tables again
		onCreate(db);
	}

	// Getting package saved
	public selectedPackage getSavedPackage(String id) {
		// Select All Query
//		String result="";
		selectedPackage result=new selectedPackage();
		
//		Cursor cursor = db.query(TABLE_USER, new String[] { COL_USERNAME },
//				COL_ID + "=?", new String[] { String.valueOf(id) }, null, null,
//				null, null);
//
//		if (cursor != null)
//			cursor.moveToFirst();

		try{
		String selectQuery = "SELECT package,packagename FROM " + TABLE_PACKAGE +" WHERE idrole="+id;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		
		if (cursor != null) 
			cursor.moveToFirst();
			result.setIdPackage(cursor.getString(0));
			result.setPackageName(cursor.getString(1));

		cursor.close();
		}catch(Exception e){
		}
		
		
		return result;
	}
	
	// Getting Data
	public List<Packages> getAllPackage() {
		List<Packages> packageList = new ArrayList<Packages>();
		// Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_PACKAGE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Packages packages=new Packages();
				packages.setStatus(cursor.getString(0));
				packages.setUser(cursor.getString(1));
				packages.setSavedPackage(cursor.getString(2));
				packageList.add(packages);
			} while (cursor.moveToNext());
		}

		cursor.close();
		// return contact list
		return packageList;
	}
	


	// Add package saved
	public void addPackageSaved(int id, int iduser, String name, String packages,String packagename) {
		String selectQuery = "SELECT count(*) as ids FROM " + TABLE_PACKAGE + " WHERE "
				+ COL_IDPACKAGE + "=" + id;


		SQLiteDatabase db2 = this.getWritableDatabase();
		Cursor cursor = db2.rawQuery(selectQuery, null);

		int result = 0;
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getInt(0);
		}
		
		if(result>0){
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(COL_IDROLE, iduser);
			values.put(COL_NAMEUSER, name);
			values.put(COL_PACKAGE, packages);
			values.put(COL_PACKAGENAME, packagename);
			db.update(TABLE_PACKAGE, values, COL_IDPACKAGE + " = ?",
					 new String[] { String.valueOf(id)});
		}else{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_IDPACKAGE, id);
		values.put(COL_IDROLE, iduser);
		values.put(COL_NAMEUSER, name);
		values.put(COL_PACKAGE, packages);
		values.put(COL_PACKAGENAME, packagename);
		db.insert(TABLE_PACKAGE, null, values);
		db.close();
		}
		cursor.close();
	}
	
	//user
	public void addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_IDU, user.getId());
		values.put(COL_GROUPID, user.getGroupId());
		values.put(COL_ROLEID, user.getRoleId());
		values.put(COL_ISADMIN, user.getIsAdmin());
		values.put(COL_GROUPNAME, user.getGroupName());
		values.put(COL_ROLENAME, user.getRoleName());
		values.put(COL_USERNAME, user.getUsername());
		values.put(COL_PASSWORD, user.getPassword());
		values.put(COL_FIRSTNAME, user.getFirstname());
		values.put(COL_LASTNAME, user.getLastname());
		db.insert(TABLE_USER, null, values);
		db.close();
	}
	
	//user
		public void deleteAllUser() {
			SQLiteDatabase db2 = this.getWritableDatabase();
			db2.delete(TABLE_LOGIN, null, null);
			db2.close();
			}
		
	
	//user
		public User getUserLogin(String username,String password) {
			User user=null;
//			int result=0;
			try{
			String selectQuery = "SELECT count(*),roleid,id FROM " + TABLE_USER + " WHERE "
					+ COL_USERNAME + " ='" + username+"' and password='"+password+"'";
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor != null) 
				cursor.moveToFirst();
//			result=cursor.getInt(0);
			user=new User(cursor.getInt(0), cursor.getString(1),cursor.getInt(2));
			cursor.close();
			}catch(Exception e){
			}
			return user;
		}

	// get count package saved
	public int getCountSaved() {
		int countSaved;
		String selectQuery = "SELECT count(*) FROM " + TABLE_PACKAGE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();

		countSaved = Integer.parseInt(cursor.getString(0));
		cursor.close();
		return countSaved;
	}

	// Get user
//	public User getUser(String id) {
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.query(TABLE_USER, new String[] { COL_USERNAME },
//				COL_ID + "=?", new String[] { String.valueOf(id) }, null, null,
//				null, null);
//		if (cursor != null)
//			cursor.moveToFirst();
//		User user = new User(cursor.getString(0));
//		cursor.close();
//		return user;
//	}

	// //Get DB USED
	// public Database getDBUsed(){
	//
	// SQLiteDatabase db = this.getReadableDatabase();
	// String query = "SELECT * FROM "+ TABLE_DBUSED;
	// Cursor cursor = db.rawQuery(query, null);
	//
	// Database database = null;
	// if(cursor.moveToFirst()){
	//
	// String s = cursor.getString(0);
	// int i = cursor.getInt(1);
	// database = new Database(s,i);
	// }
	//
	// cursor.close();
	// return database;
	// }

	// Getting All Users
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		// Select All Query
		String selectQuery = "SELECT username FROM " + TABLE_USER;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				User user = new User();
				user.setUsername(cursor.getString(0));
				userList.add(user);
			} while (cursor.moveToNext());
		}

		cursor.close();
		// return contact list
		return userList;
	}

//	// Getting LogOn Users
//	public List<User> getUsersLogOn() {
//		List<User> userList = new ArrayList<User>();
//		// Select All Query
//		String selectQuery = "SELECT  username FROM " + TABLE_USER + " WHERE "
//				+ COL_LOGON + " =1";
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//
//		// looping through all rows and adding to list
//		if (cursor.moveToFirst()) {
//			do {
//				User user = new User();
//				user.setUsername(cursor.getString(0));
//				userList.add(user);
//			} while (cursor.moveToNext());
//		}
//
//		cursor.close();
//		return userList;
//	}

	// // Getting All User Correct Answered
	// public List<Integer> getAllQuestionsID(User user) {
	// List<Integer> questionsIdList = new ArrayList<Integer>();
	//
	// // Select All Query
	// String selectQuery = "SELECT  * FROM " + TABLE_CORRECTANSWERED +
	// " WHERE " + COL_USERNAMEID + "= '" + user.getUserNameId() +"'";
	//
	// SQLiteDatabase db = this.getReadableDatabase();
	// Cursor cursor = db.rawQuery(selectQuery, null);
	//
	// // looping through all rows and adding to list
	// if (cursor.moveToFirst()) {
	// do {
	// questionsIdList.add(cursor.getInt(1));
	// } while (cursor.moveToNext());
	// }
	//
	// cursor.close();
	// // return contact list
	// return questionsIdList;
	// }
	//

	// Getting user Count
	public int getAllUsersCount() {
		String countQuery = "SELECT  count(*) FROM " + TABLE_USER;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int result = 0;

		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getInt(0);
		}

		cursor.close();

		// return count
		return result;
	}

	// Get dbUsed count
	// public int getDBUsedCount(){
	// String countQuery = "SELECT COUNT(*) FROM " + TABLE_DBUSED;
	// SQLiteDatabase db = this.getReadableDatabase();
	// Cursor cursor = db.rawQuery(countQuery, null);
	// int result = 0;
	//
	// if (cursor != null)
	// {
	// cursor.moveToFirst();
	// result=cursor.getInt(0);
	// }
	//
	// cursor.close();
	//
	// return result;
	// }

//	// Getting user Count
//	public int getCount(String usernameid) {
//		String countQuery = "SELECT  count(usernameId) FROM " + TABLE_USER
//				+ " WHERE " + COL_ID + " ='" + usernameid + "'";
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(countQuery, null);
//		int result = 0;
//
//		if (cursor != null) {
//			cursor.moveToFirst();
//			result = cursor.getInt(0);
//		}
//
//		cursor.close();
//
//		// return count
//		return result;
//	}

	// // Getting correct answered Count
	// public int getCorrectAnsweredCount(User user) {
	// String countQuery = "SELECT  count(usernameId) FROM " +
	// TABLE_CORRECTANSWERED + " WHERE "+ COL_USERNAMEID +
	// " = '"+user.getUserNameId()+ "'";
	// SQLiteDatabase db = this.getReadableDatabase();
	// Cursor cursor = db.rawQuery(countQuery, null);
	// int result=0;
	//
	// if (cursor != null)
	// {
	// cursor.moveToFirst();
	// result=cursor.getInt(0);
	// }
	//
	// cursor.close();
	//
	// // return count
	// return result;
	// }

	// Getting number of users LogOn
//	public int getNumberOfUsersLogOn() {
//		String countQuery = "SELECT  count(logon) FROM " + TABLE_USER
//				+ " WHERE " + COL_LOGON + " = 1";
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(countQuery, null);
//		int result = 0;
//
//		if (cursor != null) {
//			cursor.moveToFirst();
//			result = cursor.getInt(0);
//		}
//
//		cursor.close();
//
//		// return count
//		return result;
//	}

	// // Set DB USED
	// public void addDBUsed(Database database) {
	// SQLiteDatabase db = this.getWritableDatabase();
	//
	// ContentValues values = new ContentValues();
	// values.put(COL_DBNAME, database.getName());
	// values.put(COL_DBVERSION, database.getVersion());
	//
	// // Inserting Row
	// db.insert(TABLE_DBUSED, null, values);
	// db.close(); // Closing database connection
	// }

	// // Update DB USED
	// public int updateDBUsed(Database database) {
	// SQLiteDatabase db = this.getWritableDatabase();
	//
	// ContentValues values = new ContentValues();
	// values.put(COL_DBVERSION, database.getVersion());
	//
	//
	// // updating row
	// return db.update(TABLE_DBUSED, values, COL_DBNAME + " = ?",
	// new String[] { String.valueOf(database.getName()) });
	// }
	//

	// Insert new user
//	public void addUser(User user) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(COL_ID, user.getId());
//		values.put(COL_USERNAME, user.getUsername());
//		values.put(COL_PASSWORD, user.getPassword());
//
//		// Inserting Row
//		db.insert(TABLE_USER, null, values);
//		db.close(); // Closing database connection
//	}
	
	
//	 Insert new user
		public void addUsers(int idUser,String username) {
			SQLiteDatabase db2 = this.getWritableDatabase();
			db2.delete(TABLE_LOGIN, null, null);
			db2.close();
			
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(COL_IDUSERL, idUser);
			values.put(COL_USERNAMEL, username);
			db.insert(TABLE_LOGIN, null, values);
			db.close();
		}
		
//		 Insert new user
			public int getIdUser() {
				String countQuery = "SELECT iduser,username FROM " + TABLE_LOGIN;
				SQLiteDatabase db = this.getReadableDatabase();
				Cursor cursor = db.rawQuery(countQuery, null);
				int result = 0;
				if (cursor != null) 
					cursor.moveToFirst();
					result = cursor.getInt(0);
//					Toast.makeText(context, ""+cursor.getString(1),Toast.LENGTH_SHORT).show();
				cursor.close();
				return result;
			}

			public String getNameUser() {
				String countQuery = "SELECT username FROM " + TABLE_LOGIN;
				SQLiteDatabase db = this.getReadableDatabase();
				Cursor cursor = db.rawQuery(countQuery, null);
				String result = "";
				if (cursor != null) {
					cursor.moveToFirst();
					result = cursor.getString(0);
				}
				cursor.close();
				return result;
			}

			
	//insert question
	public void addQuestion(Question question, int idUser) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COL_IDU, question.getId());
		values.put(COL_TYPEQUESTION, question.getTypeQuestionId());
		values.put(COL_LEVELID, question.getLevelId());
		values.put(COL_VALUE, question.getValue());
		values.put(COL_PACKAGEID, question.getPackageId());
		values.put(COL_QUESTION, question.getQuestionText());
		values.put(COL_ANSWER, question.getAnswer());
		values.put(COL_OPTIONONE, question.getOptionOne());
		values.put(COL_OPTIONTWO, question.getOptionTwo());
		values.put(COL_OPTIONTHREE, question.getOptionThree());
		values.put(COL_OPTIONFOUR, question.getOptionFour());
		values.put(COL_OPTIONFIVE, question.getOptionFive());
		values.put(COL_OPTIONSIX, question.getOptionSix());
		values.put(COL_EXPLANATION, question.getExplanation());
		values.put(COL_REFERENCE, question.getReference());
		values.put(COL_IDROLEQ, idUser);

		// Inserting Row
		db.insert(TABLE_QUESTION, null, values);
		db.close(); // Closing database connection
	}
	
	// Getting All Question
	public ArrayList<Question> getAllQuestion(String idRole) {
		ArrayList<Question> questionList = new ArrayList<Question>();
		String selectQuery = "SELECT * FROM " + TABLE_QUESTION + " WHERE "+COL_IDROLEQ+"="+idRole;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				Question question = new Question(cursor.getInt(0));
				question.setTypeQuestionId(cursor.getInt(1));
				question.setLevelId(cursor.getInt(2));
				question.setValue(cursor.getInt(3));
				question.setPackageId(cursor.getInt(4));
				question.setQuestionText(cursor.getString(5));
				question.setAnswer(cursor.getString(6));
				question.setOptionOne(cursor.getString(7));
				question.setOptionTwo(cursor.getString(8));
				question.setOptionThree(cursor.getString(9));
				question.setOptionFour(cursor.getString(10));
				question.setOptionFive(cursor.getString(11));
				question.setOptionSix(cursor.getString(12));
				question.setExplanation(cursor.getString(13));
				question.setReference(cursor.getString(14));
				questionList.add(question);
			} while (cursor.moveToNext());
		}

		cursor.close();
		// return contact list
		return questionList;
	}

	// Insert new correct answered question
	// public void addQuestion(User user, Question question) {
	// SQLiteDatabase db = this.getWritableDatabase();
	//
	// ContentValues values = new ContentValues();
	// values.put(COL_USERNAMEID, user.getUserNameId());
	// values.put(COL_QUESTIONID, question.getQuestionId());
	//
	// // Inserting Row
	// db.insert(TABLE_CORRECTANSWERED, null, values);
	// db.close(); // Closing database connection
	// }
	//
	// // Update user
	// public int updateUser(User user) {
	// SQLiteDatabase db = this.getWritableDatabase();
	//
	// ContentValues values = new ContentValues();
	//
	// // updating row
	// return db.update(TABLE_USER, values, COL_USERNAMEID + " = ?",
	// new String[] { String.valueOf(user.getUserNameId()) });
	// }

	// Update logon user
//	public int updateLogOn(User user) {
//
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(COL_LOGON, user.getLogOn());
//
//		// updating row
//		return db.update(TABLE_USER, values, COL_USERNAME + " = ?",
//				new String[] { String.valueOf(user.getId()) });
//	}
	
	
	//insert result
		public void addResult(User user) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(COL_IDR, user.getId());
			values.put(COL_USERNAMER, user.getUsername());
			values.put(COL_DATETEST, user.getDateTest());
			values.put(COL_PACKAGENAMER, user.getPackageName());
			values.put(COL_CORRECTANSWER, user.getCorrectAnswer());
			values.put(COL_TOTALQUESTION, user.getTotalQuestion());
	
			db.insert(TABLE_RESULT, null, values);
			db.close();
		}

		public ArrayList<User> getResult() {
			ArrayList<User> userList = new ArrayList<User>();
			String selectQuery = "SELECT * FROM " + TABLE_RESULT;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);
			
			if (cursor.moveToFirst()) {
				do {
					User user = new User();
					user.setId(cursor.getInt(0));
					user.setUsername(cursor.getString(1));
					user.setDateTest(cursor.getString(2));
					user.setPackageName(cursor.getString(3));
					user.setCorrectAnswer(cursor.getString(4));
					user.setTotalQuestion(cursor.getString(5));
					userList.add(user);
				} while (cursor.moveToNext());
			}

			cursor.close();
			// return contact list
			return userList;
		}
		
		//insert result
		public int createSessionId(int idUser,int idPackage) {
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String dateNow = formatDate.format(date);
			
			int sessionId = 0;
			String selectQuery = "SELECT sessionid FROM " + TABLE_ANSWER + " WHERE "+
			COL_USERIDA+"="+idUser+" and "+
			COL_PACKAGEIDA+"="+idPackage+" and "+
			COL_TESTDATE+"='"+dateNow+"' order by id desc limit 1";
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			try{
			if (cursor != null) {
				cursor.moveToFirst();
				sessionId = cursor.getInt(0);
			}}catch(Exception e){
			}
			if(sessionId==0){
				sessionId=1;
			}else{
				sessionId +=1;
			}
			cursor.close();
			return sessionId;
		}
		
		//insert result
				public void addAnswered(Answer answer) {
					String countQuery = "SELECT  max(id) FROM " + TABLE_ANSWER;
					SQLiteDatabase db2 = this.getReadableDatabase();
					Cursor cursor = db2.rawQuery(countQuery, null);
					int result = 0;
					try{
					if (cursor != null) {
						cursor.moveToFirst();
						result = cursor.getInt(0);
					}}catch(Exception e){
					}
					cursor.close();

					
					SQLiteDatabase db = this.getWritableDatabase();
					ContentValues values = new ContentValues();
					values.put(COL_IDA, (result+1));
					values.put(COL_USERIDA, answer.getUserId());
					values.put(COL_PACKAGEIDA, answer.getPackageId());
					values.put(COL_QUESTIONIDA, answer.getQuestionId());
					values.put(COL_RIGHTANSWEREDA, answer.getRightAnsweredId());
					values.put(COL_USERNAMEA, answer.getUsername());
					values.put(COL_PACKAGENAMEA, answer.getPackageName());
					values.put(COL_TESTDATE, answer.getDateTest());
					values.put(COL_SESSIONID, answer.getSessionId());
					db.insert(TABLE_ANSWER, null, values);
					db.close();
				}
				
				
				// Getting All Answer
				public ArrayList<Answer> getAllAnswered(int idUser) {
					ArrayList<Answer> answerList = new ArrayList<Answer>();
					String selectQuery = "SELECT userid,packageid,questionid,rightanswered,username,packagename,testdate FROM " + TABLE_ANSWER + " WHERE "+COL_USERIDA+"="+idUser;
					SQLiteDatabase db = this.getWritableDatabase();
					Cursor cursor = db.rawQuery(selectQuery, null);
					if (cursor.moveToFirst()) {
						do {
							Answer answer = new Answer();
							answer.setUserId(cursor.getInt(0));
							answer.setPackageId(cursor.getInt(1));
							answer.setQuestionId(cursor.getInt(2));
							answer.setRightAnsweredId(cursor.getInt(3));
							answer.setUsername(cursor.getString(4));
							answer.setPackageName(cursor.getString(5));
							answer.setDateTest(cursor.getString(6));
							
							answerList.add(answer);
						} while (cursor.moveToNext());
					}

					cursor.close();
					return answerList;
				}

			}

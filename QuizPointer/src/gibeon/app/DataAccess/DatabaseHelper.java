package gibeon.app.DataAccess;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
	
	// ===========================================================
	// Fields
	// ===========================================================
	private SQLiteDatabase DataBase;
	
	public static final String TAG = "DatabaseHelper";
	private static String APP_PATH = "/data/data/%s/databases/";
	private static final String DB_NAME = "quizpointer.gib"; //"newDB"; //"ImportDb";
	private static final int DB_VERSION = 1;
	private static String DB_PATH;
	private Context HelperContext;
	
	// ===========================================================
	// Constructors
	// ===========================================================

	public DatabaseHelper(Context context, String appPackageName) {
		super(context, DB_NAME, null, DB_VERSION);
		this.DB_PATH = String.format(APP_PATH, appPackageName); //dbPath
		this.HelperContext = context;
	}
	
	// ===========================================================
	// new methods
	// ===========================================================
	
	
	public void createDataBase() throws IOException{
    	boolean dbExist = checkDataBase();
    	if(dbExist){
    		Log.d(TAG, "db exist");
    	}
    	
    	//dbExist = checkDataBase();
    	
    	else//if(!dbExist)
    	{
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
        	Log.d(TAG, "db not-exist");
    		
        	try {
 
    			copyDataBase();
    			Log.d(TAG, "copy db not-exist");
        		
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
	
	/**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
    	SQLiteDatabase checkDB = null;
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY|SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    	}catch(SQLiteException e){
    	}
    	if(checkDB != null){
    		checkDB.close();
    	}
    	return checkDB != null ? true : false;
    }
 
    private void copyDataBase() throws IOException{
    	//Open your local db as the input stream
    	InputStream myInput = HelperContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    
    	
    }
	
    public long insert(String tableName,ContentValues values)
    {
    	long newId = -1;
    	
    	newId = getWritableDatabase().insertOrThrow(tableName, null, values);	
    	
    	
    	return newId;
    }

    public Cursor select(String view, String[] columnNames, String filter)
    {
    	String queryString = "SELECT";
    	if(columnNames.length == 0)
    	{
    		Log.d("Database helper","select error");
    		throw new SQLiteException("column names are not specified");
    	}
    	
    	for (int i = 0; i < columnNames.length; i++) {
    		if(i < columnNames.length - 1)
    		{
    			queryString = queryString + " " + columnNames[i] + ",";
    		}
    		else
    		{
    			queryString = queryString + " " + columnNames[i];
    		}
    						
		}
    	
    	queryString = queryString + " FROM " + view;
    	
    	if(filter != null && filter.trim() != ""){
    		queryString = queryString + " WHERE " +filter; 
		}
    	
    	return getReadableDatabase().rawQuery(queryString, null);
    }
    
    public int update(String tableName,ContentValues values, String filter)
    {
    	int rowAffected = 0;
    	
    	rowAffected = getWritableDatabase().update(tableName, values, filter, null);
    	
    	return rowAffected;
    }
    
  public int delete(String tableName,String filter)
    {
    	int rowAffected = 0;
    	
    	rowAffected = getWritableDatabase().delete(tableName, filter, null);
    	
    	return rowAffected;
    }
    
    public Cursor query(String rawQuery, String[] argument)
    {
    	return getReadableDatabase().rawQuery(rawQuery, argument);
    }
    
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    @Override
    public synchronized void close() {
    	if(DataBase != null)
    	{
    		DataBase.close();
    	}
    	super.close();
    }
    
    @Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		HelperContext.deleteDatabase(DB_NAME);
	}

	
	
	//===================================================================
	//=================UPLOAD DATABASE FROM WEBSERVER====================
	//===================================================================
	
	public static boolean downloadDatabase(Context context) {
        try {
               // Log.d(TAG, "downloading database");
               //URL url = new URL("http://some-url.com/db/" + "db_name.s3db");
                URL url = new URL("http://gibeonwebapi.azurewebsites.net/content/" + "site.css");
                
                /* Open a connection to that URL. */
                URLConnection ucon = url.openConnection();
                /*
                 * Define InputStreams to read from the URLConnection.
                 */
                InputStream is = ucon.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                /*
                 * Read bytes to the Buffer until there is nothing more to read(-1).
                 */
                ByteArrayBuffer baf = new ByteArrayBuffer(50);
                int current = 0;
                while ((current = bis.read()) != -1) {
                        baf.append((byte) current);
                }  

                /* Convert the Bytes read to a String. */
                FileOutputStream fos = null;
                // Select storage location
                fos = context.openFileOutput("UpdateDB", Context.MODE_PRIVATE); 

                fos.write(baf.toByteArray());
                fos.close();
               // Log.d(TAG, "downloaded");
                
        } catch (IOException e) {
                Log.e(TAG, "downloadDatabase Error: " , e);
                return false;
        }  catch (NullPointerException e) {
                Log.e(TAG, "downloadDatabase Error: " , e);
                return false;
        } catch (Exception e){
                Log.e(TAG, "downloadDatabase Error: " , e);
                return false;
        }
        return true;
	}
	
	/**
	* Copies your database from your local downloaded database that is copied from the server 
	* into the just created empty database in the
	* system folder, from where it can be accessed and handled.
	* This is done by transfering bytestream.
	* */
	public void copyServerDatabase() {
	    // by calling this line an empty database will be created into the default system path
	    // of this app - we will then overwrite this with the database from the server
	    SQLiteDatabase db = getReadableDatabase();
	    db.close();
	
	
	        OutputStream os = null;
	        InputStream is = null;
	        try {
	              // Log.d(TAG, "Copying DB from server version into app");
	                is = HelperContext.openFileInput("UpdateDB"); //"site.css");
	                os = new FileOutputStream(DB_PATH+"ImportDb"); // XXX change this
	
	                copyFile(os, is);
	        } catch (Exception e) {
	                Log.e(TAG, "New Database was not found - did it download correctly?", e);                          
	        } finally {
	                try {
	                        //Close the streams
	                        if(os != null){
	                                os.close();
	                        }
	                        if(is != null){
	                                is.close();
	                        }
	                } catch (IOException e) {
	                        Log.e(TAG, "failed to close databases");
	                }
	        }
	        
	        // Log.d(TAG, "Done Copying DB from server");
	        
	        
	}
	
	
	private void copyFile(OutputStream os, InputStream is) throws IOException {
	    byte[] buffer = new byte[1024];
	    int length;
	    while((length = is.read(buffer))>0){
	            os.write(buffer, 0, length);
	    }
	    os.flush();
	}
	

}

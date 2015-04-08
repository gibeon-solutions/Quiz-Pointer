package gibeon.app.entities;

import java.util.ArrayList;

import gibeon.app.DataAccess.DatabaseHandler;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.ContentValues;
import android.content.Context;
//import android.content.res.Resources;
//
public class UserBL {
	
	//private variables 
	private DatabaseHandler dh;
    
    // constructor 
    public UserBL(Context context){ 
    	dh = new DatabaseHandler(context);
   	} 
    
    public void addUser(int idUser,String username){
    	dh.addUsers(idUser,username);
    }
    
    public int getUser(){
    	return dh.getIdUser();
    }
    
    public String getUsername(){
    	return dh.getNameUser();
    }
    
    public void addResult(User user){
    	dh.addResult(user);
    }
    
    public ArrayList<User> getResult(){
    	return dh.getResult();
    }
    public void addUser(User user){
    	dh.addUser(user);
    }
    
    public void deleteAllUser(){
    	dh.deleteAllUser();
    }
    
    public User getUserLogin(String username,String password){
    	return dh.getUserLogin(username, password);
    }
}
//    
//    public User getUser(String username){
//    	return db.getUser(username);
//    }
//  
//    public User getLoggedInUser(){
//    	return db.getUsersLogOn().get(0);
//    }
//    
//    public boolean checkLogIn(){
//    	
//    	boolean b = false;
//    	int check = db.getNumberOfUsersLogOn();
//    	
//    	if(check == 1)
//    		b = true;
//    	else if(check>1)
//    	{
//    		List<User> usersList = db.getUsersLogOn();
//    		for(User user : usersList)
//    			this.setLoggedInUser(user.getUsername(),false);
//    	}
//    	
//    	return b;
//    }
//    
//    public int getCount(String username){
//    	return db.getCount(username);
//    }
//    
//    public void setLoggedInUser(String username, boolean b){
//    	
//    	User user = db.getUser(username);
//    	user.setLogOn(b);
//    	db.updateLogOn(user);
//    }
//    
//
//}

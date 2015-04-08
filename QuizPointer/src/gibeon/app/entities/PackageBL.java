package gibeon.app.entities;

import java.util.List;

import android.content.Context;
import gibeon.app.DataAccess.DatabaseHandler;

public class PackageBL {
	private DatabaseHandler db;
    
    public PackageBL(Context context){ 
    	db = new DatabaseHandler(context);
   	} 
    
  public void addSavedPackage(int id,int iduser,String name,String packages,String namepackage){
	db.addPackageSaved(id, iduser,name, packages,namepackage);
}
  
  public selectedPackage getSavedPackage(String idRole){
	 return db.getSavedPackage(idRole);
  }
  
  public int getCountSavedPackage(){
		 return db.getCountSaved();
	  }
  
  public List<Packages> getAllPackage(){
		 return db.getAllPackage();
	  }
    
}

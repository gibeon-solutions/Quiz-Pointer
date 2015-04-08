package gibeon.app.entities;

import gibeon.app.quizpointer.R;
import android.graphics.Color;

public class Packages {
String user,savedPackage,name,status;
public int iconBg=R.drawable.custompackage,colorTc=Color.parseColor("#9EA7B2");
public Packages(){
	
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Packages(String name,String status){
	this.name=name;
	this.status=status;
}

public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public String getSavedPackage() {
	return savedPackage;
}

public void setSavedPackage(String savedPackage) {
	this.savedPackage = savedPackage;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}



}

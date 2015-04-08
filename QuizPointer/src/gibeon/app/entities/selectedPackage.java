package gibeon.app.entities;

public class selectedPackage {
String idPackage;
String packageName;


public selectedPackage(String idPackage, String packageName) {
	this.idPackage = idPackage;
	this.packageName = packageName;
}

public selectedPackage() {
}
public String getIdPackage() {
	return idPackage;
}


public void setIdPackage(String idPackage) {
	this.idPackage = idPackage;
}


public String getPackageName() {
	return packageName;
}


public void setPackageName(String packageName) {
	this.packageName = packageName;
}





}

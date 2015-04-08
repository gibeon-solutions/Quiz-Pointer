package gibeon.app.entities;

import gibeon.app.quizpointer.R;
import android.graphics.Color;

public class packageUser {
	  int roleid,packageId,isActive;
	  String packageName,startDate,endDate;
	  public int iconBg=R.drawable.custompackage,colorTc=Color.parseColor("#9EA7B2");

	  public packageUser() {
	
	  }

	  public packageUser(int packageId, String packageName) {
		this.packageId = packageId;
		this.packageName = packageName;
	}
	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	  
	  
    
}

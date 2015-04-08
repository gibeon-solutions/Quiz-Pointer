package gibeon.app.entities;

public class User {

	private int id,groupId,isAdmin,countLogin;
	private String roleId,groupName,roleName,username,password,firstname,lastname;
	
	String dateTest,packageName,correctAnswer,totalQuestion;
	
	public User(int id, int groupId, String roleId, int isAdmin, String groupName,
			String roleName, String username, String password,
			String firstname, String lastname) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.roleId = roleId;
		this.isAdmin = isAdmin;
		this.groupName = groupName;
		this.roleName = roleName;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	

	public User(String username, String dateTest, String packageName,
			String correctAnswer, String totalQuestion) {
		this.username = username;
		this.dateTest = dateTest;
		this.packageName = packageName;
		this.correctAnswer = correctAnswer;
		this.totalQuestion = totalQuestion;
	}



	public String getDateTest() {
		return dateTest;
	}



	public void setDateTest(String dateTest) {
		this.dateTest = dateTest;
	}



	public String getPackageName() {
		return packageName;
	}



	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}



	public String getCorrectAnswer() {
		return correctAnswer;
	}



	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}



	public String getTotalQuestion() {
		return totalQuestion;
	}



	public void setTotalQuestion(String totalQuestion) {
		this.totalQuestion = totalQuestion;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	
	
//	//private variables 
//
//    private String id,username,password; 
//	private int logOn;
//	
    // Empty constructor 
    public User(){ 
  
    } 
//    
//    
//    public User(String id, String username, String password, int logOn) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.logOn = logOn;
//	}
//    
    public User(int id, String username) {
 		super();
 		this.id = id;
 		this.username = username;
 	}

    
    public User(int countLogin, String idRole,int idUser) {
 		this.countLogin = countLogin;
 		this.roleId = idRole;
 		this.id=idUser;
 	}

    
	public User(String userna){ 
    	  this.username=userna;
    } 
//
//    public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public int getLogOn() {
//		return logOn;
//	}
//
//	public void setLogOn(int logOn) {
//		this.logOn = logOn;
//	}
//
//	public void setLogOn(boolean b){
//    	if(b)
//    		logOn=1;
//    	else
//    		logOn=0;
//    }



	public int getCountLogin() {
		return countLogin;
	}



	public void setCountLogin(int countLogin) {
		this.countLogin = countLogin;
	}
}

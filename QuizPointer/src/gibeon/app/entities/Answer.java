package gibeon.app.entities;

public class Answer {
	
	private int _id;
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	
	public String AnswerText;
	public String AnswerMark;
	public int AnswerId;
	public boolean isAnswer;
	public boolean Selected;
	public int iconBg;
	public static String answerUser="",correctAnswer="",answerUser2="",answerTemp="",answerTextUser,answerTextUser2,answerTextCorrect,answerTextCorrect2;
	public static boolean isCorrect=false;
	int userId,packageId,questionId,RightAnsweredId,sessionId;
	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}


	String username,packageName,dateTest;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDateTest() {
		return dateTest;
	}

	public void setDateTest(String dateTest) {
		this.dateTest = dateTest;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getRightAnsweredId() {
		return RightAnsweredId;
	}

	public void setRightAnsweredId(int rightAnsweredId) {
		RightAnsweredId = rightAnsweredId;
	}

	public Answer()
	{
		
	}
	
	public Answer(int id)
	{
		_id = id;
	}
}

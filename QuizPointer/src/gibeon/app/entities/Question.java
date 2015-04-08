package gibeon.app.entities;

public class Question {

	int id, typeQuestionId, levelId, value, packageId,userId;
	String questionText, answer, optionOne, optionTwo, optionThree, optionFour,
			optionFive, optionSix, explanation, reference;

	public Question(int questionId) {
		this.id = questionId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeQuestionId() {
		return typeQuestionId;
	}

	public void setTypeQuestionId(int typeQuestionId) {
		this.typeQuestionId = typeQuestionId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getOptionOne() {
		return optionOne;
	}

	public void setOptionOne(String optionOne) {
		this.optionOne = optionOne;
	}

	public String getOptionTwo() {
		return optionTwo;
	}

	public void setOptionTwo(String optionTwo) {
		this.optionTwo = optionTwo;
	}

	public String getOptionThree() {
		return optionThree;
	}

	public void setOptionThree(String optionThree) {
		this.optionThree = optionThree;
	}

	public String getOptionFour() {
		return optionFour;
	}

	public void setOptionFour(String optionFour) {
		this.optionFour = optionFour;
	}

	public String getOptionFive() {
		return optionFive;
	}

	public void setOptionFive(String optionFive) {
		this.optionFive = optionFive;
	}

	public String getOptionSix() {
		return optionSix;
	}

	public void setOptionSix(String optionSix) {
		this.optionSix = optionSix;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Question(int id, int typeQuestionId, int levelId, int value,
			int packageId, String questionText, String answer,
			String optionOne, String optionTwo, String optionThree,
			String optionFour, String optionFive, String optionSix,
			String explanation, String reference) {
		super();
		this.id = id;
		this.typeQuestionId = typeQuestionId;
		this.levelId = levelId;
		this.value = value;
		this.packageId = packageId;
		this.questionText = questionText;
		this.answer = answer;
		this.optionOne = optionOne;
		this.optionTwo = optionTwo;
		this.optionThree = optionThree;
		this.optionFour = optionFour;
		this.optionFive = optionFive;
		this.optionSix = optionSix;
		this.explanation = explanation;
		this.reference = reference;
	}

		// public static String[] splitQuestion(String question, int lineQuestion) {
	// String splitText[] = WordUtils.wrap(question, QuizFragment.lineSize,
	// "<split>", true).split(
	// "<split>");
	// divisionSize = new String[splitText.length];
	// int counter = 0;
	// int startDivision = 0;
	// try {
	// for (int a = 0; a < divisionSize.length; a++) {
	// divisionSize[a] = "";
	// startDivision += lineQuestion;
	// for (int b = counter; b < startDivision; b++) {
	// divisionSize[a] += splitText[b] + " ";
	// counter++;
	// }
	// }
	// } catch (Exception e) {
	// }
	// return divisionSize;
	// }

}

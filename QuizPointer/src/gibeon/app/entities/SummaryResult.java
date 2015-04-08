package gibeon.app.entities;

import gibeon.app.quizpointer.R;

public class SummaryResult {
	public String question, userAnswer, correctAnswer;
	public boolean isSection;
	public int iconBg=R.drawable.summarycorrectanswer;

	public SummaryResult(String question, boolean isSection) {
		super();
		this.question = question;
		this.isSection = isSection;
	}

	public SummaryResult(String userAnswer, String correctAnswer,
			boolean isSection) {
		super();
		this.userAnswer = userAnswer;
		this.correctAnswer = correctAnswer;
		this.isSection = isSection;
	}
	
	public SummaryResult(String question) {
		this.question = question;
	}

	public SummaryResult(String userAnswer, String correctAnswer) {
		this.userAnswer = userAnswer;
		this.correctAnswer = correctAnswer;
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public boolean isSection() {
		return isSection;
	}

	public void setSection(boolean isSection) {
		this.isSection = isSection;
	}

}

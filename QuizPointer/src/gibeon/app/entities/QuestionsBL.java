package gibeon.app.entities;

import gibeon.app.DataAccess.DatabaseHandler;
import gibeon.app.DataAccess.DatabaseHelper;
import gibeon.app.fragment.PairMatching;
import gibeon.app.fragment.Sorted;

import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;

public class QuestionsBL {
	private DatabaseHelper helper;
	private DatabaseHandler db;

	public QuestionsBL(Context context, String appPackage) {
		helper = new DatabaseHelper(context, appPackage);
		db = new DatabaseHandler(context);
	}

	public QuestionsBL(Context context) {
		db = new DatabaseHandler(context);
	}

	public void addQuestion(Question question,int idUser) {
		db.addQuestion(question,idUser);
	}

	public ArrayList<Question> getQuestion(String idRole) {
		return db.getAllQuestion(idRole);
	}

	public ArrayList<Answer> getMultiAnswer(Question question) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		Answer newAnswer;

		newAnswer = new Answer(1);
		newAnswer.AnswerText = question.getOptionOne();
		newAnswer.AnswerMark = "A";
		answers.add(newAnswer);

		newAnswer = new Answer(2);
		newAnswer.AnswerText = question.getOptionTwo();
		newAnswer.AnswerMark = "B";
		answers.add(newAnswer);

		newAnswer = new Answer(3);
		newAnswer.AnswerText = question.getOptionThree();
		newAnswer.AnswerMark = "C";
		answers.add(newAnswer);

		if (!question.getOptionFour().equals("")) {
			newAnswer = new Answer(4);
			newAnswer.AnswerText = question.getOptionFour();
			newAnswer.AnswerMark = "D";
			answers.add(newAnswer);
		}

		Collections.shuffle(answers);
		return answers;
	}

	public ArrayList<Answer> getTrueFalseAnswer(Question question) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		Answer newAnswer;

		newAnswer = new Answer(1);
		newAnswer.AnswerText = question.getOptionOne();
		newAnswer.AnswerMark = "A";
		answers.add(newAnswer);

		newAnswer = new Answer(2);
		newAnswer.AnswerText = question.getOptionTwo();
		newAnswer.AnswerMark = "B";
		answers.add(newAnswer);

		return answers;
	}

	public ArrayList<Answer> getPairAnswers(Question quest) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		Answer newAnswer;
		
		PairMatching.textChoice1.removeAll(PairMatching.textChoice1);
		PairMatching.textChoice2.removeAll(PairMatching.textChoice2);
		PairMatching.textChoice1.add(quest.getOptionOne());
		PairMatching.textChoice1.add(quest.getOptionTwo());
		PairMatching.textChoice1.add(quest.getOptionThree());
		
		PairMatching.textChoice2.add(quest.getOptionFour());
		PairMatching.textChoice2.add(quest.getOptionFive());
		PairMatching.textChoice2.add(quest.getOptionSix());

		newAnswer = new Answer(1);
		newAnswer.AnswerText = quest.getOptionOne();
		newAnswer.AnswerMark = "A";
		answers.add(newAnswer);

		newAnswer = new Answer(2);
		newAnswer.AnswerText = quest.getOptionTwo();
		newAnswer.AnswerMark = "B";
		answers.add(newAnswer);

		newAnswer = new Answer(3);
		newAnswer.AnswerText = quest.getOptionThree();
		newAnswer.AnswerMark = "C";
		answers.add(newAnswer);

		newAnswer = new Answer(4);
		newAnswer.AnswerText = quest.getOptionFour();
		newAnswer.AnswerMark = "D";
		answers.add(newAnswer);

		newAnswer = new Answer(5);
		newAnswer.AnswerText = quest.getOptionFive();
		newAnswer.AnswerMark = "E";
		answers.add(newAnswer);

		newAnswer = new Answer(6);
		newAnswer.AnswerText = quest.getOptionSix();
		newAnswer.AnswerMark = "F";
		answers.add(newAnswer);

		Collections.shuffle(answers.subList(0, 3));
		Collections.shuffle(answers.subList(3, 6));

		return answers;
	}

	public ArrayList<Answer> getSortAnswers(Question quest) {

		ArrayList<Answer> answers = new ArrayList<Answer>();
		Answer newAnswer;
		Sorted.textChoice.removeAll(Sorted.textChoice);
		Sorted.textChoice.add(quest.getOptionOne());
		Sorted.textChoice.add(quest.getOptionTwo());
		Sorted.textChoice.add(quest.getOptionThree());
		Sorted.textChoice.add(quest.getOptionFour());

		newAnswer = new Answer(1);
		newAnswer.AnswerText = quest.getOptionOne();
		newAnswer.AnswerMark = "A";
		newAnswer.AnswerId = 1;
		answers.add(newAnswer);

		newAnswer = new Answer(2);
		newAnswer.AnswerText = quest.getOptionTwo();
		newAnswer.AnswerMark = "B";
		newAnswer.AnswerId = 2;
		answers.add(newAnswer);

		newAnswer = new Answer(3);
		newAnswer.AnswerText = quest.getOptionThree();
		newAnswer.AnswerMark = "C";
		newAnswer.AnswerId = 3;
		answers.add(newAnswer);

		newAnswer = new Answer(4);
		newAnswer.AnswerText = quest.getOptionFour();
		newAnswer.AnswerMark = "D";
		newAnswer.AnswerId = 4;
		answers.add(newAnswer);
		Collections.shuffle(answers);
		return answers;
	}

	public ArrayList<Answer> getAssociatedAnswers(Question question) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		Answer newAnswer;

		newAnswer = new Answer(1);
		newAnswer.AnswerText = question.getOptionOne();
		newAnswer.AnswerMark = "A";
		answers.add(newAnswer);

		newAnswer = new Answer(2);
		newAnswer.AnswerText = question.getOptionTwo();
		newAnswer.AnswerMark = "B";
		answers.add(newAnswer);

		newAnswer = new Answer(3);
		newAnswer.AnswerText = question.getOptionThree();
		newAnswer.AnswerMark = "C";
		answers.add(newAnswer);

		newAnswer = new Answer(4);
		newAnswer.AnswerText = question.getOptionFour();
		newAnswer.AnswerMark = "D";
		answers.add(newAnswer);

		Collections.shuffle(answers);

		return answers;
	}
}

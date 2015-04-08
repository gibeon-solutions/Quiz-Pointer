package gibeon.app.entities;

import java.util.ArrayList;

import gibeon.app.DataAccess.DatabaseHandler;
import android.content.Context;

public class AnswerBL {
	// private variables
	private DatabaseHandler dh;

	// constructor
	public AnswerBL(Context context) {
		dh = new DatabaseHandler(context);
	}

	public void addAnswerQuestion(Answer answer) {
		dh.addAnswered(answer);
	}
	
	public ArrayList<Answer> getAllAnswered(int idUser) {
		return dh.getAllAnswered(idUser);
	}
	
	public int createSessionId(int idUser,int idPackage){
		return dh.createSessionId(idUser,idPackage);
	}
}

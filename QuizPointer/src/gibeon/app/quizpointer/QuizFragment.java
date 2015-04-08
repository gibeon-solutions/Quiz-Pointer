package gibeon.app.quizpointer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gibeon.app.DataAccess.WebService;
import gibeon.app.adapter.ReadAdapter;
import gibeon.app.adapter.TextViewCustom;
import gibeon.app.entities.Answer;
import gibeon.app.entities.AnswerBL;
import gibeon.app.entities.Question;
import gibeon.app.entities.QuestionsBL;
import gibeon.app.entities.SummaryResult;
import gibeon.app.fragment.Associated;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

@SuppressLint("SimpleDateFormat")
public class QuizFragment extends FragmentActivity {
	public ViewPager vPager;
	public static ViewFlipper viewFlipper;
	public static boolean checkAnswer = false;
	public static int counter,rightAnswer, wrongAnswer, pointValue, answered;
	private ReadAdapter readAdapter;
	
	public static ArrayList<SummaryResult> sr=new ArrayList<SummaryResult>();

	TextView questionNumber, questionPoint, questionTimer;

	public static ArrayList<Question> question = new ArrayList<Question>();

	final Context context = this;

	public static ArrayList<String> questionPager2 = new ArrayList<String>();;
	public static String packageId,sId;

	private Timer _timer;
	private int countDownTime = PackageActivity.time,sessionId;

	public static boolean isActiveNext, isEnableChoice = true;
	public static TextView tvMessage;
	Button btnExplanation;
	public static Button continueButton;
	boolean next = true;
	Bundle bundle;
	private ProgressDialog pDialog;
	QuestionsBL qbl;

	SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
	Date date = new Date();
	String dateNow = formatDate.format(date);

	AnswerBL abl = new AnswerBL(context);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.page_main);

		qbl = new QuestionsBL(context);

		pDialog = new ProgressDialog(context, R.style.CustomLoading);
		pDialog.setCancelable(false);

		vPager = (ViewPager) findViewById(R.id.vp_question);
		questionNumber = (TextView) findViewById(R.id.question_number);
		questionPoint = (TextView) findViewById(R.id.question_point);
		questionTimer = (TextView) findViewById(R.id.question_timer);

		tvMessage = (TextView) findViewById(R.id.tv_selectanswer);

		TextViewCustom.customFontRegular(this, questionNumber);
		TextViewCustom.customFontRegular(this, questionPoint);
		TextViewCustom.customFontBold(this, tvMessage);

		btnExplanation = (Button) findViewById(R.id.buttonexplanation);
		continueButton = (Button) findViewById(R.id.buttonnext);
		bundle = getIntent().getExtras();
		packageId=bundle.getString("packageid");

//		Toast.makeText(getApplicationContext(), packageId,
//				Toast.LENGTH_SHORT).show();
		
		loadData();

		continueButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (isActiveNext) {
					if (next) {
						if (counter < question.size() - 1) {
							btnExplanation.setVisibility(View.VISIBLE);
							if (question.get(counter).getTypeQuestionId() == 2
									|| question.get(counter)
											.getTypeQuestionId() == 3
									|| question.get(counter)
											.getTypeQuestionId() == 1) {
								checkAnswer();
							}

							else if (question.get(counter).getTypeQuestionId() == 4) {
								char sortAnswer[];
								sortAnswer = new char[Answer.answerUser
										.length()];
								for (int a = 0; a < Answer.answerUser.length(); a++) {
									sortAnswer[a] = Answer.answerUser.charAt(a);
								}

								Arrays.sort(sortAnswer);
								Answer.answerUser = "";
								for (int a = 0; a < sortAnswer.length; a++) {
									Answer.answerUser += sortAnswer[a];
									if (a < sortAnswer.length - 1) {
										Answer.answerUser += ",";
									}
								}
								checkAnswer();
							}

							else if (question.get(counter).getTypeQuestionId() == 5) {
								String sortingAnswer[] = Answer.answerUser
										.split("");
								Answer.answerUser = "";
								for (int a = 1; a < sortingAnswer.length; a++) {
									Answer.answerUser += sortingAnswer[a];
									if (a < sortingAnswer.length - 1) {
										Answer.answerUser += ",";
									}
								}
								checkAnswer();
							}

							else if (question.get(counter).getTypeQuestionId() == 6) {
								String splitAlfa1[] = Answer.answerUser
										.split("");
								String splitAlfa2[] = Answer.answerUser2
										.split("");
								Answer.answerUser = "";
								for (int a = 1; a < splitAlfa1.length; a++) {
									Answer.answerUser += splitAlfa1[a] + "-"
											+ splitAlfa2[a];
									if (a < splitAlfa1.length - 1) {
										Answer.answerUser += ",";
									}
								}
								checkAnswer();
							}

						} else {
							checkAnswer();
						}
						isEnableChoice = false;
						next = false;
					} else {
//						if (LoginActivity.online) {
							pDialog.show();
							new ServerTask().execute("getSavedQuestion");
//						} else {
//							counter++;
//							loadQuestion();
//							isEnableChoice = true;
//							next = true;
//							btnExplanation.setVisibility(View.INVISIBLE);
//						}
					}
				} else {
					final Dialog d = new Dialog(context,
							R.style.CustomExplanation);
					d.requestWindowFeature(Window.FEATURE_NO_TITLE);
					d.setContentView(R.layout.popup_selectanswer);
					TextView tv = (TextView) d
							.findViewById(R.id.selectanswer_text);

					if (question.get(counter).getTypeQuestionId() == 4) {
						tv.setText("please choice 3 answers minimally");
					}

					WindowManager.LayoutParams wmlp = d.getWindow()
							.getAttributes();
					wmlp.gravity = Gravity.BOTTOM | Gravity.LEFT;
					wmlp.x = 20;
					wmlp.y = 20;
					d.show();

					Timer time = new Timer();
					time.scheduleAtFixedRate(new TimerTask() {
						public void run() {
							d.cancel();
						}
					}, 1000, 1000);
				}
			}
		});

		btnExplanation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				final Dialog d = new Dialog(context);
				d.requestWindowFeature(Window.FEATURE_NO_TITLE);
				d.setContentView(R.layout.popup_explanation);
				WindowManager.LayoutParams wmlp = d.getWindow().getAttributes();
				wmlp.gravity = Gravity.CENTER;

				DisplayMetrics displayMetrics = context.getResources()
						.getDisplayMetrics();
				int width = displayMetrics.widthPixels - 50;
				int height = displayMetrics.heightPixels - 100;

				wmlp.width = width;
				wmlp.height = height;

				d.setCancelable(false);
				((Button) d.findViewById(R.id.btnclose))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View arg0) {
								d.cancel();
							}
						});

				((TextView) d.findViewById(R.id.explanation_text))
						.setText(QuizFragment.question
								.get(QuizFragment.counter).getExplanation());
				d.show();

			}
		});
	}

	private void loadQuestion() {
		Answer.answerUser = "";
		Answer.answerTemp = "";
		Answer.answerUser2 = "";
		Associated.counterAssociated = 0;

		isActiveNext = false;
		continueButton.setBackgroundResource(R.drawable.bnextoff);

		readAdapter = new ReadAdapter(getSupportFragmentManager());
		vPager.setAdapter(readAdapter);
		vPager.setCurrentItem(counter);
		

		questionNumber.setText((counter + 1) + "/" + question.size());
		questionPoint.setText("" + pointValue);

		if (question.get(counter).getTypeQuestionId() == 5
				|| question.get(counter).getTypeQuestionId() == 6) {
			QuizFragment.continueButton
					.setBackgroundResource(R.drawable.bnexton);
			isActiveNext = true;
		}
		playTimer();
	}

	private void checkAnswer() {
			_timer.cancel();
		if (Answer.answerUser.equals(Answer.correctAnswer)
				&& question.get(counter).getTypeQuestionId() != 6) {
			rightAnswer++;
			pointValue += QuizFragment.question.get(QuizFragment.counter)
					.getValue();
			tvMessage.setText("Right Answer");
			tvMessage.setBackgroundColor(Color.parseColor("#1ABC9C"));
			answered = 1;
			Answer.isCorrect=true;
		} else {
			if (question.get(counter).getTypeQuestionId() != 6) {
				wrongAnswer++;
				tvMessage.setText("Wrong Answer");
				tvMessage.setBackgroundColor(Color.parseColor("#E74C3C"));
				answered = 0;
				Answer.isCorrect=false;
			}
		}
		tvMessage.setTextColor(Color.WHITE);
		if (question.get(counter).getTypeQuestionId() == 2
				|| question.get(counter).getTypeQuestionId() == 3) {
			readAdapter.typeQuestion1.changeColorAnswer();
		} else if (question.get(counter).getTypeQuestionId() == 4) {
			readAdapter.typeQuestion3.changeColorAnswer();
		} else if (question.get(counter).getTypeQuestionId() == 5) {
			readAdapter.typeQuestion4.changeColorAnswer();
		} else if (question.get(counter).getTypeQuestionId() == 6) {
			readAdapter.typeQuestion5.changeColorAnswer();
		} else if (question.get(counter).getTypeQuestionId() == 1) {
			readAdapter.typeQuestion6.changeColorAnswer();
		}
		
		sr.add(new SummaryResult(Answer.answerTextUser,Answer.answerTextCorrect,Answer.isCorrect));
		sr.add(new SummaryResult(question.get(counter).getQuestionText()));

		// if (!LoginActivity.online) {

		Answer answer = new Answer();
		answer.set_id((counter + 1));
		answer.setUserId(LoginActivity.idUser);
		answer.setPackageId(question.get(counter).getPackageId());
		answer.setQuestionId(question.get(counter).getId());
		answer.setRightAnsweredId(answered);
		answer.setUsername(LoginActivity.user);
		answer.setPackageName(PackageActivity.packageName);
		answer.setDateTest(dateNow);
		answer.setSessionId(sessionId);
		abl.addAnswerQuestion(answer);

		// }
	}

	public void onBackPressed() {
	}

	public void showWarning(String s) {
		Toast t = Toast.makeText(context, s, Toast.LENGTH_SHORT);
		t.show();
	}

	public void playTimer() {
		_timer = new Timer();
		_timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				countDownTime--;
				if (countDownTime == 0) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							tvMessage.setBackgroundColor(Color
									.parseColor("#DFE2E5"));
						}
					});

					wrongAnswer += question.size() - counter;
					_timer.cancel();
					QuizFragment.this.finish();
					startActivity(new Intent(context, ResultActivity.class));
				}

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						TextViewCustom.customFontRegular(QuizFragment.this,
								questionTimer);
						questionTimer.setText(" " + countDownTime + " ");
					}
				});
			}
		}, 1000, 1000);
	}

	private class ServerTask extends AsyncTask<String, Integer, String> {
		private String task;

		@Override
		protected String doInBackground(String... arg) {
			String response = "";
			task = arg[0];
			if (task.equals("getQuestion")) {
				try {
					JSONObject parameter = new JSONObject();
					parameter.put("packageid", packageId);
					response = WebService.post("http://quizpointer.azurewebsites.net/Quizapps/GetPackageQuestion",parameter);
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}

			// else if (task.equals("getSavedQuestion")) {
			// try {
			// JSONObject parameter = new JSONObject();
			// parameter.put("UserId", LoginActivity.idUser);
			// parameter.put("PackageId", bundle.getString("packageid"));
			// parameter.put("QuestionId", question.get(counter).getId());
			// parameter.put("RightAnswered", answered);
			// response =
			// WebService.post("http://quizpointer.azurewebsites.net/Quizapps/SaveQuestionResult",parameter);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }

			else if (task.equals("getSavedQuestion")) {
				try {
					
					JSONObject parameter = new JSONObject();
					parameter.put("SessionId", sId);
					parameter.put("UserId", LoginActivity.idUser);
					parameter.put("PackageId", packageId);
					parameter.put("QuestionId", question.get(counter).getId());
					parameter.put("RightAnswered", answered);
					response = WebService.post("http://quizpointer.azurewebsites.net/Quizapps/SaveQuestionResult",parameter);
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}
			return response;
		}

		@Override
		protected void onProgressUpdate(Integer... item) {
		}

		@Override
		protected void onPostExecute(String result) {
			String savedOK = "\"Message\":\"succesfull\"";
			if (task.equals("getQuestion")) {
				try {
					JSONObject jObject = new JSONObject(result);
					JSONArray jArray = jObject
							.getJSONArray("PackageQuestionList");
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject menuObject = jArray.getJSONObject(i);
						question.add(new Question(menuObject.getInt("id"),
								menuObject.getInt("typequestionid"), menuObject
										.getInt("levelid"), menuObject
										.getInt("value"), menuObject
										.getInt("packageid"), menuObject
										.getString("questiontext"), menuObject
										.getString("answer"), menuObject
										.getString("optionone"), menuObject
										.getString("optiontwo"), menuObject
										.getString("optionthree"), menuObject
										.getString("optionfour"), menuObject
										.getString("optionfive"), menuObject
										.getString("optionsix"), menuObject
										.getString("explanation"), menuObject
										.getString("reference")));
					}

					Collections.shuffle(question);
					loadQuestion();
					sessionId = abl.createSessionId(LoginActivity.idUser,
							question.get(counter).getPackageId());
					sId=dateNow + sessionId;
					// playTimer();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			else if (task.equals("getSavedQuestion")) {
				if (result.contains(savedOK)) {
					if (counter < question.size() - 1) {
						counter++;
						loadQuestion();
						isEnableChoice = true;
						next = true;
						btnExplanation.setVisibility(View.INVISIBLE);
					} else {
						QuizFragment.this.finish();
						tvMessage.setBackgroundColor(Color
								.parseColor("#DFE2E5"));
						_timer.cancel();
						startActivity(new Intent(context, ResultActivity.class));
					}
				}
			}

			if (result.equals("")) {
				showChoices("Do you want to try again?", task);
			}
			pDialog.dismiss();
		}
	}

	private void showChoices(String msg, final String task) {
		AlertDialog.Builder ab = new AlertDialog.Builder(context);
		ab.setMessage(msg);
		ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				pDialog.show();
				new ServerTask().execute(task);
			}
		});
		ab.setNegativeButton("Maybe later",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

		ab.show();
	}

//	private void offlineMode() {
//		question = qbl.getQuestion(LoginActivity.idRole);
//		Collections.shuffle(question);
//		loadQuestion();
//		playTimer();
//	}

	private void onlineMode() {
		pDialog.show();
		new ServerTask().execute("getQuestion");
	}

	private void loadData() {
//		if (LoginActivity.online) {
			onlineMode();
//		} 
//			else {
//			offlineMode();
//		}
	}
}
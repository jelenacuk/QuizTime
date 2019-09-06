package QuizTime.QuizTime.beans;

import java.util.ArrayList;

public class QuestionBean {
	
	private String text;
	private ArrayList<AnswerBean> answers;
	private int correct_cnt;
	
	public QuestionBean() {
		// TODO Auto-generated constructor stub
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<AnswerBean> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<AnswerBean> answers) {
		this.answers = answers;
	}

	public int getCorrect_cnt() {
		return correct_cnt;
	}

	public void setCorrect_cnt(int correct_cnt) {
		this.correct_cnt = correct_cnt;
	}


}

package QuizTime.QuizTime.beans;

import java.util.ArrayList;

public class QuizBean {
	
	private String title;
	private String description;
	private String category;
	private int time;
	private ArrayList<QuestionBean> questions;
	
	public QuizBean() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public ArrayList<QuestionBean> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<QuestionBean> questions) {
		this.questions = questions;
	}
	

}

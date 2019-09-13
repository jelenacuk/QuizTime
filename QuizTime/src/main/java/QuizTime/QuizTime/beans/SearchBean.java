package QuizTime.QuizTime.beans;

public class SearchBean {
	
	private int minQuestions;
	private int maxQuestions;
	private String category;
	private boolean time;
	

	public SearchBean() {
		// TODO Auto-generated constructor stub
	}

	public int getMinQuestions() {
		return minQuestions;
	}

	public void setMinQuestions(int minQuestions) {
		this.minQuestions = minQuestions;
	}

	public int getMaxQuestions() {
		return maxQuestions;
	}

	public void setMaxQuestions(int maxQuestions) {
		this.maxQuestions = maxQuestions;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public boolean isTime() {
		return time;
	}

	public void setTime(boolean time) {
		this.time = time;
	}

}

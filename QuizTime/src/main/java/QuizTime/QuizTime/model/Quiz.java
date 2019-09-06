package QuizTime.QuizTime.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Quiz {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "category")
	private String category;
	@Column(name = "time")
	private int time;
	@OneToMany(mappedBy = "quiz")
	private Set<Question> questions;
	@OneToMany(mappedBy = "quiz")
	private Set<UserQuiz> userQuiz;

	public Quiz() {
		super();
	}

	public Quiz(String title, String description, String category, int time, Set<Question> questions) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.time = time;
		this.questions = questions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Set<UserQuiz> getUserQuiz() {
		return userQuiz;
	}

	public void setUserQuiz(Set<UserQuiz> userQuiz) {
		this.userQuiz = userQuiz;
	}

}

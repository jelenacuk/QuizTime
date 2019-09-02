package QuizTime.QuizTime.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UserQuiz {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Quiz quiz;
	@OneToMany
	private Set<Answer> answers;
	@Column(name = "score")
	private double score;

	public UserQuiz() {
		super();
	}

	public UserQuiz(User user, Quiz quiz, Set<Answer> answers, double score) {
		super();
		this.user = user;
		this.quiz = quiz;
		this.answers = answers;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}

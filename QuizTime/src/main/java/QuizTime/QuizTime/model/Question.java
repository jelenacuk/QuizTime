package QuizTime.QuizTime.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "text")
	private String text;
	@ManyToOne
	private Quiz quiz;
	@OneToMany
	private Set<Answer> answers;
	@Column(name = "questionType")
	private QuestionType questionType;

	public Question() {
		super();
	}

	public Question(String text, QuestionType questionType, Quiz quiz) {
		super();
		this.text = text;
		this.quiz = quiz;
		this.questionType = questionType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

}

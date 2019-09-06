package QuizTime.QuizTime.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import QuizTime.QuizTime.beans.AnswerBean;
import QuizTime.QuizTime.beans.QuestionBean;
import QuizTime.QuizTime.beans.QuizBean;
import QuizTime.QuizTime.model.Answer;
import QuizTime.QuizTime.model.Question;
import QuizTime.QuizTime.model.QuestionType;
import QuizTime.QuizTime.model.Quiz;
import QuizTime.QuizTime.service.AnswerService;
import QuizTime.QuizTime.service.QuestionService;
import QuizTime.QuizTime.service.QuizService;

@RestController
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	AnswerService answerService;
	
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	@RequestMapping(value = "/createQuiz", method = RequestMethod.POST)
	public boolean createQuiz( @RequestBody QuizBean quizBean) {
		
		Quiz quiz = new Quiz(quizBean.getTitle(), quizBean.getDescription(), quizBean.getCategory(), quizBean.getTime());
		quizService.save(quiz);
		Set<Question> questions = new HashSet<>();
		//iteriram kroz pitanja
		for (QuestionBean questionBean : quizBean.getQuestions() ) {
			
			QuestionType questionType = QuestionType.MORE;
			if (questionBean.getCorrect_cnt() == 1) {
				questionType = QuestionType.ONE;
			}
			Question question = new Question(questionBean.getText(), questionType, quiz);
			questionService.save(question);
			Set<Answer> answers = new HashSet<Answer>();
			//iteriram kroz odgovore
			for (AnswerBean answerBean : questionBean.getAnswers() ) {
				answers.add(new Answer(answerBean.getText(), answerBean.isCorrect(), question));
			}
			List<Answer>  answerList = new  ArrayList<>(answers);
			answerService.saveAll(answerList);
			question.setAnswers(answers);
			questions.add(question);
		}
		List<Question>   questionList = new  ArrayList<>(questions);
		questionService.saveAll(questionList);
		quiz.setQuestions(questions);
		quizService.save(quiz);
		return true;
	}

}

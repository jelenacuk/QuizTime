package QuizTime.QuizTime.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import QuizTime.QuizTime.beans.AnswerBean;
import QuizTime.QuizTime.beans.QuestionBean;
import QuizTime.QuizTime.beans.QuizBean;
import QuizTime.QuizTime.beans.SearchBean;
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
	
	@RequestMapping(value = "/api/getQuizes/{idx}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	public ArrayList<QuizBean> getQuizes( @PathVariable("idx") int idx){
		List<Quiz> quizes = quizService.findAll();
		ArrayList<QuizBean> toReturn = new ArrayList<>();
		int startIndex = (idx-1)*5;
		int endIndex = startIndex + 5;
		if (endIndex > quizes.size()) {
			endIndex = quizes.size();
		}
		for (int i = startIndex; i < endIndex; i++) {
			Quiz quiz = quizes.get(i);
			toReturn.add(new QuizBean(quiz.getTitle(), quiz.getDescription(), quiz.getCategory(), quiz.getTime()));
		}
		return toReturn;
		
	}
	
	@CrossOrigin()
	@PreAuthorize("hasAuthority('REGISTERED')")
	@RequestMapping(value = "/searchQuizes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ArrayList<QuizBean> getQuizes( @RequestBody SearchBean filter){
		System.out.println("\tZNACI = " +  filter.getMinQuestions());
		
		List<Quiz> quizes = quizService.findAll();
		ArrayList<QuizBean> toReturn = new ArrayList<>();
		if (filter.getMaxQuestions() == 0) {
			filter.setMaxQuestions(Integer.MAX_VALUE);
		}
		boolean timeLimited = false;
		for (Quiz quiz : quizes) {
			if (quiz.getTime() != 0) {
				timeLimited = true;
			}
			if (quiz.getQuestions().size() >= filter.getMinQuestions()
					&& quiz.getQuestions().size() <= filter.getMaxQuestions()
					&& (quiz.getCategory().equals(filter.getCategory()) || filter.getCategory().equals("General Knowledge")) 
					&& Boolean.compare(timeLimited, filter.isTime())  == 0) {
				toReturn.add(new QuizBean(quiz.getTitle(), quiz.getDescription(), quiz.getCategory(), quiz.getTime()));
			}
		}
		return toReturn;
		
	}

}

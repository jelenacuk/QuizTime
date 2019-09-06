package QuizTime.QuizTime.service;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QuizTime.QuizTime.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	QuestionRepository repository;
	
	public Question findOne(Long id) {
		return repository.getOne(id);
	}
	
	public List<Question> findAll() {
		return repository.findAll();
	}
	public Question save(Question question) {
		return repository.save(question);
	}
	
	public void remove(Long id) {
		//repository.delete(id);
	}


}

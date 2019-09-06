package QuizTime.QuizTime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QuizTime.QuizTime.model.Question;
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
	
	public List<Question> saveAll(List<Question> questions) {
		return repository.saveAll(questions);
	}
	
	public void remove(Long id) {
		//repository.delete(id);
	}


}

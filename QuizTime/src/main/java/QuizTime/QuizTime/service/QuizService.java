package QuizTime.QuizTime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QuizTime.QuizTime.model.Quiz;
import QuizTime.QuizTime.repository.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
	QuizRepository repository;
	
	public Quiz findOne(Long id) {
		return repository.getOne(id);
	}
	
	public List<Quiz> findAll() {
		return repository.findAll();
	}
	public Quiz save(Quiz quiz) {
		return repository.save(quiz);
	}

	public void remove(Long id) {
		//repository.delete(id);
	}

}

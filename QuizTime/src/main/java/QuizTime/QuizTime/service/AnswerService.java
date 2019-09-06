package QuizTime.QuizTime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QuizTime.QuizTime.model.Answer;
import QuizTime.QuizTime.repository.AnswerRepository;

@Service
public class AnswerService {
	
	@Autowired
	AnswerRepository repository;
	
	public Answer findOne(Long id) {
		return repository.getOne(id);
	}
	
	public List<Answer> findAll() {
		return repository.findAll();
	}
	public Answer save(Answer answer) {
		return repository.save(answer);
	}
	
	public List<Answer> saveAll(List<Answer> answers) {
		return repository.saveAll(answers);
	}
	
	public void remove(Long id) {
		//repository.delete(id);
	}

}

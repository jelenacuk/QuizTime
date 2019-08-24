package QuizTime.QuizTime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import QuizTime.QuizTime.model.User;
import QuizTime.QuizTime.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public User findOne(Long id) {
		return repository.getOne(id);
	}
	
	public User findOneByUsername(String username) {
		return repository.findOneByUsername(username);
	}

	public List<User> findAll() {
		return repository.findAll();
	}
	public User save(User user) {
		return repository.save(user);
	}

	public void remove(Long id) {
		//repository.delete(id);
	}

}

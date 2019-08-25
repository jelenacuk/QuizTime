package QuizTime.QuizTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizTime.QuizTime.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findOneByUsername(String username);

}

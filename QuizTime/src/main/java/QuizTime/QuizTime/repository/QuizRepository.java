package QuizTime.QuizTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizTime.QuizTime.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}

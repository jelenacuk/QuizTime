package QuizTime.QuizTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizTime.QuizTime.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}

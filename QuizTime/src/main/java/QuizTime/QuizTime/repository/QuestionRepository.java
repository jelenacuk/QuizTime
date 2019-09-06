package QuizTime.QuizTime.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import QuizTime.QuizTime.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}

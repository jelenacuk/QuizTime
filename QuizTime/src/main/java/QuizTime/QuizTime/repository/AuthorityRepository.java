package QuizTime.QuizTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import QuizTime.QuizTime.model.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(String name);
}

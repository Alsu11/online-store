package simbirsoft.question.questionserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simbirsoft.question.questionserver.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}

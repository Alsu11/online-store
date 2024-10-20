package simbirsoft.question.questionserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simbirsoft.question.questionserver.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
